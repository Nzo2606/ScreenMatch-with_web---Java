package br.com.alura.ScreenMatch2.service;

import br.com.alura.ScreenMatch2.dto.EpisodeDTO;
import br.com.alura.ScreenMatch2.dto.SeriesDTO;
import br.com.alura.ScreenMatch2.model.Category;
import br.com.alura.ScreenMatch2.model.Episode;
import br.com.alura.ScreenMatch2.model.Series;
import br.com.alura.ScreenMatch2.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    SerieRepository repository;

    public List<SeriesDTO> getAllSeries(){
        return dataSeriesConversor(repository.findAll());

    }

    public List<SeriesDTO> getTop5Series() {
        return dataSeriesConversor(repository.findTop5ByOrderByRatingDesc());
    }

    public List<SeriesDTO> getReleases() {
        return dataSeriesConversor(repository.searchReleases());
    }




    public SeriesDTO getById(Long id) {
        Optional<Series> series = repository.findById(id);

        if (series.isPresent()){
            Series s = series.get();
            return new SeriesDTO(s.getId(), s.getTitle(), s.getTotSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
        }
        return null;
    }

    public List<EpisodeDTO> getAllSeasons(Long id) {
        Optional<Series> series = repository.findById(id);

        if (series.isPresent()){
            Series s = series.get();
            return dataEpisodesConversor(s.getEpisodes());
        }
        return null;
    }

    public List<EpisodeDTO> getSeasonsByNumber(Long id, Long number) {
        return dataEpisodesConversor(repository.getEpisodesBySeasons(id, number));
    }



    public List<SeriesDTO> getSeriesByCategory(String genderName) {
        Category category = Category.fromPortuguese(genderName);
        return dataSeriesConversor(repository.findByGenre(category));
    }

    public List<EpisodeDTO> getTop5Episodes(Long id) {
        return dataEpisodesConversor(repository.totEpisodesPerSeries(id));
    }

    //CONVERSE

    private List<SeriesDTO> dataSeriesConversor(List<Series> series){
        return series.stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
                .toList();
    }

    private List<EpisodeDTO> dataEpisodesConversor(List<Episode> episodes){
        return episodes.stream()
                .map(e -> new EpisodeDTO(e.getSeason(), e.getEpisodeNumber(), e.getTitle()))
                .toList();
    }
}
