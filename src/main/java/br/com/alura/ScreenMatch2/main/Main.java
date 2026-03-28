package br.com.alura.ScreenMatch2.main;

import br.com.alura.ScreenMatch2.model.*;
import br.com.alura.ScreenMatch2.repository.SerieRepository;
import br.com.alura.ScreenMatch2.service.APIConsumn;
import br.com.alura.ScreenMatch2.service.DataConversor;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final String ADDRESS = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=" + System.getenv("OMDB_apiKey");

    private Scanner reader = new Scanner(System.in);

    private APIConsumn consumn = new APIConsumn();

    private DataConversor conversor = new DataConversor();

    private List<SeriesData> seriesData = new ArrayList<>();

    private SerieRepository repository;

    private List<Series> series = new ArrayList<>();

    private Optional<Series> seriesSearch;


    public Main(SerieRepository repository) {
        this.repository = repository;
    }


    public void displaysMenu(){
    var option = -1;


        while(option != 0) {
            var menu = """
            
            1  -  Search series
            2  -  Search episodes
            3  -  List searched series
            4  -  Search series by title
            5  -  Search series by actor
            6  -  Top 5 Series
            7  -  Search series by genre
            8  -  Search series by number of seasons
            9  -  Search episode from excerpt
            10 -  Top 5 episodes from a series
            11 -  Search episodes starting from a specific date
            0  -  Leave
            """;

            System.out.println(menu);
            System.out.print("Option: ");
            option = reader.nextInt();
            reader.nextLine();

            switch (option) {
                case 1:
                    searchWebSeries();
                    break;
                case 2:
                    searchEpisodeFromSerie();
                    break;
                case 3:
                    listSearchedSeries();
                    break;
                case 4:
                    searchSeriesByTitle();
                    break;
                case 5:
                    searchSeriesByActor();
                    break;
                case 6:
                    searchTop5Series();
                    break;
                case 7:
                    searchSeriesByGenre();
                    break;
                case 8:
                    searchSeriesByNumberOfSeasons();
                    break;
                case 9:
                    searchEpisodePerExcerpt();
                    break;
                case 10:
                    topEpisodesPerSerie();
                    break;
                case 11:
                    searchEpisodeAfterADate();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }



//        System.out.print("Enter the series name to search: ");
//        var seriesName = reader.nextLine();
//        var json = consumn.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);
//        SeriesData seriesData = conversor.getData(json, SeriesData.class);
//        System.out.println(seriesData);
//
//        //SEASONS SEARCH
//        List<SeasonsData> seasons = new ArrayList<>();
//        for(int i = 1; i <= seriesData.totSeasons(); i++){
//
//            json = consumn.getData(ADDRESS + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
//
//            SeasonsData seasonsData = conversor.getData(json, SeasonsData.class);
//            seasons.add(seasonsData);
//        }
//        seasons.forEach(System.out::println);

//        for(int i = 0; i < seriesData.totSeasons(); i++){
//            List<EpisodesData> seasonsEpisodes = seasons.get(i).episodes();
//            for (int j = 0; j < seasonsEpisodes.size(); j++){
//                System.out.println(seasonsEpisodes.get(j).title());
//            }
//        }
//
//        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

    // TOP 5 EPISÓDIOS
//
//        System.out.println("Top 10 episódios");
//        List<EpisodesData> episodesData = seasons.stream()
//                .flatMap(t -> t.episodes().stream())
//                .collect(Collectors.toList());
//
//        episodesData.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro Filtro " + e))
//                .sorted(Comparator.comparing(EpisodesData::rating).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite " + e))
//                .map(e -> e.title().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

//    // UTILIZANDO A CLASSE EPISODE

//        List<Episode> episodes = seasons.stream()
//                .flatMap(t -> t.episodes().stream()
//                        .map(eData -> new Episode(t.seasonNumber(), eData))
//                ).collect(Collectors.toList());
//
//        episodes.forEach(System.out::println);

        // FAZENDO A BUSCA POR NOME DE EPISÓDIO
//            System.out.print("Enter a portion of the episode title: ");
//            var excerptTitle =  reader.nextLine();
//            Optional<Episode> searchedEpisode = episodes.stream()
//                .filter(e -> e.getTitle().toUpperCase().contains(excerptTitle.toUpperCase()))
//                .findFirst();
//
//            if (searchedEpisode.isPresent()){
//                System.out.println("Episode found!");
//                System.out.println("Name: " + searchedEpisode.get().getTitle());
//                System.out.println("Number: " + searchedEpisode.get().getEpisodeNumber());
//                System.out.println("Season: "+ searchedEpisode.get().getSeason());
//            }else{
//                System.out.println("Episode not found!");
//            }


//        System.out.println("Since what year do you want to search the episodes?");
//        var year = reader.nextInt();
//        reader.nextLine();
//
//        LocalDate searchDate = LocalDate.of(year, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
//                .forEach(e -> System.out.println(
//                        "Season: " + e.getSeason() +
//                                " Episode: " + e.getTitle() +
//                                    " Release Date: " + e.getReleaseDate().format(formatter)
//
//                        )
//                        );
//
//

//   AVALIAÇÃO DAS TEMPORADAS

        // MAP
//            Map<Integer, Double> ratingPerSeason = episodes.stream()
//                    .filter(e ->  e.getRating() > 0.0)
//                    .collect(Collectors.groupingBy(Episode::getSeason,
//                             Collectors.averagingDouble(Episode::getRating)));
//
//            System.out.println("\n" + ratingPerSeason);
//
//        // STATISTICS
//            DoubleSummaryStatistics est = episodes.stream()
//                    .filter(e ->  e.getRating() > 0.0)
//                    .collect(Collectors.summarizingDouble(Episode::getRating));
//
//            System.out.println("\n" +
//                    "Average = " + est.getAverage() +
//                    "\nBest episode = " + est.getMax() +
//                    "\nWorst episode = " + est.getMin() +
//                    "\nAmount = " + est.getCount()
//                    );

//        List<String> names = Arrays.asList("Enzo", "Arthur", "Ramirez", "Elis");
//
//        names.stream()
//                .sorted()
//                .limit(3)
//                .filter(n -> n.startsWith("E"))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println);


    private void searchWebSeries() {
        SeriesData data = getSeriesData();
        Series series = new Series(data);
        try{
            repository.save(series);
        }catch (DataIntegrityViolationException e){
            System.out.println("The given series already exist in the database!");
        }

        System.out.println(data);
    }

    private SeriesData getSeriesData() {
        System.out.print("Enter the name of the series: ");
        var seriesName = reader.nextLine().toLowerCase();
        var json = consumn.getData(ADDRESS + seriesName.replace(" " , "+") + API_KEY);
        SeriesData seriesData = conversor.getData(json, SeriesData.class);
        return seriesData;
    }

    private void searchEpisodeFromSerie() {

        listSearchedSeries();

        System.out.println("Choose a series by its name");
        var seriesName = reader.nextLine();

        Optional<Series> serieOptional = repository.findByTitleContainingIgnoreCase(seriesName);

        if (serieOptional.isPresent()){
            var choosedSeries = serieOptional.get();
            List<SeasonsData> seasons = new ArrayList<>();

            for (int i = 1; i <= choosedSeries.getTotSeasons(); i++) {
                var json = consumn.getData(ADDRESS + choosedSeries.getTitle().replace(" ", "+")
                        + "&Season=" + i + API_KEY);
                SeasonsData seasonsData = conversor.getData(json, SeasonsData.class);
                seasons.add(seasonsData);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes =  seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.seasonNumber(), e)))
                    .toList();

            choosedSeries.setEpisodes(episodes);
            repository.save(choosedSeries);
        }else {
            System.out.println("Series not found!");
        }
    }
    private void listSearchedSeries() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);
    }


    private void searchSeriesByTitle() {
        System.out.print("Choose a series by its name: ");
        var seriesName = reader.nextLine();
        seriesSearch = repository.findByTitleContainingIgnoreCase(seriesName);

        if (seriesSearch.isPresent()){
            System.out.println("Series data: " + seriesSearch.get());
        }
        else {
            System.out.println("Series not found!");
        }
    }

    private void searchSeriesByActor() {
        System.out.print("What`s the actor name?: ");
        var actorName = reader.nextLine();
        System.out.print("Ratings from what value?: ");
        var baseRating = reader.nextDouble();
        List<Series> searchedSeries = repository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName, baseRating);
        System.out.println("Series that " + actorName + " casted:");
        searchedSeries
                .forEach(s -> System.out.println("  - " + s.getTitle() + " | rating: " + s.getRating()));
    }

    private void searchTop5Series() {
        List<Series> top5Series = repository.findTop5ByOrderByRatingDesc();
        top5Series.forEach(s -> System.out.println("  - " + s.getTitle() + " | rating: " + s.getRating()));
    }

    private void searchSeriesByGenre() {
        System.out.print("What's the genre?: ");
        var genreName = reader.nextLine();
        Category category = Category.fromPortuguese(genreName);
        List<Series> seriesPerGenre = repository.findByGenre(category);
        System.out.println("Series of the category: " + category);
        seriesPerGenre.forEach(System.out::println);
    }

    private void searchSeriesByNumberOfSeasons() {
        System.out.print("What is the max number of seasons?: ");
        var numberOfSeasons = reader.nextInt();
        System.out.print("Rating from what value?: ");
        var baseRating = reader.nextDouble();
        List<Series> seriesWithBaseNumberOfSeasons = repository.seriesPerSeasonAndRating(numberOfSeasons, baseRating);
        if (seriesWithBaseNumberOfSeasons.isEmpty()){
            System.out.println("There aren't any series with that number of seasons above that rating!");
        }else {
            System.out.println("Series with seasons under or equal than " + numberOfSeasons + " with rating above " + baseRating);
            seriesWithBaseNumberOfSeasons.forEach(s ->
                    System.out.println("  - " + s.getTitle() + " | Total seasons: " +
                            s.getTotSeasons()+ " | Rating: " + s.getRating()));
        }

    }


    private void searchEpisodePerExcerpt() {
        System.out.print("What`s the episode name?: ");
        var episodeExcerpt = reader.nextLine();
        List<Episode> episodesFound = repository.episodesPerExcerpt(episodeExcerpt);
        episodesFound.forEach(e ->
                System.out.printf("Series: %s Season %s - Episode %s - %s\n",
                        e.getSerie().getTitle(),
                        e.getSeason(),
                        e.getEpisodeNumber(),
                        e.getTitle()));
    }


    private void topEpisodesPerSerie() {
        searchSeriesByTitle();
        if (seriesSearch.isPresent()){
            Series series = seriesSearch.get();
            List<Episode> topEpisodes = repository.totEpisodesPerSeries(series);
            System.out.println("\nTop 5 episodes of " + series.getTitle());
            topEpisodes.forEach(e ->
                    System.out.printf("Series: %s - Season %s - Episode %s : %s - Rating: %s\n",
                            e.getSerie().getTitle(),
                            e.getSeason(),
                            e.getEpisodeNumber(),
                            e.getTitle(),
                            e.getRating()));
        }
    }

    private void searchEpisodeAfterADate() {
        searchSeriesByTitle();
        if (seriesSearch.isPresent()){
            Series series = seriesSearch.get();
            System.out.print("Enter the base launch year: ");
            var launchYear = reader.nextInt();
            reader.nextLine();

            List<Episode> episodesPerYear = repository.episodesPerYear(series, launchYear);
            episodesPerYear.forEach(System.out::println);
        }
    }


}
