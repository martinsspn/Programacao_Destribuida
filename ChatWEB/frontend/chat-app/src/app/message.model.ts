import { UsuarioModel } from "./usuario.model";

export class MessageModel{
    usuarioRemetente!: UsuarioModel;
    usuarioDestinatario!: UsuarioModel;
    message!: string;
}