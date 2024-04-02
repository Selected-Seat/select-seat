package io.nbc.selectedseat.db.core.domain.reservation.entity;

import io.nbc.selectedseat.db.core.domain.common.BaseEntity;
import io.nbc.selectedseat.domain.reservation.model.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "reservations")
public class ReservationEntity extends BaseEntity {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long reservationId;

    @Column(name = "concert_id", nullable = false)
    public Long concertId;

    @Column(name = "member_id", nullable = false)
    public Long memberId;

    @Column(name = "ticket_id", nullable = false)
    public Long ticketId;

    @Column(name = "ticket_price_id", nullable = false)
    public Long ticketPriceId;

    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime reservedAt;

    public static ReservationEntity from(final Reservation reservation) {
        return ReservationEntity.builder()
            .concertId(reservation.getConcertId())
            .memberId(reservation.getMemberId())
            .ticketId(reservation.getTicketId())
            .ticketPriceId(reservation.getTicketPriceId())
            .build();
    }

    public Reservation toModel() {
        return Reservation.builder()
            .reservationId(reservationId)
            .concertId(concertId)
            .memberId(memberId)
            .ticketId(ticketId)
            .ticketPriceId(ticketPriceId)
            .reservedAt(reservedAt)
            .createdAt(getCreatedAt())
            .modifiedAt(getModifiedAt())
            .deletedAt(getDeletedAt())
            .build();
    }
}
