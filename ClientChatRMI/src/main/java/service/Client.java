package service;

import model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class Client extends UnicastRemoteObject implements ClientInterface {
    private ServerInterface server;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Client(ServerInterface server) throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
        this.server = server;
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

                if (status == 1) {
                    System.out.println("********* Novo chat *********");
                    System.out.println("Digite o telefone ao qual quer enviar a mensagem:");
                    String telefone = leitor.nextLine();
                    System.out.println("Digite a mensagem:");
                    Message message = new Message(leitor.nextLine());
                    status = 2;
                    try {
                        server.forwardMessage(message, telefone);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        System.out.println("Telefone de destino não está cadastrado!!!");
                        status = 1;
                    }
                } /*else if (status == 2) {
                    //String telefone = aqui vai ter q pegar do banco de dados o numero atual q foi passado no status 1
                    System.out.println("Opções:");
                    System.out.println("  1 - continuar neste chat");
                    System.out.println("  2 - enviar mensagem a outra pessoa");
                    String resposta = leitor.nextLine();
                    if (resposta.equals("1")){
                        System.out.println("Caso queira sair do chat atual basta digitar 'exit' ");
                        Message message = new Message(leitor.nextLine());
                        if (message.equals("exit")){
                            status = 1;
                        }
                        else{
                            try {
                                server.forwardMessage(message, telefone);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            status = 3;
                        }
                    } else if (resposta.equals("2")) {
                        status = 1;
                    }
                    else{
                        System.out.println("Opção inválida");
                    }
                } else if (status == 3) {
                    //String telefone = aqui vai ter q pegar do banco de dados o numero atual q foi passado no status 1
                    Message message = new Message(leitor.nextLine());
                    if (message.equals("exit")){
                        status = 1;
                    }
                    else{
                        try {
                            server.forwardMessage(message, telefone);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        status = 3;
                    }
                }*/
            }
        }
    }

}
