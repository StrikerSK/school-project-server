package com.charts.general.repository.ticket;

import com.charts.general.entity.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketEntity, Long> {
}
