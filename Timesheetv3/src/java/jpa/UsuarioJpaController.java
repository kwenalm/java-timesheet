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
import modelo.Autentificacion;
import modelo.Notificaciones;
import java.util.ArrayList;
import java.util.Collection;
import modelo.Cuadricula;
import modelo.Usuario;

/**
 *
 * @author Francisco
 */
public class UsuarioJpaController {

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getNotificacionesCollection() == null) {
            usuario.setNotificacionesCollection(new ArrayList<Notificaciones>());
        }
        if (usuario.getNotificacionesCollection1() == null) {
            usuario.setNotificacionesCollection1(new ArrayList<Notificaciones>());
        }
        if (usuario.getCuadriculaCollection() == null) {
            usuario.setCuadriculaCollection(new ArrayList<Cuadricula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autentificacion autentificacion = usuario.getAutentificacion();
            if (autentificacion != null) {
                autentificacion = em.getReference(autentificacion.getClass(), autentificacion.getNif());
                usuario.setAutentificacion(autentificacion);
            }
            Collection<Notificaciones> attachedNotificacionesCollection = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollectionNotificacionesToAttach : usuario.getNotificacionesCollection()) {
                notificacionesCollectionNotificacionesToAttach = em.getReference(notificacionesCollectionNotificacionesToAttach.getClass(), notificacionesCollectionNotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollection.add(notificacionesCollectionNotificacionesToAttach);
            }
            usuario.setNotificacionesCollection(attachedNotificacionesCollection);
            Collection<Notificaciones> attachedNotificacionesCollection1 = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollection1NotificacionesToAttach : usuario.getNotificacionesCollection1()) {
                notificacionesCollection1NotificacionesToAttach = em.getReference(notificacionesCollection1NotificacionesToAttach.getClass(), notificacionesCollection1NotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollection1.add(notificacionesCollection1NotificacionesToAttach);
            }
            usuario.setNotificacionesCollection1(attachedNotificacionesCollection1);
            Collection<Cuadricula> attachedCuadriculaCollection = new ArrayList<Cuadricula>();
            for (Cuadricula cuadriculaCollectionCuadriculaToAttach : usuario.getCuadriculaCollection()) {
                cuadriculaCollectionCuadriculaToAttach = em.getReference(cuadriculaCollectionCuadriculaToAttach.getClass(), cuadriculaCollectionCuadriculaToAttach.getIdCuadricula());
                attachedCuadriculaCollection.add(cuadriculaCollectionCuadriculaToAttach);
            }
            usuario.setCuadriculaCollection(attachedCuadriculaCollection);
            em.persist(usuario);
            if (autentificacion != null) {
                Usuario oldUsuarioOfAutentificacion = autentificacion.getUsuario();
                if (oldUsuarioOfAutentificacion != null) {
                    oldUsuarioOfAutentificacion.setAutentificacion(null);
                    oldUsuarioOfAutentificacion = em.merge(oldUsuarioOfAutentificacion);
                }
                autentificacion.setUsuario(usuario);
                autentificacion = em.merge(autentificacion);
            }
            for (Notificaciones notificacionesCollectionNotificaciones : usuario.getNotificacionesCollection()) {
                Usuario oldRemitenteOfNotificacionesCollectionNotificaciones = notificacionesCollectionNotificaciones.getRemitente();
                notificacionesCollectionNotificaciones.setRemitente(usuario);
                notificacionesCollectionNotificaciones = em.merge(notificacionesCollectionNotificaciones);
                if (oldRemitenteOfNotificacionesCollectionNotificaciones != null) {
                    oldRemitenteOfNotificacionesCollectionNotificaciones.getNotificacionesCollection().remove(notificacionesCollectionNotificaciones);
                    oldRemitenteOfNotificacionesCollectionNotificaciones = em.merge(oldRemitenteOfNotificacionesCollectionNotificaciones);
                }
            }
            for (Notificaciones notificacionesCollection1Notificaciones : usuario.getNotificacionesCollection1()) {
                Usuario oldDestinatarioOfNotificacionesCollection1Notificaciones = notificacionesCollection1Notificaciones.getDestinatario();
                notificacionesCollection1Notificaciones.setDestinatario(usuario);
                notificacionesCollection1Notificaciones = em.merge(notificacionesCollection1Notificaciones);
                if (oldDestinatarioOfNotificacionesCollection1Notificaciones != null) {
                    oldDestinatarioOfNotificacionesCollection1Notificaciones.getNotificacionesCollection1().remove(notificacionesCollection1Notificaciones);
                    oldDestinatarioOfNotificacionesCollection1Notificaciones = em.merge(oldDestinatarioOfNotificacionesCollection1Notificaciones);
                }
            }
            for (Cuadricula cuadriculaCollectionCuadricula : usuario.getCuadriculaCollection()) {
                Usuario oldNifOfCuadriculaCollectionCuadricula = cuadriculaCollectionCuadricula.getNif();
                cuadriculaCollectionCuadricula.setNif(usuario);
                cuadriculaCollectionCuadricula = em.merge(cuadriculaCollectionCuadricula);
                if (oldNifOfCuadriculaCollectionCuadricula != null) {
                    oldNifOfCuadriculaCollectionCuadricula.getCuadriculaCollection().remove(cuadriculaCollectionCuadricula);
                    oldNifOfCuadriculaCollectionCuadricula = em.merge(oldNifOfCuadriculaCollectionCuadricula);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getNif()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNif());
            Autentificacion autentificacionOld = persistentUsuario.getAutentificacion();
            Autentificacion autentificacionNew = usuario.getAutentificacion();
            Collection<Notificaciones> notificacionesCollectionOld = persistentUsuario.getNotificacionesCollection();
            Collection<Notificaciones> notificacionesCollectionNew = usuario.getNotificacionesCollection();
            Collection<Notificaciones> notificacionesCollection1Old = persistentUsuario.getNotificacionesCollection1();
            Collection<Notificaciones> notificacionesCollection1New = usuario.getNotificacionesCollection1();
            Collection<Cuadricula> cuadriculaCollectionOld = persistentUsuario.getCuadriculaCollection();
            Collection<Cuadricula> cuadriculaCollectionNew = usuario.getCuadriculaCollection();
            List<String> illegalOrphanMessages = null;
            if (autentificacionOld != null && !autentificacionOld.equals(autentificacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Autentificacion " + autentificacionOld + " since its usuario field is not nullable.");
            }
            for (Notificaciones notificacionesCollectionOldNotificaciones : notificacionesCollectionOld) {
                if (!notificacionesCollectionNew.contains(notificacionesCollectionOldNotificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Notificaciones " + notificacionesCollectionOldNotificaciones + " since its remitente field is not nullable.");
                }
            }
            for (Notificaciones notificacionesCollection1OldNotificaciones : notificacionesCollection1Old) {
                if (!notificacionesCollection1New.contains(notificacionesCollection1OldNotificaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Notificaciones " + notificacionesCollection1OldNotificaciones + " since its destinatario field is not nullable.");
                }
            }
            for (Cuadricula cuadriculaCollectionOldCuadricula : cuadriculaCollectionOld) {
                if (!cuadriculaCollectionNew.contains(cuadriculaCollectionOldCuadricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuadricula " + cuadriculaCollectionOldCuadricula + " since its nif field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (autentificacionNew != null) {
                autentificacionNew = em.getReference(autentificacionNew.getClass(), autentificacionNew.getNif());
                usuario.setAutentificacion(autentificacionNew);
            }
            Collection<Notificaciones> attachedNotificacionesCollectionNew = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollectionNewNotificacionesToAttach : notificacionesCollectionNew) {
                notificacionesCollectionNewNotificacionesToAttach = em.getReference(notificacionesCollectionNewNotificacionesToAttach.getClass(), notificacionesCollectionNewNotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollectionNew.add(notificacionesCollectionNewNotificacionesToAttach);
            }
            notificacionesCollectionNew = attachedNotificacionesCollectionNew;
            usuario.setNotificacionesCollection(notificacionesCollectionNew);
            Collection<Notificaciones> attachedNotificacionesCollection1New = new ArrayList<Notificaciones>();
            for (Notificaciones notificacionesCollection1NewNotificacionesToAttach : notificacionesCollection1New) {
                notificacionesCollection1NewNotificacionesToAttach = em.getReference(notificacionesCollection1NewNotificacionesToAttach.getClass(), notificacionesCollection1NewNotificacionesToAttach.getIdNotificaciones());
                attachedNotificacionesCollection1New.add(notificacionesCollection1NewNotificacionesToAttach);
            }
            notificacionesCollection1New = attachedNotificacionesCollection1New;
            usuario.setNotificacionesCollection1(notificacionesCollection1New);
            Collection<Cuadricula> attachedCuadriculaCollectionNew = new ArrayList<Cuadricula>();
            for (Cuadricula cuadriculaCollectionNewCuadriculaToAttach : cuadriculaCollectionNew) {
                cuadriculaCollectionNewCuadriculaToAttach = em.getReference(cuadriculaCollectionNewCuadriculaToAttach.getClass(), cuadriculaCollectionNewCuadriculaToAttach.getIdCuadricula());
                attachedCuadriculaCollectionNew.add(cuadriculaCollectionNewCuadriculaToAttach);
            }
            cuadriculaCollectionNew = attachedCuadriculaCollectionNew;
            usuario.setCuadriculaCollection(cuadriculaCollectionNew);
            usuario = em.merge(usuario);
            if (autentificacionNew != null && !autentificacionNew.equals(autentificacionOld)) {
                Usuario oldUsuarioOfAutentificacion = autentificacionNew.getUsuario();
                if (oldUsuarioOfAutentificacion != null) {
                    oldUsuarioOfAutentificacion.setAutentificacion(null);
                    oldUsuarioOfAutentificacion = em.merge(oldUsuarioOfAutentificacion);
                }
                autentificacionNew.setUsuario(usuario);
                autentificacionNew = em.merge(autentificacionNew);
            }
            for (Notificaciones notificacionesCollectionNewNotificaciones : notificacionesCollectionNew) {
                if (!notificacionesCollectionOld.contains(notificacionesCollectionNewNotificaciones)) {
                    Usuario oldRemitenteOfNotificacionesCollectionNewNotificaciones = notificacionesCollectionNewNotificaciones.getRemitente();
                    notificacionesCollectionNewNotificaciones.setRemitente(usuario);
                    notificacionesCollectionNewNotificaciones = em.merge(notificacionesCollectionNewNotificaciones);
                    if (oldRemitenteOfNotificacionesCollectionNewNotificaciones != null && !oldRemitenteOfNotificacionesCollectionNewNotificaciones.equals(usuario)) {
                        oldRemitenteOfNotificacionesCollectionNewNotificaciones.getNotificacionesCollection().remove(notificacionesCollectionNewNotificaciones);
                        oldRemitenteOfNotificacionesCollectionNewNotificaciones = em.merge(oldRemitenteOfNotificacionesCollectionNewNotificaciones);
                    }
                }
            }
            for (Notificaciones notificacionesCollection1NewNotificaciones : notificacionesCollection1New) {
                if (!notificacionesCollection1Old.contains(notificacionesCollection1NewNotificaciones)) {
                    Usuario oldDestinatarioOfNotificacionesCollection1NewNotificaciones = notificacionesCollection1NewNotificaciones.getDestinatario();
                    notificacionesCollection1NewNotificaciones.setDestinatario(usuario);
                    notificacionesCollection1NewNotificaciones = em.merge(notificacionesCollection1NewNotificaciones);
                    if (oldDestinatarioOfNotificacionesCollection1NewNotificaciones != null && !oldDestinatarioOfNotificacionesCollection1NewNotificaciones.equals(usuario)) {
                        oldDestinatarioOfNotificacionesCollection1NewNotificaciones.getNotificacionesCollection1().remove(notificacionesCollection1NewNotificaciones);
                        oldDestinatarioOfNotificacionesCollection1NewNotificaciones = em.merge(oldDestinatarioOfNotificacionesCollection1NewNotificaciones);
                    }
                }
            }
            for (Cuadricula cuadriculaCollectionNewCuadricula : cuadriculaCollectionNew) {
                if (!cuadriculaCollectionOld.contains(cuadriculaCollectionNewCuadricula)) {
                    Usuario oldNifOfCuadriculaCollectionNewCuadricula = cuadriculaCollectionNewCuadricula.getNif();
                    cuadriculaCollectionNewCuadricula.setNif(usuario);
                    cuadriculaCollectionNewCuadricula = em.merge(cuadriculaCollectionNewCuadricula);
                    if (oldNifOfCuadriculaCollectionNewCuadricula != null && !oldNifOfCuadriculaCollectionNewCuadricula.equals(usuario)) {
                        oldNifOfCuadriculaCollectionNewCuadricula.getCuadriculaCollection().remove(cuadriculaCollectionNewCuadricula);
                        oldNifOfCuadriculaCollectionNewCuadricula = em.merge(oldNifOfCuadriculaCollectionNewCuadricula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getNif();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Autentificacion autentificacionOrphanCheck = usuario.getAutentificacion();
            if (autentificacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Autentificacion " + autentificacionOrphanCheck + " in its autentificacion field has a non-nullable usuario field.");
            }
            Collection<Notificaciones> notificacionesCollectionOrphanCheck = usuario.getNotificacionesCollection();
            for (Notificaciones notificacionesCollectionOrphanCheckNotificaciones : notificacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Notificaciones " + notificacionesCollectionOrphanCheckNotificaciones + " in its notificacionesCollection field has a non-nullable remitente field.");
            }
            Collection<Notificaciones> notificacionesCollection1OrphanCheck = usuario.getNotificacionesCollection1();
            for (Notificaciones notificacionesCollection1OrphanCheckNotificaciones : notificacionesCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Notificaciones " + notificacionesCollection1OrphanCheckNotificaciones + " in its notificacionesCollection1 field has a non-nullable destinatario field.");
            }
            Collection<Cuadricula> cuadriculaCollectionOrphanCheck = usuario.getCuadriculaCollection();
            for (Cuadricula cuadriculaCollectionOrphanCheckCuadricula : cuadriculaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cuadricula " + cuadriculaCollectionOrphanCheckCuadricula + " in its cuadriculaCollection field has a non-nullable nif field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Usuario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Usuario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
