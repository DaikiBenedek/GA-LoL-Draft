import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("¿Cuántos individuos quieres?: ");
        int numIndividuos = scanner.nextInt();
        System.out.println("\n¿Cuántas generaciones quieres?: ");
        int numGeneraciones = scanner.nextInt();
        System.out.println("\n¿Cuántas iteraciones quieres?: ");
        int numIteraciones = scanner.nextInt();
        System.out.println("Escribe 0 para composición aleatoria \nEscribe 1 para ingresarla manualmente\n");
        switch(scanner.nextInt()){
            case(0):
            compoAleatoria(oponenteAleatorio, numIndividuos, numGeneraciones, numIteraciones);
            break;
            case(1):
            Individuo oponente = compoEscrita(oponenteAleatorio);
            compoAleatoria(oponente, numIndividuos, numGeneraciones, numIteraciones);
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

    public static void compoAleatoria(Individuo oponente, int numIndividuos, int numGeneraciones, int numIteraciones){
        Random rn = new Random();
        //Generar composición inicial
        //Initialize population
        Generacion generacion = new Generacion();
        Individuo mejor;
        Individuo mejorAllTime;
        Individuo peor;
        Individuo peorAllTime;
        int generacionContador = 0;
        
        generacion.initializePopulation(numIndividuos);
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
        for (int generacionActual = 0; generacionActual < numGeneraciones; generacionActual++) {
            // Seleccionar padres
            //Individuo[] padres = new Individuo[100]; // Selecciona 100 padres aleatoriamente
            //for (int i = 0; i < padres.length; i++) {
            //    padres[i] = generacion.individuos[new Random().nextInt(generacion.popSize)];
            //}

            // Crear hijos mediante cruza
            generacionContador++;
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
            System.out.println("Generacion: " + generacionContador);
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
            guardarDatosGeneracion(generacionContador, mejor, peor, generacion.individuos);
        }
    }
    public static void guardarDatosGeneracion(int generacion, Individuo mejor, Individuo peor, Individuo[] poblacion) {
        String archCSV = "C:\\Users\\daiki\\Desktop\\ProyectoEvolutivo\\GA-LoL-Draft\\resultados.csv";
        try (FileWriter writer = new FileWriter(archCSV, true)) {
            // Guardar estadísticas generales
            writer.write(String.format("%d,%f,%f,%f\n", generacion, mejor.fitnessTotal, peor.fitnessTotal, calcularPromedio(poblacion)));
            // Guardar fitness de todos los individuos (opcional, para boxplot)
            //for (Individuo individuo : poblacion) {
            //    writer.write(String.format("%d,%f\n", generacion, individuo.fitnessTotal));
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static float calcularPromedio(Individuo[] poblacion) {
        float suma = 0;
        for (Individuo individuo : poblacion) {
            suma += individuo.fitnessTotal;
        }
        return suma / poblacion.length;
    }
}
