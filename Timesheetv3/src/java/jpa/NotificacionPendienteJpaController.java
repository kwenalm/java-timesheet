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
import modelo.NotificacionPendiente;
import modelo.Notificaciones;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class NotificacionPendienteJpaController {

    public NotificacionPendienteJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotificacionPendiente notificacionPendiente) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Notificaciones notificacionesOrphanCheck = notificacionPendiente.getNotificaciones();
        if (notificacionesOrphanCheck != null) {
            NotificacionPendiente oldNotificacionPendienteOfNotificaciones = notificacionesOrphanCheck.getNotificacionPendiente();
            if (oldNotificacionPendienteOfNotificaciones != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Notificaciones " + notificacionesOrphanCheck + " already has an item of type NotificacionPendiente whose notificaciones column cannot be null. Please make another selection for the notificaciones field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificaciones notificaciones = notificacionPendiente.getNotificaciones();
            if (notificaciones != null) {
                notificaciones = em.getReference(notificaciones.getClass(), notificaciones.getIdNotificaciones());
                notificacionPendiente.setNotificaciones(notificaciones);
            }
            em.persist(notificacionPendiente);
            if (notificaciones != null) {
                notificaciones.setNotificacionPendiente(notificacionPendiente);
                notificaciones = em.merge(notificaciones);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotificacionPendiente(notificacionPendiente.getIdNotificaciones()) != null) {
                throw new PreexistingEntityException("NotificacionPendiente " + notificacionPendiente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotificacionPendiente notificacionPendiente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotificacionPendiente persistentNotificacionPendiente = em.find(NotificacionPendiente.class, notificacionPendiente.getIdNotificaciones());
            Notificaciones notificacionesOld = persistentNotificacionPendiente.getNotificaciones();
            Notificaciones notificacionesNew = notificacionPendiente.getNotificaciones();
            List<String> illegalOrphanMessages = null;
            if (notificacionesNew != null && !notificacionesNew.equals(notificacionesOld)) {
                NotificacionPendiente oldNotificacionPendienteOfNotificaciones = notificacionesNew.getNotificacionPendiente();
                if (oldNotificacionPendienteOfNotificaciones != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Notificaciones " + notificacionesNew + " already has an item of type NotificacionPendiente whose notificaciones column cannot be null. Please make another selection for the notificaciones field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (notificacionesNew != null) {
                notificacionesNew = em.getReference(notificacionesNew.getClass(), notificacionesNew.getIdNotificaciones());
                notificacionPendiente.setNotificaciones(notificacionesNew);
            }
            notificacionPendiente = em.merge(notificacionPendiente);
            if (notificacionesOld != null && !notificacionesOld.equals(notificacionesNew)) {
                notificacionesOld.setNotificacionPendiente(null);
                notificacionesOld = em.merge(notificacionesOld);
            }
            if (notificacionesNew != null && !notificacionesNew.equals(notificacionesOld)) {
                notificacionesNew.setNotificacionPendiente(notificacionPendiente);
                notificacionesNew = em.merge(notificacionesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notificacionPendiente.getIdNotificaciones();
                if (findNotificacionPendiente(id) == null) {
                    throw new NonexistentEntityException("The notificacionPendiente with id " + id + " no longer exists.");
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
            NotificacionPendiente notificacionPendiente;
            try {
                notificacionPendiente = em.getReference(NotificacionPendiente.class, id);
                notificacionPendiente.getIdNotificaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificacionPendiente with id " + id + " no longer exists.", enfe);
            }
            Notificaciones notificaciones = notificacionPendiente.getNotificaciones();
            if (notificaciones != null) {
                notificaciones.setNotificacionPendiente(null);
                notificaciones = em.merge(notificaciones);
            }
            em.remove(notificacionPendiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotificacionPendiente> findNotificacionPendienteEntities() {
        return findNotificacionPendienteEntities(true, -1, -1);
    }

    public List<NotificacionPendiente> findNotificacionPendienteEntities(int maxResults, int firstResult) {
        return findNotificacionPendienteEntities(false, maxResults, firstResult);
    }

    private List<NotificacionPendiente> findNotificacionPendienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NotificacionPendiente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NotificacionPendiente findNotificacionPendiente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotificacionPendiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionPendienteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NotificacionPendiente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
