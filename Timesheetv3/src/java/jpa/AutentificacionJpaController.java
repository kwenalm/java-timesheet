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
import modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class AutentificacionJpaController {

    public AutentificacionJpaController() {
        emf = Persistence.createEntityManagerFactory("Timesheetv3PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autentificacion autentificacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Usuario usuarioOrphanCheck = autentificacion.getUsuario();
        if (usuarioOrphanCheck != null) {
            Autentificacion oldAutentificacionOfUsuario = usuarioOrphanCheck.getAutentificacion();
            if (oldAutentificacionOfUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuario " + usuarioOrphanCheck + " already has an item of type Autentificacion whose usuario column cannot be null. Please make another selection for the usuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = autentificacion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getNif());
                autentificacion.setUsuario(usuario);
            }
            em.persist(autentificacion);
            if (usuario != null) {
                usuario.setAutentificacion(autentificacion);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutentificacion(autentificacion.getNif()) != null) {
                throw new PreexistingEntityException("Autentificacion " + autentificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autentificacion autentificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autentificacion persistentAutentificacion = em.find(Autentificacion.class, autentificacion.getNif());
            Usuario usuarioOld = persistentAutentificacion.getUsuario();
            Usuario usuarioNew = autentificacion.getUsuario();
            List<String> illegalOrphanMessages = null;
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Autentificacion oldAutentificacionOfUsuario = usuarioNew.getAutentificacion();
                if (oldAutentificacionOfUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuario " + usuarioNew + " already has an item of type Autentificacion whose usuario column cannot be null. Please make another selection for the usuario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getNif());
                autentificacion.setUsuario(usuarioNew);
            }
            autentificacion = em.merge(autentificacion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setAutentificacion(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.setAutentificacion(autentificacion);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = autentificacion.getNif();
                if (findAutentificacion(id) == null) {
                    throw new NonexistentEntityException("The autentificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autentificacion autentificacion;
            try {
                autentificacion = em.getReference(Autentificacion.class, id);
                autentificacion.getNif();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autentificacion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = autentificacion.getUsuario();
            if (usuario != null) {
                usuario.setAutentificacion(null);
                usuario = em.merge(usuario);
            }
            em.remove(autentificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autentificacion> findAutentificacionEntities() {
        return findAutentificacionEntities(true, -1, -1);
    }

    public List<Autentificacion> findAutentificacionEntities(int maxResults, int firstResult) {
        return findAutentificacionEntities(false, maxResults, firstResult);
    }

    private List<Autentificacion> findAutentificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Autentificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Autentificacion findAutentificacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autentificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutentificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Autentificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
