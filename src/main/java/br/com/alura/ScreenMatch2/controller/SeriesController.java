package br.com.alura.ScreenMatch2.controller;


import br.com.alura.ScreenMatch2.dto.EpisodeDTO;
import br.com.alura.ScreenMatch2.dto.SeriesDTO;
import br.com.alura.ScreenMatch2.model.Series;
import br.com.alura.ScreenMatch2.repository.SerieRepository;
import br.com.alura.ScreenMatch2.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SeriesController {


    @Autowired
    private SeriesService service;

    @GetMapping
    public List<SeriesDTO> getSeries(){
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> getTop5Series(){
        return service.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SeriesDTO> getReleases(){
        return service.getReleases();
    }

    @GetMapping("/{id}")
    public SeriesDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeasons(@PathVariable Long id){
        return service.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{number}")
    public List<EpisodeDTO> getSeasonsByNumber(@PathVariable Long id, @PathVariable Long number){
        return service.getSeasonsByNumber(id, number);
    }

    @GetMapping("/categoria/{genderName}")
    public List<SeriesDTO> getSeriesByCategory(@PathVariable String genderName){
        return service.getSeriesByCategory(genderName);
    }

    @GetMapping("/{id}/temporadas/top5")
    public List<EpisodeDTO> getTop5Episodes(@PathVariable Long id){
        return service.getTop5Episodes(id);
    }
}
