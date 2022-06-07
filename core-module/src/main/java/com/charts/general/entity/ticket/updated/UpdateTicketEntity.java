package com.charts.general.entity.ticket.updated;

import com.charts.general.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "updated_coupons")
@NoArgsConstructor
@Getter
@Setter
public class UpdateTicketEntity extends AbstractEntity {

	@Column(name = "meno")
	private String name;

	@Column(name = "hodnota")
	private Long value;

	@Column(name = "zlavneny")
	private Boolean discounted;

}
