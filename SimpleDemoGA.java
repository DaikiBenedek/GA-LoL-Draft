import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Tokuhara Daiki
 */

//Main class
public class SimpleDemoGA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Individuo oponenteAleatorio = new Individuo();
        System.out.println("Escribe 0 para composición aleatoria \nEscribe 1 para ingresarla manualmente\n");
        switch(scanner.nextInt()){
            case(0):
            compoAleatoria(oponenteAleatorio);
            break;
            case(1):
            Individuo oponente = compoEscrita(oponenteAleatorio);
            compoAleatoria(oponente);
            break;
            default:
            System.out.println("Opcion invalida");
        }
    }

    public static Individuo compoEscrita(Individuo oponente){
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < oponente.campeones.length ; i++){
            System.out.println("Introduce el nombre del campeón de la posicion " + i + " ");
            String nombreBuscado = scanner.nextLine();
            Campeon resultado = buscarPorNombre(oponente.championPool, nombreBuscado);
            // Mostrar el resultado
            if (resultado != null) {
                System.out.println("Hemos añadido a: " + resultado + " a la composición.");
                oponente.campeones[i] = resultado;
            } else {
                i--;
                System.out.println("No se encontró ningún campeón con el nombre " + nombreBuscado + ", vuelve a intentarlo.");
            }
        }
        scanner.close();
        return oponente;
    }

    // Método para buscar un campeón por nombre
    public static Campeon buscarPorNombre(Map<Integer, Campeon> campeonPool, String nombre) {
        for (Campeon campeon : campeonPool.values()) {
            if (campeon.nombre.equalsIgnoreCase(nombre)) { // Comparación insensible a mayúsculas
                return campeon;
            }
        }
        return null; // No encontrado
    }

    public static void compoAleatoria(Individuo oponente){
        Random rn = new Random();
        //Generar composición inicial
        //Initialize population
        Generacion generacion = new Generacion();
        Individuo mejor;
        Individuo mejorAllTime;
        Individuo peor;
        Individuo peorAllTime;
        
        generacion.initializePopulation(100);
        mejor = generacion.getFittest(oponente);
        peor = generacion.getLeastFittest(oponente);
        mejorAllTime = mejor;
        peorAllTime = peor;
        //Individuo[] test = generacion.seleccionarPadres();
        //for (int i = 0; i < 2; i++) {
        //    System.out.println(test[i]);
        //}
        //Imprimir el oponente
        System.out.println("Oponente " + oponente.aString() + "\nPoke: " + oponente.fitnessPoke + " Engage: " + oponente.fitnessEngage + " Team Fight: " + oponente.fitnessTeamFight + "\n");
        // Simulación de generaciones
        for (int generacionActual = 0; generacionActual < 10; generacionActual++) {
            // Seleccionar padres
            //Individuo[] padres = new Individuo[100]; // Selecciona 100 padres aleatoriamente
            //for (int i = 0; i < padres.length; i++) {
            //    padres[i] = generacion.individuos[new Random().nextInt(generacion.popSize)];
            //}

            // Crear hijos mediante cruza
            Individuo[] hijos = new Individuo[10];
            Individuo[] padres = new Individuo[2];
            for (int i = 0; i < hijos.length; i++){
                padres = generacion.seleccionarPadres();
                //System.out.println("\nPadre 1: " + padres[0].aString() + "\n\nPadre 2: " + padres[1].aString());
                hijos[i] = generacion.cruzar(padres);
                //System.out.println("\nHijo: " + hijos[i].aString());
            }

            // Aplicar mutación a los hijos
            //for (Individuo hijo : hijos) {
            //    generacion.mutacion(hijo);
            //}

            //Reemplazar población usando (μ + λ)
            generacion.reemplazoSeleccion(hijos, oponente);
            System.out.println(hijos);
            System.out.println("-------------------------------------------");
            System.out.println("Generacion: " + generacionActual);
            System.out.println("-------------------------------------------");
            System.out.println("Peor: " + peor.aString());
            System.out.println("Poke: " + peor.fitnessPoke + " Engage: " + peor.fitnessEngage + " Team Fight: " + peor.fitnessTeamFight);
            System.out.println("Total: " + peor.fitnessTotal);
            System.out.println("\nMejor: " + mejor.aString());
            System.out.println("Poke: " + mejor.fitnessPoke + " Engage: " + mejor.fitnessEngage + " Team Fight: " + mejor.fitnessTeamFight);
            System.out.println("Total: " + mejor.fitnessTotal);
            mejor = generacion.getFittest(oponente);
            peor = generacion.getLeastFittest(oponente);
            if(mejor.fitnessTotal > mejorAllTime.fitnessTotal){
                mejorAllTime = mejor;
            }
            if(peor.fitnessTotal < peorAllTime.fitnessTotal){
                peorAllTime = peor;
            }
            System.out.println("\nMejor Actual: " + mejor.fitnessTotal);
            System.out.println("\nPeor Actual: " + peor.fitnessTotal);
            System.out.println("\nMejor All Time: " + mejorAllTime.fitnessTotal);
            System.out.println("\nPeor All Time: " + peorAllTime.fitnessTotal);
            System.out.println("Oponente " + oponente.aString() + "\nPoke: " + oponente.fitnessPoke + " Engage: " + oponente.fitnessEngage + " Team Fight: " + oponente.fitnessTeamFight + "\n");
            // Imprimir mejor fitness de la generación
            //System.out.println("Generación " + generacionActual + " - Mejor fitness: " + generacion.individuos[0].fitnessTotal);
        }
    }
}

    //Selection
    //void selection() {

        //Select the most fittest individual
        //fittest = population.getFittest();

        //Select the second most fittest individual
        //secondFittest = population.getSecondFittest();
    //}

        /**
         * 
         
        Random rn = new Random();

        SimpleDemoGA demo = new SimpleDemoGA();

        //Initialize population
        demo.population.initializePopulation(10);

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);

        //While population gets an individual with maximum fitness
        while (demo.population.fittest < 5) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }

        System.out.println("");

    }

    //Selection
    void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }*/
