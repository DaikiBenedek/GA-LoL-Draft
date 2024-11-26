import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Tokuhara Daiki
 */

// Clase Individuo
public class Individuo implements Cloneable{
    Campeon[] campeones = new Campeon[5];
    Map<Integer, Campeon> championPool;

    float fitnessEngage = 0;
    float fitnessPoke = 0;
    float fitnessTeamFight = 0;
    float fitnessTotal = 0;

    // Constructor de Individuo
    public Individuo() {
        championPool = crearChampionPool();
        Random rn = new Random();

        // Asignar valores aleatorios a los genes del individuo
        campeones[0] = championPool.get(rn.nextInt(20 - 1 + 1) + 1);    // Rango 1-20
        campeones[1] = championPool.get(rn.nextInt(40 - 21 + 1) + 21);  // Rango 21-40
        campeones[2] = championPool.get(rn.nextInt(55 - 41 + 1) + 41);  // Rango 41-55
        campeones[3] = championPool.get(rn.nextInt(75 - 56 + 1) + 56);  // Rango 56-75
        campeones[4] = championPool.get(rn.nextInt(90 - 76 + 1) + 76);  // Rango 76-90
        calcFitness();
    }

    public Map<Integer, Campeon> crearChampionPool(){
        Map<Integer, Campeon> pool = new HashMap<>();
        pool.put(1, new Campeon(1, 50, 30, 300, 20, 1, 3, 2, 3, 4, 5)); //Jax
        pool.put(2, new Campeon(2, 60, 20, 320, 25, 2, 5, 4, 4, 4, 4)); //Gnar
        pool.put(3, new Campeon(3, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); //
        pool.put(4, new Campeon(4, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 4
        pool.put(5, new Campeon(5, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 5
        pool.put(6, new Campeon(6, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 6
        pool.put(7, new Campeon(7, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 7
        pool.put(8, new Campeon(8, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 8
        pool.put(9, new Campeon(9, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 9
        pool.put(10, new Campeon(10, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 10
        pool.put(11, new Campeon(11, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 11
        pool.put(12, new Campeon(12, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 12
        pool.put(13, new Campeon(13, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 13
        pool.put(14, new Campeon(14, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 14
        pool.put(15, new Campeon(15, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 15
        pool.put(16, new Campeon(16, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 16
        pool.put(17, new Campeon(17, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 17
        pool.put(18, new Campeon(18, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 18
        pool.put(19, new Campeon(19, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 19
        pool.put(20, new Campeon(20, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 20
        pool.put(21, new Campeon(21, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 21
        pool.put(22, new Campeon(22, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 22
        pool.put(23, new Campeon(23, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 23
        pool.put(24, new Campeon(24, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 24
        pool.put(25, new Campeon(25, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 25
        pool.put(26, new Campeon(26, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 26
        pool.put(27, new Campeon(27, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 27
        pool.put(28, new Campeon(28, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 28
        pool.put(29, new Campeon(29, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 29
        pool.put(30, new Campeon(30, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 30
        pool.put(31, new Campeon(31, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 31
        pool.put(32, new Campeon(32, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 32
        pool.put(33, new Campeon(33, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 33
        pool.put(34, new Campeon(34, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 34
        pool.put(35, new Campeon(35, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 35
        pool.put(36, new Campeon(36, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 36
        pool.put(37, new Campeon(37, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 37
        pool.put(38, new Campeon(38, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 38
        pool.put(39, new Campeon(39, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 39
        pool.put(40, new Campeon(40, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 40
        pool.put(41, new Campeon(41, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 41
        pool.put(42, new Campeon(42, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 42
        pool.put(43, new Campeon(43, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 43
        pool.put(44, new Campeon(44, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 44
        pool.put(45, new Campeon(45, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 45
        pool.put(46, new Campeon(46, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 46
        pool.put(47, new Campeon(47, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 47
        pool.put(48, new Campeon(48, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 48
        pool.put(49, new Campeon(49, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 49
        pool.put(50, new Campeon(50, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 50
        pool.put(51, new Campeon(51, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 51
        pool.put(52, new Campeon(52, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 52
        pool.put(53, new Campeon(53, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 53
        pool.put(54, new Campeon(54, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 54
        pool.put(55, new Campeon(55, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 55
        pool.put(56, new Campeon(56, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 56
        pool.put(57, new Campeon(57, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 57
        pool.put(58, new Campeon(58, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 58
        pool.put(59, new Campeon(59, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 59
        pool.put(60, new Campeon(60, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 60
        pool.put(61, new Campeon(61, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 61
        pool.put(62, new Campeon(62, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 62
        pool.put(63, new Campeon(63, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 63
        pool.put(64, new Campeon(64, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 64
        pool.put(65, new Campeon(65, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 65
        pool.put(66, new Campeon(66, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 66
        pool.put(67, new Campeon(67, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 67
        pool.put(68, new Campeon(68, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 68
        pool.put(69, new Campeon(69, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 69
        pool.put(70, new Campeon(70, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 70
        pool.put(71, new Campeon(71, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 71
        pool.put(72, new Campeon(72, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 72
        pool.put(73, new Campeon(73, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 73
        pool.put(74, new Campeon(74, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 74
        pool.put(75, new Campeon(75, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 75
        pool.put(76, new Campeon(76, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 76
        pool.put(77, new Campeon(77, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 77
        pool.put(78, new Campeon(78, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 78
        pool.put(79, new Campeon(79, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 79
        pool.put(80, new Campeon(80, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 80
        pool.put(81, new Campeon(81, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 81
        pool.put(82, new Campeon(82, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 82
        pool.put(83, new Campeon(83, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 83
        pool.put(84, new Campeon(84, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 84
        pool.put(85, new Campeon(85, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 85
        pool.put(86, new Campeon(86, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 86
        pool.put(87, new Campeon(87, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 87
        pool.put(88, new Campeon(88, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 88
        pool.put(89, new Campeon(89, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 89
        pool.put(90, new Campeon(90, 70, 25, 280, 30, 3, 4, 4, 4, 4, 4)); // Línea 90 
        return pool;
    }

    public void calcFitness() {
        for (Campeon campeon : campeones){
            fitnessEngage += ((campeon.AD + campeon.AP) * 40)/800 + ((campeon.MS)*20)/400 + ((campeon.CC)*40)/25;
            fitnessPoke += ((campeon.AD + campeon.AP) * 40)/800 + ((campeon.R)*40)/1000 + ((campeon.AS)*20)/2.5;
            fitnessTeamFight += ((campeon.AD + campeon.AP) * 30)/800 + ((campeon.AR + campeon.MR)*30)/600 + ((campeon.HP)*30)/6000 + ((campeon.AS)*10)/2.5;
        }
    }

    // Sobrescribir el método toString
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("Individuo:\n");
        for (Campeon campeon : campeones) {
            resultado.append(campeon).append("\n");
        }
        return resultado.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Individuo individuo = (Individuo)super.clone();
        for(int i = 0; i < individuo.campeones.length; i++){
            individuo.campeones[i] = this.campeones[i];
        }
        return individuo;
    }
}
