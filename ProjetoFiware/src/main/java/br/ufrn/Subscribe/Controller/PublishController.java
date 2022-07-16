package br.ufrn.Subscribe.Controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PublishController {
    public static String createContext() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("informe o id do deposito de lixo:");
        String idDeposito = leitor.next();
        System.out.println("Qual o nível de lotação do deposito?");
        System.out.println("1 - baixo");
        System.out.println("2 - medio");
        System.out.println("3 - alto");
        System.out.print("> ");
        String nivelLotacao = leitor.next();
        System.out.println("Informe as coordenadas do deposito:");
        System.out.print("   latitude:");
        Float latitude = leitor.nextFloat();
        System.out.print("   longitude:");
        Float longitude = leitor.nextFloat();
        JSONObject entity = new JSONObject();
        entity.put("type", "Deposito_de_lixo");
        entity.put("isPattern", "false");
        entity.put("id", idDeposito);
        List<Object> attributes = new ArrayList<>();
        JSONObject firstAttribute = new JSONObject();
        firstAttribute.put("name", "Nivel_qtd_lixo");
        firstAttribute.put("type", "String");
        firstAttribute.put("value", nivelLotacao);
        attributes.add(firstAttribute);
        JSONObject secondAttribute = new JSONObject();
        secondAttribute.put("name", "latitude");
        secondAttribute.put("type", "Float");
        secondAttribute.put("value", latitude);
        attributes.add(secondAttribute);
        JSONObject thirdAttribute = new JSONObject();
        thirdAttribute.put("name", "longitude");
        thirdAttribute.put("type", "Float");
        thirdAttribute.put("value", longitude);
        attributes.add(thirdAttribute);
        entity.put("attributes", attributes);
        return publishContext(entity);
    }

    public static String publishContext(JSONObject entity) {
        String url = "http://localhost:1026/v1/contextEntities";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<String>(entity.toString(), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(url, httpEntity, String.class);
    }
}
