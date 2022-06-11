package com.charts.general.entity.coupon.updated;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
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
public class UpdateCouponEntity extends AbstractUpdateEntity {

	@Column(name = "platnost")
	private Validity validity;

	@Column(name = "typ_predaja")
	private SellType sellType;

	@Column(name = "typ_osoby")
	private PersonType personType;

}
