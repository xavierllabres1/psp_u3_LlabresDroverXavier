package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    // Per crear, li passam l'objecte User
    public TxRxData create(User user){

        // Variables
        // Gson gson = new Gson();     // Es modifica per emprar el pretty
        Gson gson = new GsonBuilder().setPrettyPrinting().create();     // gson per interactuar entre objectes - Json
        TxRxData resposta;          // Objecte de resposta
        BufferedReader br = null;   // buffer de lectura
        int flg = -1;               // flag indicador de la recerca
        ArrayList<User> usuaris = new ArrayList<User>();    // Llista de usuaris

        // Si existeix el fitxer
        if (file.exists()){
            System.out.println("[INFO] Existeix el fitxer");

            try {
                // Lectura al buffer
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
                // Aqui no hi hauria d'arribar, ja que feim la comprovació abans
            }

            // Obtenim la llista d'objectes usuaris
            usuaris = new Gson().fromJson(br, new TypeToken<ArrayList<User>>() {}.getType());
            System.out.println("[INFO] Es recuperen " + usuaris.size() + " registre de la BBDD");

            // Verificam que no existeixi
            for (int i = 0; i< usuaris.size(); i++){
                if (usuaris.get(i).getId() == user.getId()){
                    // Recuperam la posició en cas de que existeixi
                    flg = i;
                }
            }

            // Si no es troben similituts, s'inclou el registre
            if (flg == -1){
                usuaris.add(user);
                try (Writer writer = new FileWriter(file)) {
                    gson.toJson(usuaris, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resposta = new TxRxData("Inserció Correcte");
                System.out.println("[ OK ] Procés Insert");

            } else{
                //Si s'han trobat similituts
                resposta = new TxRxData("No es procedeix a al inserció ja que existeix l'ID a la Base de Dades");
                System.out.println("[ NG ] Procés Insert - ID existent");
            }

        } else {    // El fitxer no existeix i es crea.
            System.out.println("[INFO] NO existeix el fitxer");
            usuaris.add(user);
            String json = gson.toJson(usuaris);

            try (Writer writer = new FileWriter(file)) {
                gson.toJson(usuaris, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            resposta = new TxRxData("Inserció Correcte");
            System.out.println("[ OK ] generar fitxer");
            System.out.println("[ OK ] Procés Insert");
        }
        return resposta;
    }

    public TxRxData select(User user){

        // Variables
        Gson gson = new Gson();     // gson per interactuar entre objectes - Json
        TxRxData resposta;          // Objecte de resposta
        BufferedReader br = null;   // buffer de lectura
        int flg = -1;               // flag indicador de la recerca
        ArrayList<User> usuaris = new ArrayList<User>();    // Llista de usuaris

        // Si existeix el fitxer
        if (file.exists()){
            System.out.println("[INFO] Existeix el fitxer");

            try {
                // Lectura al buffer
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
                // Aqui no hi hauria d'arribar, ja que feim la comprovació abans
            }

            // Obtenim la llista d'objectes usuaris
            usuaris = new Gson().fromJson(br, new TypeToken<ArrayList<User>>() {}.getType());
            System.out.println("[INFO] Es recuperen " + usuaris.size() + " registre de la BBDD");

            // Verificam que existeixi
            for (int i = 0; i< usuaris.size(); i++){
                if (usuaris.get(i).getId() == user.getId()){
                    // Recuperam la posició en cas de que existeixi
                    flg = i;
                }
            }

            // Si no es troben similituts,
            if (flg == -1){  // Si no es troben similituts,

                resposta = new TxRxData("No s'ha trobat l'identificador");
                System.out.println("[ NG ] ID no trobat");

            }
            //Si s'han trobat similituts
            else {

                // Es retorna l'objecte User
                resposta = new TxRxData( 1, usuaris.get(flg));
                System.out.println("[ OK ] ID trobat");
            }

        }
        // El fitxer no existeix
        else {
            System.out.println("[INFO] NO existeix el fitxer");

            resposta = new TxRxData("El fitxer no existeix");
            System.out.println("[ NG ] No es produceix la cerca");
        }
        return resposta;
    }


    // Metode per eliminar usuaris
    public TxRxData delete(User user){

        // Variables
        Gson gson = new Gson();     // gson per interactuar entre objectes - Json
        TxRxData resposta;          // Objecte de resposta
        BufferedReader br = null;   // buffer de lectura
        int flg = -1;               // flag indicador de la recerca
        ArrayList<User> usuaris = new ArrayList<User>();    // Llista de usuaris

        // Si existeix el fitxer
        if (file.exists()){
            System.out.println("[INFO] Existeix el fitxer");

            try {
                // Lectura al buffer
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
                // Aqui no hi hauria d'arribar, ja que feim la comprovació abans
            }

            // Obtenim la llista d'objectes usuaris
            usuaris = new Gson().fromJson(br, new TypeToken<ArrayList<User>>() {}.getType());
            System.out.println("[INFO] Es recuperen " + usuaris.size() + " registre de la BBDD");

            // Verificam que existeixi
            for (int i = 0; i< usuaris.size(); i++){
                if (usuaris.get(i).getId() == user.getId()){
                    // Recuperam la posició en cas de que existeixi
                    flg = i;
                }
            }

            // Si no es troben similituts,
            if (flg == -1){  // Si no es troben similituts,

                resposta = new TxRxData("No s'ha trobat l'identificador");
                System.out.println("[ NG ] ID no trobat");

            }
            //Si s'han trobat similituts
            else {

                // S'elimina l'usuari de la bbdd
                usuaris.remove(flg);
                // Reescriptura del fitxer amb la nova llista
                try (Writer writer = new FileWriter(file)) {
                    gson.toJson(usuaris, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resposta = new TxRxData("S'ha esborrat l'usuari");
                System.out.println("[ OK ] ID Esborrat");

            }

        }
        // El fitxer no existeix
        else {
            System.out.println("[INFO] NO existeix el fitxer");

            resposta = new TxRxData("El fitxer no existeix");
            System.out.println("[ NG ] No es produceix la cerca");
        }
        return resposta;
    }

}

