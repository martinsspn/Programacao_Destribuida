package service;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientInterface extends Remote {

    public void printMessage(Message message) throws RemoteException;

}
