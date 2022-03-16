package presentation;

import bll.RezervariBLL;
import bll.TerenuriBLL;
import bll.UsersBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Rezervari;
import model.Terenuri;
import model.Users;


import java.io.IOException;
import java.util.List;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    int locatieID = 0;

    @FXML
    private Button backAdmin;

    @FXML
    private Button backUser;

    @FXML
    private Button buttonBalcescuUser;

    @FXML
    private Button buttonGolazoUser;

    @FXML
    private Button buttonTerapiaUser;

    @FXML
    private TableView<Terenuri> terenuriTable;

    @FXML
    private DatePicker dataRezervare;

    @FXML
    private Label disponibilUser;

    @FXML
    private Label numeTerenUser;

    @FXML
    private Label orarUser;

    @FXML
    private Label pretTeren;

    @FXML
    private TableColumn<Users, String> passwordCol;

    @FXML
    private TableColumn<Users, String> usernameCol;

    @FXML
    private TableView<Users> usersTable;

    @FXML
    private Button buttonBalcescu;

    @FXML
    private Button buttonGolazo;

    @FXML
    private Button buttonTerapia;

    @FXML
    private Label disponibil;

    @FXML
    private Label numeTeren;

    @FXML
    private Label orar;

    @FXML
    private Label aplicatie;

    @FXML
    private Button guesst;

    @FXML
    private Button reincarcaButton;

    @FXML
    private TableColumn<Terenuri,String> disponibilCol;

    @FXML
    private TableColumn<Terenuri, Integer> idCol;

    @FXML
    private TableColumn<Terenuri,String> locatieCol;

    @FXML
    private TableColumn<Terenuri, Integer> oraDeschidereCol;

    @FXML
    private TableColumn<Terenuri, Integer> oraInchidereCol;

    @FXML
    private TableColumn<Terenuri, Integer> pretCol;

    @FXML
    private Label nume;

    @FXML
    private TextField oraFinal;

    @FXML
    private TextField oraStart;

    @FXML
    private Label eroareLabel;

    @FXML
    private Label confirmareLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signIn;

    @FXML
    private CheckBox echipament;

    @FXML
    private TextField usernameField;

    @FXML
    private CheckBox admin;

    @FXML
    private CheckBox guesstBox;

    @FXML
    private CheckBox userCheckBox;
    private ObservableList<Terenuri> data = FXCollections.observableArrayList();
    private ObservableList<Users> dataUsers = FXCollections.observableArrayList();

    @FXML
    private Button actualizeazaUser;

    @FXML
    private Button eliminaUser;

    @FXML
    private TextField oldUsernameAdminF;

    @FXML
    private TextField passwordAdminF;

    @FXML
    private TextField usernameAdminF;

    @FXML
    private TextField disponibilAdminF;

    @FXML
    private TextField idAdminF;

    @FXML
    private TextField locatieAdminF;

    @FXML
    private TextField oldIdAdminF;

    @FXML
    private TextField oraDescAdminF;

    @FXML
    private TextField oraInchAdminF;

    @FXML
    private TextField pretAdminF;

    @FXML
    private Button actualizareTeren;

    public void goLogIn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("../presentation/logIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changePage(ActionEvent event) throws IOException {
        if(guesstBox.isSelected()){
            root = FXMLLoader.load(getClass().getResource("../presentation/guestFrame.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        if(userCheckBox.isSelected()){
            UsersBLL usersBLL = new UsersBLL();
            List<Users> users = usersBLL.findAll();

            for(Users user : users){
                if(usernameField.getText().equals(user.getUsername()) && passwordField.getText().equals(user.getPassword())){
                    System.out.println("Succes!");

                    root = FXMLLoader.load(getClass().getResource("../presentation/userFrame.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }
                else{
                    eroareLabel.setText("Nu s-a introdus corect user-ul sau parola!");
                }
            }
        }

        if(admin.isSelected()){
            if(usernameField.getText().equals("admin") && passwordField.getText().equals("admin")){
                root = FXMLLoader.load(getClass().getResource("../presentation/adminFrame.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                eroareLabel.setText("Nu s-a introdus corect user-ul sau parola pentru admin!");
            }
        }
    }

    @FXML
    void register(ActionEvent event) {
        UsersBLL usersBLL = new UsersBLL();
        Users user = new Users(usernameField.getText(),passwordField.getText());

        Users userSucc = usersBLL.insert(user);

        if(userSucc != null){
            confirmareLabel.setText("Inregistrat!");
            eroareLabel.setText("");
        }
        else{
            eroareLabel.setText("Ceva nu a mers bine!");
            confirmareLabel.setText("");
        }

    }

    @FXML
    void checkBoxManage1(ActionEvent event) {
        if(userCheckBox.isSelected()){
            admin.setSelected(false);
            guesstBox.setSelected(false);
        }
    }

    @FXML
    void checkBoxManage2(ActionEvent event) {
        if(admin.isSelected()){
            userCheckBox.setSelected(false);
            guesstBox.setSelected(false);
        }
    }

    @FXML
    void checkBoxManage3(ActionEvent event) {
        if(guesstBox.isSelected()){
            userCheckBox.setSelected(false);
            admin.setSelected(false);
        }
    }

    @FXML
    void clickSelectBalcescu(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 3){
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTeren.setText(teren.getLocatie());
            orar.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibil.setText(sb.toString());
        }
    }

    @FXML
    void clickSelectGolazo(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 2){
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTeren.setText(teren.getLocatie());
            orar.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibil.setText(sb.toString());
        }
    }

    @FXML
    void clickSelectTerapia(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 1){
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTeren.setText(teren.getLocatie());
            orar.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibil.setText(sb.toString());
        }
    }

    @FXML
    void clickSelectBalcescuUser(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 3){
                locatieID = 3;
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTerenUser.setText(teren.getLocatie());
            orarUser.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibilUser.setText(sb.toString());
            pretTeren.setText(teren.getPret() + "");
        }
    }

    @FXML
    void clickSelectGolazoUser(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 2){
                locatieID = 2;
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTerenUser.setText(teren.getLocatie());
            orarUser.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibilUser.setText(sb.toString());
            pretTeren.setText(teren.getPret() + "");
        }
    }

    @FXML
    void clickSelectTerapiaUser(ActionEvent event) {

        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == 1){
                locatieID = 1;
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        if(teren.getDisponibil().equals("da")) {
            numeTerenUser.setText(teren.getLocatie());
            orarUser.setText("Orar : " + teren.getOraDeschidere() + ":00-" + teren.getOraInchidere() + ":00");

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupat: " + "\n");

            for (Rezervari rezervari1 : rezervari) {
                if (rezervari1.getLocatie().equals(teren.getLocatie())) {
                    sb.append(rezervari1.getOraStart() + ":00-" + rezervari1.getOraFinal() + ":00" + "\n");
                }
            }

            disponibilUser.setText(sb.toString());
            pretTeren.setText(teren.getPret() + "");
        }
    }

    @FXML
    void calculeaza(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == locatieID){
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        int pretTotal = 0;
        int oraFinalCalc = Integer.parseInt(oraFinal.getText());
        int oraStartCalc = Integer.parseInt(oraStart.getText());

        if(echipament.isSelected()){
            pretTotal = (oraFinalCalc - oraStartCalc) * teren.getPret();
            pretTotal = pretTotal + 5;
        }
        else {
            pretTotal = (oraFinalCalc - oraStartCalc) * teren.getPret();
        }

        pretTeren.setText(pretTotal + "");
    }

    @FXML
    void rezervareTeren(ActionEvent event) {
        Rezervari rezervare;

        TerenuriBLL terenuriBLL = new TerenuriBLL();
        RezervariBLL rezervariBLL = new RezervariBLL();

        List<Terenuri> terenuri = terenuriBLL.findAll();
        List<Rezervari> rezervari = rezervariBLL.findAll();

        Rezervari rezervariEfectuate = new Rezervari();
        Terenuri teren = new Terenuri();

        for(Terenuri terenuri1 : terenuri){
            if(terenuri1.getId() == locatieID){
                teren.setId(terenuri1.getId());
                teren.setLocatie(terenuri1.getLocatie());
                teren.setOraDeschidere(terenuri1.getOraDeschidere());
                teren.setOraInchidere(terenuri1.getOraInchidere());
                teren.setPret(terenuri1.getPret());
                teren.setDisponibil(terenuri1.getDisponibil());
            }
        }

        int pretTotal = 0;
        int oraFinalCalc = Integer.parseInt(oraFinal.getText());
        int oraStartCalc = Integer.parseInt(oraStart.getText());

        if(echipament.isSelected()){
            pretTotal = (oraFinalCalc - oraStartCalc) * teren.getPret();
            pretTotal = pretTotal + 5;
        }
        else {
            pretTotal = (oraFinalCalc - oraStartCalc) * teren.getPret();
        }

        if(echipament.isSelected()){
            rezervare = new Rezervari(dataRezervare.getValue().toString(),oraStartCalc,oraFinalCalc,teren.getLocatie(),
                    pretTotal,"da");
        }
        else {
            rezervare = new Rezervari(dataRezervare.getValue().toString(),oraStartCalc,oraFinalCalc,teren.getLocatie(),
                    pretTotal,"nu");
        }

        rezervariBLL.insert(rezervare);
    }

    @FXML
    void reincarca(ActionEvent event) {
        data = FXCollections.observableArrayList();
        dataUsers = FXCollections.observableArrayList();

        idCol.setCellValueFactory(new PropertyValueFactory<Terenuri, Integer>("id"));
        locatieCol.setCellValueFactory(new PropertyValueFactory<Terenuri, String>("locatie"));
        oraDeschidereCol.setCellValueFactory(new PropertyValueFactory<Terenuri, Integer>("oraDeschidere"));
        oraInchidereCol.setCellValueFactory(new PropertyValueFactory<Terenuri, Integer>("oraInchidere"));
        pretCol.setCellValueFactory(new PropertyValueFactory<Terenuri, Integer>("pret"));
        disponibilCol.setCellValueFactory(new PropertyValueFactory<Terenuri, String>("disponibil"));

        usernameCol.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));


        TerenuriBLL terenuriBLL = new TerenuriBLL();
        List<Terenuri> terenuri = terenuriBLL.findAll();

        for(Terenuri teren : terenuri){
            data.add(teren);
        }

        terenuriTable.setItems(data);


        UsersBLL usersBLL = new UsersBLL();
        List<Users> users = usersBLL.findAll();

        for(Users user : users){
            dataUsers.add(user);
        }
        usersTable.setItems(dataUsers);
    }

    @FXML
    void actualizareUser(ActionEvent event) {
        UsersBLL usersBLL = new UsersBLL();

        usersBLL.updateUser(oldUsernameAdminF.getText(),new Users(usernameAdminF.getText(),passwordAdminF.getText()));
    }

    @FXML
    void eliminareUser(ActionEvent event) {
        UsersBLL usersBLL = new UsersBLL();

        usersBLL.deleteUser(oldUsernameAdminF.getText());
    }

    @FXML
    void actualizeazaTeren(ActionEvent event) {
        TerenuriBLL terenuriBLL = new TerenuriBLL();

        terenuriBLL.updateTeren(Integer.parseInt(oldIdAdminF.getText()), new Terenuri(Integer.parseInt(idAdminF.getText()),
                locatieAdminF.getText(), Integer.parseInt(oraDescAdminF.getText()),Integer.parseInt(oraInchAdminF.getText()),
                Integer.parseInt(pretAdminF.getText()),disponibilAdminF.getText()));
    }
}
