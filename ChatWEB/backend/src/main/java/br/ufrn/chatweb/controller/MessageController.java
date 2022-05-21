package br.ufrn.chatweb.controller;

import br.ufrn.chatweb.model.Message;
import br.ufrn.chatweb.model.Usuario;
import br.ufrn.chatweb.service.MessageService;
import br.ufrn.chatweb.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "X-Total-Count")
public class MessageController {
    private final MessageService service;
    private final UsuarioService usuarioService;

    public MessageController(MessageService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{remetente}/{destinatario}")
    public ResponseEntity<List<Message>> getByTelefone(@PathVariable String remetente, @PathVariable String destinatario){
        System.out.println(remetente + " " + destinatario);
        return ResponseEntity.ok(service.findByTelefones(remetente, destinatario));
    }

    @PostMapping("/{telefoneRemetente}/{telefoneDestinatario}")
    public ResponseEntity<Message> insert(@RequestBody Message message, @PathVariable String telefoneRemetente, @PathVariable String telefoneDestinatario){
        Optional<Usuario> usuarioRemetente = usuarioService.findByTelefone(telefoneRemetente);
        Optional<Usuario> usuarioDestinatario = usuarioService.findByTelefone(telefoneDestinatario);
        if(usuarioRemetente.isPresent()){
            message.setTelefoneRemetente(usuarioRemetente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        if(usuarioDestinatario.isPresent()){
            message.setTelefoneDestinatario(usuarioDestinatario.get());
        }else {
            return ResponseEntity.notFound().build();
        }
        message.setDate(Date.from(Instant.now()));
        return ResponseEntity.ok(service.create(message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Message Message){
        Optional<Message> userUpdate = service.update(id, Message);
        return userUpdate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
