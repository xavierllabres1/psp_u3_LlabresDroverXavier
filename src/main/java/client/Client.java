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
    static int port = 5555;

    static ObjectOutputStream obOut;
    static ObjectInputStream obIn;


    public static TxRxData tx(TxRxData paquet){

        TxRxData resposta;

        try {

            // Declarar el canal i fluxe
            clientSocket = new Socket(ip, port);
            obOut = new ObjectOutputStream(clientSocket.getOutputStream());
            obIn = new ObjectInputStream(clientSocket.getInputStream());

            // Enviamnt
            obOut.writeObject(paquet);

            // Resposta
            resposta = (TxRxData) obIn.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Tancar fluxes sempre al acabar
        finally {
            try {
                stopConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resposta;
    }


    // Metodes
    public TxRxData startTransmission(TxRxData paquet) {

        TxRxData resposta;

        // Inciciar connexió;
        try {
            startConnection(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {


            resposta = sendMessage(paquet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stopConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return resposta;

    }

    // Declarar i inicialitzar paràmetres i fluxes
    private void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        obIn = new ObjectInputStream(clientSocket.getInputStream());
        obOut = new ObjectOutputStream(clientSocket.getOutputStream());

    }

    // Enviament de missatge
    private TxRxData sendMessage(TxRxData paquet) throws IOException, ClassNotFoundException {
        obOut.writeObject(paquet);

        return (TxRxData) obIn.readObject();
    }


    private static void stopConnection() throws IOException {
        if (obIn == null){obIn.close();}
        if (obOut == null){obOut.close();}
        if (clientSocket == null){clientSocket.close();}

    }



}
