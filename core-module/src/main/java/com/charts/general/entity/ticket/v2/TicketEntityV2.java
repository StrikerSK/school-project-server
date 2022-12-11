package com.charts.general.entity.ticket.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.TicketTypes;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "updated_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntityV2 extends AbstractUpdateEntity {

	@Column(name = "typ_listka")
	private TicketTypes ticketType;

	@Column(name = "zlavneny")
	private Boolean discounted;

}
