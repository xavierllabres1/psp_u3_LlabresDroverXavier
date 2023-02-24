package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import object.TxRxData;
import object.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

// Aquesta classe s'encarrega de realitzar les operacions de Create, Read i Delete
public class CRD {

    // Atributs
    private File file;

    public CRD() {
        file = new File("bbdd.txt");
    }

    public TxRxData create(User user){

        // Variables
        // Objecte per interactuar entre objectes - Json
        Gson gson = new Gson();
        TxRxData resposta;
        BufferedReader br = null;
        int flg = 0;
        ArrayList<User> usuaris = new ArrayList<User>();

        // Si existeix el fitxer, es cerca si existex
        if (file.exists()){

            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            // Obtenim la llista d'objectes usuaris
            usuaris = new Gson().fromJson(br, new TypeToken<ArrayList<User>>() {}.getType());

            // Comprovam que no existeixi
            Iterator<User> iter = usuaris.iterator();

            while (iter.hasNext()){
                if (iter.next().getId() == user.getId()){
                    flg = 1;    // Indicam que hi ha un ID igual
                }
            }

            if (flg == 0){  // Si no es troben similituts, s'inclou el registre
                usuaris.add(user);
                System.out.println(usuaris);
                resposta = new TxRxData("Inserció Correcte");
                System.out.println("[OK: insert]");

            } else{
                //Si s'han trobat similituts
                resposta = new TxRxData("No es procedeix a al inserció ja que existeix l'ID a la Base de Dades");
                System.out.println("[NG: insert - ID existent]");
            }

        } else {    // El fitxer no existeix i es crea.
            usuaris.add(user);
            String json = gson.toJson(usuaris);

            try (Writer writer = new FileWriter(file)) {
                gson.toJson(usuaris, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            resposta = new TxRxData("Inserció Correcte");
            System.out.println("[OK: generar fitxer]");
            System.out.println("[OK: insert]");
        }
        return resposta;
    }

    public TxRxData select(User user){

        // Variables
        // Objecte per interactuar entre objectes - Json
        Gson gson = new Gson();
        TxRxData resposta = null;
        BufferedReader br = null;
        int flg = 0;
        ArrayList<User> usuaris = new ArrayList<User>();

        // Si existeix el fitxer, es cerca si existex
        if (file.exists()){

            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            // Obtenim la llista d'objectes usuaris
            usuaris = new Gson().fromJson(br, new TypeToken<ArrayList<User>>() {}.getType());

            // Comprovam si existeixi
            Iterator<User> iter = usuaris.iterator();

            while (iter.hasNext()){
                if (iter.next().getId() == user.getId()){

                    flg = 1;    // Indicam que hi ha un ID igual
                    System.out.println("[OK: ID Coincident]");
                    resposta = new TxRxData("");
                    resposta.setUser(new User(iter.next().getId(),iter.next().getNom(),iter.next().getCognom()));
                }
            }

            if (flg == 0){  // Si no es troben similituts,
                resposta = new TxRxData("No hi registres amb aquest ID");
                System.out.println("[NG: no ID]");
            }

        } else {    // El fitxer no existeix i es crea.

            resposta = new TxRxData("Fitxer Inexistent");
            System.out.println("[NG: No Fitxer]");
        }
        return resposta;
    }

}

