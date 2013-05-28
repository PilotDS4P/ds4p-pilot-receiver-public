/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.ds4p.ws;

import gov.va.ehtac.ds4p.jpa.UserAuthProfile;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Duane DeCouteau
 */
@WebService(serviceName = "UserProfile")
public class UserProfile {
    String jndiERR = "DS4PUserProfile:JNDI Resource Error";
    String connectionERR = "DS4PUserProfile:Connection Error";
    String baseERR = "DS4PUserProfileWS";


    //for jpa stuff
    EntityManagerFactory emf = null;

    private EntityManagerFactory getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("DS4PACSServicesPU");
        }
        return emf;
    }
    protected void persist(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(object);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
            //throw new RuntimeException(e);
        }
    }

    protected void update(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.merge(object);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
            //throw new RuntimeException(e);
        }
    }

    protected void delete(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(object);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
            //throw new RuntimeException(e);
        }
    }

    protected void flush() {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            em.setFlushMode(FlushModeType.COMMIT);
            em.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "authUserProfile")
    public UserAuthProfile authUserProfile(@WebParam(name = "username") String username, @WebParam(name = "userpass") String userpass) {
        UserAuthProfile res = null;
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            Query q = em.createNamedQuery("UserAuthProfile.findByUseridAndUserpass");
            q.setParameter("userid", username);
            q.setParameter("userpass", userpass);
            res = (UserAuthProfile)q.getSingleResult();
            t.commit();
        }
        catch (Exception ex) {
            System.err.println(baseERR + ex.getMessage());
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "saveUserProfile")
    public Boolean saveUserProfile(@WebParam(name = "uprofile") UserAuthProfile uprofile) {
        Boolean res = new Boolean(true);
        try {
            persist(uprofile);
        }
        catch (Exception ex) {
            res = new Boolean(false);
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateUserProfile")
    public Boolean updateUserProfile(@WebParam(name = "uprofile") UserAuthProfile uprofile) {
        Boolean res = Boolean.TRUE;
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            Query q = em.createNamedQuery("UserAuthProfile.findByIduserauthprofile");
            q.setParameter("iduserauthprofile", uprofile.getIduserauthprofile().toString());
            UserAuthProfile obj = (UserAuthProfile)q.getSingleResult();
            obj.setUserpass(uprofile.getUserpass());
            obj.setProviderid(uprofile.getProviderid());
            obj.setProfile(uprofile.getProfile());
            update(obj);
            t.commit();
        }
        catch (Exception ex) {
            System.err.println(baseERR + ex.getMessage());
            res = Boolean.FALSE;
        }
        return res;
    }
    
}
