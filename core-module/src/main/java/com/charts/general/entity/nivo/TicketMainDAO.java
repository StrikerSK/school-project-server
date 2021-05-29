package com.charts.general.entity.nivo;

import lombok.Data;

@Data
public class TicketMainDAO {

	private Long fifteenMinutes;
	private Long oneDay;
	private Long oneDayAll;
	private Long twoZones;
	private Long threeZones;
	private Long fourZones;
	private Long fiveZones;
	private Long sixZones;
	private Long sevenZones;
	private Long eightZones;
	private Long nineZones;
	private Long tenZones;
	private Long elevenZones;

	public TicketMainDAO(Long fifteenMinutes, Long oneDay, Long oneDayAll, Long twoZones, Long threeZones, Long fourZones, Long fiveZones, Long sixZones, Long sevenZones, Long eightZones, Long nineZones, Long tenZones, Long elevenZones) {
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
