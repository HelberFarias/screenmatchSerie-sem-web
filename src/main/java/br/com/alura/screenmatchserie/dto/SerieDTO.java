package br.com.alura.screenmatchserie.dto;

import br.com.alura.screenmatchserie.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporada,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {}
