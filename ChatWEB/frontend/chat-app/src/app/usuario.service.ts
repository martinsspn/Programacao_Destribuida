import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioModel } from './usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  getUsuarios() : Observable<any> {
    return this.http.get("http://localhost:8080/usuario");
  }

  constructor(private http: HttpClient) { }

  getUsuario(usuario: UsuarioModel) : Observable<any>{
    return this.http.get("http://localhost:8080/usuario/" + usuario.nome + "/" + usuario.telefone);
  }

  insertUsuario(usuario: UsuarioModel) : Observable<any>{
    const url = "http://localhost:8080/usuario/";
    const messageJSON = {
      "nome": usuario.nome,
      "telefone": usuario.telefone
    }
    return this.http.post(url, messageJSON);
  }
}
