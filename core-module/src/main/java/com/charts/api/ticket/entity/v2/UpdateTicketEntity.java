package com.charts.api.ticket.entity.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.api.ticket.enums.TicketType;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "tickets_v2")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UpdateTicketEntity extends AbstractUpdateEntity implements Comparable<UpdateTicketEntity> {

	@Column(name = "ticket_type")
	@CsvBindByName
	private TicketType ticketType;

	@Column(name = "discounted")
	@CsvBindByName
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
