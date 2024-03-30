package io.nbc.selectedseat.db.core.domain.member.repository;

import io.nbc.selectedseat.db.core.domain.member.entity.FollowEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

    Optional<FollowEntity> findByMemberIdAndArtistId(final Long memberId, final Long artistId);

    @Query("SELECT f.artistId FROM FollowEntity f WHERE f.memberId = :memberId")
    List<Long> findArtistIdByMemberId(@Param("memberId") Long memberId);
}
