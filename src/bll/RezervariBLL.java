package bll;

import dao.RezervariDAO;
import model.Rezervari;
import model.Users;

import java.util.List;

public class RezervariBLL {
    private RezervariDAO rezervariDAO;

    public RezervariBLL() {
        rezervariDAO = new RezervariDAO();
    }

    public List<Rezervari> findAll(){
        List<Rezervari> rezervari = rezervariDAO.findAll();

        if(rezervari != null){
            return rezervari;
        }
        else{
            return null;
        }
    }

    public Rezervari insert(Rezervari rezervare){
        return rezervariDAO.insert(rezervare);
    }
}
