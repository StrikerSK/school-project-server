package com.charts.api.ticket.entity.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.types.Months;
import com.charts.api.ticket.enums.TicketType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
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
	@CsvBindByName
	private String code;

	@Column(name = "month")
	@CsvBindByName
	private Months month;

	@Column(name = "year")
	@CsvBindByName
	private Integer year;

	@Column(name = "value")
	@CsvBindByName
	private Integer value;

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
