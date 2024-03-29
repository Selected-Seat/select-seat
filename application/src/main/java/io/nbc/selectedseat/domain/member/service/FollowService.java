package io.nbc.selectedseat.domain.member.service;

import io.nbc.selectedseat.domain.member.dto.FollowInfo;
import io.nbc.selectedseat.domain.member.exception.ExistFollowException;
import io.nbc.selectedseat.domain.member.model.Follow;
import io.nbc.selectedseat.domain.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public FollowInfo followArtist(
        final Long memberId,
        final Long artistId
    ) {
        if (followRepository.findByMemberIdAndArtistId(memberId, artistId).isPresent()) {
            throw new ExistFollowException("이미 follow한 Artist 입니다");
        }
        Follow savedFollow = followRepository.save(Follow.builder()
            .memberId(memberId)
            .artistId(artistId)
            .build());
        return new FollowInfo(savedFollow.getFollowId());
    }
}

