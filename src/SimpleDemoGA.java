import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tokuhara Daiki
 */

//Main class
public class SimpleDemoGA {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Obtener un individuo por la champion pool
        Individuo oponenteAleatorio = new Individuo();
        // Ruta del directorio donde están los archivos
        String directoryPath = "./output/";
        File directory = new File(directoryPath);
        // Verificar si el directorio existe y es un directorio válido
        if (directory.exists() && directory.isDirectory()) {
            // Obtener todos los archivos del directorio
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv") || name.endsWith(".png"));

            if (files != null) {
                for (File file : files) {
                    // Intentar eliminar cada archivo
                    if (file.delete()) {
                        System.out.println("Archivo eliminado: " + file.getName());
                    } else {
                        System.out.println("No se pudo eliminar: " + file.getName());
                    }
                }
            } else {
                System.out.println("No se encontraron archivos para eliminar.");
            }
        } else {
            System.out.println("El directorio no existe o no es válido.");
        }
        System.out.println("¿Cuántos individuos quieres?: ");
        int numIndividuos = scanner.nextInt();
        System.out.println("\n¿Cuántas generaciones quieres?: ");
        int numGeneraciones = scanner.nextInt();
        System.out.println("\n¿Cuántas iteraciones quieres?: ");
        int numIteraciones = scanner.nextInt();
        List<Campeon> baneados = obtenerBaneados(oponenteAleatorio.championPool);
        System.out.println("Escribe 0 para composición aleatoria \nEscribe 1 para ingresarla manualmente");
        switch(scanner.nextInt()){
            case(0):
            oponenteAleatorio.asignarCampeonesConRestricciones(oponenteAleatorio.championPool, null, baneados);
            compoAleatoria(oponenteAleatorio, numIndividuos, numGeneraciones, numIteraciones, baneados);
            break;
            case(1):
            System.out.println("Recuerda que no puedes poner campeones baneados que son los siguientes:");
            for (Campeon baneado : baneados) {
              System.out.println("- " + baneado.nombre);
            }
            Individuo oponente = compoEscrita(oponenteAleatorio);
            compoAleatoria(oponente, numIndividuos, numGeneraciones, numIteraciones, baneados);
            break;
            default:
            System.out.println("Opcion invalida");
        }
    }

    public static List<Campeon> obtenerBaneados(Map<Integer, Campeon> championPool) {
      Scanner scanner = new Scanner(System.in);
      List<Campeon> baneados = new ArrayList<>();
      System.out.println("Escribe 0 para baneos aleatorios \nEscribe 1 para ingresar baneos manualmente\n");
      int opcion = scanner.nextInt();

      if (opcion == 1) {
        System.out.println("Introduce los nombres de los campeones baneados (10 en total): ");
        scanner.nextLine(); // Consumir la línea sobrante
        for (int i = 0; i < 10; i++) {
            String nombre = scanner.nextLine();
            Campeon campeon = buscarPorNombre(championPool, nombre);
            if (campeon != null) {
                baneados.add(campeon);
            } else {
                System.out.println("Campeón no encontrado, intenta de nuevo.");
                i--; // Repetir si no se encuentra el campeón
            }
        }
      } else {
        Random rn = new Random();
        List<Integer> ids = new ArrayList<>(championPool.keySet());
        for (int i = 0; i < 10; i++) {
            int id = ids.remove(rn.nextInt(ids.size())); // Remover para evitar duplicados
            baneados.add(championPool.get(id));
        }
      }
      return baneados;
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

    public static void compoAleatoria(Individuo oponente, int numIndividuos, int numGeneraciones, int numIteraciones, List<Campeon> baneados){
        for(int j = 0; j<numIteraciones;j++){
          long inicioEjecucion = System.currentTimeMillis(); // Inicia el contador de tiempo
          Random rn = new Random();
          // Inicializar población con restricciones
          Generacion generacion = new Generacion();
          generacion.initializePopulation(numIndividuos, oponente.campeones, baneados);
          Individuo mejor;
          Individuo mejorAllTime;
          Individuo peor;
          Individuo peorAllTime;
          int generacionContador = 0;
          int generacionContadorConverge = 0;
          boolean temp = true;

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

              //Aplicar mutación a los hijos
              for (Individuo hijo : hijos) {
                  generacion.mutacion(hijo, oponente.campeones, baneados);
              }

              //Reemplazar población usando (μ + λ)
              generacion.reemplazoSeleccion(hijos, oponente);
              //Para hacer pruebas
              //System.out.println(hijos);
              //System.out.println("-------------------------------------------");
              //System.out.println("Generacion: " + generacionContador);
              //System.out.println("-------------------------------------------");
              //System.out.println("Peor: " + peor.aString());
              //System.out.println("Poke: " + peor.fitnessPoke + " Engage: " + peor.fitnessEngage + " Team Fight: " + peor.fitnessTeamFight);
              //System.out.println("Total: " + peor.fitnessTotal);
              //System.out.println("\nMejor: " + mejor.aString());
              //System.out.println("Poke: " + mejor.fitnessPoke + " Engage: " + mejor.fitnessEngage + " Team Fight: " + mejor.fitnessTeamFight);
              //System.out.println("Total: " + mejor.fitnessTotal);
              mejor = generacion.getFittest(oponente);
              peor = generacion.getLeastFittest(oponente);
              if(mejor.fitnessTotal > mejorAllTime.fitnessTotal){
                  mejorAllTime = mejor;
              }
              if(peor.fitnessTotal < peorAllTime.fitnessTotal){
                  peorAllTime = peor;
              }
              //System.out.println("\nGeneracion: " + generacionContador);
              //System.out.println("\nMejor Actual: " + mejor.fitnessTotal);
              //System.out.println("\nPeor Actual: " + peor.fitnessTotal);
              //System.out.println("\nMejor All Time: " + mejorAllTime.fitnessTotal);
              //System.out.println("\nPeor All Time: " + peorAllTime.fitnessTotal);
              //System.out.println("Oponente " + oponente.aString() + "\nPoke: " + oponente.fitnessPoke + " Engage: " + oponente.fitnessEngage + " Team Fight: " + oponente.fitnessTeamFight + "\n");
              //System.out.println("Baneados: ");
              // Calcular promedio de fitness
              float promedioFitness = calcularPromedio(generacion.individuos);

              // Imprimir datos de la generación
              System.out.printf(
              "Generación: %d | Mejor Fitness: %.2f | Peor Fitness: %.2f | Promedio Fitness: %.2f%n",
              generacionActual + 1,
              mejor.fitnessTotal,
              peor.fitnessTotal,
              promedioFitness
              );
              // Calcular tiempo de ejecución

              // Imprimir mejor fitness de la generación
              //System.out.println("Generación " + generacionActual + " - Mejor fitness: " + generacion.individuos[0].fitnessTotal);
              int k = j +1;
              if(mejor.fitnessTotal == peor.fitnessTotal && temp){
                generacionContadorConverge = generacionContador;
                temp = false;
              }
              guardarDatosGeneracion(generacionContador, mejor, peor, generacion.individuos, k);
          }
          long finEjecucion = System.currentTimeMillis();
          long tiempoTotalMs = finEjecucion - inicioEjecucion;
          // Resumen final
          System.out.println("\n=== Resumen Final ===");
          System.out.printf("Total de Generaciones: %d%n", numGeneraciones);
          System.out.printf("Número de Individuos por Generación: %d%n", numIndividuos);
          System.out.printf("Tiempo de Ejecución: %.4f segundos%n", tiempoTotalMs / 1000.0);
          System.out.printf("La poblacion convergio en la generacion: %d%n", generacionContadorConverge);
          // Imprimir los campeones baneados
          System.out.println("Campeones Baneados:");
          for (Campeon baneado : baneados) {
            System.out.println("- " + baneado.nombre);
          }
          System.out.println("\nOponente " + oponente.aString() + "\nPoke: " + oponente.fitnessPoke + " Engage: " + oponente.fitnessEngage + " Team Fight: " + oponente.fitnessTeamFight);
          System.out.println("\nMejor al final: " + mejor.aString());
          System.out.println("Poke: " + mejor.fitnessPoke + " Engage: " + mejor.fitnessEngage + " Team Fight: " + mejor.fitnessTeamFight);
          System.out.println("Fitness Total: " + mejor.fitnessTotal);
          System.out.println("\nPeor al final: " + peor.aString());
          System.out.println("Poke: " + peor.fitnessPoke + " Engage: " + peor.fitnessEngage + " Team Fight: " + peor.fitnessTeamFight);
          System.out.println("Fitness Total: " + peor.fitnessTotal);
          System.out.println("\nMejor de todas las generaciones: " + mejorAllTime.aString());
          System.out.println("Poke: " + mejorAllTime.fitnessPoke + " Engage: " + mejorAllTime.fitnessEngage + " Team Fight: " + mejorAllTime.fitnessTeamFight);
          System.out.println("Fitness Total: " + mejorAllTime.fitnessTotal);
          System.out.println("\nPeor de todas las generaciones: " + peorAllTime.aString());
          System.out.println("Poke: " + peorAllTime.fitnessPoke + " Engage: " + peorAllTime.fitnessEngage + " Team Fight: " + peorAllTime.fitnessTeamFight);
          System.out.println("Fitness Total: " + peorAllTime.fitnessTotal);
        }
    }
    public static void guardarDatosGeneracion(int generacion, Individuo mejor, Individuo peor, Individuo[] poblacion, int k) {
        String archCSV = String.format("output/resultados%d.csv", k);
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
