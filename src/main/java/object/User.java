package object;

import java.io.Serializable;

public class User implements Serializable {

    // Atributs
    private int id;
    private String cognom;
    private String nom;

    // Constructors
    public User(int id, String cognom, String nom) {
        this.id = id;
        this.cognom = cognom;
        this.nom = nom;
    }
    public User(int id) {
        this.id = id;
    }

    public User(){
    };


    // Getters i Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
