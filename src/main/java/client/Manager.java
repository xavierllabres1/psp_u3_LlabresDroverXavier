package client;

import java.util.Scanner;

// Aquesta classe s'usa per la intereacció entre usuari i aplicació
public class Manager {

    // Metode per introduir nombres (consola) a dins un menú. // NOMES NUMERICS //
    public static int introduirMenu(int min, int max, int intentsAd) {

        // Variables
        String cadena = "";
        int valorMenu = -1;
        boolean valorCorrecte = false;

        // Bucle per introduir el valor del menu
        // (es limita a tres el nombre d'intents per evitar bucles infinits)
        System.out.println("Seleccioni opció: ");
        while (intentsAd >= 0 && valorCorrecte == false) {

            System.out.print(">>> ");

            Scanner sc = new Scanner(System.in);// Llegim la cadena
            cadena = sc.nextLine();

            // S'implementa un control d'errors per tal de capturar
            try {
                valorMenu = Integer.parseInt(cadena);   // Passam l'entrada a Caracter

                if (valorMenu >= min && valorMenu <= max) {    // Valor correcte
                    valorCorrecte = true;
                }
            } catch (Exception ex) {    // Valor incorrecte (int NG!)
                // System.out.println(ex.getMessage());
                // No actuam al catch. Simplement no s'haurà activat el flag de "valorCorrecte = true"
            }

            // Si el valor és incorrecte
            if (valorCorrecte == false) {
                System.out.println("Valor incorrecte");

                if (intentsAd > 0) {
                    System.out.println("Li queden " + intentsAd + " intents.");
                } else {
                    valorMenu = -1;
                }
                intentsAd--;
            }
        }
        // Es retorna el valor del menú indicat
        return valorMenu;
    }


    // Metode per introduir un Y/N (Defecte = NO)
    public static String introduirRespostaYN(int intentsAd){

        // Variables
        String cadena = "-1";
        String valors = "sSyYnN";
        int cont = 0;

        // Bucle per introduir la paraula
        // (es limita a <intents +1> el nombre d'intents per evitar bucles infinits)
        while(intentsAd >= 0 && (cont != cadena.length())){

            System.out.print(">>> ");

            Scanner sc = new Scanner(System.in);// Llegim la cadena
            cadena = sc.nextLine();

            // Comprovació de que és una lletra correcte
            if (!cadena.contains(" ")){  //Si la cadena no conté espais

                // Determina si la lletra és correcte. Seleccionada de la llista de valors
                for (int i = 0; i < valors.length(); i++){
                    for (int j = 0; j < cadena.length(); j++){

                        if (cadena.substring(j, j+1).equals(valors.substring(i, i+1))){
                            cont++; // Augmentam el contador de coincidencies entre valos permesos i lletres
                        }
                    }
                }

                // Si no coincideixen els caracters amb els caracters permesos
                if (cont != cadena.length()){

                    System.out.println("Heu introduit un valor incorrecte.");
                    System.out.println("Li queden " + intentsAd + " intents.");
                    intentsAd--;
                    cadena = "-1";
                }

            }else{  // Si s'han introduit espais-> més d'una paraula (error directe)
                System.out.println("Heu introduit més d'una paraula.");
                System.out.println("Li queden " + intentsAd + " intents.");
                intentsAd--;
                cadena = "-1";
            }
        }

        return cadena;
    }


    // Metode per introduir paraules
    public static String introduirRespostaStr(int intentsAd, boolean possibleNul){

        // Variables
        String cadena = "-1";
        boolean respOk = false;

        // Bucle per introduir la paraula
        // (es limita a <intents +1> el nombre d'intents per evitar bucles infinits)
        while(intentsAd >= 0 && !respOk){

            System.out.print(">>> ");

            Scanner sc = new Scanner(System.in);// Llegim la cadena
            cadena = sc.nextLine();

            // Si la cadena pot ser null i en blanc retorna -2
            if ((cadena.equals("") && possibleNul)) {
                cadena = "";
                respOk = true;

                // Si pot ser nula i hi ha valor, retorna el valor
            }else if (!cadena.equals("")&& possibleNul){
                respOk = true;

                // Si la cadena no esta buita i no pot ser null
            }else if ( !cadena.equals("") && !possibleNul){
                respOk = true;
            }

            // Resta de casos son incorrectes
            else{  // Si s'han introduit espais-> més d'una paraula (error directe)
                System.out.println("Heu introduit un valor incorrecte");
                System.out.println("Li queden " + intentsAd + " intents.");
                intentsAd--;
                cadena = "";
            }
        }

        return cadena;
    }
}
