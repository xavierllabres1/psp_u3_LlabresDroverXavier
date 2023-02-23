package object;

import java.io.Serializable;

// Objecte que s'enviarà per realitzar la Transmisió i Recepció de les dades.
public class TxRxData implements Serializable {

    // Atributs
    private String query;  // Indica el tipus de consulta a realitzar
    private String resposta;   // Indica el resultat de l'acció
    private User user;  // Objecte Usuari que transmet

    public TxRxData(String query, User user) {
        this.query = query;
        this.user = user;
        this.resposta = "";
    }

    public TxRxData(String resposta) {
        this.resposta = resposta;
    }

    // Getter i Setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
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
}
