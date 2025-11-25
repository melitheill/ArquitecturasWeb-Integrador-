package org.grupo14.msvcia.service;

import org.grupo14.msvcia.client.GroqClient;
import org.grupo14.msvcia.dto.RespuestaApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service

public class IaService {

    private final RestTemplate restTemplate;

    private final GroqClient groqClient;

    private final String ENDPOINTS;

    private static final Logger log = LoggerFactory.getLogger(IaService.class);

    public IaService(GroqClient groqClient) {
        this.groqClient = groqClient;
        this.restTemplate = new RestTemplate();
        this.ENDPOINTS = cargarListadoDeEndpoints("servicios.txt");
    }

    private String cargarListadoDeEndpoints(String nombreArchivo) {
        try(InputStream inputStream = new ClassPathResource(nombreArchivo).getInputStream()){
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e){
            throw new RuntimeException("Error al cargar el esquema " + nombreArchivo, e);
        }
    }

    public ResponseEntity<?> procesarPrompt(String promptUsuario){
        try{
            String promptFinal = """
                    Este es el listado de mis endpoints:
                    %s

                    Basándote exclusivamente en este listado, devolveme ÚNICAMENTE la url del endpoint
                    (sin texto adicional, sin markdown, sin comentarios, sin tipo de request (GET/POST/PUT/DELETE), sin comillas" 
                    que responda a la siguiente necesidad:
                    %s
                    """.formatted(ENDPOINTS, promptUsuario);

            log.info("==== PROMPT ENVIADO A LA IA ====\n{}", promptFinal);
            String respuestaIa = groqClient.preguntar(promptFinal);
            log.info("==== RESPUESTA IA ====\n{}", respuestaIa);
            Object response = restTemplate.getForObject("http://localhost:8090" + respuestaIa, Object.class);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            log.error("Fallo al procesar prompt", e);
            return new ResponseEntity<>(
                    new RespuestaApi<>(false, "Error al procesar el prompt: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

}
