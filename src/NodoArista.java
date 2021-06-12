/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author @princp
 */
public class NodoArista {

    Nodo direccion;
    NodoArista abajo;
    NodoArista arriba;

    public NodoArista(Nodo d) {
        direccion = d;
        abajo = arriba = null;
    }
}
