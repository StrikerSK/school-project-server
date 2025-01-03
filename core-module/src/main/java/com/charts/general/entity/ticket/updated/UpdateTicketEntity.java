package com.charts.general.entity.ticket.updated;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.TicketTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "updated_tickets")
@NoArgsConstructor
@Getter
@Setter
public class UpdateTicketEntity extends AbstractUpdateEntity {

	@Column(name = "typ_listka")
	private TicketTypes ticketType;

	@Column(name = "zlavneny")
	private Boolean discounted;

}
