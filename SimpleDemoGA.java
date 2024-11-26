import java.util.Random;

/**
 * @author Tokuhara Daiki
 */

//Main class
public class SimpleDemoGA {

    public static void main(String[] args) {
        Random rn = new Random();
        //Generar composición inicial
        Individuo oponente = new Individuo();
        System.out.println("Oponente " + oponente);
        //Initialize population
        Generacion generacion = new Generacion();
        Individuo mejor;
        Individuo promedio;
        Individuo peor;
        int contadorGeneracion = 0;
        
        generacion.initializePopulation(100);
        mejor = generacion.getFittest(oponente);
        peor = generacion.getLeastFittest(oponente);
        System.out.println("Mejor " + mejor);
        System.out.println("Peor " + peor);

        // Simulación de generaciones
        for (int generacionActual = 0; generacionActual < 10; generacionActual++) {
            // Seleccionar padres
            Individuo[] padres = new Individuo[10]; // Selecciona 10 padres aleatoriamente
            for (int i = 0; i < padres.length; i++) {
                padres[i] = generacion.individuos[new Random().nextInt(generacion.popSize)];
            }

            // Crear hijos mediante cruza
            Individuo[] hijos = new Individuo[10];
            for (int i = 0; i < hijos.length; i++) {
                Individuo padre1 = padres[new Random().nextInt(padres.length)];
                Individuo padre2 = padres[new Random().nextInt(padres.length)];
                hijos[i] = generacion.cruzar(padre1, padre2);
            }

            // Aplicar mutación a los hijos
            for (Individuo hijo : hijos) {
                generacion.mutacion(hijo);
            }

            // Reemplazar población usando (μ + λ)
            generacion.reemplazoSeleccion(hijos);

            // Imprimir mejor fitness de la generación
            System.out.println("Generación " + generacionActual + " - Mejor fitness: " + generacion.individuos[0].fitnessTotal);
        }
    }

    //Selection
    //void selection() {

        //Select the most fittest individual
        //fittest = population.getFittest();

        //Select the second most fittest individual
        //secondFittest = population.getSecondFittest();
    //}
}

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
