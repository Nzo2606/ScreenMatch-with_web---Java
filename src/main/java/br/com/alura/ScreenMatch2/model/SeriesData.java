package br.com.alura.ScreenMatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public record SeriesData(@JsonAlias ("Title") String title,
                         @JsonAlias ("totalSeasons") Integer totSeasons,
                         @JsonAlias ("imdbRating") String rating) {
}


