package model;

public class Terenuri {
    private int id;
    private String locatie;
    private int oraDeschidere;
    private int oraInchidere;
    private int pret;
    private String disponibil;

    public Terenuri() { }

    public Terenuri(int id, String locatie, int oraDeschidere, int oraInchidere, int pret, String disponibil) {
        this.id = id;
        this.locatie = locatie;
        this.oraDeschidere = oraDeschidere;
        this.oraInchidere = oraInchidere;
        this.pret = pret;
        this.disponibil = disponibil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getOraDeschidere() {
        return oraDeschidere;
    }

    public void setOraDeschidere(int oraDeschidere) {
        this.oraDeschidere = oraDeschidere;
    }

    public int getOraInchidere() {
        return oraInchidere;
    }

    public void setOraInchidere(int oraInchidere) {
        this.oraInchidere = oraInchidere;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getDisponibil() {
        return disponibil;
    }

    public void setDisponibil(String disponibil) {
        this.disponibil = disponibil;
    }

    @Override
    public String toString() {
        return "Terenuri{" +
                "id=" + id +
                ", locatie='" + locatie + '\'' +
                ", oraDeschidere=" + oraDeschidere +
                ", oraInchidere=" + oraInchidere +
                ", pret=" + pret +
                '}';
    }
}
