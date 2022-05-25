package br.ufrn.chatweb.controller;

import br.ufrn.chatweb.model.Usuario;
import br.ufrn.chatweb.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = "X-Total-Count")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{nome}/{telefone}")
    public ResponseEntity<Usuario> getByNomeAndTelefone(@PathVariable String nome, @PathVariable String telefone){
        Optional<Usuario> usuario = service.findByNomeAndTelefone(nome, telefone);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{telefone}")
    public ResponseEntity<Usuario> getByTelefone(@PathVariable String telefone){
        Optional<Usuario> usuario = service.findByTelefone(telefone);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario){
        return ResponseEntity.ok(service.create(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> userUpdate = service.update(id, usuario);
        return userUpdate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
