package service;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{

    public void registerClient(ClientInterface client) throws RemoteException;


}

