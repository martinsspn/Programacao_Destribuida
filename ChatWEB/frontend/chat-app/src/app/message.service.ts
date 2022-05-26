import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MessageModel } from './message.model';
import { LoginComponent } from './login/login/login.component';

@Injectable({
  providedIn: 'root'
})
export class MessageService {


  constructor(private http: HttpClient) { }


  getMessages() : Observable<any>{
    return this.http.get("http://localhost:8080/message/");
  }

  getMessagesFromChat() : Observable<any>{
    const url = "http://localhost:8080/message/"+ LoginComponent.usuarioRemetente.telefone 
                                                  + "/"+LoginComponent.usuarioDestinatario.telefone;
    console.log(url);
    return this.http.get(url);
  }

  sendMessage(message : MessageModel) : Observable<any>{
    const url = "http://localhost:8080/message/"+ LoginComponent.usuarioRemetente.telefone 
                                                  + "/"+LoginComponent.usuarioDestinatario.telefone;
    const messageJSON = {
      "message": message.message
    }
    return this.http.post(url, messageJSON);
  }
}
