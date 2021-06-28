package PSO;
import java.util.Random;
import PSO.Particula.FunctionType;

public class Enjambre {

    private int particulasNum, epocas;
    private double inercia, valorCognitivo, valorSocial;
    private Vector mejorPosicion;
    private double mejorEval;
    private FunctionType funcion; //La función a buscar
    public static final double DEFAULT_INERCIA = 0.729844;
    public static final double DEFAULT_COGNITIVO = 1.496180;
    public static final double DEFAULT_SOCIAL = 1.496180;

    /**
        Las partículas son creadas por un valor rándom dentro de un rango
     */
    private int rangoMin, rangoMax;
    private static final int DEFAULT_RANGO_MIN = -100;
    private static final int DEFAULT_RANGO_MAX = 101;


    public Enjambre (FunctionType funcion, int Particulas, int epocas) {
        this(funcion, Particulas, epocas, DEFAULT_INERCIA, DEFAULT_COGNITIVO, DEFAULT_SOCIAL);
    }

    /**
     * Constructor para el enjambre
     * @param Particulas     número de partículas a crear
     * @param epocas        número de épocas, iteraciones
     * @param inercia       Resistencia al cambio
     * @param cognitivo     Componente cognitivo
     * @param social        Componente Social
     */
    public Enjambre (FunctionType funcion, int Particulas, int epocas, double inercia, double cognitivo, double social) {
        this.particulasNum = Particulas;
        this.epocas = epocas;
        this.inercia = inercia;
        this.valorCognitivo = cognitivo;
        this.valorSocial = social;
        this.funcion = funcion;
        double infinity = Double.POSITIVE_INFINITY;
        mejorPosicion = new Vector(infinity, infinity, infinity);
        mejorEval = Double.POSITIVE_INFINITY;
        rangoMin = DEFAULT_RANGO_MIN;
        rangoMax = DEFAULT_RANGO_MAX;
    }

    public void run () {
        Particula[] Particulas = initialize();

        double oldEval = mejorEval;
        System.out.println("--------------------------PROCESANDO------------------------");
        System.out.println("Mejor Global (Época " + 0 + "):\t"  + mejorEval);

        for (int i = 0; i < epocas; i++) {

            if (mejorEval < oldEval) {
                System.out.println("Mejor Global (Época " + (i + 1) + "):\t" + mejorEval);
                oldEval = mejorEval;
            }

            for (Particula p : Particulas) {
                p.actualizarMejorPersonal();
                actualizarMejorGlobal(p);
            }

            for (Particula p : Particulas) {
                actualizarVelocidad(p);
                p.actualizarposicion();
            }
        }

        System.out.println("------------------------Resultado---------------------------");
        System.out.println("x = " + mejorPosicion.getX());
        if (funcion != FunctionType.FuncionA) {
            System.out.println("y = " + mejorPosicion.getY());
        }
        System.out.println("Mejor Evaluación Final: " + mejorEval);
        System.out.println("---------------------------COMPLETO-------------------------");

    }

    private Particula[] initialize () {
        Particula[] Particulas = new Particula[particulasNum];
        for (int i = 0; i < particulasNum; i++) {
            Particula Particula = new Particula(funcion, rangoMin, rangoMax);
            Particulas[i] = Particula;
            actualizarMejorGlobal(Particula);
        }
        return Particulas;
    }

    /**
     * Actualizar si el mejor global tiene una mejor solución
     */
    private void actualizarMejorGlobal (Particula Particula) {
        if (Particula.obtenermejorEval() < mejorEval) {
            mejorPosicion = Particula.obtenermejorPosicion();
            mejorEval = Particula.obtenermejorEval();
        }
    }

    private void actualizarVelocidad (Particula Particula) {
        Vector velocidadVieja = Particula.obtenervelocidad();
        Vector pBest = Particula.obtenermejorPosicion();
        Vector gBest = mejorPosicion.clone();
        Vector pos = Particula.getPosition();

        Random random = new Random();
        double r1 = random.nextDouble();
        double r2 = random.nextDouble();

        // Primer producto
        Vector velocidadNueva = velocidadVieja.clone();
        velocidadNueva.mul(inercia);

        // Segundo producto de la fórmula
        pBest.sub(pos);
        pBest.mul(valorCognitivo);
        pBest.mul(r1);
        velocidadNueva.add(pBest);

        // Tercer producto
        gBest.sub(pos);
        gBest.mul(valorSocial);
        gBest.mul(r2);
        velocidadNueva.add(gBest);

        Particula.setVelocity(velocidadNueva);
    }

}