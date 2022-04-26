package service;
import model.EncriptaDecripta;

import model.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class Client extends UnicastRemoteObject implements ClientInterface {
    private final ServerInterface server;
    private int status;
    private final String telefoneRemetente;
    private EncriptaDecripta encriptaDecripta;

    public Client(ServerInterface server, String telefoneRemetente) throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
        this.server = server;
        this.status = 1;
        this.telefoneRemetente = telefoneRemetente;
        this.encriptaDecripta = new EncriptaDecripta();
    }

    @Override
    public void printMessage(Message message, boolean is_connect_response) throws RemoteException {
        if(is_connect_response){
            System.out.println(message);
            new sendMessage().start();
        }else {
            String messageDecrypted = encriptaDecripta.descriptografiaBase64Decode(message.getMessage());
            message.setMessage(messageDecrypted);
            System.out.println(message);
        }
    }

    class sendMessage extends Thread {
        String telefoneDestino = null;
        Scanner leitor = new Scanner(System.in);
        public void run() {
            for (; ; ) {

                if (status == 1) {
                    System.out.println("********* Novo chat *********");
                    System.out.println("Digite o telefone ao qual quer enviar a mensagem:");
                    System.out.print("> ");
                    String telefone = leitor.nextLine();
                    try {
                        server.getMessages(telefoneRemetente, telefone);
                    } catch (RemoteException e) {
                        System.out.println("Não há mensagens com o destino ainda!");
                    }finally {
                        System.out.println("Digite a mensagem:");
                        System.out.print("> ");
                        String mensagem = leitor.nextLine();
                        Message message = null;
                        String mensagemEncriptada = encriptaDecripta.criptografiaBase64Encoder(mensagem);
                        message = new Message(mensagemEncriptada, telefoneRemetente, telefone);
                        status = 2;
                        try {
                            server.forwardMessage(message);
                            telefoneDestino = telefone;
                        } catch (RemoteException e) {
                            System.out.println("Telefone de destino não está conectado no momento!");
                            System.out.println("Suas mensagens serão entregue quando o usuário conectar!");
                        } catch (NullPointerException e) {
                            System.out.println("Telefone de destino não está cadastrado!!!");
                            status = 1;
                            telefoneDestino = null;
                        }
                    }

                } else if (status == 2) {
                    //String telefone = aqui vai ter q pegar do banco de dados o numero atual q foi passado no status 1
                    String telefone = telefoneDestino;
                    System.out.println("Opções:");
                    System.out.println("  1 - continuar neste chat");
                    System.out.println("  2 - enviar mensagem a outra pessoa");
                    System.out.print("> ");
                    String resposta = leitor.nextLine();
                    if (resposta.equals("1")){
                        System.out.println("Caso queira sair do chat atual basta digitar 'exit' ");
                        createNextMessage(telefone);
                    } else if (resposta.equals("2")) {
                        status = 1;
                        telefoneDestino = null;
                    }
                    else{
                        System.out.println("Opção inválida");
                    }
                } else if (status == 3) {
                    //String telefone = aqui vai ter q pegar do banco de dados o numero atual q foi passado no status 1
                    String telefone = telefoneDestino;
                    createNextMessage(telefone);
                }
            }
        }

        private void createNextMessage(String telefone) {
            System.out.print("> ");
            String mensagem = leitor.nextLine();
            Message message = new Message(encriptaDecripta.criptografiaBase64Encoder(mensagem), telefoneRemetente, telefone);
            if (mensagem.equals("exit")){
                status = 1;
                telefoneDestino = null;
            }
            else{
                try {
                    server.forwardMessage(message);
                } catch (RemoteException e) {
                    System.out.println("Telefone de destino não está conectado no momento!");
                }
                status = 3;
            }
        }
    }
}
