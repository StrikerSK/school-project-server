package com.charts.api.coupon.entity.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.types.Months;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
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
	@CsvBindByName(column = "code")
	private String code;

	@Column(name = "month")
	@CsvBindByName(column = "month")
	private Months month;

	@Column(name = "year")
	@CsvBindByName(column = "year")
	private Integer year;

	@Column(name = "value")
	@CsvBindByName(column = "value")
	private Integer value;

	@Column(name = VALIDITY_COLUMN)
	@CsvBindByName(column = "validity")
	private Validity validity;

	@Column(name = SELL_TYPE_COLUMN)
	@CsvBindByName(column = "sellType")
	private SellType sellType;

	@Column(name = PERSON_TYPE_COLUMN)
	@CsvBindByName(column = "personType")
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
