import java.util.Arrays;
import java.util.Random;

public class Generacion {
    int popSize = 100;
    Individuo[] individuos = new Individuo[100];
    int fittest = 0;
    int worst = 0;

    //Initialize population
    public void initializePopulation(int size) {
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = new Individuo();
            //System.out.println(individuos[i]);
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
        padres[1] = individuos[random.nextInt(popSize)];
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
        return hijo;
    }

    //Mutacion

    public void mutacion(Individuo individuo){
        Random random = new Random();
        if (random.nextDouble() < 0.1) { // 10% de probabilidad
            int index = random.nextInt(individuo.campeones.length); // Índice del campeón a mutar
            int nuevoId = random.nextInt(90 - 1 + 1) + 1; // ID aleatorio del nuevo campeón
            Campeon nuevoCampeon = individuo.championPool.get(nuevoId); // Selecciona un campeón aleatorio
            individuo.campeones[index] = nuevoCampeon; // Reemplaza el campeón
            individuo.calcFitness(); // Recalcula el fitness
        }
    }

    public void reemplazoSeleccion(Individuo[] hijos) {
        // Combina padres e hijos en una sola población
        Individuo[] nuevaPoblacion = new Individuo[individuos.length + hijos.length];
        System.arraycopy(individuos, 0, nuevaPoblacion, 0, individuos.length);
        System.arraycopy(hijos, 0, nuevaPoblacion, individuos.length, hijos.length);

        // Ordena la población combinada por fitness (descendente)
        Arrays.sort(nuevaPoblacion, (a, b) -> Float.compare(b.fitnessTotal, a.fitnessTotal));

        // Selecciona los mejores individuos para la nueva población
        System.arraycopy(nuevaPoblacion, 0, individuos, 0, individuos.length);
    }
  /*
    //Get the second most fittest individual
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }*/ 
}
