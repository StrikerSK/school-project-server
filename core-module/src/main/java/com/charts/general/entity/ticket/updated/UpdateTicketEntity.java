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
public class UpdateTicketEntity extends AbstractUpdateEntity implements Comparable<UpdateTicketEntity> {

	@Column(name = "typ_listka")
	private TicketTypes ticketType;

	@Column(name = "zlavneny")
	private Boolean discounted;

	@Override
	public int compareTo(UpdateTicketEntity o) {

		int yearComparison = this.getYear().compareTo(o.getYear());
		if (yearComparison == 0) {
			return this.getMonth().getOrderValue().compareTo(o.getMonth().getOrderValue());
		} else {
			return yearComparison;
		}

	}

}
