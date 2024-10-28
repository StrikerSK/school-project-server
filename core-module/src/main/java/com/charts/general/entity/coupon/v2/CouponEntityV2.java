package com.charts.general.entity.coupon.v2;

import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.PersonType;
import com.charts.general.entity.enums.SellType;
import com.charts.general.entity.enums.Validity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_entity_v2")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class CouponEntityV2 extends AbstractUpdateEntity {

	@Column(name = "platnost")
	private Validity validity;

	@Column(name = "typ_predaja")
	private SellType sellType;

	@Column(name = "typ_osoby")
	private PersonType personType;

}
