package com.charts.general.entity.coupon.updated;

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

import javax.persistence.*;

@Entity
@Table(name = "coupon_entity_v2")
@NoArgsConstructor
@Getter
@Setter
public class UpdateCouponEntity extends AbstractUpdateEntity {

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

	@Column(name = "validity")
	private Validity validity;

	@Column(name = "sellType")
	private SellType sellType;

	@Column(name = "personType")
	private PersonType personType;

}
