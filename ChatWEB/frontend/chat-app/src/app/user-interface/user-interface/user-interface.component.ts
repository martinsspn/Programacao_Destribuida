import { Component, OnInit } from '@angular/core';
import { LoginComponent } from 'src/app/login/login/login.component';
import { MessageModel } from 'src/app/message.model';
import { MessageService } from 'src/app/message.service';
import { UsuarioModel } from 'src/app/usuario.model';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent implements OnInit {

  message: MessageModel = new MessageModel();
  messages: Array<any> = new Array();
  usuario: UsuarioModel = new UsuarioModel();

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.getMessages();
  }

  getMessages(){
    this.messageService.getMessages().subscribe(messages => {
      this.messages = messages;   
    }, err => {
      console.log("Erro ao listar as mensagens", err); 
    })
  }

  sendMessage(){
    this.message.telefoneRemetente = LoginComponent.usuarioS.telefone;
    this.messageService.sendMessage(this.message).subscribe(message => {
      console.log(message);
      this.getMessages();
    }, err => {
      console.log("Erro ao enviar mensagem", err); 
    });
  }

  set telefoneDestinatario(value : string){
    this.message.telefoneDestinatario = value;
    if(this.telefoneDestinatario !== ""){
      this.messageService.getMessagesFromChat(this.message).subscribe(messages => {
        this.messages = messages;   
      }, err => {
        console.log("Erro ao listar as mensagens", err);
      });
    }else{
      this.messageService.getMessages().subscribe(messages => {
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
