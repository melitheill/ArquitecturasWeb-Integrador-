package org.grupo14.msvcusuario.controller;

import org.grupo14.msvcusuario.dto.UsoMonopatinesDTO;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.model.Monopatin;
import org.grupo14.msvcusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/usoMonopatines/{id}/{year}/{mesInicio}/{mesFin}")
    public ResponseEntity<Map<Long, UsoMonopatinesDTO>> getUsoMonopatines(@PathVariable Long id, @PathVariable int year, @PathVariable int mesInicio, @PathVariable int mesFin, @RequestParam(defaultValue = "false", required = false) boolean otrosUsuarios){
        return  ResponseEntity.ok(usuarioService.getUsoMonopatines(id, year, mesInicio, mesFin, otrosUsuarios));
    }
    @GetMapping("/monopatinesCercanos/{id}")
    public ResponseEntity<List<Monopatin>> getByZona(@PathVariable Long id){
        List<Monopatin> lista = usuarioService.getMonopatinMasCercano(id);
        return ResponseEntity.ok(lista);
    }
    //http://localhost:8003/api/usuario/monopatinesCercanos/7
    //[{"id":13,"estado":"disponible","latitud":52.52,"longitud":13.405},{"id":32,"estado":"disponible","latitud":52.52,"longitud":13.405}

}
