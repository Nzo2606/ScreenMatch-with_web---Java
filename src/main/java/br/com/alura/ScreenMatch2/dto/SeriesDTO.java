package br.com.alura.ScreenMatch2.dto;

import br.com.alura.ScreenMatch2.model.Category;
import jakarta.persistence.*;

public record SeriesDTO(Long id,
                        String title,
                        Integer totSeasons,
                        Double rating,
                        Category genre,
                        String actors,
                        String poster,
                        String plot) {
}
