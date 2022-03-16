package model;

public class Rezervari {
    private String data;
    private int oraStart;
    private int oraFinal;
    private String locatie;
    private int pret;
    private String echipament;

    public Rezervari() { }

    public Rezervari(String data, int oraStart, int oraFinal, String locatie, int pret, String echipament) {
        this.data = data;
        this.oraStart = oraStart;
        this.oraFinal = oraFinal;
        this.locatie = locatie;
        this.pret = pret;
        this.echipament = echipament;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getOraStart() {
        return oraStart;
    }

    public void setOraStart(int oraStart) {
        this.oraStart = oraStart;
    }

    public int getOraFinal() {
        return oraFinal;
    }

    public void setOraFinal(int oraFinal) {
        this.oraFinal = oraFinal;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getEchipament() {
        return echipament;
    }

    public void setEchipament(String echipament) {
        this.echipament = echipament;
    }

    @Override
    public String toString() {
        return "Rezervari{" +
                ", data='" + data + '\'' +
                ", oraStart=" + oraStart +
                ", oraFinal=" + oraFinal +
                ", locatie='" + locatie + '\'' +
                ", pret=" + pret +
                ", echipament='" + echipament + '\'' +
                '}';
    }
}
