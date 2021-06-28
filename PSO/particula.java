package PSO;

import java.util.Random;

class Particula {

    private Vector posicion;        // Posición Actual
    private Vector velocidad;
    private Vector mejorposicion;    
    private double mejorEval;        
    private FunctionType funcion;  

    Particula (FunctionType funcion, int valorMinimo, int valorMaximo) {
        if (valorMinimo >= valorMaximo) {
            throw new IllegalArgumentException("El valor mínimo debe ser menor que el rango máximo.");
        }
        this.funcion = funcion;
        posicion = new Vector();
        velocidad = new Vector();
        setRandomposicion(valorMinimo, valorMaximo);
        mejorposicion = velocidad.clone();
        mejorEval = eval();
    }

    private double eval () {
        if (funcion == FunctionType.funcionA) {
            return Function.funcionA(posicion.getX());
        } else if (funcion == FunctionType.Ackleys) {
            return Function.ackleysFunction(posicion.getX(), posicion.getY());
        } else if (funcion == FunctionType.Booths) {
            return Function.boothsFunction(posicion.getX(), posicion.getY());
        } else {
            return Function.threeHumpCamelFunction(posicion.getX(), posicion.getY());
        }
    }

    private void setRandomposicion (int valorMinimo, int valorMaximo) {
        int x = rand(valorMinimo, valorMaximo);
        int y = rand(valorMinimo, valorMaximo);
        int z = rand(valorMinimo, valorMaximo);
        posicion.set(x, y, z);
    }

    /**
     * Hacer un número rándom.
     * @param valorMinimo   El valor mínimo
     * @param valorMaximo     El valor máximo
     * @return             
     */
    private static int rand (int valorMinimo, int valorMaximo) {
        Random r = new java.util.Random();
        return r.nextInt(valorMaximo - valorMinimo) + valorMinimo;
    }

    void actualizarMejorPersonal () {
        double eval = eval();
        if (eval < mejorEval) {
            mejorposicion = posicion.clone();
            mejorEval = eval;
        }
    }

    Vector obtenerposicion () {
        return posicion.clone();
    }

    Vector obtenervelocidad () {
        return velocidad.clone();
    }

    Vector obtenermejorposicion() {
        return mejorposicion.clone();
    }

    double obtenermejorEval () {
        return mejorEval;
    }


    void actualizarposicion () {
        this.posicion.add(velocidad);
    }

    void setvelocidad (Vector velocidad) {
        this.velocidad = velocidad.clone();
    }

    public enum FunctionType {
        FunctionA,
        Ackleys,
        Booths,
        ThreeHumpCamel
    }

}