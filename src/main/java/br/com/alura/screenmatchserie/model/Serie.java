package br.com.alura.screenmatchserie.model;

import br.com.alura.screenmatchserie.service.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) //não permite repetição do titulo da serie
    private String titulo;
    private Integer totalTemporada;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;
    //@Transient anotação que informa pra JPA que não precisa se impotar como essa entidade ainda
    @OneToMany (mappedBy = "serie", cascade = CascadeType.ALL)
    //precisar indicar qual atributo da outra classe que será mapeado e o cascade serve para indicar que queremos salvar os episodios
    private List<Episodio> episodios = new ArrayList<>();

    public Serie () {} //esse construtor foi feito pq é exigencia da JPA

    public Serie (DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporada = dadosSerie.totalTemporada();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0.0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim()); //split pra transformar em lista e pegar o primeiro elemento, primeiro genero [0] e o trim pra tirar os espaços
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse());
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporada() {
        return totalTemporada;
    }

    public void setTotalTemporada(Integer totalTemporada) {
        this.totalTemporada = totalTemporada;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return
                "titulo= '" + titulo + '\'' +
                ", genero= " + genero +
                ", totalTemporada= " + totalTemporada +
                ", avaliacao= " + avaliacao +
                ", atores= '" + atores + '\'' +
                ", poster= '" + poster + '\'' +
                ", sinopse= '" + sinopse + '\'' +
                '}';
    }
}
