package io.nbc.selectedseat.domain.artist.repository;

import io.nbc.selectedseat.domain.artist.model.Artist;
import java.util.List;

public interface ArtistRepository {

    Long save(Artist artist);

    Artist getArtist(final Long artistId);

    Long update(
        final Long artistId,
        final String name,
        final String profile
    );

    void delete(final Long artistId);

    List<Artist> findArtistsByIdList(final List<Long> artistIds);

    Artist findByName(String text);
}
