package io.nbc.selectedseat.domain.concert.repository;

import io.nbc.selectedseat.domain.concert.dto.ConcertSearchMapper;
import io.nbc.selectedseat.domain.concert.model.Concert;
import io.nbc.selectedseat.domain.concert.model.ConcertDate;
import io.nbc.selectedseat.domain.concert.model.ConcertRating;
import io.nbc.selectedseat.domain.concert.model.Performer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ConcertRepository {

    Long save(final Concert concert);

    Optional<Concert> findById(final Long concertId);

    Concert update(final Concert concert);

    void delete(final Long concertId);

    List<Concert> getConcerts();

    List<Concert> getConcertsByConcertIds(final List<Long> concertIds);

    Optional<ConcertRating> getConcertRating(final Long ratingId);

    List<ConcertDate> getConcertDates(final Long concertId);

    List<Concert> getConcertsByCategory(final Long categoryId);

    Optional<ConcertDate> getConcertDate(Long concertId);

    List<Concert> searchConcertByTextAndFilter(ConcertSearchMapper concertSearchVo, int page,
        int size);

}
