
package com.fs.superherosighting.dao;

import com.fs.superherosighting.dao.SuperHeroVillanDaoDB.SuperHeroVillanMapper;
import com.fs.superherosighting.entities.Organization;
import com.fs.superherosighting.entities.SuperHeroVillan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    SuperHeroVillanDaoDB superHeroVillanDao;
    
    @Autowired
    JdbcTemplate jdbc;
    
 
    @Override
    public Organization getOrganizationById(int organization_id) {
        try {
            final String SELECT_Organization_BY_ID = "SELECT * FROM organization WHERE organization_id = ?";
            Organization organization = jdbc.queryForObject(SELECT_Organization_BY_ID, new OrganizationMapper(), organization_id);
            organization.setSupers(getSuperForOrganization(organization_id));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    //Helper Method that gets heros from organization
    //Join with super_organization based off of id
    //might need to change return
    private List<SuperHeroVillan> getSuperForOrganization(int organization_id) {
        final String SELECT_SUPER_FOR_ORG = "select \n" +
                                        "	sv.*\n" +
                                            "from superhero_supervillan sv\n" +
                                            "join superhero_supervillan_organization sso on sso.super_id = sv.super_id\n" +
                                             "where sso.organization_id = ?;";
       
        List<SuperHeroVillan> superHeros = jdbc.query(SELECT_SUPER_FOR_ORG, new SuperHeroVillanMapper(), organization_id);
        superHeroVillanDao.associatePowersAndSightings(superHeros); //might be prob
        return superHeros;
  
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_OrganizationS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_OrganizationS, new OrganizationMapper());
        associateSupers(organizations);
        return organizations;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        final String INSERT_Organization = "INSERT INTO organization(name, description, address, contact, ishero) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_Organization,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.isIsHero());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganization_id(newId);
        //insertOrganizationHero(organization);
        return organization;
    }
    
    /*
    loops through the list of heros in the Org and 
    adds database entries to super_org for each
    */
    private void insertOrganizationHero(Organization organization) {
        final String INSERT_ORG_HERO = "INSERT INTO "
                + "superhero_supervillan_organization(super_id, organization_id) VALUES(?,?)";
        for(SuperHeroVillan superHeroVillan : organization.getSupers()) {
            jdbc.update(INSERT_ORG_HERO, 
                    superHeroVillan.getSuper_id(),
                    organization.getOrganization_id());
        }
    }
    

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_Organization = "UPDATE Organization SET name = ?, description = ?, "
                + "address = ?, contact = ?, ishero = ?  WHERE organization_id = ?";
        jdbc.update(UPDATE_Organization,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.isIsHero(),
                organization.getOrganization_id());
        
        final String DELETE_SUPER_ORG = "DELETE FROM superhero_supervillan_organization WHERE organization_id = ?";
        jdbc.update(DELETE_SUPER_ORG, organization.getOrganization_id());
        insertOrganizationHero(organization);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int organization_id) {
        final String DELETE_SUPER_Organization = "DELETE FROM superhero_supervillan_organization WHERE organization_id = ?";
        jdbc.update(DELETE_SUPER_Organization, organization_id);
        
        final String DELETE_Organization = "DELETE FROM organization WHERE organization_id = ?";
        jdbc.update(DELETE_Organization, organization_id);
    }

    //Need association method
    private void associateSupers(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setSupers(getSuperForOrganization(organization.getOrganization_id()));
        }
    }
    
    /*
    The system must be able to report all of the organizations a particular 
    superhero/villain belongs to.
    */
    @Override
    public List<Organization> getOrganizationForSuperHeroVillan(SuperHeroVillan superHeroVillan) {
       final String SELECT_ORGANIZATIONS_FOR_HERO = "select\n" +
        "	org.*\n" +
        "from organization org \n" +
        "join superhero_supervillan_organization sso on sso.organization_id = org.organization_id\n" +
        "where sso.super_id = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO, 
                new OrganizationMapper(), superHeroVillan.getSuper_id());
        associateSupers(organizations);
        return organizations;
        
    }
    

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganization_id(rs.getInt("organization_id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setContact(rs.getString("contact"));
            organization.setIsHero(rs.getString("ishero"));


            return organization;
        }
    }
    
}
