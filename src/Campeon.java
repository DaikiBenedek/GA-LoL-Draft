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
    double AS;
    int AR;
    int MR;
    int HP;
    int CC;
    String nombre;

    public Campeon(int id, int AD, int AP, int MS, int R, double AS, int AR, int MR, int HP, int CC, String nombre){
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
        this.nombre = nombre;
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

    public double getAS() {
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

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder(String.format(
            "%s, ID %d -> AD: %d, AP: %d, MS: %d, R: %d, AS: %f, AR: %d, MR: %d, HP: %d, CC: %d",
            nombre, id, AD, AP, MS, R, AS, AR, MR, HP, CC
        ));
        return resultado.toString();
    }
}
