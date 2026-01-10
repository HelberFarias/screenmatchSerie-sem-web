package br.com.alura.screenmatchserie.principal;

import br.com.alura.screenmatchserie.model.DadosEpisodio;
import br.com.alura.screenmatchserie.model.DadosSerie;
import br.com.alura.screenmatchserie.model.DadosTemporada;
import br.com.alura.screenmatchserie.model.Episodio;
import br.com.alura.screenmatchserie.service.ConsumoApi;
import br.com.alura.screenmatchserie.service.ConverteDados;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String  API_KEY = "&apikey=d4a73045";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    Scanner input = new Scanner(System.in);
    List<DadosTemporada> temporadas = new ArrayList<>();

    public void exibeMenu() {
        System.out.println("Digite o nome da serie: ");
        var nomeSerie = input.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        for (int i = 1; i <= dados.totalTemporada(); i++) {
            json = consumo.obterDados(ENDERECO +
                    nomeSerie.replace(" ", "+") +
                    "&season=" +
                    i +
                    API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
//        temporadas.forEach(System.out::println);
//        for (int i = 0; i <= dados.totalTemporada(); i++) {
//            List<DadosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
//                for (int j = 0; j <= episodiosTemporadas.size(); j++) {
//                    System.out.println(episodiosTemporadas.get(j).titulo());
//            }
//        }
        temporadas.forEach(t -> t.episodios());
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


        // Estudo do Peek
        // Função usada para acompanhar cada fluxo do stream() e ter melhor controle sobre

//        System.out.println("\nTop 10 Episodios");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro" + e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e -> System.out.println("Primeira ordenação" + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite" + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento" + e))
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        // Encontrando a primeira ocorrencia .findFirst
        // .findFirst não tem um retorno especifico e por isso, é bom guarda-lo em CONTAINER do tipo <Optional>
        // Esse container é especial e sua caracteristica especial é a possibilidade dele ter ou não um conteudo dentro
        // Metodo usado para buscar episodio por nome ou trecho do nome (titulo episodio).
        // Optional não é uma barra de busca, mas sim o resultado consciente da busca

//        System.out.println("Digite o nome do titulo: ");
//        var trechoTitulo = input.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado!");
//            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//        } else {
//            System.out.println("Episodio não encontrado!");
//        }

        // Estudo do MAP
        // Estrutura AGRUPAMENTO de dados que necessita de uma chave e um valor

        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(e -> e.getAvalicao() > 0.0)
                .collect(Collectors.groupingBy(e -> e.getTemporada(),
                         Collectors.averagingDouble(e -> e.getAvalicao())));
        System.out.println(avaliacaoPorTemporada);


        // Coletando estatisticas
        // Uso do DoubleSummaryStatistics

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvalicao() > 0.0)
                .collect(Collectors.summarizingDouble(e -> e.getAvalicao()));
        System.out.println("Media: " + est.getAverage());
        System.out.println("Melhor avaliação: " + est.getMax());
        System.out.println("Pior avaliação: " + est.getMin());
        System.out.println("Quantidade avaliados: " + est.getCount());







//        System.out.println("A partir de que ano? ");
//        var ano = input.nextInt();
//        input.nextLine();
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLacamento() != null && e.getDataLacamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                "Episodio: " + e.getTitulo() +
//                                "Data de Lançamento: " + e.getDataLacamento().format(formatador)
//                ));










//        List<String> nomes = Arrays.asList("Helber", "Helena", "Junyo", "Gomes", "Farias");
//        nomes.stream()
//                .sorted() //operacao intermediaria
//                .limit(5)
//                .filter(n -> n.startsWith("H"))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println); //operacao final
//
//        //OPERACAO INTERMEDIARIA: gera um novo fluxo de dado e possibilitou fazer outra ação nesse fluxo
//        //OPERACAO FINAL: operações que finalizam o stream() (ex acima.: forEach)
    }
}
