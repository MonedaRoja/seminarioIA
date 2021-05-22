
    package com.luchi.genetic;  
      
    import java.util.ArrayList;  
    import java.util.Arrays;  
    import java.util.List;  
    import java.util.Random;  
      
    import javax.swing.plaf.synth.SynthSeparatorUI;  
     
    

    public class Genoma {  
      
      
        private List<Double> genomaList=new ArrayList<Double>();  // Almacenar secuencia génica  
        private double fitness;  // El valor de la función fitness  
        public List<Double> getGenomaList() {  
            return genomaList;  
        }  
        public void setGenomaList(List<Double> genomaList) {  
            this.genomaList = genomaList;  
        }  
        public double getFitness() {  
            return fitness;  
        }  
        public void setFitness(double fitness) {  
            this.fitness = fitness;  
        }  
        public Genoma(){  
            super();  
        }  
        public Genoma(List<Double> genomaList, double fitness) {  
            super();  
            this.genomaList = genomaList;  
            this.fitness = fitness;  
        }  
          
    }  



    public class GAlgoritmo {  
          
        // Coloca toda la información genética de la población  
        private List<Genoma> population=new ArrayList<Genoma>();  
        // información del número de población  
        private int poblacionTam;  
        // Número total de cada cromosoma  
        private int chromoLength;  
        // El valor total de aptitud de la población  
        private double totalFitness;  
        // Mejor estado físico de la población  
        private double mejorFitnes;  
        // peor estado físico de la población  
        private double peorFitnes;  
        // aptitud media de la población  
        private double promedioFitness;  
        // La mejor aptitud para el cromosoma correspondiente  
        private Genoma bestGenoma;  
        // probabilidad de mutación genética  
        private double mutationRate;  
        // Probabilidad cruzada de genes  
        private double crossoverRate;  
        // Álgebra genética (generaciones)  
        private int generacion;  
        // Longitud asincrónica máxima  
        private double maxPerturbation;  
        // El rango de aptitud de la población, izquierda izquierda, derecha derecha  
        private double leftPoint;  
        private double rightPoint;  
        // iteraciones máximas genéticas  
        private int iterNum;  
          
        public int getIterNum() {  
            return iterNum;  
        }  
        public void setIterNum(int iterNum) {  
            this.iterNum = iterNum;  
        }  
      
        private Random random=new Random();  
        private MathCalc mathCalc=new MathCalc();  
        public List<Genoma> getPopulation() {  
            return population;  
        }  
        public void setPopulation(List<Genoma> population) {  
            this.population = population;  
        }  
        public int getpoblacionTam() {  
            return poblacionTam;  
        }  
        public void setpoblacionTam(int poblacionTam) {  
            this.poblacionTam = poblacionTam;  
        }  
        public int getChromoLength() {  
            return chromoLength;  
        }  
        public void setChromoLength(int chromoLength) {  
            this.chromoLength = chromoLength;  
        }  
        public double getTotalFitness() {  
            return totalFitness;  
        }  
        public void setTotalFitness(double totalFitness) {  
            this.totalFitness = totalFitness;  
        }  
        public double getmejorFitness() {  
            return mejorFitnes;  
        }  
        public void setmejorFitnes(double mejorFitnes) {  
            this.mejorFitnes = mejorFitnes;  
        }  
        public double getpeorFitnes() {  
            return peorFitnes;  
        }  
        public void setpeorFitnes(double peorFitnes) {  
            this.peorFitnes = peorFitnes;  
        }  
        public double getpromedioFitness() {  
            return promedioFitness;  
        }  
        public void setpromedioFitness(double promedioFitness) {  
            this.promedioFitness = promedioFitness;  
        }  
        public Genoma getBestGenoma() {  
            return bestGenoma;  
        }  
        public void setBestGenoma(Genoma bestGenoma) {  
            this.bestGenoma = bestGenoma;  
        }  
        public double getMutationRate() {  
            return mutationRate;  
        }  
        public void setMutationRate(double mutationRate) {  
            this.mutationRate = mutationRate;  
        }  
        public double getCrossoverRate() {  
            return crossoverRate;  
        }  
        public void setCrossoverRate(double crossoverRate) {  
            this.crossoverRate = crossoverRate;  
        }  
        public int getgeneracion() {  
            return generacion;  
        }  
        public void setgeneracion(int generacion) {  
            this.generacion = generacion;  
        }  
        public double getMaxPerturbation() {  
            return maxPerturbation;  
        }  
        public void setMaxPerturbation(double maxPerturbation) {  
            this.maxPerturbation = maxPerturbation;  
        }  
        public double getLeftPoint() {  
            return leftPoint;  
        }  
        public void setLeftPoint(double leftPoint) {  
            this.leftPoint = leftPoint;  
        }  
        public double getRightPoint() {  
            return rightPoint;  
        }  
        public void setRightPoint(double rightPoint) {  
      
            this.rightPoint = rightPoint;  
        }  
          
        // Valor del parámetro de inicialización del constructor  
        public GeneticAlgorithm(int poblacionTam, int chromoLength, double totalFitness, double mejorFitnes, double peorFitnes,  
                double promedioFitness, double mutationRate, double crossoverRate, int generacion, double maxPerturbation,  
                double leftPoint, double rightPoint,int iterNum) {  
            super();  
            this.poblacionTam = poblacionTam;  
            this.chromoLength = chromoLength;  
            this.totalFitness = totalFitness;  
            this.mejorFitnes = mejorFitnes;  
            this.peorFitnes = peorFitnes;  
            this.promedioFitness = promedioFitness;  
            this.mutationRate = mutationRate;  
            this.crossoverRate = crossoverRate;  
            this.generacion = generacion;  
            this.maxPerturbation = maxPerturbation;  
            this.leftPoint = leftPoint;  
            this.rightPoint = rightPoint;  
            this.iterNum=iterNum;  
        }  
          
        // Inicializa la información de la población  
        public void init(){  
            // claro  
            population.clear();  
            List list;  
            double sum=0.0;  
            // Dar información inicial a la población.  
            for(int i=0;i<poblacionTam;i++){  
                double gene=random.nextDouble()*(rightPoint-leftPoint)+leftPoint;  
                list=new ArrayList<Double>();  
                list.add(gene);  
                double fitness=mathCalc.calcFunction(list);  
                sum+=fitness;  
                Genoma Genoma=new Genoma(list, fitness);  
                population.add(genoma);  
            }  
            setGenInfo();  
            printGenInfo();  
        }  
          
        // Algoritmo de ruleta  
        public Genoma getChromoRoulette(){  
              
              
            double slice=random.nextDouble()*totalFitness;  
            Genoma elegirGenoma=null;  
            double sum=0.0;  
            for(int i=0;i<population.size();i++){  
                elegirGenoma=population.get(i);  
                sum+=elegirGenoma.getFitness();  
                if(sum>=slice && elegirGenoma.getFitness()>this.promedioFitness){  
                    break;  
                }  
            }  
            return elegirGenoma;  
              
        }  
          
        // mutación genética  
        public void Mutate(List<Double> genomaLista){  
              
            for(int i=0;i<genomaLista.size();i++){  
                  
                // si va a mutar de acuerdo con la tasa de mutación especificada  
                double randomRate=random.nextDouble();  
                if(randomRate<this.getMutationRate()){  
                    double raw=(double) genomaLista.get(i);  
                    raw+=(random.nextDouble()-0.5)*this.getMaxPerturbation();  
                    if(raw<leftPoint){  
                        raw=leftPoint;  
                    }else if(raw>rightPoint){  
                        raw=rightPoint;  
                    }  
                    genomaLista.set(i, raw);  
                }  
                  
            }  
        }  
          
        // método core de evolución, cada padre genera dos hijos  
        public void Epoch(final List<Genoma> popList){  
              
            List <Genoma>newPopList=new ArrayList<Genoma>();  
            while(newPopList.size()<this.getpoblacionTam()){  
                  
                Genoma mum=this.getChromoRoulette();  
                Genoma dad=this.getChromoRoulette();  
                // Genera una nueva secuencia de genes  
                List<Double> baby1=mum.getGenomaList();  
                List<Double> baby2=dad.getGenomaList();  
                this.Mutate(baby1);  
                this.Mutate(baby2);  
                Genoma newBabyGenoma1=new Genoma(baby1,mathCalc.calcFunction(baby1));  
                Genoma newBabyGenoma2=new Genoma(baby2,mathCalc.calcFunction(baby1));  
                newPopList.add(newBabyGenoma1);  
                newPopList.add(newBabyGenoma2);  
                  
            }  
            popList.clear();  
            // Genera una nueva generación  
            for(int i=0;i<newPopList.size();i++){  
                popList.add(newPopList.get(i));  
            }  
            newPopList=null;  
              
        }  
        public void setGenInfo(){  
              
            Genoma bestGenoma=population.get(0);  
            Genoma worstGenoma=population.get(0);  
            double totalFit=0.0;  
            for(int i=0;i<population.size();i++){  
                Genoma genom=population.get(i);  
                totalFit+=genom.getFitness();  
                if(genom.getFitness()>bestGenoma.getFitness()){  
                    bestGenoma=genom;  
                }  
                if(genom.getFitness()<worstGenoma.getFitness()){  
                    worstGenoma=genom;  
                }  
                  
                  
            }  
              
            double averageFit=totalFit/population.size();  
            this.setTotalFitness(totalFit);  
            this.setmejorFitnes(bestGenoma.getFitness());  
            this.setpeorFitnes(worstGenoma.getFitness());  
            this.setpromedioFitness(averageFit);  
            this.setgeneracion(this.getgeneracion()+1);  
              
        }  
        public void printGenInfo(){  
            System.out.print("la generación es:\t");  
            System.out.println(this.generacion);  
            System.out.print("el mejor fitness es:\t");  
            System.out.println(this.getmejorFitnes());  
            System.out.print("el peor fitness es:\t");  
            System.out.println(this.getpeorFitnes());  
            System.out.print("el fitness promedio es:\t");  
            System.out.println(this.getpromedioFitness());  
            System.out.print("el fitness total es:\t");  
            System.out.println(this.getTotalFitness());  
        }  

        public void ProcesoGAL(){  
              
            int iterNum=this.iterNum;  
            this.init();  
            for (int gen=0;gen<this.iterNum;gen++){  
                this.Epoch(this.population);  
                setGenInfo();  
                printGenInfo();  
            }  
      
              
        }  
          
          
      
    }  


    public class GAInicial {  
      
        public static void main(String[]args){  
              
            GeneticAlgorithm genAL=new GAlgoritmo(50, 1, 0.0,  
                    0.0, 99999999, 0.0, 0.8,  
                    0.8, 0, 0.004, 0, 4, 100);  
          
            genAL.ProcesoGAL();  
        }  
    }  