import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginComponent } from 'src/app/login/login/login.component';
import { MessageModel } from 'src/app/message.model';
import { MessageService } from 'src/app/message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  messages: Array<any> = new Array();
  message: MessageModel = new MessageModel();

  constructor(private messageService: MessageService,  private router : Router, private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    this.getMessages();
    this.message.usuarioRemetente = LoginComponent.usuarioRemetente;
  }

  updateMessages(){
    this.getMessages();
  }

  getMessages(){
    this.messageService.getMessagesFromChat().subscribe(messages => {
      this.messages = messages;   
      console.log(messages);
    }, err => {
      console.log("Erro ao listar as mensagens", err);
    });
  }

  sendMessage(){
    this.message.usuarioDestinatario = LoginComponent.usuarioDestinatario;
    this.messageService.sendMessage(this.message).subscribe(message => {
      console.log(message);
      this.getMessages();
      console.log(this.message);
    }, err => {
      console.log("Erro ao enviar mensagem", err); 
    });
  }

  voltar(){
    this.router.navigateByUrl('chat');
  }
}
