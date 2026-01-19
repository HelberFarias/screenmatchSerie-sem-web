package br.com.alura.screenmatchserie.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table (name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private double avalicao;
    private LocalDate dataLacamento;
    @ManyToOne
    private Serie serie;

    public Episodio () {}

    public Episodio (Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.episodio();

        try {
            this.avalicao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (NumberFormatException e) {
            this.avalicao = 0;
        }
        try {
            this.dataLacamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException e) {
            this.dataLacamento = null;
        }

}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public double getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(double avalicao) {
        this.avalicao = avalicao;
    }

    public LocalDate getDataLacamento() {
        return dataLacamento;
    }

    public void setDataLacamento(LocalDate dataLacamento) {
        this.dataLacamento = dataLacamento;
    }

    @Override
    public String toString() {
        return  "temporada= " + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avalicao=" + avalicao +
                ", dataLacamento=" + dataLacamento
                ;
    }
}
