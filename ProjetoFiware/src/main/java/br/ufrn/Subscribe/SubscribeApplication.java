package br.ufrn.Subscribe;

import br.ufrn.Subscribe.Controller.PublishController;
import br.ufrn.Subscribe.Controller.SubscribeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SubscribeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscribeApplication.class, args);
        Menu menu = new Menu();
        menu.start();
    }

    static class Menu extends Thread{
        public void run(){
            while (true) {
                System.out.println("Escolha a opção:");
                System.out.println("1 - Adicionar novo deposito de lixo");
                System.out.println("2 - Subscrever em um deposito de lixo");
                System.out.print("> ");
                Scanner leitor = new Scanner(System.in);
                int op = leitor.nextInt();
                switch (op) {
                    case 1 -> System.out.println(PublishController.createContext());
                    case 2 -> {
                        System.out.println("informe o id do deposito:");
                        String idDeposito = leitor.next();
                        System.out.println(SubscribeController.createSubscription(idDeposito));
                    }
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("Opcao invalida");
                }
            }
        }
    }
}
