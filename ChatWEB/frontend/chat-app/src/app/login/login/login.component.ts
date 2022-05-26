import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioModel } from 'src/app/usuario.model';
import { UsuarioService } from 'src/app/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usuario : UsuarioModel = new UsuarioModel();
  static usuarioRemetente: UsuarioModel;  
  static usuarioDestinatario: UsuarioModel;  
  constructor(private usuarioService: UsuarioService, private router : Router, private activatedRoute : ActivatedRoute) { }
  logged : boolean = false;
  ngOnInit(): void {
  }

  logIn(){
    this.usuarioService.getUsuario(this.usuario).subscribe(u =>{
      this.usuario = u;
      LoginComponent.usuarioRemetente = u;
      this.entrar();
    }, err => {
      console.log("Usuário não encontrado. Talvez você queira registrar-se?", err);
    })
  }

  registrarSe(){
    this.usuarioService.insertUsuario(this.usuario).subscribe(u => {
      this.usuario = u;
      LoginComponent.usuarioRemetente = u;
      this.entrar();
    }, err => {
      console.log("Usuário não foi cadastrado!", err);
    })
  }

  entrar(){
    this.router.navigate(['chat'], {relativeTo: this.activatedRoute})
  }
}
