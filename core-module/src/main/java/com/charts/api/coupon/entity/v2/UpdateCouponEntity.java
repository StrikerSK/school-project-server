package com.charts.api.coupon.entity.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_entity_v2")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UpdateCouponEntity extends AbstractUpdateEntity implements Comparable<UpdateCouponEntity> {

	public static final String PERSON_TYPE_COLUMN = "person_type";
	public static final String SELL_TYPE_COLUMN = "sell_type";
	public static final String VALIDITY_COLUMN = "validity";

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

	@Column(name = VALIDITY_COLUMN)
	private Validity validity;

	@Column(name = SELL_TYPE_COLUMN)
	private SellType sellType;

	@Column(name = PERSON_TYPE_COLUMN)
	private PersonType personType;

	@Override
	public int compareTo(UpdateCouponEntity o) {

		int yearComparison = this.getYear().compareTo(o.getYear());
		if (yearComparison == 0) {
			return this.getMonth().getOrderValue().compareTo(o.getMonth().getOrderValue());
		} else {
			return yearComparison;
		}

	}

}
