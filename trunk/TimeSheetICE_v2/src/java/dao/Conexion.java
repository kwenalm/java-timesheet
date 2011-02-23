package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Conexion {

    private Session sesion;
    private Transaction tx;
    protected void iniciaOperacion()
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.getTransaction();
        tx.begin();
    }

    protected void terminaOperacion()
    {
        tx.commit();
        sesion.close();
    }

    public Session getSesion() {
        return sesion;
    }

    public static <T> T getEntidad(java.io.Serializable id, Class<T> claseEntidad) throws HibernateException
    {
        Conexion dummy = new Conexion();

        T objetoRecuperado = null;

        try
        {
            dummy.iniciaOperacion();
            objetoRecuperado = (T) dummy.getSesion().get(claseEntidad, id);
        }
        catch (Exception he)
        {
            //dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return objetoRecuperado;
    }

    public static void almacenaEntidad(Object entidad) throws HibernateException
    {
        Conexion dummy = new Conexion(){};

        try
        {
            dummy.iniciaOperacion();
            dummy.getSesion().saveOrUpdate(entidad);
            dummy.getSesion().flush();
        }
        catch (HibernateException he)
        {
            //dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
    }
}
