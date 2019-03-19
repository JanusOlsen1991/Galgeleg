/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Janus
 */
public interface GalgelegInt extends java.rmi.Remote {
    
    
    public ArrayList<String> getBrugteBogstaver() throws java.rmi.RemoteException;
    public boolean loginSuccess(String username, String password) throws java.rmi.RemoteException;
  public String getSynligtOrd(String username) throws java.rmi.RemoteException;
  public String startGame(String username, String Password) throws java.rmi.RemoteException;
  public int getWrongGuesses(String username) throws java.rmi.RemoteException;
  public String getActualWord(String username) throws java.rmi.RemoteException;
  public void setGame(String username) throws java.rmi.RemoteException;

  public String getOrdet(String username, String pass) throws java.rmi.RemoteException;

  public int getAntalForkerteBogstaver(String username, String pass) throws java.rmi.RemoteException;

  public boolean erSidsteBogstavKorrekt(String username, String pass) throws java.rmi.RemoteException;

  public boolean erSpilletVundet(String username, String pass) throws java.rmi.RemoteException;

  public boolean erSpilletTabt(String username, String pass) throws java.rmi.RemoteException;

  public boolean erSpilletSlut(String username, String pass) throws java.rmi.RemoteException;


  public void nulstil(String username, String pass) throws java.rmi.RemoteException;

  public String guess(String username, String bogstav) throws java.rmi.RemoteException;

  public void logStatus(String username, String pass) throws java.rmi.RemoteException;


  //public static String hentUrl(String url) throws IOException;
  


  public void hentOrdFraDr() throws Exception;

    public String getVisibleWord(String brugernavn)throws java.rmi.RemoteException;
    
    
}
