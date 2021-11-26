import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoMaxOne {
    private static int populationSize = 6, Generacion=0;
    private static ArrayList<Individuo> population;
    private static ArrayList<Individuo> children;

    private static boolean flag_1023 = false;

    public static final String reset_ansii = "\u001B[0m";
    public static final String green_ansii = "\u001B[32m";

    public static class Individuo
    {
        private long Genoma;
        private float Fitness;
        private float probability;
        private float acumulatedProbability;

        public String DecimalToBinary(long num)
        {
            if(num<0) //absoluto
            {
                num = num * -1;
            }
            long n = num;
            String bin = "";

            if(n == 0)
            {
                return "0000000000";
            }

            while(n>0)
            {
                if(n%2 == 0)
                    bin = "0" + bin;
                else
                    bin = "1" + bin;
                n = n/2;
            }

            while (bin.length() < 10)
            {
                bin = "0" + bin;
            }

            return bin;
        }

        public long binaryToDecimal(String binario)
        {
            long decimal = 0;
            int posicion = 0;
            for (int x = binario.length() - 1; x >= 0; x--) {
                short digito = 1;
                if (binario.charAt(x) == '0') {
                    digito = 0;
                }
                double multiplicador = Math.pow(2, posicion);
                decimal += digito * multiplicador;
                posicion++;
            }
            return decimal;
        }
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    public static void genertePopulation()
    {
        population = new ArrayList<>();
        children = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) 
        {
            Individuo ind = new Individuo(); 
            ind.Genoma = getRandomInt(1, 255); 
            population.add(ind);
        }
    }

    public static void bubbleSort(){
        boolean sw = false;
        while (!sw)
        {
            sw = true;
            for (int i = 1; i < population.size(); i++)
            {
                if (population.get(i).Fitness > population.get(i - 1).Fitness)
                {
                    Individuo ind = population.get(i);
                    population.set(i, population.get(i - 1));
                    population.set(i - 1, ind);
                    sw = false;
                }
            }
        }
    }

    static double FuncionFitness(float x)
    {
        return x/1023;
    }

    public static void getFitness(){
        for (int i = 0; i < population.size(); i++)
        {
            Individuo ind = population.get(i);
            ind.Fitness = (float) (FuncionFitness(population.get(i).Genoma));
            population.set(i, ind);
        }
    }

    public static void ImprimirValores()
    {
        System.out.println("Generacion no. " + Generacion);
        for (int i = 0; i < populationSize; i++)
        {
            if(population.get(i).Fitness == 1f || flag_1023 == true)
                System.out.println("(" + i + ")" + green_ansii + population.get(i).Genoma + reset_ansii + " p:" +  green_ansii + population.get(i).Fitness + reset_ansii);
            else
                System.out.println("(" + i + ")" + population.get(i).Genoma + " p:" + population.get(i).Fitness);
        }
        System.out.println("\n");
        Generacion++;
    }

    static void Combinacion()
    {
        float puntaje, puntajeAcum = 0, puntajeAcumInd = 0, buff=0;
        for(int i = 0; i < population.size(); i++)
        {
            puntaje = population.get(i).Fitness;
            puntajeAcum = puntajeAcum + puntaje;
        }
        for(int i = 0; i < population.size(); i++)
        {
            Individuo ind = population.get(i);
            puntaje = population.get(i).Fitness;
            puntajeAcumInd = puntaje/puntajeAcum;
            ind.probability = puntajeAcumInd;
            buff = puntajeAcumInd + buff;
            ind.acumulatedProbability = buff;
            population.set(i,ind);
        }
        Random r = new Random();
        float randRuletaNum = 0 + r.nextFloat() * (population.get(population.size()-1).acumulatedProbability - 0);
        float randRuletaNum2 = 0 + r.nextFloat() * (population.get(population.size()-1).acumulatedProbability - 0);
        float bufferRuleta = 0, probabilityActual;
        long temporal;
        String Padre1 = "0000000000";
        String Padre2 = "0000000000";
        boolean Padre1Listo = false;
        boolean Padre2Listo = false;
        for(int i = 0; i < population.size(); i++)
        {
            Individuo ind = population.get(i);
            probabilityActual = population.get(i).acumulatedProbability;
            if((bufferRuleta<randRuletaNum && randRuletaNum<=probabilityActual) && Padre1Listo == false)
            {
                temporal = population.get(i).Genoma;
                Padre1 = ind.DecimalToBinary(temporal);
                Padre1Listo = true;
            }
            else
            {
                if((bufferRuleta<randRuletaNum2 && randRuletaNum2<=probabilityActual) && Padre2Listo == false)
                {
                    temporal = population.get(i).Genoma;
                    Padre2 = ind.DecimalToBinary(temporal);
                    Padre2Listo = true;
                }
                else {
                    bufferRuleta = probabilityActual;
                }
            }
        }

        int mutacion = getRandomInt(0,Padre1.length());
        int contador;
        StringBuilder Hijo1 = new StringBuilder(Padre1.length());
        StringBuilder Hijo2 = new StringBuilder(Padre1.length());

        for(contador = 0; contador < mutacion; contador++)
        {
            Hijo1.append(Padre2.charAt(contador));
            Hijo2.append(Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < Padre1.length(); cont2++)
        {
            Hijo1.append(Padre1.charAt(cont2));
            try {
                Hijo2.append(Padre2.charAt(cont2));
            }
            catch(Exception e){
                System.out.println("Hijo1: " + Hijo1);
                System.out.println("Hijo1 TAM: " + Hijo1.length());
                System.out.println("Hijo2: " + Hijo2);
                System.out.println("Hijo2 TAM: " + Hijo2.length());
            }
        }
        Individuo ind = new Individuo();
        ind.Genoma = ind.binaryToDecimal(String.valueOf(Hijo1));
        children.add(ind);
        Individuo ind2 = new Individuo();
        ind2.Genoma = ind2.binaryToDecimal(String.valueOf(Hijo2));
        children.add(ind2);
    }

    static void Mutacion(){
        long GenomaHijo;
        StringBuilder SGenoma;
        float ratioMutacion = 0.1f;
        Random r = new Random();
        for(int i=0; i< children.size(); i++)
        {
            float randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo ind = children.get(i);
                GenomaHijo = children.get(i).Genoma;
                SGenoma = new StringBuilder(ind.DecimalToBinary(GenomaHijo));
                int rand = getRandomInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                ind.Genoma = ind.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }
        }
    }

    static void ActualizarGeneracion()
    {
        population = new ArrayList<>();
        for(int i = 0; i < children.size(); i++)
        {
            population.add(children.get(i));
        }
        children = new ArrayList<>();
    }

    public static void AlgoritmoGenetico(){
        genertePopulation(); 
        int ite = 0;
        while(flag_1023!=true) 
        {
            getFitness();
            ImprimirValores();
            int j =0;
            while(j < population.size())
            {
                if(population.get(j).Fitness == 1.0f)
                {
                    flag_1023 = true;
                    break;
                }
                j++;
            }
            while (children.size() < population.size()) {
                Combinacion();
            }
            Mutacion();
            ActualizarGeneracion();
            ite++;
        }
        System.out.println("\nNumero Final de Iteraciones: " + ite++);
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AlgoritmoGenetico();
        System.out.println("Tiempo de Ejecucion en milisegundos: "+(System.currentTimeMillis()-start));
    }
}
