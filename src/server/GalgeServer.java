/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Naming;

/**
 *
 * @author Janus
 */
public class GalgeServer {
        //DONE
    	public static void main(String[] arg) throws Exception
	{
		// Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
		java.rmi.registry.LocateRegistry.createRegistry(1234); // start i server-JVM

		GalgelegInt k = new GalgelegImpl();
		Naming.rebind("rmi://localhost:1234/Galgeleg", k);
		System.out.println("Gelelegtjeneste registreret.");
	}
    
}
