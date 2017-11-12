package reception;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Classe de reception d'un datagramme UDP
 * @author djebien
 */
public class ReceptionUDP {

    //Taille d'un datagramme en octet
    public static final int TAILLE_DATAGRAMME = 1024;
    //Socket pour la connexion reseau
    private DatagramSocket ds;

    //Accesseur
    public DatagramSocket getSocket(){
	return this.ds;
    }

    //Constructeur d'un datagrammeUDP avec le datagramSocket
    public ReceptionUDP(int portEcoute){
	try{
	this.ds = new DatagramSocket(portEcoute);
	}catch(SocketException s){
	    s.printStackTrace();
	}
    }

    //Methode d'ecoute sur le port avec ecriture des message sur la sortie standard
    public void ecouter(){
	//Buffer pour stocker le message
	byte[] buffer = new byte[ReceptionUDP.TAILLE_DATAGRAMME];
	//Creation d'un packet datagramme pour recevoir les données
        //Ces données seront stockées dans le buffer de taille TAILLE_DATAGRAMME
        DatagramPacket packet = new DatagramPacket(buffer,ReceptionUDP.TAILLE_DATAGRAMME);
        //Reception du packet sur la socket du datagramme
	try{
	    this.getSocket().receive(packet);
	}catch(IOException i){
	    i.printStackTrace();
	}
	//On stocke le message
	String message = new String(packet.getData());
	
        //Ecriture du message receptionner sur la sortie standard
	System.out.println("message : "+message);
    }

    public static void main(String[] args){
	if (args.length == 1){
	    try{
		//On recupere le port choisi par l'utilisateur
		int p = Integer.parseInt(args[0]);
		System.out.println("Debut d'ecoute sur le port " + p);
		//On instancie notre recepteur de datagramme UDP
		ReceptionUDP monRecepteurUDP = new ReceptionUDP(p);
		//On ecoute tant que le message recu est different de "exit"
		while(true){
		    monRecepteurUDP.ecouter();
		}
		//monRecepteurUDP.getSocket().close();
	    }catch(NumberFormatException n){
		System.out.println("entrer un entier pour le port d'ecoute svp");
	    }

	}else System.out.println("Usage : $ java exercice1.java.ReceptionUDP <portEcoute> ");
    }

}
