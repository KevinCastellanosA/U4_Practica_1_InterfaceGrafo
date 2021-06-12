/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author @princp
 */
public class Grafo {
    Nodo nodo;
    public Grafo() {
        nodo = null;
    }
    public boolean InsertarNodo(char valor) {
        if (nodo == null) {
            nodo = new Nodo(valor);
            return true;
        }
        if (BuscarNodo(valor) != null){
            return false;
        }
        Nodo nuevo = new Nodo(valor);
        if (nuevo == null) {
            return false;
        }
        Añadir();
        nodo.sig = nuevo;
        nuevo.ant = nodo;
        return true;
    }
    
    
    public boolean InsertarArista(char puntoa, char puntob) {
        Nodo Origen = BuscarNodo(puntoa);
        Nodo Destino = BuscarNodo(puntob);
        if (Destino == null || Origen == null) return false;
        return Origen.insertarArista(Destino);
    }
    
    public boolean EliminarNodo(char valor) {
        Nodo temp = BuscarNodo(valor);
        if (nodo == null) return false;
        if (temp == null) return false;
        if (temp.arista != null) return false;
        EliminarConexiones(temp);
        if (temp == nodo) {
            if (NodoUnico()) {
                nodo = null;
            } else {
                nodo = temp.sig;
                temp.sig.ant = temp.sig = null;
            }
            return true;
        }
        if (temp.sig == null) {
            temp.ant.sig = temp.ant = null;
            return true;
        }
        temp.ant.sig = temp.sig;
        temp.sig.ant = temp.ant;
        temp.sig = temp.ant = null;
        return true;
    }
    // Metodo axuliar para eliminar las aristas caundo se elimina un nodo
    private void EliminarConexiones(Nodo NE) {
        Principio();
        for (Nodo buscar = nodo; buscar != null; buscar = buscar.sig) {
            buscar.EliminarArista(NE);
        }
    }

    public boolean EliminarArista(char puntoa, char puntob) {
        Nodo Origen = BuscarNodo(puntoa);
        Nodo Destino = BuscarNodo(puntob);
        if (Destino == null || Origen == null)return false;
        return Origen.EliminarArista(Destino);
    }
  
    
    public int TotalNodos() {
        if (nodo == null) {
            return 0;
        }
        int i = 0;
        Principio();
        Nodo temp = nodo;
        while (temp != null) {
            i++;
            temp = temp.sig;
        }
        return i;
    }
    private Nodo BuscarNodo(char valor) {
        if (nodo == null) return null;
        Principio();
        for (Nodo buscar = nodo; buscar != null; buscar = buscar.sig) {
            if (buscar.valor == valor) {
               return buscar;
            }
        }
        return null;
    }
    private void Añadir() {
        while (nodo.sig != null) {
           nodo = nodo.sig;
        }
    }
    private void Principio() {
        while (nodo.ant != null) {
           nodo = nodo.ant;
        }
    }
    
    public boolean NodoUnico() {
       return nodo.sig == null && nodo.ant == null;
    }
    
    //Crea la lista de adyacencia
    public String List_adyacencia(char valor) {
        return BuscarNodo(valor).toString();
    }
    
    public String BuscarCamino(char[] camino) {
        String cad = "";
        for (int i = 0; i < camino.length - 1; i++) {
            if (BuscarNodo(camino[i]).BuscarArista(BuscarNodo(camino[i + 1])) != null) {
                cad = "El camino si existe";
            }else{
                cad = "El camino no existe";
            }
        }
        return cad;
    }
    //Crea la matriz de adyacencia
    public boolean[][] Mat_Adyacencia() {
       int Nodos = TotalNodos();
       int Ren,Col;
        if (nodo == null) {
            return null;
        }
        boolean matriz[][] = new boolean[Nodos][Nodos];
        for (Ren = 0; Ren < Nodos; Ren++) {
            for (Col = 0; Col < Nodos; Col++) {
                matriz[Ren][Col] = false;
            }
        }
        for (Ren = 0; Ren < Nodos; Ren++) {
            Col = 0;
            while (Ren != Col) {
                Col++;
                nodo = nodo.sig;
            }
            NodoArista temp = nodo.arista;
            Principio();
            while (temp != null) {
                Col = 0;
                while (temp.direccion != nodo) {
                    nodo = nodo.sig;
                    Col++;
                }
                matriz[Ren][Col] = true;
                temp = temp.abajo;
                Principio();
            }
        }
        return matriz;
    }
}
