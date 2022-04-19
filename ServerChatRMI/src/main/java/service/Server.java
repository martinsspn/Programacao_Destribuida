package service;

import model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class Server extends UnicastRemoteObject implements ServerInterface {

    private volatile Map<String, ClientInterface> clients = new HashMap<>();

    public Server() throws RemoteException {
        super();

    }

    @Override
    public void registerClient(ClientInterface client, String telefone) throws RemoteException {

        clients.put(telefone, client);
        System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
        System.out.println("Cliente: " + telefone);
        client.printMessage(new Message(telefone + " have connected successfully!"), true);

    }

    @Override
    public void forwardMessage(Message message, String telefone) throws RemoteException {
        clients.get(telefone).printMessage(message, false);
    }


/*    private class Notify extends Thread{

        public void run() {

            for(;;) {

                if(clients.size() > 0) {

                    System.out.println("Notificando clientes");

                    int i = 0;
                    for (ClientInterface helloClientInterface : clients) {

                        try {
                            helloClientInterface.printMessage(new Message("Hello client " + (i++)));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }

        }
    }
*/

}

