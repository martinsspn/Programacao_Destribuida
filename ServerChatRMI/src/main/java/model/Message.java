package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

    private final String telefoneRemetente;
    private final String telefoneDestinatario;
    private final String message;
    private final Date date;

    public Message(String message, String telefoneRemetente, String telefoneDestinatario) {
        this.message = message;
        this.date = new Date();
        this.telefoneDestinatario = telefoneDestinatario;
        this.telefoneRemetente = telefoneRemetente;
    }

    public Date getDate() {
        return date;
    }
    public String getMessage(){
        return message;
    }
    public String getTelefoneRemetente(){
        return telefoneRemetente;
    }
    public String getTelefoneDestinatario(){
        return telefoneDestinatario;
    }

    @Override
    public String toString() {
        return "Message [message=" + message + ", date=" + date + ", telefoneRemetente=" + telefoneRemetente + ", telefoneDestinatario=" + telefoneDestinatario + "]";
    }
}
