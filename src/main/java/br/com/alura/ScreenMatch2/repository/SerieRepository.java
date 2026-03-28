package br.com.alura.ScreenMatch2.repository;

import br.com.alura.ScreenMatch2.model.Category;
import br.com.alura.ScreenMatch2.model.Episode;
import br.com.alura.ScreenMatch2.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainingIgnoreCase(String nomeSerie);

    List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, double baseRating);

    List<Series> findTop5ByOrderByRatingDesc();

    List<Series> findByGenre(Category category);

    List<Series> findByTotSeasonsLessThanEqualAndRatingGreaterThanEqual(Integer numberOfSeasons, double baseRating);

    @Query("select s from Series s WHERE s.totSeasons <= :totSeasons AND s.rating >= :rating")
    List<Series> seriesPerSeasonAndRating(int totSeasons, double rating);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:episodeExcerpt%")
    List<Episode> episodesPerExcerpt(String episodeExcerpt);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s= :series ORDER BY e.rating DESC LIMIT 5" )
    List<Episode> totEpisodesPerSeries(Series series);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s= :series AND YEAR(e.releaseDate) >= :launchYear")
    List<Episode> episodesPerYear(Series series, int launchYear);
}
