package service;

import model.Message;
import repository.MessageRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Server extends UnicastRemoteObject implements ServerInterface {

    private final Map<String, ClientInterface> clients = new HashMap<>();
    private final MessageRepository repository = new MessageRepository();
    private ArrayList<String> clientsRegistred;

    public Server() throws RemoteException {
        super();
        clientsRegistred = repository.getClients();
        System.out.println("Clientes registrados: " + clientsRegistred.size());
    }

    @Override
    public void registerClient(ClientInterface client, String telefone) throws RemoteException {
        clients.put(telefone, client);
        client.printMessage(new Message(telefone + " have connected successfully!", telefone, "0"), true);
        if(clientsRegistred.contains(telefone)){
            System.out.println("Cliente resgatado com sucesso!");
        }else{
            System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
            System.out.println("Cliente: " + telefone);
            repository.saveTelefone(telefone);
            clientsRegistred.add(telefone);
        }
    }

    @Override
    public void forwardMessage(Message message) throws RemoteException {
        String[] telefones = message.getTelefoneDestinatario().split(" ");
        for(String telefoneDestino : telefones){
            if(clientsRegistred.contains(telefoneDestino)){
                repository.saveMessage(message);
                if(clients.containsKey(telefoneDestino)){
                    clients.get(telefoneDestino).printMessage(message, false);
                }else{
                    throw new RemoteException();
                }
            }else{
                throw new NullPointerException();
            }
        }
    }

    @Override
    public void getMessages(String telefoneRemetente, String telefoneDestinatario) throws RemoteException {
        for(Message message : repository.getClientMessages(telefoneRemetente, telefoneDestinatario)){
            clients.get(telefoneRemetente).printMessage(message, false);
        }
    }
}

