/**
 * @author Tokuhara Daiki
 */

// Clase Individuo
public class Campeon{
    int id;
    int AD;
    int AP;
    int MS;
    int R;
    int AS;
    int AR;
    int MR;
    int HP;
    int CC;
    int[] counter;

    public Campeon(int id, int AD, int AP, int MS, int R, int AS, int AR, int MR, int HP, int CC, int CounterSize){
        this.id = id;
        this.AD = AD;
        this.AP = AP;
        this.MS = MS;
        this.R = R;
        this.AS = AS;
        this.AR = AR;
        this.MR = MR;
        this.HP = HP;
        this.CC = CC;
        this.counter = new int[CounterSize];
    }

    // Getters para las estadísticas
    public int getId() {
        return id;
    }

    public int getAD() {
        return AD;
    }

    public int getAP() {
        return AP;
    }

    public int getMS() {
        return MS;
    }

    public int getR() {
        return R;
    }

    public int getAS() {
        return AS;
    }

    public int getAR() {
        return AR;
    }

    public int getMR() {
        return MR;
    }

    public int getHP() {
        return HP;
    }

    public int getCC() {
        return CC;
    }

    public int[] getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder(String.format(
            "Gen %d -> AD: %d, AP: %d, MS: %d, R: %d, AS: %d, AR: %d, MR: %d, HP: %d, CC: %d, Counter: [",
            id, AD, AP, MS, R, AS, AR, MR, HP, CC
        ));
        for (int i = 0; i < counter.length; i++) {
            resultado.append(counter[i]);
            if (i < counter.length - 1) {
                resultado.append(", ");
            }
        }
        resultado.append("]");
        return resultado.toString();
    }
}   