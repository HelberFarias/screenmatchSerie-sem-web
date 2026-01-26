package br.com.alura.screenmatchserie.controller;

import br.com.alura.screenmatchserie.model.Serie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @GetMapping ("/series")
    public List<Serie> obterSeries() {

    }
}
