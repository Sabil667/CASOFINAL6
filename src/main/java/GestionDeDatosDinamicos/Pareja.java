package GestionDeDatosDinamicos;
public class Pareja {
    private int key;
    private double value;

    public Pareja(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(double value) {
        this.value = value;
    }
}