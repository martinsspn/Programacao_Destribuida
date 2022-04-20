package service;

import model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class Client extends UnicastRemoteObject implements ClientInterface {
    private final ServerInterface server;
    private final String telefone;

    public Client(ServerInterface server, String telefone) throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
        this.server = server;
        this.telefone = telefone;
    }

    @Override
    public void printMessage(Message message, boolean is_connect_response) throws RemoteException {
        System.out.println(message);
        if(is_connect_response){
            new sendMessage().start();
        }
    }

    class sendMessage extends Thread {
        Scanner leitor = new Scanner(System.in);
        public void run() {
            for (; ; ) {
                System.out.println("Digite o telefone ao qual quer enviar a mensagem:");
                String telefoneDestinatario = leitor.nextLine();
                System.out.println("Digite a mensagem:");
                Message message = new Message(leitor.nextLine(), telefone, telefoneDestinatario);
                try {
                    server.forwardMessage(message, telefone);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
