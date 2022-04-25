package service;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{

    public void registerClient(ClientInterface client, String telefone) throws RemoteException;
    public void forwardMessage(Message message) throws RemoteException;
    public void getMessages(String telefoneRemetente, String telefoneDestinatario) throws RemoteException;

}

