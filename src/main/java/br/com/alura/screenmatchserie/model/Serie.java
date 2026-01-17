package br.com.alura.screenmatchserie.model;

import java.util.OptionalDouble;

public class Serie {
    private  String titulo;
    private  Integer totalTemporada;
    private Double avaliacao;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie (DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporada = dadosSerie.totalTemporada();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0.0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim()); //split pra transformar em lista e pegar o primeiro elemento, primeiro genero [0] e o trim pra tirar os espaços
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();


    }


}
