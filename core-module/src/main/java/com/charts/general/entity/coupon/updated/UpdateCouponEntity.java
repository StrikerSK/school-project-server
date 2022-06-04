package com.charts.general.entity.coupon.updated;

import com.charts.general.entity.GeneralEntity;
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
public class UpdateCouponEntity extends GeneralEntity {

	@Column(name = "hodnota")
	private Integer value;

	@Column(name = "platnost")
	private String validity;

	@Column(name = "typ_predaja")
	private String sellType;

	@Column(name = "typ_osoby")
	private String personType;

}
