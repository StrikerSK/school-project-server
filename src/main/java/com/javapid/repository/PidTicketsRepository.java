package com.javapid.repository;

import com.javapid.entity.TicketEntity;
import com.javapid.entity.nivo.DataAbstractJizdenky;
import com.javapid.entity.nivo.DataXY;
import com.javapid.entity.nivo.NivoBarJizdenkyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PidTicketsRepository extends JpaRepository<TicketEntity, Long> {

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fifteenMinutes)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFifteenMinutes(@Param("discounted") Collection<Boolean> discounted,
	                               @Param("months") Collection<String> months,
	                               @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(oneDay)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getOneDay(@Param("discounted") Collection<Boolean> discounted,
	                       @Param("months") Collection<String> months,
	                       @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(oneDayAll)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getOneDayAll(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(twoZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getTwoZones(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(threeZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getThreeZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fourZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFourZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fiveZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFiveZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(sixZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getSixZone(@Param("discounted") Collection<Boolean> discounted,
	                        @Param("months") Collection<String> months,
	                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(sevenZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getSevenZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(eightZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getEightZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(nineZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getNineZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(tenZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getTenZone(@Param("discounted") Collection<Boolean> discounted,
	                        @Param("months") Collection<String> months,
	                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(elevenZones)) from TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getElevenZone(@Param("discounted") Collection<Boolean> discounted,
	                           @Param("months") Collection<String> months,
	                           @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.NivoBarJizdenkyDAO(month,SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarJizdenkyDAO> getTicketsBarData(@Param("discounted") Collection<Boolean> discounted,
	                                           @Param("months") Collection<String> months,
	                                           @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataAbstractJizdenky(SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM TicketEntity WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY year")
	DataAbstractJizdenky getTicketsPieData(@Param("discounted") Collection<Boolean> discounted,
	                                       @Param("months") Collection<String> months,
	                                       @Param("year") Collection<Integer> year);
}
