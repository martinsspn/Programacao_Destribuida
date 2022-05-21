import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MessageModel } from './message.model';

@Injectable({
  providedIn: 'root'
})
export class MessageService {


  constructor(private http: HttpClient) { }


  getMessages() : Observable<any>{
    return this.http.get("http://localhost:8080/message/");
  }

  getMessagesFromChat(message: MessageModel) : Observable<any>{
    message.telefoneRemetente = "12";
    const url = "http://localhost:8080/message/"+ message.telefoneRemetente 
                                                  + "/"+message.telefoneDestinatario;
    console.log(url);
    return this.http.get(url);
  }

  sendMessage(message : MessageModel) : Observable<any>{
    const url = "http://localhost:8080/message/"+ message.telefoneRemetente 
                                                  + "/"+message.telefoneDestinatario;
    const messageJSON = {
      "message": message.message
    }
    return this.http.post(url, messageJSON);
  }
}
