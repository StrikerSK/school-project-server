package com.charts.general.repository.ticket;

import com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth;
import com.charts.general.entity.TicketEntity;
import com.charts.general.entity.nivo.TicketMainDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	@Query("SELECT new com.charts.general.entity.nivo.bar.NivoBarTicketsDAOByMonth(month, SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarTicketsDAOByMonth> getTicketsBarData(@Param("discounted") Collection<Boolean> discounted,
	                                                 @Param("months") Collection<String> months,
	                                                 @Param("year") Collection<Integer> year);

	@Query("SELECT new com.charts.general.entity.nivo.TicketMainDAO(SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year")
	TicketMainDAO getTicketsPieData(@Param("discounted") Collection<Boolean> discounted,
	                                @Param("months") Collection<String> months,
	                                @Param("year") Collection<Integer> year);
}
