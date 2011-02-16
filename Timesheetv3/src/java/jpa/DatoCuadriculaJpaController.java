/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import modelo.Cuadricula;
import modelo.DatoCuadricula;

/**
 *
 * @author Francisco
 */
public class DatoCuadriculaJpaController {

    public DatoCuadriculaJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatoCuadricula datoCuadricula) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadricula idCuadricula = datoCuadricula.getIdCuadricula();
            if (idCuadricula != null) {
                idCuadricula = em.getReference(idCuadricula.getClass(), idCuadricula.getIdCuadricula());
                datoCuadricula.setIdCuadricula(idCuadricula);
            }
            em.persist(datoCuadricula);
            if (idCuadricula != null) {
                idCuadricula.getDatoCuadriculaCollection().add(datoCuadricula);
                idCuadricula = em.merge(idCuadricula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatoCuadricula(datoCuadricula.getIdDatoCuadricula()) != null) {
                throw new PreexistingEntityException("DatoCuadricula " + datoCuadricula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatoCuadricula datoCuadricula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatoCuadricula persistentDatoCuadricula = em.find(DatoCuadricula.class, datoCuadricula.getIdDatoCuadricula());
            Cuadricula idCuadriculaOld = persistentDatoCuadricula.getIdCuadricula();
            Cuadricula idCuadriculaNew = datoCuadricula.getIdCuadricula();
            if (idCuadriculaNew != null) {
                idCuadriculaNew = em.getReference(idCuadriculaNew.getClass(), idCuadriculaNew.getIdCuadricula());
                datoCuadricula.setIdCuadricula(idCuadriculaNew);
            }
            datoCuadricula = em.merge(datoCuadricula);
            if (idCuadriculaOld != null && !idCuadriculaOld.equals(idCuadriculaNew)) {
                idCuadriculaOld.getDatoCuadriculaCollection().remove(datoCuadricula);
                idCuadriculaOld = em.merge(idCuadriculaOld);
            }
            if (idCuadriculaNew != null && !idCuadriculaNew.equals(idCuadriculaOld)) {
                idCuadriculaNew.getDatoCuadriculaCollection().add(datoCuadricula);
                idCuadriculaNew = em.merge(idCuadriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datoCuadricula.getIdDatoCuadricula();
                if (findDatoCuadricula(id) == null) {
                    throw new NonexistentEntityException("The datoCuadricula with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatoCuadricula datoCuadricula;
            try {
                datoCuadricula = em.getReference(DatoCuadricula.class, id);
                datoCuadricula.getIdDatoCuadricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datoCuadricula with id " + id + " no longer exists.", enfe);
            }
            Cuadricula idCuadricula = datoCuadricula.getIdCuadricula();
            if (idCuadricula != null) {
                idCuadricula.getDatoCuadriculaCollection().remove(datoCuadricula);
                idCuadricula = em.merge(idCuadricula);
            }
            em.remove(datoCuadricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatoCuadricula> findDatoCuadriculaEntities() {
        return findDatoCuadriculaEntities(true, -1, -1);
    }

    public List<DatoCuadricula> findDatoCuadriculaEntities(int maxResults, int firstResult) {
        return findDatoCuadriculaEntities(false, maxResults, firstResult);
    }

    private List<DatoCuadricula> findDatoCuadriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DatoCuadricula as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DatoCuadricula findDatoCuadricula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatoCuadricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatoCuadriculaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DatoCuadricula as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
