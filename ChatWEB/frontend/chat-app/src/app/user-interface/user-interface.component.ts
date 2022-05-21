import { Component, OnInit } from '@angular/core';
import { MessageModel } from '../message.model';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent implements OnInit {

  message: MessageModel = new MessageModel();
  messages: Array<any> = new Array();

  constructor(private service: MessageService) { }

  ngOnInit(): void {
    this.getMessages();
  }

  getMessages(){
    this.service.getMessages().subscribe(messages => {
      this.messages = messages;   
    }, err => {
      console.log("Erro ao listar as mensagens", err);
    })
  }

  sendMessage(){
    this.message.telefoneRemetente = "12";
    this.service.sendMessage(this.message).subscribe(message => {
      console.log(message);
      this.getMessages();
    }, err => {
      console.log("Erro ao enviar mensagem", err);
    });
  }

  set telefoneDestinatario(value : string){
    this.message.telefoneDestinatario = value;
    if(this.telefoneDestinatario !== ""){
      this.service.getMessagesFromChat(this.message).subscribe(messages => {
        this.messages = messages;   
      }, err => {
        console.log("Erro ao listar as mensagens", err);
      });
    }else{
      this.service.getMessages().subscribe(messages => {
        this.messages = messages;
      }, err => {
        console.log("Erro ao listar as mensagens", err);
      });
    }
  }

  get telefoneDestinatario(){
    return this.message.telefoneDestinatario;
  }
}
