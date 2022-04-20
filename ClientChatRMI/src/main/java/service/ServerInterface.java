package service;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{

    void registerClient(ClientInterface client, String telefoneRemetente, String telefoneDestinatario) throws RemoteException;
    void forwardMessage(Message message, String telefoneDestinatario) throws RemoteException;

}

