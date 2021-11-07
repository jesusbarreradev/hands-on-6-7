package examples.behaviours;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Maxones extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  }

  private class MyOneShotBehaviour extends OneShotBehaviour {
    public int generation = 0;

    public static int[] generateRandomPopulation(){
      int[] arr; arr = new int[11];
      Random rn = new Random();
      for(int i =0; i < 11; i++){
        arr[i] = rn.nextInt(1 - 0 + 1) + 0;
      }
      return arr;
    }

    public static boolean evaluatePopulation(int[] population){
      return IntStream.of(population).anyMatch(x -> x == 0);
    }

    public static int countOnes(int[] population){
      int counter = 0;
      for(int i = 0; i < 11; i++){
        if(population[i] == 1) counter++;
      }
      return counter;
    }

    public static int[][] selectParents(int[][] population){
      int[] firstParent; firstParent = new int[11];
      int[] secondParent; secondParent = new int[11];
      firstParent = population[0]; secondParent = population[1];
      for(int index_1 = 2; index_1 < 8; index_1++) {
        if(countOnes(population[index_1]) > countOnes(firstParent)){
          firstParent = population[index_1];
        }else if(countOnes(population[index_1]) > countOnes(secondParent)){
          secondParent = population[index_1];
        }
      }
      int[][] parents = new int[10][];
      parents[0] = firstParent; parents[1] = secondParent;
      return parents;
    }

    public static int[][] crossover(int[][] population){
      int[] firstParent; firstParent = new int[11];
      int[] secondParent; secondParent = new int[11];
      for(int i = 0; i < population.length; i++){
        if(i < (population.length/2)){
          firstParent[i] = population[0][i];
        }
        else{
          firstParent[i] = population[1][i];
        }
      }
      for(int i = 0; i < population.length; i++){
        if(i < (population.length/2)){
          secondParent[i] = population[1][i];
        }
        else{
          secondParent[i] = population[0][i];
        }
      }
      int[][] parents = new int[10][];
      parents[0] = firstParent; parents[1] = secondParent;
      return parents;
    }

    public void action() {
      boolean done = false;
      int[][] population = new int[10][];
      int[][] newPopulation = new int[10][];
      //Generamos 10 poblaciones.
      for(int i = 0; i < 10; i++){
        population[i] = generateRandomPopulation();
      }

      do{
        //Evaluamos si alguna de las poblaciones cumple el criterio.
        for(int i = 0; i < population.length; i++){
          //Si se cumple el criterio, se asigna a fitness y se imprime en pantalla.
          if(!evaluatePopulation(population[i])){
            int[] fittest = population[i];
            for(int j = 0; j < 2; j++){
              System.out.println(fittest[j] + " ");
            }
            done = true;
          }
        }

        population = selectParents(population);
        //newPopulation = crossover(population);

      }while(!done);

      System.out.println("Fin del programa");
      
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}

