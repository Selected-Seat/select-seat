package io.nbc.selectedseat.batch.task.ticket;

import static io.nbc.selectedseat.domain.ticket.model.TicketRating.A;
import static io.nbc.selectedseat.domain.ticket.model.TicketRating.R;
import static io.nbc.selectedseat.domain.ticket.model.TicketRating.S;

import io.nbc.selectedseat.domain.ticket.model.TicketRating;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component("ticketCreateItemReader")
@RequiredArgsConstructor
public class TicketCreateItemReader implements ItemReader<TicketBatchEntity>,
    StepExecutionListener {

    private Iterator<TicketBatchEntity> iterator;

    @Value("#{jobParameters['concertId']}")
    private Long concertId;

    @Value("#{jobParameters['concertDateId']}")
    private Long concertDateId;

    @Value("#{jobParameters['numOfRow']}")
    private Long numOfRow;

    @Value("#{jobParameters['numOfRRatingTicket']}")
    private Long numOfRRatingTicket;

    @Value("#{jobParameters['numOfSRatingTicket']}")
    private Long numOfSRatingTicket;

    @Value("#{jobParameters['numOfARatingTicket']}")
    private Long numOfARatingTicket;

    @Override
    public TicketBatchEntity read()
        throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!iterator.hasNext()) {
            return null;
        }

        return iterator.next();
    }

    @Override
    public void beforeStep(final StepExecution stepExecution) {
        List<TicketBatchEntity> allSeats = new ArrayList<>();

        allSeats.addAll(
            generateTicketsByRating(numOfRRatingTicket, numOfRow, R, concertId,
                concertDateId)
        );

        allSeats.addAll(
            generateTicketsByRating(numOfSRatingTicket, numOfRow, S, concertId,
                concertDateId)
        );

        allSeats.addAll(
            generateTicketsByRating(numOfARatingTicket, numOfRow, A, concertId,
                concertDateId)
        );

        this.iterator = allSeats.iterator();
    }

    private List<TicketBatchEntity> generateTicketsByRating(
        final Long numOfSeats,
        final Long numOfRow,
        final TicketRating ticketRating,
        final Long concertId,
        final Long concertDateId
    ) {

        List<TicketBatchEntity> ticketBatchEntities = new ArrayList<>();

        for (int i = 0; i < numOfSeats; i++) {
            String ticketNumber =
                ((i / numOfRow) + 1) + ":" + ((i % numOfRow) + 1);

            LocalDateTime now = LocalDateTime.now();

            ticketBatchEntities.add(TicketBatchEntity.builder()
                .createdAt(now)
                .modifiedAt(now)
                .ticketRating(ticketRating.toString())
                .ticketNumber(ticketNumber)
                .concertId(concertId)
                .concertDateId(concertDateId)
                .build());
        }

        return ticketBatchEntities;
    }
}
