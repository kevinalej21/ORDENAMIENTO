package org.example;

public class List<T extends Comparable<T>> {
    private Node<T> head; // Puntero al primer nodo de la lista
    private int size; // Número de elementos en la lista
    private boolean insertionSortEnabled; // Bandera para la ordenación automática por inserción

    // Constructor para inicializar una lista vacía
    public List() {
        this.head = new Node<>(); // Crea un nodo ficticio como cabeza
        this.size = 0; // Inicializa el tamaño en 0
        this.insertionSortEnabled = false; // Ordenación automática por inserción deshabilitada por defecto
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return head.getNext() == null; // Si el puntero siguiente de la cabeza es nulo, la lista está vacía
    }

    // Método para obtener el tamaño de la lista
    public int getSize() {
        return size; // Devuelve el tamaño actual de la lista
    }

    // Método para agregar un nuevo nodo con el valor especificado al final de la
    // lista
    public void addToEnd(T value) {
        Node<T> newNode = new Node<>(); // Crea un nuevo nodo
        newNode.setValue(value); // Establece su valor

        Node<T> current = head; // Comienza desde la cabeza
        // Recorre la lista para encontrar el último nodo
        while (current.getNext() != null) {
            current = current.getNext();
        }
        // Establece el puntero siguiente del último nodo al nuevo nodo
        current.setNext(newNode);
        size++; // Aumenta el tamaño de la lista

        if (insertionSortEnabled) {
            insertionSort(); // Ordena la lista automáticamente si está habilitado
        }
    }

    // Método para agregar un nuevo nodo con el valor especificado al inicio de la
    // lista
    public void prepend(T value) {
        Node<T> newNode = new Node<>(); // Crea un nuevo nodo
        newNode.setValue(value); // Establece su valor
        // Establece el puntero siguiente del nuevo nodo al nodo actualmente en primer
        // lugar
        newNode.setNext(head.getNext());
        // Establece el puntero siguiente de la cabeza al nuevo nodo, convirtiéndolo en
        // el nuevo primer nodo
        head.setNext(newNode);
        size++; // Aumenta el tamaño de la lista

        if (insertionSortEnabled) {
            insertionSort(); // Ordena la lista automáticamente si está habilitado
        }
    }

    // Método para imprimir los valores de todos los nodos en la lista
    public void printValues() {
        Node<T> current = head.getNext(); // Comienza desde el primer nodo real
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
        System.out.println(); // Salto de línea al final
    }

    // Método para eliminar el primer elemento de la lista
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede eliminar ningún elemento.");
            return;
        }

        head.setNext(head.getNext().getNext()); // El nuevo primer elemento es el siguiente al actual
        size--; // Reduce el tamaño de la lista
        System.out.println("Se ha eliminado el primer elemento de la lista.");
    }

    // Método para eliminar el último elemento de la lista
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede eliminar ningún elemento.");
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        while (current.getNext() != null) {
            prev = current;
            current = current.getNext();
        }

        if (prev != null) {
            prev.setNext(null); // Elimina el último elemento
            size--; // Reduce el tamaño de la lista
            System.out.println("Se ha eliminado el último elemento de la lista.");
        } else {
            head.setNext(null); // La lista tenía solo un elemento
            size = 0; // La lista ahora está vacía
            System.out.println("La lista ahora está vacía.");
        }
    }

    // Método para ordenar la lista utilizando el algoritmo de intercambio (bubble
    // sort)
    public void sort() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede ordenar.");
            return;
        }

        boolean swapped;
        do {
            Node<T> current = head.getNext();
            Node<T> prev = head;
            Node<T> next = current.getNext();
            swapped = false;

            while (next != null) {
                if (current.getValue().compareTo(next.getValue()) > 0) {
                    current.setNext(next.getNext());
                    next.setNext(current);
                    prev.setNext(next);
                    swapped = true;
                }
                prev = current;
                current = next;
                next = next.getNext();
            }
        } while (swapped);
    }

    // Método para ordenar la lista utilizando el algoritmo de ordenación por
    // inserción
    public void insertionSort() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede ordenar.");
            return;
        }

        Node<T> sorted = null;
        Node<T> current = head.getNext();

        while (current != null) {
            Node<T> next = current.getNext();
            if (sorted == null || sorted.getValue().compareTo(current.getValue()) >= 0) {
                current.setNext(sorted);
                sorted = current;
            } else {
                Node<T> temp = sorted;
                while (temp.getNext() != null && temp.getNext().getValue().compareTo(current.getValue()) < 0) {
                    temp = temp.getNext();
                }
                current.setNext(temp.getNext());
                temp.setNext(current);
            }
            current = next;
        }

        head.setNext(sorted);
    }

    // Método para habilitar o deshabilitar la ordenación automática por inserción
    public void toggleInsertionSort() {
        insertionSortEnabled = !insertionSortEnabled;
    }

    // Método para verificar si la ordenación automática por inserción está
    // habilitada
    public boolean isInsertionSortEnabled() {
        return insertionSortEnabled;
    }

    public void shellSort() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede ordenar.");
            return;
        }

        int n = size;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                Node<T> temp = getNodeAt(i);
                T tempValue = temp.getValue();

                int j;
                for (j = i; j >= gap && getNodeAt(j - gap).getValue().compareTo(tempValue) > 0; j -= gap) {
                    getNodeAt(j).setValue(getNodeAt(j - gap).getValue());
                }
                getNodeAt(j).setValue(tempValue);
            }
            gap /= 2;
        }
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current = head.getNext();
        for (int i = 0; i < index; i++) {
            if (current != null) {
                current = current.getNext();
            }
        }
        return current;
    }

    // Método para ordenar la lista utilizando el algoritmo de ordenación rápida
    // (quicksort)
    public void quickSort() {
        head.setNext(quickSortRec(head.getNext()));
    }

    // Método recursivo para realizar quicksort en los nodos
    private Node<T> quickSortRec(Node<T> start) {
        if (start == null || start.getNext() == null) {
            return start;
        }

        Node<T> pivot = start;
        Node<T> less = null, greater = null;
        Node<T> current = start.getNext();

        while (current != null) {
            Node<T> next = current.getNext();
            if (current.getValue().compareTo(pivot.getValue()) < 0) {
                current.setNext(less);
                less = current;
            } else {
                current.setNext(greater);
                greater = current;
            }
            current = next;
        }

        less = quickSortRec(less);
        greater = quickSortRec(greater);

        return concatenate(less, pivot, greater);
    }

    // Método auxiliar para concatenar listas
    private Node<T> concatenate(Node<T> less, Node<T> pivot, Node<T> greater) {
        if (less == null) {
            pivot.setNext(greater);
            return pivot;
        }

        Node<T> current = less;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(pivot);
        pivot.setNext(greater);

        return less;
    }

    // Método para realizar una búsqueda secuencial
    public boolean sequentialSearch(T value) {
        Node<T> current = head.getNext(); // Comienza desde el primer nodo real
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true; // Elemento encontrado
            }
            current = current.getNext();
        }
        return false; // Elemento no encontrado
    }

    // Método para convertir la lista enlazada en un array
    private T[] toArray() {
        // Crea un array del tamaño de la lista
        T[] array = (T[]) new Comparable[size];
        Node<T> current = head.getNext();
        int index = 0;
        while (current != null) {
            array[index++] = current.getValue();
            current = current.getNext();
        }
        return array;
    }

    // Método para realizar la búsqueda binaria
    public boolean binarySearch(T value) {
        // Asegúrate de que la lista esté ordenada antes de realizar la búsqueda binaria
        quickSort();
        T[] array = toArray();
        return binarySearchRec(array, value, 0, size - 1);
    }

    // Método recursivo para realizar la búsqueda binaria en un array
    private boolean binarySearchRec(T[] array, T value, int low, int high) {
        if (low > high) {
            return false; // Elemento no encontrado
        }

        int mid = (low + high) / 2;

        if (array[mid].compareTo(value) == 0) {
            return true; // Elemento encontrado
        } else if (array[mid].compareTo(value) > 0) {
            return binarySearchRec(array, value, low, mid - 1);
        } else {
            return binarySearchRec(array, value, mid + 1, high);
        }
    }

    // Método para ordenar la lista utilizando el algoritmo de ordenación por
    // selección
    public void selectionSort() {
        if (isEmpty()) {
            System.out.println("La lista está vacía. No se puede ordenar.");
            return;
        }

        Node<T> start = head.getNext(); // Inicio real de la lista

        while (start != null) {
            Node<T> minNode = start;
            Node<T> current = start.getNext();

            while (current != null) {
                if (current.getValue().compareTo(minNode.getValue()) < 0) {
                    minNode = current;
                }
                current = current.getNext();
            }

            // Intercambiar valores del nodo inicial con el nodo mínimo encontrado
            T temp = start.getValue();
            start.setValue(minNode.getValue());
            minNode.setValue(temp);

            // Mover al siguiente nodo
            start = start.getNext();
        }
    }

}
