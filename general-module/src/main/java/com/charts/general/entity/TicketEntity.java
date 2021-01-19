package com.charts.general.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pid_jizdenky")
@Getter
@Setter
@NoArgsConstructor
public class TicketEntity extends AbstractDataEntity {

	@Column(name = "zlavneny")
	private Boolean discounted;

	@Column(name = "stvrt_minut")
	private Long fifteenMinutes;

	@Column(name = "jeden_den")
	private Long oneDay;

	@Column(name = "jeden_den_vsetky")
	private Long oneDayAll;

	@Column(name = "dve_pasma")
	private Long twoZones;

	@Column(name = "tri_pasma")
	private Long threeZones;

	@Column(name = "styri_pasma")
	private Long fourZones;

	@Column(name = "pat_pasem")
	private Long fiveZones;

	@Column(name = "sest_pasem")
	private Long sixZones;

	@Column(name = "sedem_pasem")
	private Long sevenZones;

	@Column(name = "osem_pasem")
	private Long eightZones;

	@Column(name = "devat_pasem")
	private Long nineZones;

	@Column(name = "desat_pasem")
	private Long tenZones;

	@Column(name = "jedenast_pasem")
	private Long elevenZones;

	public TicketEntity(String code, String month, Integer year, Boolean discounted, Long fifteenMinutes, Long oneDay, Long oneDayAll, Long twoZones, Long threeZones, Long fourZones, Long fiveZones, Long sixZones, Long sevenZones, Long eightZones, Long nineZones, Long tenZones, Long elevenZones) {
		super(code, month, year);
		this.discounted = discounted;
		this.fifteenMinutes = fifteenMinutes;
		this.oneDay = oneDay;
		this.oneDayAll = oneDayAll;
		this.twoZones = twoZones;
		this.threeZones = threeZones;
		this.fourZones = fourZones;
		this.fiveZones = fiveZones;
		this.sixZones = sixZones;
		this.sevenZones = sevenZones;
		this.eightZones = eightZones;
		this.nineZones = nineZones;
		this.tenZones = tenZones;
		this.elevenZones = elevenZones;
	}
}
