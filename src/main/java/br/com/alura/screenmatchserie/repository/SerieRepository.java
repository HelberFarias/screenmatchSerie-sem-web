package br.com.alura.screenmatchserie.repository;

import br.com.alura.screenmatchserie.model.Categoria;
import br.com.alura.screenmatchserie.model.Episodio;
import br.com.alura.screenmatchserie.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase (String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc(); //desc = ordenar em ordem decrescente

    List<Serie> findByGenero(Categoria categoria);

    //List<Serie> findByTotalTemporadaLessThanEqualAndAvaliacaoGreaterThanEqual(Integer totalTemporada, Double avaliacao);

    @Query ("""
            SELECT s 
            FROM Serie s 
            WHERE s.totalTemporada <= :totalTemporada
            AND s.avaliacao >= :avaliacao
            """) // ":" significa parametro
    List<Serie> seriesPorTemporadaEAvaliacao(Integer totalTemporada, Double avaliacao);

    @Query ("""
            SELECT e
            FROM Serie s
            JOIN s.episodios e
            WHERE e.titulo
            ILIKE %:trechoEpisodio%
            """)
    List<Episodio> episodioPorTrecho(String trechoEpisodio);

    @Query ("""
            SELECT e
            FROM Serie s
            JOIN s.episodios e
            WHERE s = :serie
            ORDER BY e.avaliacao 
            DESC LIMIT 5
            """)
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query ("""
            SELECT e
            FROM Serie s
            JOIN s.episodios e
            WHERE s = :serie
            AND YEAR (e.dataLancamento) >= :anoLancamento
            """)
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);
}
