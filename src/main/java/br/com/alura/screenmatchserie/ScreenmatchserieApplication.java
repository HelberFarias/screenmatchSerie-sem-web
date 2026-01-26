package br.com.alura.screenmatchserie;

import br.com.alura.screenmatchserie.model.DadosTemporada;
import br.com.alura.screenmatchserie.principal.Principal;
import br.com.alura.screenmatchserie.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchserieApplication  {

    public static void main(String[] args) {
		SpringApplication.run(ScreenmatchserieApplication.class, args);
	}


}
