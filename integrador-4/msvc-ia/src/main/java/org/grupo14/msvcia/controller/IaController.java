package org.grupo14.msvcia.controller;

import lombok.RequiredArgsConstructor;
import org.grupo14.msvcia.service.IaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
public class IaController {

    private final IaService iaService;

    @PostMapping(value = "/prompt", produces = "application/json")
    public ResponseEntity<?> procesarPrompt(@RequestBody String prompt) {
        try{
            return iaService.procesarPrompt(prompt);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el prompt" + e.getMessage());
        }
    }
}
