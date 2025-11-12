package org.grupo14.msvcusuario.controller;

import org.grupo14.msvcusuario.dto.UsoMonopatinesDTO;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);

    }

    @PostMapping("")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
         Usuario usuarioUpdate = usuarioService.findById(id);
         if(usuarioUpdate == null){
             return ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(usuarioService.update(usuario));
    }

    @DeleteMapping ("/id")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
       Usuario usuarioDelete = usuarioService.findById(id);
       if(usuarioDelete == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(usuarioService.delete(usuarioDelete));
    }

    @GetMapping("/usoMonopatines/{id}")
    public ResponseEntity<UsoMonopatinesDTO> getUsoMonopatines(@PathVariable Long id){
        return  ResponseEntity.ok(usuarioService.getUsoMonopatines(id));
    }

}
