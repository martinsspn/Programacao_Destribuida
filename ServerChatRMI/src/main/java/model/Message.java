package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

    private String telefoneRemetente;
    private String telefoneDestinatario;
    private String message;
    private Date date;

    public Message(String message, String telefoneRemetente, String telefoneDestinatario) {
        this.message = message;
        this.date = new Date();
        this.telefoneRemetente = telefoneRemetente;
        this.telefoneDestinatario = telefoneDestinatario;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTelefoneRemetente() {
        return telefoneRemetente;
    }
    public void setTelefoneRemetente(String telefoneRemetente) {
        this.telefoneRemetente = telefoneRemetente;
    }
    public String getTelefoneDestinatario() {
        return telefoneDestinatario;
    }
    public void setTelefoneDestinatario(String telefoneDestinatario) {
        this.telefoneDestinatario = telefoneDestinatario;
    }

    @Override
    public String toString() {
        return "Messagem recebida:\n[Enviado por: " + telefoneRemetente + "\npara: " + telefoneDestinatario + "\nem " + date + "\nMessagem: " + message +"]";
    }
}