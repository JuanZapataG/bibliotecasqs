package com.sgb.bibliotecasqs.controller;

import com.sgb.bibliotecasqs.model.Autor;
import com.sgb.bibliotecasqs.services.AutoresSQSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/autores-libros")
public class AutorController {

    AutoresSQSService autoresSQSService;
    @PostMapping(path = "/aws", consumes = "application/json", produces = "application/json")
    public Mono<String> postMessageQueue(@RequestBody Autor autor) {
        return Mono.just(autoresSQSService.publishStandardQueueMessage(10, autor));
    }

}
