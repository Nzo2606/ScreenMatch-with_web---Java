package br.com.alura.ScreenMatch2.service;

import br.com.alura.ScreenMatch2.dto.SeriesDTO;
import br.com.alura.ScreenMatch2.model.Series;
import br.com.alura.ScreenMatch2.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    SerieRepository repository;

    public List<SeriesDTO> getAllSeries(){
        return dataConversor(repository.findAll());

    }

    public List<SeriesDTO> getTop5Series() {
        return dataConversor(repository.findTop5ByOrderByRatingDesc());
    }

    public List<SeriesDTO> getReleases() {
        return dataConversor(repository.searchReleases());
    }

    private List<SeriesDTO> dataConversor(List<Series> series){
        return series.stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitle(), s.getTotSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
                .toList();
    }


    public SeriesDTO getById(Long id) {
        Optional<Series> series = repository.findById(id);

        if (series.isPresent()){
            Series s = series.get();
            return new SeriesDTO(s.getId(), s.getTitle(), s.getTotSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
        }
        return null;
    }
}
