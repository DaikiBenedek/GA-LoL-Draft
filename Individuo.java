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
        campeones[0] = championPool.get(rn.nextInt(16 - 1 + 1) + 1);    // Rango 1-16
        campeones[1] = championPool.get(rn.nextInt(36 - 17 + 1) + 17);  // Rango 17-36
        campeones[2] = championPool.get(rn.nextInt(56 - 37 + 1) + 37);  // Rango 37-56
        campeones[3] = championPool.get(rn.nextInt(70 - 57 + 1) + 57);  // Rango 57-70
        campeones[4] = championPool.get(rn.nextInt(87 - 71 + 1) + 71);  // Rango 71-87
        calcFitness();
    }

    public Map<Integer, Campeon> crearChampionPool(){
        Map<Integer, Campeon> pool = new HashMap<>();
        pool.put(1, new Campeon(1, 250, 100, 350, 300, 2, 250, 250, 4000, 2, 1, "Jax")); //Jax, Counter: Poppy,
        pool.put(2, new Campeon(2, 250, 0, 300, 1000, 1.8, 300, 280, 4000, 3, 2, "Gnar")); //Gnar, Counter: Camille, Renekton  //Gnar, Counter: Camille, Renekton 
        pool.put(3, new Campeon(3, 80, 350, 300, 700, 1, 100, 100, 2500, 0, 2, "Rumble")); //Rumble, Counter: Sion, Renekton
        pool.put(4, new Campeon(4, 250, 0, 280, 300, 1.6, 250, 200, 4000, 2, 2, "Renekton")); //Renekton, Counter: Jax, Darius
        pool.put(5, new Campeon(5, 260, 0, 280, 600, 1.5, 300, 200, 4000, 3, 2, "K'sante")); //K'sante, Counter: Kennen, Rumble
        pool.put(6, new Campeon(6, 70, 300, 280, 800, 1, 200, 200, 3400, 3, 1, "Gragas")); //Gragas, Counter: Kennen
        pool.put(7, new Campeon(7, 260, 0, 350, 900, 2.2, 250, 200, 3500, 0, 1, "Olaf")); //Olaf, Counter: Kennen
        pool.put(8, new Campeon(8, 250, 50, 280, 900, 1.5, 230, 230, 3700, 2, 2, "Camille")); //Camille, Counter: Kennen, Rumble
        pool.put(9, new Campeon(9, 80, 320, 300, 800, 1, 120, 120, 2600, 2, 1, "Kennen")); //Kennen, Counter: Urgot
        pool.put(10, new Campeon(10, 100, 50, 270, 500, 1,400, 160, 4000, 3, 4, "Sion")); // Línea 10
        pool.put(11, new Campeon(11, 216, 0, 425, 800, 0.625, 159, 77, 2856, 2, 0, "Urgot")); // Urgot, Counter:
        pool.put(12, new Campeon(12, 265, 0, 353, 400, 1.05, 114, 32, 3338, 2, 0, "Sett")); // Sett, Counter:
        pool.put(13, new Campeon(13, 128, 0, 385, 700, 0.625, 322, 77, 2883, 3, 0, "Ornn")); // Ornn, Counter:
        pool.put(14, new Campeon(14, 221, 0, 353, 550, 0.875, 119, 32, 3106, 2, 0, "Darius")); // Darius, Counter:
        pool.put(15, new Campeon(15, 288, 0, 385, 600, 0.75, 32, 32, 1860, 2, 0, "Jayce")); // Jayce, Counter:
        pool.put(16, new Campeon(16, 118, 0, 385, 650, 0.625, 115, 157, 2766, 3, 0, "Sejuani")); // Sejuani, Counter:

        pool.put(17, new Campeon(17, 204, 0, 385, 250, 0.75, 42, 57, 3066, 3, 0, "Vi")); // Vi, Counter:
        pool.put(18, new Campeon(18, 246, 0, 385, 1000, 1.04, 38, 52, 2800, 2, 0, "Nocturne")); // Nocturne, Counter:
        pool.put(19, new Campeon(19, 124, 0, 385, 600, 0.625, 135, 82, 3340, 3, 0, "Maokai")); // Maokai, Counter:
        pool.put(20, new Campeon(20, 222, 0, 385, 175, 0.668, 63, 32, 2816, 2, 0, "Xin Zhao")); // Xin Zhao, Counter:
        pool.put(21, new Campeon(21, 198, 0, 385, 325, 0.8125, 63, 32, 3199, 2, 0, "Wukong")); // Wukong, Counter:
        pool.put(22, new Campeon(22, 113, 70, 385, 550, 0.625, 53, 32, 2406, 2, 0, "Ivern")); // Ivern, Counter:
        pool.put(23, new Campeon(23, 62, 400, 400, 1500, 0.668, 81, 30, 2050, 2, 0, "Nidalee")); // Nidalee, Counter:
        pool.put(24, new Campeon(24, 53, 185, 385, 800, 0.625, 38, 32, 2810, 3, 0, "Zyra")); // Zyra, Counter:
        pool.put(25, new Campeon(25, 90 , 240, 385, 700, 0.82 , 73 , 44 , 2370 , 1 , 4, "Brand")); // Línea 25
        pool.put(26, new Campeon(26, 221, 0 , 385, 700 , 1.43 , 80 , 44 , 1734 , 0 , 4, "Kindred")); // Línea 26
        pool.put(27, new Campeon(27, 99, 70, 380, 125, 0.89, 152, 99, 2665, 2, 4, "Amumu")); // Línea 27
        pool.put(28, new Campeon(28, 250, 0, 385, 175, 0.84, 118, 54, 2579, 1, 4, "Jarvan IV")); // Línea 28
        pool.put(29, new Campeon(29, 94, 0, 390, 125, 0.78, 225, 79, 2496, 1, 4, "Nunu")); // Línea 29
        pool.put(30, new Campeon(30, 250, 0, 390, 125, 0.86, 90, 74, 2628, 1, 4, "Lee Sin")); // Línea 30
        pool.put(31, new Campeon(31, 94, 105, 380, 1000, 0.73, 155, 69, 2169, 2, 4, "Morgana")); // Línea 31
        pool.put(32, new Campeon(32, 95, 245, 375, 325, 0.81, 71, 109, 2405, 1, 4, "Lillia")); // Línea 32
        pool.put(33, new Campeon(33, 228, 0, 390, 200, 1.04, 84, 74, 2957, 1, 4, "Viego")); // Línea 33
        pool.put(34, new Campeon(34, 84, 260, 380, 480, 0.77, 135, 44, 2161, 1, 4, "Fiddlesticks")); // Línea 34
        pool.put(35, new Campeon(35, 88, 295, 390, 550, 0.74, 79, 44, 1844, 1, 4, "Elise")); // Línea 35
        pool.put(36, new Campeon(36, 96, 0, 380, 150, 0.76, 146, 125, 3485, 2, 4, "Skarner")); // Línea 36

        pool.put(37, new Campeon(37, 86, 260, 375, 700, 0.82, 122, 44, 2095, 1, 4, "Ahri")); // Línea 37
        pool.put(38, new Campeon(38, 94, 235, 400, 700, 1, 70, 60, 2700, 2, 4, "Sylas")); // Línea 38
        pool.put(39, new Campeon(39, 70, 256, 370, 700, .8, 70, 53, 2100, 1, 4, "Orianna")); // Línea 39
        pool.put(40, new Campeon(40, 200, 0, 370, 900, 1.5, 70, 40, 2000, 0, 4, "Smolder")); // Línea 40
        pool.put(41, new Campeon(41, 70, 300, 370, 500, .8, 70, 50, 2100, 1, 4, "Aurora")); // Línea 41
        pool.put(42, new Campeon(42, 70, 320, 400, 350, 1.1, 70, 50, 2569, 0, 4, "Akali")); // Línea 42
        pool.put(43, new Campeon(43, 200, 0, 370, 900, 2, 70, 40, 2100, 1, 4, "Tristana")); // Línea 43
        pool.put(44, new Campeon(44, 70, 234, 370, 700, .8, 70, 40, 2100, 1, 4, "Syndra")); // Línea 44
        pool.put(45, new Campeon(45, 70, 120, 370, 150, .8, 120, 180, 3200, 3, 4, "Galio")); // Línea 45
        pool.put(46, new Campeon(46, 70, 250, 370, 700, .8, 70, 40, 2100, 2, 4, "Neeko")); // Línea 46
        pool.put(47, new Campeon(47, 200, 0, 375, 900, 2, 70, 40, 2100, 0, 4, "Lucian")); // Línea 47
        pool.put(48, new Campeon(48, 70, 250, 370, 350, .8, 70, 40, 2100, 1, 4, "LeeBlanc")); // Línea 48
        pool.put(49, new Campeon(49, 70, 243, 370, 800, .8, 70, 40, 2100, 1, 4, "Cassiopeia")); // Línea 49
        pool.put(50, new Campeon(50, 150, 50, 385, 350, 1.5, 70, 54, 2700, 2, 4, "Yasuo")); // Línea 50
        pool.put(51, new Campeon(51, 70, 250, 370, 700, .8, 70, 40, 2100, 2, 4, "Hwei")); // Línea 51
        pool.put(52, new Campeon(52, 150, 100, 387, 350, 1.5, 83, 54, 2700, 2, 4, "Yone")); // Línea 52
        pool.put(53, new Campeon(53, 70, 263, 370, 700, .8, 70, 69, 2500, 1, 4, "Swain")); // Línea 53
        pool.put(54, new Campeon(54, 70, 262, 370, 700, .8, 70, 69, 2700, 1, 4, "Ryze")); // Línea 54
        pool.put(55, new Campeon(55, 70, 267, 370, 700, .8, 70, 40, 2100, 2, 4, "Seraphine")); // Línea 55
        pool.put(56, new Campeon(56, 70, 261, 370, 500, .8, 70, 69, 2700, 0, 4, "Kassadin")); // Línea 56

        pool.put(57, new Campeon(57, 200, 150, 380, 900, 2, 70, 40, 2000, 0, 4, "Kai'Sa")); // Línea 57
        pool.put(58, new Campeon(58, 200, 100, 380, 1200, 2, 70, 40, 3000, 0, 4, "Ezreal")); // Línea 58
        pool.put(59, new Campeon(59, 200, 50, 380, 900, 2, 70, 40, 2000, 2, 4, "Ashe")); // Línea 59
        pool.put(60, new Campeon(60, 250, 0, 380, 900, 2.5, 70, 40, 2500, 0, 4, "Kalista")); // Línea 60
        pool.put(61, new Campeon(61, 300, 0, 410,1000, 1.5,  70, 40, 2000, 0, 4, "Jhin")); // Línea 61
        pool.put(62, new Campeon(62, 200, 50, 380, 900, 2, 70, 70, 3000, 1, 4, "Xayah")); // Línea 62
        pool.put(63, new Campeon(63, 250, 50, 380, 900, 2, 70, 40, 2000, 1, 4, "Miss Fortune")); // Línea 63
        pool.put(64, new Campeon(64, 70, 400, 350, 1100, 1, 70, 40, 2000, 1, 4, "Ziggs")); // Línea 64
        pool.put(65, new Campeon(65, 200, 50, 370, 1000, 2, 70, 40, 2000, 1, 4, "Varus")); // Línea 65
        pool.put(66, new Campeon(66, 250, 50, 350, 1200, 2, 70, 40, 2000, 2, 4, "Caitlyn")); // Línea 66
        pool.put(67, new Campeon(67, 250, 50, 400, 1100, 2.5, 70, 40, 2000, 1, 4, "Jinx")); // Línea 67
        pool.put(68, new Campeon(68, 300, 0, 380, 900, 2, 70, 40, 2000, 1, 4, "Draven")); // Línea 68
        pool.put(69, new Campeon(69, 200, 150, 450, 900, 2, 70, 40, 2500, 0, 4, "Zeri")); // Línea 69
        pool.put(70, new Campeon(70, 250, 400, 400, 1100, 2.5, 70, 40, 2000, 0, 4, "Twitch")); // Línea 70
        
        pool.put(71, new Campeon(71, 70, 25, 380, 650, .8, 150, 150, 2500, 3, 4, "Rell")); // Línea 71
        pool.put(72, new Campeon(72, 70, 25, 420, 700, .8, 150, 150, 2000, 2, 4, "Rakan")); // Línea 72
        pool.put(73, new Campeon(73, 70, 25, 400, 700, .8, 150, 150, 2500, 3, 4, "Leona")); // Línea 73
        pool.put(74, new Campeon(74, 70, 100, 350, 700, .8, 70, 70, 3000, 2, 4, "Renata Glasc")); // Línea 74
        pool.put(75, new Campeon(75, 70, 25, 380, 700, .8, 150, 150, 2500, 3, 4, "Alistar")); // Línea 75
        pool.put(76, new Campeon(76, 70, 25, 380, 600, .8, 150, 150, 2500, 3, 4, "Braum")); // Línea 76
        pool.put(77, new Campeon(77, 70, 25, 380, 400, 1.2, 150, 150, 2500, 4, 4, "Poppy")); // Línea 77
        pool.put(78, new Campeon(78, 70, 26, 380, 700, .8, 150, 150, 2500, 4, 4, "Nautilus")); // Línea 78
        pool.put(79, new Campeon(79, 200, 0, 450, 1000, 1.3, 70, 70, 2000, 2, 4, "Pyke")); // Línea 79
        pool.put(80, new Campeon(80, 70, 200, 380, 900, .8, 70, 70, 2500, 2, 4, "Nami")); // Línea 80
        pool.put(81, new Campeon(81, 70, 350, 360, 1100, .8, 70, 70, 2000, 2, 4, "Lux")); // Línea 81
        pool.put(82, new Campeon(82, 70, 21, 380, 700, .8, 150, 150, 2500, 4, 4, "Taric")); // Línea 82
        pool.put(83, new Campeon(83, 70, 200, 350, 1000, 1, 70, 70, 2000, 2, 4, "Lulu")); // Línea 83
        pool.put(84, new Campeon(84, 70, 26, 380, 700, .8, 150, 150, 2500, 3, 4, "Tahm Kench")); // Línea 84
        pool.put(85, new Campeon(85, 200, 0, 360, 1100, 1.5, 70, 70, 2000, 1, 4, "Senna")); // Línea 85
        pool.put(86, new Campeon(86, 70, 200, 380, 1000, 1.5, 70, 70, 2000, 2, 4, "Bardo")); // Línea 86
        pool.put(87, new Campeon(87, 70, 26, 380, 700, .8, 150, 150, 2500, 3, 4, "Blitzcrank")); // Línea 87
        return pool;
    }

    public void calcFitness() {
        fitnessEngage = 0;
        fitnessPoke = 0;
        fitnessTeamFight = 0;
        for (Campeon campeon : campeones){
            fitnessEngage += ((campeon.AD + campeon.AP) * 40)/800 + ((campeon.MS)*20)/450 + ((campeon.CC)*40)/5;
            fitnessPoke += ((campeon.AD + campeon.AP) * 40)/800 + ((campeon.R)*40)/1200 + ((campeon.AS)*20)/2.5;
            fitnessTeamFight += ((campeon.AD + campeon.AP) * 30)/800 + ((campeon.AR + campeon.MR)*30)/600 + ((campeon.HP)*30)/4000 + ((campeon.AS)*10)/2.5;
        }
    }

    // Sobrescribir el método toString
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("\n");
        for (Campeon campeon : campeones) {
            resultado.append(campeon).append("\n");
        }
        int last = resultado.lastIndexOf("\n");
        if (last >= 0) { resultado.delete(last, resultado.length());}
        return resultado.toString();
    }

    public String aString(){
        String resultado = "";
        for (int i = 0; i<5 ; i++) {
            resultado += campeones[i].nombre + " ";
        }
        return resultado;
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
