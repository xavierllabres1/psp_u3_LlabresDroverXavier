package client;

import java.io.IOException;

// Imports
import object.TxRxData;
import object.User;

public class Programa {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Iniciar aplicació i solicitar informació
        // Variables
        int valorMenu = -1;

        // Aquest menú indica el sector del programa
        while (valorMenu != 0) {

            System.out.println("\n   Menú Principal\n"
                    + "1. Insertar Usuari.\n"
                    + "2. Consultar Usuari.\n"
                    + "3. Esborrar Usuari.\n"
                    + "0. Sortir\n");

            // Cridar a la funció per recuperar el valor del menú
            valorMenu = Manager.introduirMenu(0, 3, 2);

            // Selector de opcions
            switch (valorMenu) {
                case 1 -> insert();
                case 2 -> select();
                case 3 -> delete();
                case 0 -> System.out.println("Sortint del programa...");
                default -> {
                }
            }
        }

    }

    private static void insert() throws IOException, ClassNotFoundException {

        // Variables
        User user = emplenarDades();
        // User user = new User(1,"Pere", "Mateu");

        // Preparar paquet d'enviament
        TxRxData paquet = new TxRxData(1, user);

        // Cridar al enviament
        TxRxData resPaquet = Client.tx(paquet);

        System.out.println(resPaquet.getResposta());

    }

    private static void select() throws IOException, ClassNotFoundException {

        System.out.println("Introduir id a cercar:");
        User user = new User (Manager.introduirMenu(1,1000,2));  // Es limita a 1000 id

        TxRxData paquet = new TxRxData(2, user);
        // Cridar al enviament
        TxRxData resPaquet = Client.tx(paquet);

        if (resPaquet.getQuery() == 1){
            System.out.println("ID:     " + resPaquet.getUser().getId());
            System.out.println("Nom:    " + resPaquet.getUser().getNom());
            System.out.println("Cognom: " + resPaquet.getUser().getCognom());
        } else {
            System.out.println(resPaquet.getResposta());
        }


    }
    private static void delete() throws IOException, ClassNotFoundException {

        System.out.println("Introduir id a esborrar:");
        User user = new User (Manager.introduirMenu(1,1000,2));  // Es limita a 1000 id

        TxRxData paquet = new TxRxData(3, user);
        // Cridar al enviament
        TxRxData resPaquet = Client.tx(paquet);
        System.out.println(resPaquet.getResposta());
    }

    // Metode per introduir dades a un objecte User
    private static User emplenarDades(){

        User user = new User();

        // Emplenar usuari
        System.out.println("Introduir id:");
        user.setId(Manager.introduirMenu(1,1000,2));  // Es limita a 1000 id
        System.out.println("Introduir nom:");
        user.setNom(Manager.introduirRespostaStr(2,false));
        System.out.println("Introduir Cognom:");
        user.setCognom(Manager.introduirRespostaStr(2,false));

        return user;
    }



}
