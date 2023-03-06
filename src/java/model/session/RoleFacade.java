/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.cequre.Role;

/**
 *
 * @author pupil
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "JPTV21WebShop_IvtsenkovPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }

    public Role findRoleByName(String roleName) {
        return (Role) em.createQuery("SELECT r FROM Role r WHERE r.roleName=:roleName")
                .setParameter("roleName", roleName)
                .getSingleResult();
    }
    
}
