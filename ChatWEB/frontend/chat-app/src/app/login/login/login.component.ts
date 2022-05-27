import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  
  constructor(
    private usuarioService: UsuarioService, 
    private router : Router, 
    private activatedRoute : ActivatedRoute,
    private snackBar : MatSnackBar) { }
  
  ngOnInit(): void {
    LoginComponent.usuarioRemetente = new UsuarioModel();
    LoginComponent.usuarioDestinatario = new UsuarioModel();
  }

  logIn(){
    this.usuarioService.getUsuario(this.usuario).subscribe(u =>{
      this.usuario = u;
      LoginComponent.usuarioRemetente = u;
      this.entrar();
    }, err => {
      const msg = "Usuário não encontrado. Talvez você queira registrar-se?"; 
      console.log(msg, err);
      this.snackBar.open(msg, "", {
        duration: 5000
      });
    })
  }

  registrarSe(){
    this.usuarioService.insertUsuario(this.usuario).subscribe(u => {
      this.usuario = u;
      LoginComponent.usuarioRemetente = u;
      this.snackBar.open("Usuário registrado com sucesso!", "", {
        duration: 5000
      });
    }, err => {
      console.log("Usuário não foi cadastrado!", err);
      this.snackBar.open("O usuário não foi cadastrado!", "", {duration:5000})
    })
  }

  entrar(){
    this.router.navigate(['chat'], {relativeTo: this.activatedRoute})
  }
}
