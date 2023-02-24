package client;

import object.TxRxData;

import java.io.*;
import java.net.Socket;

public class Client {

    // Atributs
    private static Socket clientSocket;
//    private PrintWriter out;
//    private BufferedReader in;
//    private InputStreamReader in2;
    static String ip = "127.0.0.1";
    static int port = 9595;

    static ObjectOutputStream obOut;
    static ObjectInputStream obIn;


    public static TxRxData tx(TxRxData paquet) throws ClassNotFoundException, IOException {

        TxRxData resposta;

        Socket socket = new Socket(ip, port);
        obOut = new ObjectOutputStream(socket.getOutputStream());
        obIn = new ObjectInputStream(socket.getInputStream());

        obOut.writeObject(paquet);
        obOut.flush();
        //obOut.close();

        // Rebre resposta
        resposta = (TxRxData) obIn.readObject();

        System.out.println("Received response: " + resposta.getResposta());

        // Aturar fluxes
        //stopConnection();
        return resposta;
    }

    private static void stopConnection() throws IOException {
        if (obIn == null){obIn.close();}
        if (obOut == null){obOut.close();}
        if (clientSocket == null){clientSocket.close();}

    }



}
