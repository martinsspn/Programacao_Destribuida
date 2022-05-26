import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/usuario.service';
import { UsuarioModel } from 'src/app/usuario.model';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { LoginComponent } from 'src/app/login/login/login.component';

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent implements OnInit {

  usuarios: Array<any> = new Array();
  usuario : UsuarioModel = LoginComponent.usuarioRemetente;
  constructor(private usuarioService: UsuarioService, private router : Router, private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    this.getUsuarios();
  }

  getUsuarios(){
    this.usuarioService.getUsuarios().subscribe(usuarios => {
      this.usuarios = usuarios;
    }, err => {
      console.log("Erro ao listar usuários", err);
    })
  }

  goToChat(usuario : UsuarioModel){
    LoginComponent.usuarioDestinatario = usuario;
    this.router.navigate(['message'], {relativeTo: this.activatedRoute})
  }
}
