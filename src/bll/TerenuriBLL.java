package bll;

import dao.TerenuriDAO;
import model.Terenuri;

import java.util.List;

public class TerenuriBLL {
    private TerenuriDAO terenuriDAO;

    public TerenuriBLL() {
        terenuriDAO = new TerenuriDAO();
    }

    public List<Terenuri> findAll(){
        List<Terenuri> terenuri = terenuriDAO.findAll();

        if(terenuri != null){
            return terenuri;
        }
        else{
            return null;
        }
    }

    public Terenuri updateTeren(int oldId, Terenuri teren){
        return terenuriDAO.update(oldId,teren);
    }
}
