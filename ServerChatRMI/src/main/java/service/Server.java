package service;

import model.Message;
import repository.MessageRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class Server extends UnicastRemoteObject implements ServerInterface {

    private final Map<String, ClientInterface> clients = new HashMap<>();
    private final MessageRepository repository = new MessageRepository();

    public Server() throws RemoteException {
        super();

    }

    @Override
    public void registerClient(ClientInterface client, String telefoneRemetente, String telefoneDestinatario) throws RemoteException {
        clients.put(telefoneRemetente, client);
        System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
        System.out.println("Cliente: " + telefoneRemetente);
        Message message = new Message(telefoneRemetente + " have connected successfully!", telefoneRemetente, telefoneDestinatario);
        client.printMessage(message, true);
        repository.saveMessage(message);
    }

    @Override
    public void forwardMessage(Message message, String telefoneDestinatario) throws RemoteException {
        clients.get(telefoneDestinatario).printMessage(message, false);
        repository.saveMessage(message);
    }

}

