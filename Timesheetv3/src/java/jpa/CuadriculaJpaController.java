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
import jpa.exceptions.IllegalOrphanException;
import jpa.exceptions.NonexistentEntityException;
import modelo.Cuadricula;
import modelo.Usuario;
import modelo.Notificaciones;
import java.util.ArrayList;
import java.util.Collection;
import modelo.DatoCuadricula;

/**
 *
 * @author Francisco
 */
public class CuadriculaJpaController {

    public CuadriculaJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuadricula cuadricula) {
        if (cuadricula.getNotificacionesCollection() == null) {
            cuadricula.setNotificacionesCollection(new ArrayList<Notificaciones>());
        }
        if (cuadricula.getDatoCuadriculaCollection() == null) {
            cuadricula.setDatoCuadriculaCollection(new ArrayList<DatoCuadricula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario nif = cuadricula.getNif();
            if (nif != null) {
                nif = em.getReference(nif.getClass(), nif.getNif());
                cuadricula.setNif(nif);
            }
            Collection<Notificaciones> attachedNotificacionesCollection = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollectionNotificacionesToAttach : cuadricula.getNotificacionesCollection()) {
                notificacionesCollectionNotificacionesToAttach = em.getReference(notificacionesCollectionNotificacionesToAttach.getClass(), notificacionesCollectionNotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollection.add(notificacionesCollectionNotificacionesToAttach);
            }
            cuadricula.setNotificacionesCollection(attachedNotificacionesCollection);
            Collection<DatoCuadricula> attachedDatoCuadriculaCollection = new ArrayList<DatoCuadricula>();
            for (DatoCuadricula datoCuadriculaCollectionDatoCuadriculaToAttach : cuadricula.getDatoCuadriculaCollection()) {
                datoCuadriculaCollectionDatoCuadriculaToAttach = em.getReference(datoCuadriculaCollectionDatoCuadriculaToAttach.getClass(), datoCuadriculaCollectionDatoCuadriculaToAttach.getIdDatoCuadricula());
                attachedDatoCuadriculaCollection.add(datoCuadriculaCollectionDatoCuadriculaToAttach);
            }
            cuadricula.setDatoCuadriculaCollection(attachedDatoCuadriculaCollection);
            em.persist(cuadricula);
            if (nif != null) {
                nif.getCuadriculaCollection().add(cuadricula);
                nif = em.merge(nif);
            }
            for (Notificaciones notificacionesCollectionNotificaciones : cuadricula.getNotificacionesCollection()) {
                Cuadricula oldIdCuadriculaOfNotificacionesCollectionNotificaciones = notificacionesCollectionNotificaciones.getIdCuadricula();
                notificacionesCollectionNotificaciones.setIdCuadricula(cuadricula);
                notificacionesCollectionNotificaciones = em.merge(notificacionesCollectionNotificaciones);
                if (oldIdCuadriculaOfNotificacionesCollectionNotificaciones != null) {
                    oldIdCuadriculaOfNotificacionesCollectionNotificaciones.getNotificacionesCollection().remove(notificacionesCollectionNotificaciones);
                    oldIdCuadriculaOfNotificacionesCollectionNotificaciones = em.merge(oldIdCuadriculaOfNotificacionesCollectionNotificaciones);
                }
            }
            for (DatoCuadricula datoCuadriculaCollectionDatoCuadricula : cuadricula.getDatoCuadriculaCollection()) {
                Cuadricula oldIdCuadriculaOfDatoCuadriculaCollectionDatoCuadricula = datoCuadriculaCollectionDatoCuadricula.getIdCuadricula();
                datoCuadriculaCollectionDatoCuadricula.setIdCuadricula(cuadricula);
                datoCuadriculaCollectionDatoCuadricula = em.merge(datoCuadriculaCollectionDatoCuadricula);
                if (oldIdCuadriculaOfDatoCuadriculaCollectionDatoCuadricula != null) {
                    oldIdCuadriculaOfDatoCuadriculaCollectionDatoCuadricula.getDatoCuadriculaCollection().remove(datoCuadriculaCollectionDatoCuadricula);
                    oldIdCuadriculaOfDatoCuadriculaCollectionDatoCuadricula = em.merge(oldIdCuadriculaOfDatoCuadriculaCollectionDatoCuadricula);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuadricula cuadricula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadricula persistentCuadricula = em.find(Cuadricula.class, cuadricula.getIdCuadricula());
            Usuario nifOld = persistentCuadricula.getNif();
            Usuario nifNew = cuadricula.getNif();
            Collection<Notificaciones> notificacionesCollectionOld = persistentCuadricula.getNotificacionesCollection();
            Collection<Notificaciones> notificacionesCollectionNew = cuadricula.getNotificacionesCollection();
            Collection<DatoCuadricula> datoCuadriculaCollectionOld = persistentCuadricula.getDatoCuadriculaCollection();
            Collection<DatoCuadricula> datoCuadriculaCollectionNew = cuadricula.getDatoCuadriculaCollection();
            List<String> illegalOrphanMessages = null;
            for (Notificaciones notificacionesCollectionOldNotificaciones : notificacionesCollectionOld) {
                if (!notificacionesCollectionNew.contains(notificacionesCollectionOldNotificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Notificaciones " + notificacionesCollectionOldNotificaciones + " since its idCuadricula field is not nullable.");
                }
            }
            for (DatoCuadricula datoCuadriculaCollectionOldDatoCuadricula : datoCuadriculaCollectionOld) {
                if (!datoCuadriculaCollectionNew.contains(datoCuadriculaCollectionOldDatoCuadricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatoCuadricula " + datoCuadriculaCollectionOldDatoCuadricula + " since its idCuadricula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nifNew != null) {
                nifNew = em.getReference(nifNew.getClass(), nifNew.getNif());
                cuadricula.setNif(nifNew);
            }
            Collection<Notificaciones> attachedNotificacionesCollectionNew = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollectionNewNotificacionesToAttach : notificacionesCollectionNew) {
                notificacionesCollectionNewNotificacionesToAttach = em.getReference(notificacionesCollectionNewNotificacionesToAttach.getClass(), notificacionesCollectionNewNotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollectionNew.add(notificacionesCollectionNewNotificacionesToAttach);
            }
            notificacionesCollectionNew = attachedNotificacionesCollectionNew;
            cuadricula.setNotificacionesCollection(notificacionesCollectionNew);
            Collection<DatoCuadricula> attachedDatoCuadriculaCollectionNew = new ArrayList<DatoCuadricula>();
            for (DatoCuadricula datoCuadriculaCollectionNewDatoCuadriculaToAttach : datoCuadriculaCollectionNew) {
                datoCuadriculaCollectionNewDatoCuadriculaToAttach = em.getReference(datoCuadriculaCollectionNewDatoCuadriculaToAttach.getClass(), datoCuadriculaCollectionNewDatoCuadriculaToAttach.getIdDatoCuadricula());
                attachedDatoCuadriculaCollectionNew.add(datoCuadriculaCollectionNewDatoCuadriculaToAttach);
            }
            datoCuadriculaCollectionNew = attachedDatoCuadriculaCollectionNew;
            cuadricula.setDatoCuadriculaCollection(datoCuadriculaCollectionNew);
            cuadricula = em.merge(cuadricula);
            if (nifOld != null && !nifOld.equals(nifNew)) {
                nifOld.getCuadriculaCollection().remove(cuadricula);
                nifOld = em.merge(nifOld);
            }
            if (nifNew != null && !nifNew.equals(nifOld)) {
                nifNew.getCuadriculaCollection().add(cuadricula);
                nifNew = em.merge(nifNew);
            }
            for (Notificaciones notificacionesCollectionNewNotificaciones : notificacionesCollectionNew) {
                if (!notificacionesCollectionOld.contains(notificacionesCollectionNewNotificaciones)) {
                    Cuadricula oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones = notificacionesCollectionNewNotificaciones.getIdCuadricula();
                    notificacionesCollectionNewNotificaciones.setIdCuadricula(cuadricula);
                    notificacionesCollectionNewNotificaciones = em.merge(notificacionesCollectionNewNotificaciones);
                    if (oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones != null && !oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones.equals(cuadricula)) {
                        oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones.getNotificacionesCollection().remove(notificacionesCollectionNewNotificaciones);
                        oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones = em.merge(oldIdCuadriculaOfNotificacionesCollectionNewNotificaciones);
                    }
                }
            }
            for (DatoCuadricula datoCuadriculaCollectionNewDatoCuadricula : datoCuadriculaCollectionNew) {
                if (!datoCuadriculaCollectionOld.contains(datoCuadriculaCollectionNewDatoCuadricula)) {
                    Cuadricula oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula = datoCuadriculaCollectionNewDatoCuadricula.getIdCuadricula();
                    datoCuadriculaCollectionNewDatoCuadricula.setIdCuadricula(cuadricula);
                    datoCuadriculaCollectionNewDatoCuadricula = em.merge(datoCuadriculaCollectionNewDatoCuadricula);
                    if (oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula != null && !oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula.equals(cuadricula)) {
                        oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula.getDatoCuadriculaCollection().remove(datoCuadriculaCollectionNewDatoCuadricula);
                        oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula = em.merge(oldIdCuadriculaOfDatoCuadriculaCollectionNewDatoCuadricula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuadricula.getIdCuadricula();
                if (findCuadricula(id) == null) {
                    throw new NonexistentEntityException("The cuadricula with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadricula cuadricula;
            try {
                cuadricula = em.getReference(Cuadricula.class, id);
                cuadricula.getIdCuadricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuadricula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Notificaciones> notificacionesCollectionOrphanCheck = cuadricula.getNotificacionesCollection();
            for (Notificaciones notificacionesCollectionOrphanCheckNotificaciones : notificacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuadricula (" + cuadricula + ") cannot be destroyed since the Notificaciones " + notificacionesCollectionOrphanCheckNotificaciones + " in its notificacionesCollection field has a non-nullable idCuadricula field.");
            }
            Collection<DatoCuadricula> datoCuadriculaCollectionOrphanCheck = cuadricula.getDatoCuadriculaCollection();
            for (DatoCuadricula datoCuadriculaCollectionOrphanCheckDatoCuadricula : datoCuadriculaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuadricula (" + cuadricula + ") cannot be destroyed since the DatoCuadricula " + datoCuadriculaCollectionOrphanCheckDatoCuadricula + " in its datoCuadriculaCollection field has a non-nullable idCuadricula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario nif = cuadricula.getNif();
            if (nif != null) {
                nif.getCuadriculaCollection().remove(cuadricula);
                nif = em.merge(nif);
            }
            em.remove(cuadricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuadricula> findCuadriculaEntities() {
        return findCuadriculaEntities(true, -1, -1);
    }

    public List<Cuadricula> findCuadriculaEntities(int maxResults, int firstResult) {
        return findCuadriculaEntities(false, maxResults, firstResult);
    }

    private List<Cuadricula> findCuadriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cuadricula as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuadricula findCuadricula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuadricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuadriculaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cuadricula as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
