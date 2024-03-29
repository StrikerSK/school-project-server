package com.charts.general.entity.ticket.v2;

import com.charts.general.entity.AbstractEntityV2;
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
public class TicketEntityV2 extends AbstractEntityV2 {

	@Column(name = "typ_listka")
	private TicketTypes ticketType;

	@Column(name = "zlavneny")
	private Boolean discounted;

}
