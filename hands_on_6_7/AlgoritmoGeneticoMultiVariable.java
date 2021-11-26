import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoGeneticoMultiVariable {
    private static int numIndividuals = 6;
    private static int numIterations = 1000000;
    private static int generation = 0;
    private static ArrayList<Individuo> populationA;
    private static ArrayList<Individuo> populationB;
    private static ArrayList<Individuo> populationC;
    private static ArrayList<Individuo> populationD;
    private static ArrayList<Individuo> populationE;
    private static ArrayList<Individuo> populationF;
    private static ArrayList<Individuo> childrenA;
    private static ArrayList<Individuo> childrenB;
    private static ArrayList<Individuo> childrenC;
    private static ArrayList<Individuo> childrenD;
    private static ArrayList<Individuo> childrenE;
    private static ArrayList<Individuo> childrenF;
    

    public static class Individuo
    {
        private long Genoma;
        private float score;
        private float probability;
        private float acumulatedProbability;

        public String getGenoma()
        {
            return decimalToBinary(Genoma);
        }

        public void setGenoma(StringBuilder n)
        {
            this.Genoma = binaryToDecimal(String.valueOf(n));
        }

        public String decimalToBinary(long num)
        {
            if(num<0) //absoluto
            {
                num = num * -1;
            }
            long n = num;
            String bin = "";

            if(n == 0)
            {
                return "00000000";
            }

            while(n>0)
            {
                if(n%2 == 0)
                    bin = "0" + bin;
                else
                    bin = "1" + bin;
                n = n/2;
            }

            while (bin.length() < 8) //FORZAR 8 bits
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

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void Crearpopulation()
    {
        populationA = new ArrayList<>();
        populationB = new ArrayList<>();
        populationC = new ArrayList<>();
        populationD = new ArrayList<>();
        populationE = new ArrayList<>();
        populationF = new ArrayList<>();
        childrenA = new ArrayList<>();
        childrenB = new ArrayList<>();
        childrenC = new ArrayList<>();
        childrenD = new ArrayList<>();
        childrenE = new ArrayList<>();
        childrenF = new ArrayList<>();
        for (int i = 0; i < numIndividuals; i++)
        {
            for(int j=0; j<=6;j++) {
                Individuo ind = new Individuo();
                if(j==0){
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationA.add(ind);
                } else if(j==1) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationB.add(ind);
                } else if(j==2) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationC.add(ind);
                } else if(j==3) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationD.add(ind);
                } else if(j==4) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationE.add(ind);
                } else if(j==6) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    populationF.add(ind);
                }
            }
        }
    }

    static float MiFuncion(int contador)
    {
        float resultado;
        resultado = populationA.get(contador).Genoma + (2 * populationB.get(contador).Genoma) -
                (3 * populationC.get(contador).Genoma) + populationD.get(contador).Genoma +
                (4 * populationE.get(contador).Genoma) + populationF.get(contador).Genoma;
        return resultado;
    }

    static double Fitness(boolean mayor, float x)
    {
        if(mayor == false)
        {
            return x/30;
        }
        else
        {
            return 30/x;
        }
    }

    public static void analizeScores(){
        boolean mayor = true;
        for (int i = 0; i < numIndividuals; i++)
        {
            float resFuncion = MiFuncion(i);
            mayor = true;
            if(resFuncion < 30)
                mayor = false;

            Individuo ind = populationA.get(i);
            ind.score = (float) (Fitness(mayor, resFuncion));
            populationA.set(i, ind);

            Individuo indB = populationB.get(i);
            indB.score = populationA.get(i).score;
            populationB.set(i, indB);

            Individuo indC = populationC.get(i);
            indC.score = populationA.get(i).score;
            populationC.set(i, indC);

            Individuo indD = populationD.get(i);
            indD.score = populationA.get(i).score;
            populationD.set(i, indD);

            Individuo indE = populationE.get(i);
            indE.score = populationA.get(i).score;
            populationE.set(i, indE);

            Individuo indF = populationF.get(i);
            indF.score = populationA.get(i).score;
            populationF.set(i, indF);
        }
    }


    public static void Show()
    {
        System.out.println("generation no. " + generation);
        System.out.print("\ngeneration A: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationA.get(i).Genoma + " p:" + populationA.get(i).score + "   ");
        }
        System.out.print("\ngeneration B: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationB.get(i).Genoma + " p:" + populationB.get(i).score + "   ");
        }
        System.out.print("\ngeneration C: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationC.get(i).Genoma + " p:" + populationC.get(i).score + "   ");
        }
        System.out.print("\ngeneration D: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationD.get(i).Genoma + " p:" + populationD.get(i).score + "   ");
        }
        System.out.print("\ngeneration E: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationE.get(i).Genoma + " p:" + populationE.get(i).score + "   ");
        }
        System.out.print("\ngeneration F: ");
        for (int i = 0; i < numIndividuals; i++)
        {
            System.out.print("(" + i + ")" + populationF.get(i).Genoma + " p:" + populationF.get(i).score + "   ");
        }
        System.out.print("\n\n");
        generation++;
    }

    static void Combination()
    {
        float score, scoreAcumA = 0, scoreAcumB = 0, scoreAcumC = 0, scoreAcumD = 0, scoreAcumE = 0, scoreAcumF = 0, scoreAcumInd = 0,
                buffA = 0, buffB = 0, buffC = 0, buffD = 0, buffE = 0, buffF = 0;

        //se determina el acumulado del porcentaje
        for(int i = 0; i < numIndividuals; i++)
        {
            score = populationA.get(i).score;
            scoreAcumA = scoreAcumA + score;
            score = populationB.get(i).score;
            scoreAcumB = scoreAcumB + score;
            score = populationC.get(i).score;
            scoreAcumC = scoreAcumC + score;
            score = populationD.get(i).score;
            scoreAcumD = scoreAcumD + score;
            score = populationE.get(i).score;
            scoreAcumE = scoreAcumE + score;
            score = populationF.get(i).score;
            scoreAcumF = scoreAcumF + score;
        }

        for(int i = 0; i < numIndividuals; i++)
        {
            Individuo indA = populationA.get(i);
            score = populationA.get(i).score;
            scoreAcumInd = score/scoreAcumA;
            indA.probability = scoreAcumInd;
            buffA = scoreAcumInd + buffA;
            indA.acumulatedProbability = buffA;
            populationA.set(i,indA);

            Individuo indB = populationB.get(i);
            score = populationB.get(i).score;
            scoreAcumInd = score/scoreAcumB;
            indB.probability = scoreAcumInd;
            buffB = scoreAcumInd + buffB;
            indB.acumulatedProbability = buffB;
            populationB.set(i,indB);

            Individuo indC = populationC.get(i);
            score = populationC.get(i).score;
            scoreAcumInd = score/scoreAcumC;
            indC.probability = scoreAcumInd;
            buffC = scoreAcumInd + buffC;
            indC.acumulatedProbability = buffC;
            populationC.set(i,indC);

            Individuo indD = populationD.get(i);
            score = populationD.get(i).score;
            scoreAcumInd = score/scoreAcumD;
            indD.probability = scoreAcumInd;
            buffD = scoreAcumInd + buffD;
            indD.acumulatedProbability = buffD;
            populationD.set(i,indD);

            Individuo indE = populationE.get(i);
            score = populationE.get(i).score;
            scoreAcumInd = score/scoreAcumE;
            indE.probability = scoreAcumInd;
            buffE = scoreAcumInd + buffE;
            indE.acumulatedProbability = buffE;
            populationE.set(i,indE);

            Individuo indF = populationF.get(i);
            score = populationF.get(i).score;
            scoreAcumInd = score/scoreAcumF;
            indF.probability = scoreAcumInd;
            buffF = scoreAcumInd + buffF;
            indF.acumulatedProbability = buffF;
            populationF.set(i,indF);
        }

        Random r = new Random();
        float A_RandRuletaNum = 0 + r.nextFloat() * (populationA.get(populationA.size()-1).acumulatedProbability - 0);
        float A_RandRuletaNum2 = 0 + r.nextFloat() * (populationA.get(populationA.size()-1).acumulatedProbability - 0);
        float B_RandRuletaNum = 0 + r.nextFloat() * (populationB.get(populationB.size()-1).acumulatedProbability - 0);
        float B_RandRuletaNum2 = 0 + r.nextFloat() * (populationB.get(populationB.size()-1).acumulatedProbability - 0);
        float C_RandRuletaNum = 0 + r.nextFloat() * (populationC.get(populationC.size()-1).acumulatedProbability - 0);
        float C_RandRuletaNum2 = 0 + r.nextFloat() * (populationC.get(populationC.size()-1).acumulatedProbability - 0);
        float D_RandRuletaNum = 0 + r.nextFloat() * (populationD.get(populationD.size()-1).acumulatedProbability - 0);
        float D_RandRuletaNum2 = 0 + r.nextFloat() * (populationD.get(populationD.size()-1).acumulatedProbability - 0);
        float E_RandRuletaNum = 0 + r.nextFloat() * (populationE.get(populationE.size()-1).acumulatedProbability - 0);
        float E_RandRuletaNum2 = 0 + r.nextFloat() * (populationE.get(populationE.size()-1).acumulatedProbability - 0);
        float F_RandRuletaNum = 0 + r.nextFloat() * (populationF.get(populationF.size()-1).acumulatedProbability - 0);
        float F_RandRuletaNum2 = 0 + r.nextFloat() * (populationF.get(populationF.size()-1).acumulatedProbability - 0);

        float A_bufferRuleta = 0, probabilityActual=0;
        float B_bufferRuleta = 0;
        float C_bufferRuleta = 0;
        float D_bufferRuleta = 0;
        float E_bufferRuleta = 0;
        float F_bufferRuleta = 0;

        long temporal;

        String A_Padre1 = "00000000";
        String A_Padre2 = "00000000";
        String B_Padre1 = "00000000";
        String B_Padre2 = "00000000";
        String C_Padre1 = "00000000";
        String C_Padre2 = "00000000";
        String D_Padre1 = "00000000";
        String D_Padre2 = "00000000";
        String E_Padre1 = "00000000";
        String E_Padre2 = "00000000";
        String F_Padre1 = "00000000";
        String F_Padre2 = "00000000";

        boolean A_Padre1Listo = false;
        boolean A_Padre2Listo = false;
        boolean B_Padre1Listo = false;
        boolean B_Padre2Listo = false;
        boolean C_Padre1Listo = false;
        boolean C_Padre2Listo = false;
        boolean D_Padre1Listo = false;
        boolean D_Padre2Listo = false;
        boolean E_Padre1Listo = false;
        boolean E_Padre2Listo = false;
        boolean F_Padre1Listo = false;
        boolean F_Padre2Listo = false;

        for(int i = 0; i < numIndividuals; i++)
        {
            Individuo indA = populationA.get(i);
            Individuo indB = populationB.get(i);
            Individuo indC = populationC.get(i);
            Individuo indD = populationD.get(i);
            Individuo indE = populationE.get(i);
            Individuo indF = populationF.get(i);

            probabilityActual = populationA.get(i).acumulatedProbability;
            if((A_bufferRuleta<A_RandRuletaNum && A_RandRuletaNum<=probabilityActual) && A_Padre1Listo == false) {
                temporal = populationA.get(i).Genoma;
                A_Padre1 = indA.decimalToBinary(temporal);
                A_Padre1Listo = true;
            } else {
                if((A_bufferRuleta<A_RandRuletaNum2 && A_RandRuletaNum2<=probabilityActual) && A_Padre2Listo == false) {
                    temporal = populationA.get(i).Genoma;
                    A_Padre2 = indA.decimalToBinary(temporal);
                    A_Padre1Listo = true;
                }
                else {
                    A_bufferRuleta = probabilityActual;
                }
            }

            probabilityActual = populationB.get(i).acumulatedProbability;
            if((B_bufferRuleta<B_RandRuletaNum && B_RandRuletaNum<=probabilityActual) && B_Padre1Listo == false) {
                temporal = populationB.get(i).Genoma;
                B_Padre1 = indB.decimalToBinary(temporal);
                B_Padre1Listo = true;
            } else {
                if((B_bufferRuleta<B_RandRuletaNum2 && B_RandRuletaNum2<=probabilityActual) && B_Padre2Listo == false) {
                    temporal = populationB.get(i).Genoma;
                    B_Padre2 = indB.decimalToBinary(temporal);
                    B_Padre1Listo = true;
                }
                else {
                    B_bufferRuleta = probabilityActual;
                }
            }

            probabilityActual = populationC.get(i).acumulatedProbability;
            if((C_bufferRuleta<C_RandRuletaNum && C_RandRuletaNum<=probabilityActual) && C_Padre1Listo == false) {
                temporal = populationC.get(i).Genoma;
                C_Padre1 = indC.decimalToBinary(temporal);
                C_Padre1Listo = true;
            } else {
                if((C_bufferRuleta<C_RandRuletaNum2 && C_RandRuletaNum2<=probabilityActual) && C_Padre2Listo == false) {
                    temporal = populationC.get(i).Genoma;
                    C_Padre2 = indC.decimalToBinary(temporal);
                    C_Padre1Listo = true;
                }
                else {
                    C_bufferRuleta = probabilityActual;
                }
            }

            probabilityActual = populationD.get(i).acumulatedProbability;
            if((D_bufferRuleta<D_RandRuletaNum && D_RandRuletaNum<=probabilityActual) && D_Padre1Listo == false) {
                temporal = populationD.get(i).Genoma;
                D_Padre1 = indD.decimalToBinary(temporal);
                D_Padre1Listo = true;
            } else {
                if((D_bufferRuleta<D_RandRuletaNum2 && D_RandRuletaNum2<=probabilityActual) && D_Padre2Listo == false) {
                    temporal = populationD.get(i).Genoma;
                    D_Padre2 = indD.decimalToBinary(temporal);
                    D_Padre1Listo = true;
                }
                else {
                    D_bufferRuleta = probabilityActual;
                }
            }

            probabilityActual = populationE.get(i).acumulatedProbability;
            if((E_bufferRuleta<E_RandRuletaNum && E_RandRuletaNum<=probabilityActual) && E_Padre1Listo == false) {
                temporal = populationE.get(i).Genoma;
                E_Padre1 = indE.decimalToBinary(temporal);
                E_Padre1Listo = true;
            } else {
                if((E_bufferRuleta<E_RandRuletaNum2 && E_RandRuletaNum2<=probabilityActual) && E_Padre2Listo == false) {
                    temporal = populationE.get(i).Genoma;
                    E_Padre2 = indE.decimalToBinary(temporal);
                    E_Padre1Listo = true;
                }
                else {
                    E_bufferRuleta = probabilityActual;
                }
            }

            probabilityActual = populationF.get(i).acumulatedProbability;
            if((F_bufferRuleta<F_RandRuletaNum && F_RandRuletaNum<=probabilityActual) && F_Padre1Listo == false) {
                temporal = populationF.get(i).Genoma;
                F_Padre1 = indF.decimalToBinary(temporal);
                F_Padre1Listo = true;
            } else {
                if((F_bufferRuleta<F_RandRuletaNum2 && F_RandRuletaNum2<=probabilityActual) && F_Padre2Listo == false) {
                    temporal = populationF.get(i).Genoma;
                    F_Padre2 = indF.decimalToBinary(temporal);
                    F_Padre1Listo = true;
                }
                else {
                    F_bufferRuleta = probabilityActual;
                }
            }
        }
        
        int contador;
        
        StringBuilder A_Hijo1 = new StringBuilder(A_Padre1.length());
        StringBuilder A_Hijo2 = new StringBuilder(A_Padre1.length());
        int Mutation = getRandomNumberUsingNextInt(0,A_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            A_Hijo1.append(A_Padre2.charAt(contador));
            A_Hijo2.append(A_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < A_Padre1.length(); cont2++)
        {
            A_Hijo1.append(A_Padre1.charAt(cont2));
            try {
                A_Hijo2.append(A_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("A_ Hijo1: " + A_Hijo1);
                System.out.println("A_ Hijo1 TAM: " + A_Hijo1.length());
                System.out.println("A_ Hijo2: " + A_Hijo2);
                System.out.println("A_ Hijo2 TAM: " + A_Hijo2.length());
                System.out.println("A_ Padre1: " + A_Padre1);
                System.out.println("A_ Padre1 TAM: " + A_Padre1.length());
                System.out.println("A_ Padre2: " + A_Padre2);
                System.out.println("A_ Padre2 TAM: " + A_Padre2.length());
            }
        }

        StringBuilder B_Hijo1 = new StringBuilder(B_Padre1.length());
        StringBuilder B_Hijo2 = new StringBuilder(B_Padre1.length());
        Mutation = getRandomNumberUsingNextInt(0,B_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            B_Hijo1.append(B_Padre2.charAt(contador));
            B_Hijo2.append(B_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < B_Padre1.length(); cont2++)
        {
            B_Hijo1.append(B_Padre1.charAt(cont2));
            try {
                B_Hijo2.append(B_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("B_ Hijo1: " + B_Hijo1);
                System.out.println("B_ Hijo1 TAM: " + B_Hijo1.length());
                System.out.println("B_ Hijo2: " + B_Hijo2);
                System.out.println("B_ Hijo2 TAM: " + B_Hijo2.length());
                System.out.println("B_ Padre1: " + B_Padre1);
                System.out.println("B_ Padre1 TAM: " + B_Padre1.length());
                System.out.println("B_ Padre2: " + B_Padre2);
                System.out.println("B_ Padre2 TAM: " + B_Padre2.length());
            }
        }

        StringBuilder C_Hijo1 = new StringBuilder(C_Padre1.length());
        StringBuilder C_Hijo2 = new StringBuilder(C_Padre1.length());
        Mutation = getRandomNumberUsingNextInt(0,C_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            C_Hijo1.append(C_Padre2.charAt(contador));
            C_Hijo2.append(C_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < C_Padre1.length(); cont2++)
        {
            C_Hijo1.append(C_Padre1.charAt(cont2));
            try {
                C_Hijo2.append(C_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("C_ Hijo1: " + C_Hijo1);
                System.out.println("C_ Hijo1 TAM: " + C_Hijo1.length());
                System.out.println("C_ Hijo2: " + C_Hijo2);
                System.out.println("C_ Hijo2 TAM: " + C_Hijo2.length());
                System.out.println("C_ Padre1: " + C_Padre1);
                System.out.println("C_ Padre1 TAM: " + C_Padre1.length());
                System.out.println("C_ Padre2: " + C_Padre2);
                System.out.println("C_ Padre2 TAM: " + C_Padre2.length());
            }
        }

        StringBuilder D_Hijo1 = new StringBuilder(D_Padre1.length());
        StringBuilder D_Hijo2 = new StringBuilder(D_Padre1.length());
        Mutation = getRandomNumberUsingNextInt(0,D_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            D_Hijo1.append(D_Padre2.charAt(contador));
            D_Hijo2.append(D_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < D_Padre1.length(); cont2++)
        {
            D_Hijo1.append(D_Padre1.charAt(cont2));
            try {
                D_Hijo2.append(D_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("D_ Hijo1: " + D_Hijo1);
                System.out.println("D_ Hijo1 TAM: " + D_Hijo1.length());
                System.out.println("D_ Hijo2: " + D_Hijo2);
                System.out.println("D_ Hijo2 TAM: " + D_Hijo2.length());
                System.out.println("D_ Padre1: " + D_Padre1);
                System.out.println("D_ Padre1 TAM: " + D_Padre1.length());
                System.out.println("D_ Padre2: " + D_Padre2);
                System.out.println("D_ Padre2 TAM: " + D_Padre2.length());
            }
        }

        StringBuilder E_Hijo1 = new StringBuilder(E_Padre1.length());
        StringBuilder E_Hijo2 = new StringBuilder(E_Padre1.length());
        Mutation = getRandomNumberUsingNextInt(0,E_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            E_Hijo1.append(E_Padre2.charAt(contador));
            E_Hijo2.append(E_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < E_Padre1.length(); cont2++)
        {
            E_Hijo1.append(E_Padre1.charAt(cont2));
            try {
                E_Hijo2.append(E_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("E_ Hijo1: " + E_Hijo1);
                System.out.println("E_ Hijo1 TAM: " + E_Hijo1.length());
                System.out.println("E_ Hijo2: " + E_Hijo2);
                System.out.println("E_ Hijo2 TAM: " + E_Hijo2.length());
                System.out.println("E_ Padre1: " + E_Padre1);
                System.out.println("E_ Padre1 TAM: " + E_Padre1.length());
                System.out.println("E_ Padre2: " + E_Padre2);
                System.out.println("E_ Padre2 TAM: " + E_Padre2.length());
            }
        }

        StringBuilder F_Hijo1 = new StringBuilder(F_Padre1.length());
        StringBuilder F_Hijo2 = new StringBuilder(F_Padre1.length());
        Mutation = getRandomNumberUsingNextInt(0,F_Padre1.length());
        for(contador = 0; contador < Mutation; contador++)
        {
            F_Hijo1.append(F_Padre2.charAt(contador));
            F_Hijo2.append(F_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < F_Padre1.length(); cont2++)
        {
            F_Hijo1.append(F_Padre1.charAt(cont2));
            try {
                F_Hijo2.append(F_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("F_ Hijo1: " + F_Hijo1);
                System.out.println("F_ Hijo1 TAM: " + F_Hijo1.length());
                System.out.println("F_ Hijo2: " + F_Hijo2);
                System.out.println("F_ Hijo2 TAM: " + F_Hijo2.length());
                System.out.println("F_ Padre1: " + F_Padre1);
                System.out.println("F_ Padre1 TAM: " + F_Padre1.length());
                System.out.println("F_ Padre2: " + F_Padre2);
                System.out.println("F_ Padre2 TAM: " + F_Padre2.length());
            }
        }
        
        Individuo A_ind = new Individuo();
        A_ind.Genoma = A_ind.binaryToDecimal(String.valueOf(A_Hijo1)); 
        childrenA.add(A_ind);
        Individuo A_ind2 = new Individuo();
        A_ind2.Genoma = A_ind2.binaryToDecimal(String.valueOf(A_Hijo2)); 
        childrenA.add(A_ind2);

        Individuo B_ind = new Individuo();
        B_ind.Genoma = B_ind.binaryToDecimal(String.valueOf(B_Hijo1)); 
        childrenB.add(B_ind);
        Individuo B_ind2 = new Individuo();
        B_ind2.Genoma = B_ind2.binaryToDecimal(String.valueOf(B_Hijo2)); 
        childrenB.add(B_ind2);

        Individuo C_ind = new Individuo();
        C_ind.Genoma = C_ind.binaryToDecimal(String.valueOf(C_Hijo1)); 
        childrenC.add(C_ind);
        Individuo C_ind2 = new Individuo();
        C_ind2.Genoma = C_ind2.binaryToDecimal(String.valueOf(C_Hijo2)); 
        childrenC.add(C_ind2);

        Individuo D_ind = new Individuo();
        D_ind.Genoma = D_ind.binaryToDecimal(String.valueOf(D_Hijo1)); 
        childrenD.add(D_ind);
        Individuo D_ind2 = new Individuo();
        D_ind2.Genoma = D_ind2.binaryToDecimal(String.valueOf(D_Hijo2)); 
        childrenD.add(D_ind2);

        Individuo E_ind = new Individuo();
        E_ind.Genoma = E_ind.binaryToDecimal(String.valueOf(E_Hijo1)); 
        childrenE.add(E_ind);
        Individuo E_ind2 = new Individuo();
        E_ind2.Genoma = E_ind2.binaryToDecimal(String.valueOf(E_Hijo2)); 
        childrenE.add(E_ind2);

        Individuo F_ind = new Individuo();
        F_ind.Genoma = F_ind.binaryToDecimal(String.valueOf(F_Hijo1)); 
        childrenF.add(F_ind);
        Individuo F_ind2 = new Individuo();
        F_ind2.Genoma = F_ind2.binaryToDecimal(String.valueOf(F_Hijo2)); 
        childrenF.add(F_ind2);
    }


    static void Mutation(){
        long GenomaHijo;
        StringBuilder SGenoma;
        float ratioMutation = 0.01f;
        Random r = new Random();
        for(int i=0; i< numIndividuals; i++)
        {
            float randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indA = childrenA.get(i);
                GenomaHijo = childrenA.get(i).Genoma;
                SGenoma = new StringBuilder(indA.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indA.Genoma = indA.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indB = childrenB.get(i);
                GenomaHijo = childrenB.get(i).Genoma;
                SGenoma = new StringBuilder(indB.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indB.Genoma = indB.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indC = childrenC.get(i);
                GenomaHijo = childrenC.get(i).Genoma;
                SGenoma = new StringBuilder(indC.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indC.Genoma = indC.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indD = childrenD.get(i);
                GenomaHijo = childrenD.get(i).Genoma;
                SGenoma = new StringBuilder(indD.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indD.Genoma = indD.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indE = childrenE.get(i);
                GenomaHijo = childrenE.get(i).Genoma;
                SGenoma = new StringBuilder(indE.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indE.Genoma = indE.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutation = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutation < ratioMutation)
            {
                Individuo indF = childrenF.get(i);
                GenomaHijo = childrenF.get(i).Genoma;
                SGenoma = new StringBuilder(indF.decimalToBinary(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indF.Genoma = indF.binaryToDecimal(String.valueOf(SGenoma)); //B0 255
            }
        }
    }

    static void replacePopulation()
    {
        populationA = new ArrayList<>();
        populationB = new ArrayList<>();
        populationC = new ArrayList<>();
        populationD = new ArrayList<>();
        populationE = new ArrayList<>();
        populationF = new ArrayList<>();
        for(int i = 0; i < numIndividuals; i++)
        {
            populationA.add(childrenA.get(i));
            populationB.add(childrenB.get(i));
            populationC.add(childrenC.get(i));
            populationD.add(childrenD.get(i));
            populationE.add(childrenE.get(i));
            populationF.add(childrenF.get(i));
        }
        childrenA = new ArrayList<>();
        childrenB = new ArrayList<>();
        childrenC = new ArrayList<>();
        childrenD = new ArrayList<>();
        childrenE = new ArrayList<>();
        childrenF = new ArrayList<>();
    }

    public static void Start(){
        Crearpopulation();
        int ite = 0;
        boolean bandBreak = false;
        while(true)
        {
            analizeScores();
            //Organizar();
            Show();
            int j =0;
            while(j < populationA.size())
            {
                if(populationA.get(j).score == 1)
                {
                    bandBreak = true;
                }
                j++;
            }
            if(bandBreak == true)
            {
                break;
            }
            while (childrenA.size() < populationA.size()) {
                Combination();
            }
            Mutation();
            replacePopulation();
            ite++;
        }
        System.out.println("FIN  " + ite++);
    }

    public static void main(String[] args) {
        Start();
    }
}
