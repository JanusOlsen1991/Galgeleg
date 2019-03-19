/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.GalgelegInt;
import brugerautorisation.transport.rmi.Brugeradmin;
import java.io.Serializable;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Janus
 */

public class GalgelegImpl extends UnicastRemoteObject implements GalgelegInt, Serializable{
    private HashMap<String, Galgelogik> allGames = new HashMap<String, Galgelogik>();
    
    public GalgelegImpl() throws Exception{
   
    }
    public void setGame(String username){
                allGames.put(username, new Galgelogik());
    }
        public GalgelegImpl(String username) throws Exception{
        allGames.put(username, new Galgelogik());
    
    }
    
        @Override
    public String startGame(String username, String password)throws java.rmi.RemoteException{
        String displayedMessage;
        if(loginSuccess(username, password)){
            setGame(username);
                        System.out.println("Ordet der skal gættes er: " + allGames.get(username).getOrdet());
                        System.out.println("Det synlige ord er : " + allGames.get(username).getSynligtOrd());

            //allGames.get(username).nulstil();            
            displayedMessage = "Login successfull, starting new game" ;
        }
        else
            displayedMessage = "Login error. Check if the username and password you've typed are right.";
        
            
            System.out.println(displayedMessage );
            return displayedMessage;
    }
    @Override
    public boolean loginSuccess(String username, String password){
        
        Brugeradmin b;
        
        try{
        b = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        b.hentBruger(username,password);
            System.out.println("Username and Password accepted.");
            return true;
        }catch(Exception e){
            return false;
        }
        //return true;
    }
    
        public String getWord(String username, String password) throws java.rmi.RemoteException {
        if (loginSuccess(username, password)) {
            return allGames.get(username).getOrdet();
        }
        return null;
        }
        
            public String guess(String username, String s) throws RemoteException {
        
                   
            if (allGames.containsKey(username)) {
                allGames.get(username).logStatus();
                allGames.get(username).gætBogstav(s);
                return allGames.get(username).getSynligtOrd();
            } else {
                allGames.put(username, new Galgelogik());
                allGames.get(username).gætBogstav(s);
                return allGames.get(username).getSynligtOrd();
            }
        }

    @Override
    public ArrayList<String> getBrugteBogstaver() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSynligtOrd(String username) throws RemoteException {
        return allGames.get(username).getSynligtOrd();
    }

    @Override
    public String getOrdet(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAntalForkerteBogstaver(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSidsteBogstavKorrekt(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletVundet(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletTabt(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletSlut(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nulstil(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logStatus(String username, String pass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hentOrdFraDr() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWrongGuesses(String username) throws RemoteException {
return allGames.get(username).getAntalForkerteBogstaver();
        }

    @Override
    public String getActualWord(String username) throws RemoteException {
return allGames.get(username).getOrdet();
    }

    @Override
    public String getVisibleWord(String username) {
                System.out.println("Størrelsen på HashMap " + allGames.size());

        System.out.println("Mit nuværende spil " + allGames.get(username).getOrdet());
return allGames.get(username).getSynligtOrd();
    }
    
   
}
