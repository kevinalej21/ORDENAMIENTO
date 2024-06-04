package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Integer> myList = new List<>();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar al final");
            System.out.println("2. Agregar al principio");
            System.out.println("3. Eliminar último elemento");
            System.out.println("4. Eliminar primer elemento");
            System.out.println("5. Imprimir valores");
            System.out.println("6. Obtener tamaño");
            System.out.println("7. Verificar si está vacía");
            System.out.println("8. Ordenar lista por intercambio");
            System.out.println("9. Habilitar/deshabilitar ordenación automática por inserción");
            System.out.println("10. Ordenar lista por Shell");
            System.out.println("11. Salir");
            System.out.print("Ingresa tu elección: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el valor a agregar al final: ");
                    int valor1 = scanner.nextInt();
                    myList.addToEnd(valor1);
                    break;
                case 2:
                    System.out.print("Ingresa el valor a agregar al principio: ");
                    int valor2 = scanner.nextInt();
                    myList.prepend(valor2);
                    break;
                case 3:
                    myList.removeLast(); // Nueva opción: Eliminar al final
                    break;
                case 4:
                    myList.removeFirst(); // Nueva opción: Eliminar al inicio
                    break;
                case 5:
                    myList.printValues();
                    break;
                case 6:
                    System.out.println("Tamaño de la lista: " + myList.getSize());
                    break;
                case 7:
                    System.out.println("¿La lista está vacía? " + myList.isEmpty());
                    break;
                case 8:
                    myList.sort(); // Nueva opción: Ordenar lista por intercambio
                    System.out.println("Lista ordenada por intercambio.");
                    break;
                case 9:
                    myList.toggleInsertionSort(); // Nueva opción: Habilitar/deshabilitar ordenación automática por
                                                  // inserción
                    System.out.println("Ordenación automática por inserción: "
                            + (myList.isInsertionSortEnabled() ? "Habilitada" : "Deshabilitada"));
                    break;
                case 10:
                    myList.shellSort();
                    System.out.println("Lista ordenada por Shell.");
                    break;
                case 11:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor ingresa un número entre 1 y 10.");
                    break;
            }
        } while (opcion != 10);

        scanner.close();
    }
}
