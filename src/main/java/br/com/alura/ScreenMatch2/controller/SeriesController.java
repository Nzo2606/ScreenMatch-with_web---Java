package br.com.alura.ScreenMatch2.controller;


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
}
