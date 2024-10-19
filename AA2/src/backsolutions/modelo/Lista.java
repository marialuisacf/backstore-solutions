package backsolutions.modelo;

import java.util.ArrayList;
import java.util.List;

public class Lista<T> {

    private List<T> elementos;
    //Lista interna de tipo ArrayList para almacenar elementos genéricos
    public Lista() {
        elementos = new ArrayList<>();
    }


    //Metodo para añadir un elemento a la lista
    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    //metodo para eliminar un elemento de la lista
    public void eliminar(T elemento) {
        elementos.remove(elemento);
    }

    //metodo para obtener un elemento de la lista según su índice
    public T obtener(int index) {
        return elementos.get(index);
    }

    //metodo que devuelve el número de elementos de la lista
    public int size() {
        return elementos.size();
    }

    //metodo devuelve la lista de elementos
    public List<T> obtenerElementos() {
        return elementos;
    }

}
