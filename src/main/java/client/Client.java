package client;

import object.TxRxData;

import java.io.*;
import java.net.Socket;

public class Client {

    // Atributs
    private static Socket clientSocket;

    static String ip = "127.0.0.1";
    static int port = 9595;

    static ObjectOutputStream obOut;
    static ObjectInputStream obIn;


    public static TxRxData tx(TxRxData paquet) throws ClassNotFoundException, IOException {

        // Objecte resposta
        TxRxData resposta;

        // Declaració de Socket i fluxes
        Socket socket = new Socket(ip, port);
        obOut = new ObjectOutputStream(socket.getOutputStream());
        obIn = new ObjectInputStream(socket.getInputStream());

        // Enviament de dades
        obOut.writeObject(paquet);
        obOut.flush();

        // Rebre resposta
        resposta = (TxRxData) obIn.readObject();
        return resposta;
    }

    private static void stopConnection() throws IOException {
        if (obIn == null){obIn.close();}
        if (obOut == null){obOut.close();}
        if (clientSocket == null){clientSocket.close();}

    }



}
