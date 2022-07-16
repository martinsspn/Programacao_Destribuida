package br.ufrn.Subscribe.Controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {


	public static String createSubscription(String id){
		JSONObject subscription = new JSONObject();
		List<JSONObject> entities = new ArrayList<>();
		JSONObject json = new JSONObject();
		json.put("isPattern", "false");
		json.put("id", id);
		json.put("type", "Deposito_de_lixo");
		entities.add(json);
		subscription.put("entities", entities);
		List<String> attributes = new ArrayList<>();
		attributes.add("Nivel_qtd_lixo");
		subscription.put("attributes", attributes);
		subscription.put("reference", "http://172.17.0.1:8080/Subscribe/subscribe");
		subscription.put("duration", "P1M");
		List<JSONObject> notifyConditions = new ArrayList<>();
		JSONObject type = new JSONObject();
		type.put("type", "ONCHANGE");
		notifyConditions.add(type);
		subscription.put("notifyConditions", notifyConditions);
		subscription.put("throttling", "PT5S");
		return subscribe(subscription);
	}

	public static String subscribe(JSONObject subscription){
		String url = "http://127.0.0.1:1026/v1/subscribeContext";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(subscription.toString(), httpHeaders);
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(url, httpEntity, String.class);

	}

	@PostMapping
	public void exibirSubscricao(@RequestBody JsonNode body) {

		System.out.println("\n\n\n"+body.toPrettyString());
		
	}

}
