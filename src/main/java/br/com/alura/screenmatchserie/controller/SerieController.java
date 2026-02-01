package br.com.alura.screenmatchserie.controller;

import br.com.alura.screenmatchserie.dto.EpisodioDTO;
import br.com.alura.screenmatchserie.dto.SerieDTO;
import br.com.alura.screenmatchserie.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping ("/series") //anotação que deixa "fixo" esse endpoint para evitar repetições nos GetMapping abaixo
public class SerieController {

    @Autowired
    SerieService servico;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Serie() {
        return servico.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamento() {
        return servico.obterLancamento();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas") //esse endpoint apareceu na app web (relaxe, vc pega o jeito)
    public List<EpisodioDTO> obterTodasAsTemporadas (@PathVariable Long id) {
        return servico.obterTodasAsTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero) {
        return servico.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero) {
        return servico.obterSeriesPorCategoria(nomeGenero);
    }


}
