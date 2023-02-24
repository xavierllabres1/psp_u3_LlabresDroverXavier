package server;

import object.TxRxData;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerFil extends Thread {

    // Atributs
    private Socket socket;
    private ObjectInputStream obIn;
    private ObjectOutputStream obOut;
    private TxRxData paquet;

    // Constructor
    public ServerFil(Socket socket, ObjectInputStream obIn, ObjectOutputStream obOut) {
        this.socket = socket;
        this.obIn = obIn;
        this.obOut = obOut;
    }

    public void run() {
        System.out.println("[INFO] Fil Generat");

        // Variables
        TxRxData resposta;
        boolean bucle = true;

        try {
            while (bucle) {
                try {
                    // Captura del paquet
                    paquet = (TxRxData) obIn.readObject();
                    System.out.println("[INFO] Objecte recuperat");

                    // Processar la petició del client
                    resposta = controlQuerys(paquet);
                    obOut.writeObject(resposta);
                    obOut.flush();
                    System.out.println("[INFO] Resposta enviada\n");

                } catch ( EOFException e) {
                    //System.out.println("Fluxe dades acabat");
                    bucle = false; // Sortir del bucle
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            // Tancar la conexió amb el client
            try {
                if (obOut != null) {obOut.close();}
                if (obIn != null) {obIn.close();}
                if (socket != null) {socket.close();}
                System.out.println("[INFO] Tancament de fluxes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TxRxData controlQuerys(TxRxData paquet){
        TxRxData resposta;
        CRD crd = new CRD();

        switch (paquet.getQuery()){
            case 1:
                System.out.println("[INFO] Query Insert");
                resposta = crd.create(paquet.getUser());
                break;
            case 2:
                System.out.println("[INFO] Query Select");
                resposta = crd.select(paquet.getUser());

                break;
            case 3:
                System.out.println("[INFO] Query Delete");
                resposta = crd.delete(paquet.getUser());
                break;
            default:
                System.out.println("[INFO] Bad Query!");
                resposta = new TxRxData("valor incorrecte");
        }

        return resposta;
    }
}
