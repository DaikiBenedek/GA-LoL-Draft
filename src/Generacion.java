import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Generacion {
    int popSize = 0;
    Individuo[] individuos = new Individuo[1];
    int fittest = 0;
    int worst = 0;

    //Initialize population
    public void initializePopulation(int size, Campeon[] enemigos, List<Campeon> baneados) {
        popSize = size;
        individuos = new Individuo[size];
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = new Individuo();
            individuos[i].asignarCampeonesConRestricciones(individuos[i].championPool, enemigos, baneados);
        }
    }

    public void calcFitnessTotal(Individuo[] nuevaPoblacion, Individuo oponente){
        for (int i = 0; i < nuevaPoblacion.length; i++){
            nuevaPoblacion[i].fitnessTotal = (nuevaPoblacion[i].fitnessEngage - oponente.fitnessPoke) + (nuevaPoblacion[i].fitnessPoke - oponente.fitnessTeamFight) + (nuevaPoblacion[i].fitnessTeamFight - oponente.fitnessEngage);
        }
    }

    //Get the best individual
    public Individuo getFittest(Individuo oponente) {
        float maxFit = Float.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuos.length; i++){
            individuos[i].fitnessTotal = (individuos[i].fitnessEngage - oponente.fitnessPoke) + (individuos[i].fitnessPoke - oponente.fitnessTeamFight) + (individuos[i].fitnessTeamFight - oponente.fitnessEngage);
        }
        for (int i = 0; i < individuos.length; i++) {
            if (maxFit <= individuos[i].fitnessTotal) {
                maxFit = individuos[i].fitnessTotal;
                maxFitIndex = i;
            }
        }
        fittest = maxFitIndex;
        try {
            return (Individuo) individuos[maxFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get the least fittest individual
    public Individuo getLeastFittest(Individuo oponente) {
        float minFit = Float.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuos.length; i++){
            individuos[i].fitnessTotal = (individuos[i].fitnessEngage - oponente.fitnessPoke) + (individuos[i].fitnessPoke - oponente.fitnessTeamFight) + (individuos[i].fitnessTeamFight - oponente.fitnessEngage);
        }
        for (int i = 0; i < individuos.length; i++) {
            if (minFit >= individuos[i].fitnessTotal) {
                minFit = individuos[i].fitnessTotal;
                minFitIndex = i;
            }
        }
        worst = minFitIndex;
        //System.out.println("Poke: " + individuos[minFitIndex].fitnessPoke + "\nEngage: " + individuos[minFitIndex].fitnessEngage + "\nTeam Fight:" + individuos[minFitIndex].fitnessTeamFight);
        try {
            return (Individuo) individuos[minFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Individuo[] seleccionarPadres() {
        Random random = new Random();
        Individuo[] padres = new Individuo[2];
        padres[0] = individuos[random.nextInt(popSize)];
        Arrays.asList(individuos).indexOf(padres[0]);
        padres[1] = individuos[random.nextInt(popSize)];
        Arrays.asList(individuos).indexOf(padres[1]);
        return padres;
    }
    // Operador de cruza uniforme
    public Individuo cruzar(Individuo[] padres) {
        Random random = new Random();
        Individuo hijo = new Individuo();
        Individuo padre1 = padres[0];
        Individuo padre2 = padres[1];
        for (int i = 0; i < hijo.campeones.length; i++) {
            // Decide aleatoriamente si toma el gen del padre1 o del padre2
            if (random.nextBoolean()) {
                hijo.campeones[i] = padre1.campeones[i];
            } else {
                hijo.campeones[i] = padre2.campeones[i];
            }
        }
        hijo.calcFitness();
        //System.out.println("\nPadre 1" + " " + padre1.aString());
        //System.out.println("Padre 2 " + " " + padre2.aString());
        //System.out.println("Hijo " + " " + hijo.aString());
        return hijo;
    }

    //Mutacion
    public void mutacion(Individuo individuo, Campeon[] enemigos, List<Campeon> baneados) {
      Random random = new Random();
      if (random.nextDouble() < .1) { // 10% de probabilidad de mutación
        int index = random.nextInt(individuo.campeones.length); // Índice del campeón a mutar
        // Determinar el rango de IDs válidos para la posición
        int min = 0;
        int max = 0;
        switch(index){
          case(0):
          min = 1;
          max = 15;
          break;
          case(1):
           min = 16;
           max = 36;
           break;
          case(2):
           min = 37;
           max = 56;
           break;
          case(3):
           min = 57;
           max = 70;
           break;
          case(4):
           min = 71;
           max = 87;
           break;
          default:
          break;
        }
        // Crear una lista de IDs válidos para la posición, excluyendo enemigos y baneados
        List<Integer> idsValidos = new ArrayList<>();
        for (int id = min; id <= max; id++) {
            boolean esRestringido = false;

            // Verificar si el ID está en la lista de enemigos
            if (enemigos != null) {
                for (Campeon enemigo : enemigos) {
                    if (enemigo.id == id) {
                        esRestringido = true;
                        break;
                    }
                }
            }

            // Verificar si el ID está en la lista de baneados
            if (baneados != null) {
                for (Campeon baneado : baneados) {
                    if (baneado.id == id) {
                        esRestringido = true;
                        break;
                    }
                }
            }

            if (!esRestringido) {
                idsValidos.add(id);
            }

        }

        // Seleccionar un nuevo ID aleatorio de los IDs válidos
        if (!idsValidos.isEmpty()) {
            int nuevoId = idsValidos.get(random.nextInt(idsValidos.size()));
            Campeon nuevoCampeon = individuo.championPool.get(nuevoId);
            individuo.campeones[index] = nuevoCampeon; // Reemplazar el campeón en la posición mutada
            individuo.calcFitness(); // Recalcular el fitness
        } else {
            System.out.println("No hay campeones disponibles para mutar en la posición " + index);
        }
      }
  }



    public void reemplazoSeleccion(Individuo[] hijos, Individuo oponente) {
        //Combina padres e hijos en una sola población
        Individuo[] nuevaPoblacion = new Individuo[individuos.length + hijos.length];
        System.arraycopy(individuos, 0, nuevaPoblacion, 0, individuos.length);
        System.arraycopy(hijos, 0, nuevaPoblacion, individuos.length, hijos.length);
        //System.out.println("Nueva poblacion");
        for(int i = 0; i < nuevaPoblacion.length; i++){
            nuevaPoblacion[i].calcFitness();
            //System.out.println("\nNumero " + i + " " + nuevaPoblacion[i].aString());
            //System.out.println("Poke: " + nuevaPoblacion[i].fitnessPoke + " Engage: " + nuevaPoblacion[i].fitnessEngage + " Team Fight: " + nuevaPoblacion[i].fitnessTeamFight);
            calcFitnessTotal(nuevaPoblacion, oponente);
            //System.out.println("Total: " + nuevaPoblacion[i].fitnessTotal);
        }

        // Ordena la población combinada por fitness (descendente)
        Arrays.sort(nuevaPoblacion, (i1, i2) -> Float.compare(i2.fitnessTotal, i1.fitnessTotal));
        //System.out.println("Poblacion ordenada");
        for(int i = 0;i<nuevaPoblacion.length;i++){
            //System.out.println("\nNumero " + i + " " + nuevaPoblacion[i].aString());
            nuevaPoblacion[i].aString();
            //System.out.println("Poke: " + nuevaPoblacion[i].fitnessPoke + " Engage: " + nuevaPoblacion[i].fitnessEngage + " Team Fight: " + nuevaPoblacion[i].fitnessTeamFight);
            //System.out.println("Total: " + nuevaPoblacion[i].fitnessTotal);
        }
        // Selecciona los mejores individuos para la nueva población
        System.arraycopy(nuevaPoblacion, 0, individuos, 0, individuos.length);
        //System.out.println("Poblacion SIGUIENTE");
        for(int i = 0;i<individuos.length;i++){
            //System.out.println("\nNumero " + i + " " + individuos[i].aString());
            individuos[i].aString();
            //System.out.println("Poke: " + individuos[i].fitnessPoke + " Engage: " + individuos[i].fitnessEngage + " Team Fight: " + individuos[i].fitnessTeamFight);
            //System.out.println("Total: " + individuos[i].fitnessTotal);
        }
    }
}
