package service;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    void printMessage(Message message, boolean is_connect_response) throws RemoteException;

}
