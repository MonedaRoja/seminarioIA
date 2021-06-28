package PSO;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        if (args.length == 1 && args[0].equals("-p")) {
            menu(true);
        } else {
            System.out.print("'-p para cambiar valores de ejecución");
            System.out.println("Inercia:             " + Enjambre.DEFAULT_INERCIA);
            System.out.println("Componente Cognitivo: " + Enjambre.DEFAULT_COGNITIVO);
            System.out.println("Componente Social:    " + Enjambre.DEFAULT_SOCIAL);
            menu(false);
        }
    }

    private static void menu (boolean flag) {
        Enjambre Enjambre;
        Particula.FunctionType funcion;
        int particulas, epocas;
        double inercia, cognitivo, social;

        funcion = obtenerFuncion();
        particulas = obtenerNum("particulas: ");
        epocas = obtenerNum("epocas:    ");

        if (flag) {
            inercia = getUserDouble("Inercia:   ");
            cognitivo = getUserDouble("Valor Cognitivo: ");
            social = getUserDouble("VaLor Social:    ");
            Enjambre = new Enjambre(funcion, particulas, epocas, inercia, cognitivo, social);
        } else {
            Enjambre = new Enjambre(funcion, particulas, epocas);

        }

        Enjambre.run();
    }

    private static Particula.FunctionType obtenerFuncion () {
        Particula.FunctionType function = null;
        do {
            Scanner sc = new Scanner(System.in);
            printMenu();

            if (sc.hasNextInt()) {
                function = obtenerFuncion(sc.nextInt());
            } else {
                System.out.println("captura de datos inválida.");
            }

        } while (function == null);
        return function;
    }

    private static int obtenerNum (String msg) {
        int input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextInt()) {
                input = sc.nextInt();

                if (input <= 0) {
                    System.out.println("El número ingresado no debe ser negativo.");
                } else {
                    break;
                }

            } else {
                System.out.println("Error de captura.");
            }
        }
        return input;
    }

    private static double getUserDouble (String msg) {
        double input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextDouble()) {
                input = sc.nextDouble();

                if (input <= 0) {
                    System.out.println("El número a ingresar debe ser positivo.");
                } else {
                    break;
                }

            } else {
                System.out.println("El número no se capturó correctamente.");
            }
        }
        return input;
    }

    private static void printMenu () {
        System.out.println("----------------------------MENU----------------------------");
        System.out.println("Elige una función:");
        System.out.println("1. (x^4)-2(x^3)");
        System.out.print("Function:  ");
    }

    private static Particula.FunctionType obtenerFuncion (int input) {
        if (input == 1)         return Particula.FunctionType.funcionA;
        System.out.println("Invalid Input.");
        return null;
    }

}