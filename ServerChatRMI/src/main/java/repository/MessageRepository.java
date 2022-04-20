package repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Message;
import org.bson.Document;

public class MessageRepository {
    private final MongoCollection<Document> collection;

    public MessageRepository() {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("Chat");
        this.collection = database.getCollection("messages");
    }

    public void saveMessage(Message message){
        Document document = new Document("telefoneRemetente", message.getTelefoneRemetente())
                .append("telefoneDestinatario", message.getTelefoneDestinatario())
                .append("message", message.getMessage())
                .append("date", message.getDate());

        collection.insertOne(document);
    }
}
