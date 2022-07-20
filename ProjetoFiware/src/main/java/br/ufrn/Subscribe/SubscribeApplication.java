package br.ufrn.Subscribe;

import br.ufrn.Subscribe.Controller.PublishController;
import br.ufrn.Subscribe.Controller.SubscribeController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
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
                System.out.println("Sistema de monitoramento de depositos de lixo da cidade de Natal");
                System.out.println("Escolha a opção:");
                System.out.println("1 - publicar novo deposito de lixo");
                System.out.println("2 - Subscrever em um deposito de lixo");
                System.out.print("> ");
                Scanner leitor = new Scanner(System.in);
                int op = leitor.nextInt();
                switch (op) {
                    case 1 -> {
                        JSONObject jsonObject = new JSONObject(PublishController.createContext());
                        JSONArray contextResponses = (JSONArray) jsonObject.get("contextResponses");
                        JSONObject attributes = contextResponses.getJSONObject(0);
                        JSONObject status = attributes.getJSONObject("statusCode");
                        String code = status.getString("code");
                        if(Objects.equals(code, "200")){
                            System.out.println("Depósito de lixo publicado com sucesso!");
                        }else{
                            System.out.println("Não foi possivel publicar o novo deposito de lixo");
                        }
                    }
                    case 2 -> {
                        System.out.println("informe o id do deposito:");
                        String idDeposito = leitor.next();
                        JSONObject jsonObject =  new JSONObject(SubscribeController.createSubscription(idDeposito));
                        JSONObject subscribeResponse = jsonObject.getJSONObject("subscribeResponse");
                        String subscriptionId = subscribeResponse.getString("subscriptionId");
                        System.out.println("O depósito " + idDeposito + " está sendo monitorado agora");
                        System.out.println("id de subscrição: " + subscriptionId);
                    }
                    case 99 -> {
                        return;
                    }
                    default -> System.out.println("Opcao invalida");
                }
            }
        }
    }
}
