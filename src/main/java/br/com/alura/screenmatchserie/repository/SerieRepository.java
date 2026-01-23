package br.com.alura.screenmatchserie.repository;

import br.com.alura.screenmatchserie.model.Categoria;
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
            select s 
            from Serie s 
            WHERE s.totalTemporada <= :totalTemporada
            AND s.avaliacao >= :avaliacao
            """) // ":" significa parametro
    List<Serie> seriesPorTemporadaEAvaliacao(Integer totalTemporada, Double avaliacao);

}
