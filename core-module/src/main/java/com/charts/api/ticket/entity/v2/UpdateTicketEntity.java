package com.charts.api.ticket.entity.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.Months;
import com.charts.api.ticket.enums.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets_v2")
@NoArgsConstructor
@Getter
@Setter
public class UpdateTicketEntity extends AbstractUpdateEntity implements Comparable<UpdateTicketEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@Column(name = "code")
	@JsonProperty("code")
	private String code;

	@Column(name = "month")
	private Months month;

	@Column(name = "year")
	private Integer year;

	@Column(name = "value")
	private Integer value;

	@Column(name = "ticket_type")
	private TicketType ticketType;

	@Column(name = "discounted")
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
