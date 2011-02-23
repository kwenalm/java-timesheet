/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;


/**
 *
 * @author Java I
 */
import modelo.Usuario;
public class pruebaICE {
    public Usuario[] listaUsuarios = new Usuario[]{
        new Usuario("4445", "Pepe", "Gotera", "y Otilio"),
        new Usuario("6664", "Zipi", "y", "Zape"),
        new Usuario("7778", "Mortadelo", "13 Rue", "del Percebe")
    };

    public Usuario[] getListaUsuarios()
    {
        return listaUsuarios;
    }

}

