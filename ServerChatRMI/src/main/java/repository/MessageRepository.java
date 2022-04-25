package repository;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Message;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

public class MessageRepository {
    private final MongoCollection<Document> messages;
    private final MongoCollection<Document> telefones;

    public MessageRepository() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://martinsspn:baby2765@cluster0.o0zyk.mongodb.net/Chat?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("Chat");
        this.messages = database.getCollection("messages");
        this.telefones = database.getCollection("telefones");
    }

    public void saveMessage(Message message){
        Document document = new Document("telefoneRemetente", message.getTelefoneRemetente())
                .append("telefoneDestinatario", message.getTelefoneDestinatario())
                .append("message", message.getMessage())
                .append("date", message.getDate());

        messages.insertOne(document);
    }

    public void saveTelefone(String telefone) {
        Document document = new Document("telefone", telefone);
        telefones.insertOne(document);
    }

    public String getClient(String telefone) {
        Bson filter = eq("telefone", telefone);
        ArrayList<Document> client = telefones.find(filter).into(new ArrayList<Document>());
        System.out.println(client.size());
        if(client.size() > 0){
            return client.get(0).getString("telefone");
        }
        return "";
    }

    public ArrayList<Message> getClientMessages(String telefoneRemetente, String telefoneDestinatario) {
        Bson filter = or(and(eq("telefoneRemetente", telefoneRemetente),
                             eq("telefoneDestinatario", telefoneDestinatario)),
                         and(eq("telefoneDestinatario", telefoneRemetente),
                             eq("telefoneRemetente", telefoneDestinatario)));
        ArrayList<Document> documents = messages.find(filter).into(new ArrayList<Document>());
        ArrayList<Message> messages = new ArrayList<>();
        for(Document document : documents){
            Message message = new Message(document.getString("message"), document.getString("telefoneRemetente"), document.getString("telefoneDestinatario"));
            message.setDate(document.getDate("date"));
            messages.add(message);
        }
        return messages;
    }

    public ArrayList<String> getClients(){
        ArrayList<String> clients = new ArrayList<>();
        ArrayList<Document> clientsDocument = telefones.find().into(new ArrayList<Document>());
        for(Document document : clientsDocument){
            clients.add(document.getString("telefone"));
        }
        return clients;
    }
}
