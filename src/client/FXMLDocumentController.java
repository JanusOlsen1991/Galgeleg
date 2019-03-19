/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import server.GalgelegInt;

/**
 *
 * @author Janus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button loginButton;
    
    @FXML
    private TextField username1, password1;
    
    @FXML
    private AnchorPane AnchorPane, Login_creds, tastatur;
    
    private GalgelegInt obj;
    private String brugernavn;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
                String s = ((Button)event.getSource()).getText().toLowerCase();

        try {
            
            
        if(!obj.getActualWord(brugernavn).contains(s))
        ((Button)event.getSource()).setStyle("-fx-background-color: Red");
        else
        ((Button)event.getSource()).setStyle("-fx-background-color: Green");            
        
        label.setText("Gæt ordet: " + obj.guess(brugernavn, s) + "\nDu har nu " + (6-obj.getWrongGuesses(brugernavn)) + " forkerte gæt tilbage.");
            if(obj.getWrongGuesses(brugernavn)>=6)
                label.setText("Du har tabt. Ordet du skulle gætte var: " + obj.getActualWord(brugernavn) +  ".\nTryk på XXX for at spille igen.");
        
            if(!obj.getVisibleWord(brugernavn).contains("*"))
                label.setText("Tillykke. Du har vundet omgangen!");
                //TODO Spørg om der skal spilles igen?
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
        obj = (GalgelegInt) Naming.lookup("rmi://localhost:1234/Galgeleg");
        } catch (Exception e){
            System.out.println("forbindelse til serverobjekt er ikke tilgængeligt på nuværende tidspunkt");
        }

    }

    @FXML
    private void attemptLogin(ActionEvent event) throws Exception {
        if(obj.loginSuccess(username1.getText(), password1.getText())){ //TODO ændr tilbage når der ikke skal authenticates mere
            obj.startGame(username1.getText(), password1.getText());
            brugernavn = username1.getText();
            System.out.println("FXMLDocController Kommer her");
        Login_creds.setVisible(false);
        tastatur.setVisible(true);
        label.setVisible(true);
            System.out.println("Brugernavn er" + brugernavn);
        label.setText(/*label.getText() +*/ obj.getVisibleWord(brugernavn) ); //TODO kan ikke hente visible word? - Siger den er null
        brugernavn = username1.getText();
                
        }
        else
            System.out.println("Dine loginoplysninger er forkerte");
        
        
    }
    
    
}
