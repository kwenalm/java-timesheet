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
import modelo.NotificacionPendiente;
import modelo.Cuadricula;
import modelo.Notificaciones;
import modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class NotificacionesJpaController {

    public NotificacionesJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notificaciones notificaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotificacionDenegada notificacionDenegada = notificaciones.getNotificacionDenegada();
            if (notificacionDenegada != null) {
                notificacionDenegada = em.getReference(notificacionDenegada.getClass(), notificacionDenegada.getIdNotificaciones());
                notificaciones.setNotificacionDenegada(notificacionDenegada);
            }
            NotificacionPendiente notificacionPendiente = notificaciones.getNotificacionPendiente();
            if (notificacionPendiente != null) {
                notificacionPendiente = em.getReference(notificacionPendiente.getClass(), notificacionPendiente.getIdNotificaciones());
                notificaciones.setNotificacionPendiente(notificacionPendiente);
            }
            Cuadricula idCuadricula = notificaciones.getIdCuadricula();
            if (idCuadricula != null) {
                idCuadricula = em.getReference(idCuadricula.getClass(), idCuadricula.getIdCuadricula());
                notificaciones.setIdCuadricula(idCuadricula);
            }
            Usuario remitente = notificaciones.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getNif());
                notificaciones.setRemitente(remitente);
            }
            Usuario destinatario = notificaciones.getDestinatario();
            if (destinatario != null) {
                destinatario = em.getReference(destinatario.getClass(), destinatario.getNif());
                notificaciones.setDestinatario(destinatario);
            }
            em.persist(notificaciones);
            if (notificacionDenegada != null) {
                Notificaciones oldNotificacionesOfNotificacionDenegada = notificacionDenegada.getNotificaciones();
                if (oldNotificacionesOfNotificacionDenegada != null) {
                    oldNotificacionesOfNotificacionDenegada.setNotificacionDenegada(null);
                    oldNotificacionesOfNotificacionDenegada = em.merge(oldNotificacionesOfNotificacionDenegada);
                }
                notificacionDenegada.setNotificaciones(notificaciones);
                notificacionDenegada = em.merge(notificacionDenegada);
            }
            if (notificacionPendiente != null) {
                Notificaciones oldNotificacionesOfNotificacionPendiente = notificacionPendiente.getNotificaciones();
                if (oldNotificacionesOfNotificacionPendiente != null) {
                    oldNotificacionesOfNotificacionPendiente.setNotificacionPendiente(null);
                    oldNotificacionesOfNotificacionPendiente = em.merge(oldNotificacionesOfNotificacionPendiente);
                }
                notificacionPendiente.setNotificaciones(notificaciones);
                notificacionPendiente = em.merge(notificacionPendiente);
            }
            if (idCuadricula != null) {
                idCuadricula.getNotificacionesCollection().add(notificaciones);
                idCuadricula = em.merge(idCuadricula);
            }
            if (remitente != null) {
                remitente.getNotificacionesCollection().add(notificaciones);
                remitente = em.merge(remitente);
            }
            if (destinatario != null) {
                destinatario.getNotificacionesCollection().add(notificaciones);
                destinatario = em.merge(destinatario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotificaciones(notificaciones.getIdNotificaciones()) != null) {
                throw new PreexistingEntityException("Notificaciones " + notificaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notificaciones notificaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notificaciones persistentNotificaciones = em.find(Notificaciones.class, notificaciones.getIdNotificaciones());
            NotificacionDenegada notificacionDenegadaOld = persistentNotificaciones.getNotificacionDenegada();
            NotificacionDenegada notificacionDenegadaNew = notificaciones.getNotificacionDenegada();
            NotificacionPendiente notificacionPendienteOld = persistentNotificaciones.getNotificacionPendiente();
            NotificacionPendiente notificacionPendienteNew = notificaciones.getNotificacionPendiente();
            Cuadricula idCuadriculaOld = persistentNotificaciones.getIdCuadricula();
            Cuadricula idCuadriculaNew = notificaciones.getIdCuadricula();
            Usuario remitenteOld = persistentNotificaciones.getRemitente();
            Usuario remitenteNew = notificaciones.getRemitente();
            Usuario destinatarioOld = persistentNotificaciones.getDestinatario();
            Usuario destinatarioNew = notificaciones.getDestinatario();
            List<String> illegalOrphanMessages = null;
            if (notificacionDenegadaOld != null && !notificacionDenegadaOld.equals(notificacionDenegadaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain NotificacionDenegada " + notificacionDenegadaOld + " since its notificaciones field is not nullable.");
            }
            if (notificacionPendienteOld != null && !notificacionPendienteOld.equals(notificacionPendienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain NotificacionPendiente " + notificacionPendienteOld + " since its notificaciones field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (notificacionDenegadaNew != null) {
                notificacionDenegadaNew = em.getReference(notificacionDenegadaNew.getClass(), notificacionDenegadaNew.getIdNotificaciones());
                notificaciones.setNotificacionDenegada(notificacionDenegadaNew);
            }
            if (notificacionPendienteNew != null) {
                notificacionPendienteNew = em.getReference(notificacionPendienteNew.getClass(), notificacionPendienteNew.getIdNotificaciones());
                notificaciones.setNotificacionPendiente(notificacionPendienteNew);
            }
            if (idCuadriculaNew != null) {
                idCuadriculaNew = em.getReference(idCuadriculaNew.getClass(), idCuadriculaNew.getIdCuadricula());
                notificaciones.setIdCuadricula(idCuadriculaNew);
            }
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getNif());
                notificaciones.setRemitente(remitenteNew);
            }
            if (destinatarioNew != null) {
                destinatarioNew = em.getReference(destinatarioNew.getClass(), destinatarioNew.getNif());
                notificaciones.setDestinatario(destinatarioNew);
            }
            notificaciones = em.merge(notificaciones);
            if (notificacionDenegadaNew != null && !notificacionDenegadaNew.equals(notificacionDenegadaOld)) {
                Notificaciones oldNotificacionesOfNotificacionDenegada = notificacionDenegadaNew.getNotificaciones();
                if (oldNotificacionesOfNotificacionDenegada != null) {
                    oldNotificacionesOfNotificacionDenegada.setNotificacionDenegada(null);
                    oldNotificacionesOfNotificacionDenegada = em.merge(oldNotificacionesOfNotificacionDenegada);
                }
                notificacionDenegadaNew.setNotificaciones(notificaciones);
                notificacionDenegadaNew = em.merge(notificacionDenegadaNew);
            }
            if (notificacionPendienteNew != null && !notificacionPendienteNew.equals(notificacionPendienteOld)) {
                Notificaciones oldNotificacionesOfNotificacionPendiente = notificacionPendienteNew.getNotificaciones();
                if (oldNotificacionesOfNotificacionPendiente != null) {
                    oldNotificacionesOfNotificacionPendiente.setNotificacionPendiente(null);
                    oldNotificacionesOfNotificacionPendiente = em.merge(oldNotificacionesOfNotificacionPendiente);
                }
                notificacionPendienteNew.setNotificaciones(notificaciones);
                notificacionPendienteNew = em.merge(notificacionPendienteNew);
            }
            if (idCuadriculaOld != null && !idCuadriculaOld.equals(idCuadriculaNew)) {
                idCuadriculaOld.getNotificacionesCollection().remove(notificaciones);
                idCuadriculaOld = em.merge(idCuadriculaOld);
            }
            if (idCuadriculaNew != null && !idCuadriculaNew.equals(idCuadriculaOld)) {
                idCuadriculaNew.getNotificacionesCollection().add(notificaciones);
                idCuadriculaNew = em.merge(idCuadriculaNew);
            }
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getNotificacionesCollection().remove(notificaciones);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getNotificacionesCollection().add(notificaciones);
                remitenteNew = em.merge(remitenteNew);
            }
            if (destinatarioOld != null && !destinatarioOld.equals(destinatarioNew)) {
                destinatarioOld.getNotificacionesCollection().remove(notificaciones);
                destinatarioOld = em.merge(destinatarioOld);
            }
            if (destinatarioNew != null && !destinatarioNew.equals(destinatarioOld)) {
                destinatarioNew.getNotificacionesCollection().add(notificaciones);
                destinatarioNew = em.merge(destinatarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notificaciones.getIdNotificaciones();
                if (findNotificaciones(id) == null) {
                    throw new NonexistentEntityException("The notificaciones with id " + id + " no longer exists.");
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
            Notificaciones notificaciones;
            try {
                notificaciones = em.getReference(Notificaciones.class, id);
                notificaciones.getIdNotificaciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notificaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            NotificacionDenegada notificacionDenegadaOrphanCheck = notificaciones.getNotificacionDenegada();
            if (notificacionDenegadaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Notificaciones (" + notificaciones + ") cannot be destroyed since the NotificacionDenegada " + notificacionDenegadaOrphanCheck + " in its notificacionDenegada field has a non-nullable notificaciones field.");
            }
            NotificacionPendiente notificacionPendienteOrphanCheck = notificaciones.getNotificacionPendiente();
            if (notificacionPendienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Notificaciones (" + notificaciones + ") cannot be destroyed since the NotificacionPendiente " + notificacionPendienteOrphanCheck + " in its notificacionPendiente field has a non-nullable notificaciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cuadricula idCuadricula = notificaciones.getIdCuadricula();
            if (idCuadricula != null) {
                idCuadricula.getNotificacionesCollection().remove(notificaciones);
                idCuadricula = em.merge(idCuadricula);
            }
            Usuario remitente = notificaciones.getRemitente();
            if (remitente != null) {
                remitente.getNotificacionesCollection().remove(notificaciones);
                remitente = em.merge(remitente);
            }
            Usuario destinatario = notificaciones.getDestinatario();
            if (destinatario != null) {
                destinatario.getNotificacionesCollection().remove(notificaciones);
                destinatario = em.merge(destinatario);
            }
            em.remove(notificaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notificaciones> findNotificacionesEntities() {
        return findNotificacionesEntities(true, -1, -1);
    }

    public List<Notificaciones> findNotificacionesEntities(int maxResults, int firstResult) {
        return findNotificacionesEntities(false, maxResults, firstResult);
    }

    private List<Notificaciones> findNotificacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Notificaciones as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Notificaciones findNotificaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notificaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotificacionesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Notificaciones as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
