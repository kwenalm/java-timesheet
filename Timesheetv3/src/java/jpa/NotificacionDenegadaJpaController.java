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
import jpa.exceptions.PreexistingEntityException;
import modelo.NotificacionDenegada;
import modelo.Notificaciones;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class NotificacionDenegadaJpaController {

    public NotificacionDenegadaJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotificacionDenegada notificacionDenegada) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Notificaciones notificacionesOrphanCheck = notificacionDenegada.getNotificaciones();
        if (notificacionesOrphanCheck != null) {
            NotificacionDenegada oldNotificacionDenegadaOfNotificaciones = notificacionesOrphanCheck.getNotificacionDenegada();
            if (oldNotificacionDenegadaOfNotificaciones != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Notificaciones " + notificacionesOrphanCheck + " already has an item of type NotificacionDenegada whose notificaciones column cannot be null. Please make another selection for the notificaciones field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificaciones notificaciones = notificacionDenegada.getNotificaciones();
            if (notificaciones != null) {
                notificaciones = em.getReference(notificaciones.getClass(), notificaciones.getIdNotificaciones());
                notificacionDenegada.setNotificaciones(notificaciones);
            }
            em.persist(notificacionDenegada);
            if (notificaciones != null) {
                notificaciones.setNotificacionDenegada(notificacionDenegada);
                notificaciones = em.merge(notificaciones);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotificacionDenegada(notificacionDenegada.getIdNotificaciones()) != null) {
                throw new PreexistingEntityException("NotificacionDenegada " + notificacionDenegada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotificacionDenegada notificacionDenegada) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotificacionDenegada persistentNotificacionDenegada = em.find(NotificacionDenegada.class, notificacionDenegada.getIdNotificaciones());
            Notificaciones notificacionesOld = persistentNotificacionDenegada.getNotificaciones();
            Notificaciones notificacionesNew = notificacionDenegada.getNotificaciones();
            List<String> illegalOrphanMessages = null;
            if (notificacionesNew != null && !notificacionesNew.equals(notificacionesOld)) {
                NotificacionDenegada oldNotificacionDenegadaOfNotificaciones = notificacionesNew.getNotificacionDenegada();
                if (oldNotificacionDenegadaOfNotificaciones != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Notificaciones " + notificacionesNew + " already has an item of type NotificacionDenegada whose notificaciones column cannot be null. Please make another selection for the notificaciones field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (notificacionesNew != null) {
                notificacionesNew = em.getReference(notificacionesNew.getClass(), notificacionesNew.getIdNotificaciones());
                notificacionDenegada.setNotificaciones(notificacionesNew);
            }
            notificacionDenegada = em.merge(notificacionDenegada);
            if (notificacionesOld != null && !notificacionesOld.equals(notificacionesNew)) {
                notificacionesOld.setNotificacionDenegada(null);
                notificacionesOld = em.merge(notificacionesOld);
            }
            if (notificacionesNew != null && !notificacionesNew.equals(notificacionesOld)) {
                notificacionesNew.setNotificacionDenegada(notificacionDenegada);
                notificacionesNew = em.merge(notificacionesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notificacionDenegada.getIdNotificaciones();
                if (findNotificacionDenegada(id) == null) {
                    throw new NonexistentEntityException("The notificacionDenegada with id " + id + " no longer exists.");
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
            NotificacionDenegada notificacionDenegada;
            try {
                notificacionDenegada = em.getReference(NotificacionDenegada.class, id);
                notificacionDenegada.getIdNotificaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacionDenegada with id " + id + " no longer exists.", enfe);
            }
            Notificaciones notificaciones = notificacionDenegada.getNotificaciones();
            if (notificaciones != null) {
                notificaciones.setNotificacionDenegada(null);
                notificaciones = em.merge(notificaciones);
            }
            em.remove(notificacionDenegada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotificacionDenegada> findNotificacionDenegadaEntities() {
        return findNotificacionDenegadaEntities(true, -1, -1);
    }

    public List<NotificacionDenegada> findNotificacionDenegadaEntities(int maxResults, int firstResult) {
        return findNotificacionDenegadaEntities(false, maxResults, firstResult);
    }

    private List<NotificacionDenegada> findNotificacionDenegadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NotificacionDenegada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NotificacionDenegada findNotificacionDenegada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotificacionDenegada.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionDenegadaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NotificacionDenegada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
