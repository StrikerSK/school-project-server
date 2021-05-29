package com.charts.general.entity.nivo.bar;

import com.charts.general.entity.nivo.TicketMainDAO;

public class NivoBarTicketsDAOByMonth extends TicketMainDAO {

	private String month;

	public NivoBarTicketsDAOByMonth(
			String month,
			Long fifteenMinutes,
			Long oneDay,
			Long oneDayAll,
			Long twoZones,
			Long threeZones,
			Long fourZones,
			Long fiveZones,
			Long sixZones,
			Long sevenZones,
			Long eightZones,
			Long nineZones,
			Long tenZones,
			Long elevenZones
	) {
		super(fifteenMinutes, oneDay, oneDayAll, twoZones, threeZones, fourZones, fiveZones, sixZones, sevenZones, eightZones, nineZones, tenZones, elevenZones);
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
