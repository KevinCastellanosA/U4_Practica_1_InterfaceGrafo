/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author @princp
 */
public class Nodo {

    char valor;
    Nodo sig;
    Nodo ant;
    NodoArista arista;

    public Nodo(char d) {
        valor = d;
        sig = ant = null;
        arista = null;
    }

    public boolean insertarArista(Nodo direccion) {
        NodoArista nuevo = new NodoArista(direccion);
        if (nuevo == null) {
            return false;
        }

        if (arista == null) {
            arista = new NodoArista(direccion);
            return true;
        }

        if (BuscarArista(direccion) != null) {
            return false;
        }

        Añadir();
        arista.abajo = nuevo;
        nuevo.arriba = arista;
        nuevo.arriba = arista;
        return true;
    }
    
    public boolean EliminarArista(Nodo direccion) {
        if (arista == null) {
            return false;
        }

        NodoArista temp = BuscarArista(direccion);
        if (temp == null) {
            return false;
        }

        if (temp == arista) {
            if (AristaUnica()) {
                arista = null;
            } else {
                arista = arista.abajo;
                temp.abajo.arriba = temp.abajo = null;
            }
            return true;
        }

        if (temp.abajo == null) {
            temp.arriba.abajo = temp.arriba = null;
            return true;
        }

        temp.arriba.abajo = temp.abajo;
        temp.abajo.arriba = temp.arriba;
        temp.abajo = temp.arriba = null;
        return true;
    }

    public NodoArista BuscarArista(Nodo direccion) {
        if (arista == null) {
            return null;
        }
        Principio();
        for (NodoArista buscar = arista; buscar != null; buscar = buscar.abajo) {
            if (buscar.direccion == direccion) {
                return buscar;
            }
        }

        return null;
    }

    private boolean AristaUnica() {
        return arista.abajo == null && arista.arriba == null;
    }

    @Override
    public String toString() {
        String cad = valor + ",";
        NodoArista temp = arista;
        while (temp != null) {
            cad += "," + temp.direccion.valor;
            temp = temp.abajo;
        }
        return cad;
    }
    private void Añadir() {
        while (arista.abajo != null) {
            arista = arista.abajo;
        }
    }

    private void Principio() {
        while (arista.arriba != null) {
            arista = arista.arriba;
        }
    }

}
