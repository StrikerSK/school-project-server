package com.charts.api.ticket.repository;

import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketV2Repository extends JpaRepository<UpdateTicketEntity, Long> {
}
