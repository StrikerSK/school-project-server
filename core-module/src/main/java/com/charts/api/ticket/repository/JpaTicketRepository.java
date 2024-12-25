package com.charts.api.ticket.repository;

import com.charts.api.ticket.entity.v1.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketEntity, Long> {
}
