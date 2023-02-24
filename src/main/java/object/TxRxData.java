package object;

import java.io.Serializable;

// Objecte que s'enviarà per realitzar la Transmisió i Recepció de les dades.
public class TxRxData implements Serializable {

    // Atributs
    private int query;  // Indica el tipus de consulta a realitzar (1 insert, 2 select, 3 delete)
    private String resposta;   // Indica el resultat de l'acció
    private User user;  // Objecte Usuari que transmet

    public TxRxData(int query, User user) {
        this.query = query;
        this.user = user;

    }

    public TxRxData(String resposta) {
        this.resposta = resposta;
    }

    // Getter i Setters
    public int getQuery() {
        return query;
    }

    public void setQuery(int query) {
        this.query = query;
    }

    public String isResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResposta() {
        return resposta;
    }
}
