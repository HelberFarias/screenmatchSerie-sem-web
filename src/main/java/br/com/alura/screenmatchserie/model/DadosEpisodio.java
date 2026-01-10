package br.com.alura.screenmatchserie.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias ({"Title", "title"}) String titulo,
                            @JsonAlias ({"episode", "Episode"}) Integer episodio,
                            @JsonAlias ("imdbRating")String avaliacao,
                            @JsonAlias ("Released") String dataLancamento ) {
}
