import model.Message;
import service.Client;
import service.ClientInterface;
import service.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        ServerInterface server = (ServerInterface)
                Naming.lookup("rmi://127.0.0.1:1098/ServerCallbak");

        ClientInterface client = new Client(server);
        System.out.println("===== ||  || ====== ======");
        System.out.println("|     ||==|| ||==||   ||  ");
        System.out.println("===== ||  || ||  ||   ||  ");
        Scanner leitor = new Scanner(System.in);
        System.out.println("╠══=====seu Telefone=====══╣");
        String telefone = leitor.nextLine();
        server.registerClient(client, telefone);

    }

}