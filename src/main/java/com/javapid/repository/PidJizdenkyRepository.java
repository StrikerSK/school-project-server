package com.javapid.repository;

import com.javapid.entity.PidJizenky;
import com.javapid.entity.nivo.DataSumJizdenkyDTO;
import com.javapid.entity.nivo.DataXY;
import com.javapid.entity.nivo.NivoJizdenkyBarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PidJizdenkyRepository extends JpaRepository<PidJizenky, Long> {

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fifteenMinutes)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFifteenMinutes(@Param("discounted") Collection<Boolean> discounted,
	                               @Param("months") Collection<String> months,
	                               @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(oneDay)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getOneDay(@Param("discounted") Collection<Boolean> discounted,
	                       @Param("months") Collection<String> months,
	                       @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(oneDayAll)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getOneDayAll(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(twoZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getTwoZones(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(threeZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getThreeZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fourZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFourZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(fiveZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getFiveZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(sixZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getSixZone(@Param("discounted") Collection<Boolean> discounted,
	                        @Param("months") Collection<String> months,
	                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(sevenZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getSevenZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(eightZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getEightZone(@Param("discounted") Collection<Boolean> discounted,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(nineZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getNineZone(@Param("discounted") Collection<Boolean> discounted,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(tenZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getTenZone(@Param("discounted") Collection<Boolean> discounted,
	                        @Param("months") Collection<String> months,
	                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(elevenZones)) from PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getElevenZone(@Param("discounted") Collection<Boolean> discounted,
	                           @Param("months") Collection<String> months,
	                           @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.NivoJizdenkyBarData(month,SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoJizdenkyBarData> getJizdenkyBarData(@Param("discounted") Collection<Boolean> discounted,
	                                             @Param("months") Collection<String> months,
	                                             @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataSumJizdenkyDTO(SUM(fifteenMinutes),SUM(oneDay),SUM(oneDayAll),SUM(twoZones),SUM(threeZones),SUM(fourZones),SUM(fiveZones),SUM(sixZones),SUM(sevenZones),SUM(eightZones),SUM(nineZones),SUM(tenZones),SUM(elevenZones)) FROM PidJizenky WHERE discounted IN :discounted AND month IN :months AND year IN :year GROUP BY year")
	DataSumJizdenkyDTO getJizdenkyPieData(@Param("discounted") Collection<Boolean> discounted,
	                                      @Param("months") Collection<String> months,
	                                      @Param("year") Collection<Integer> year);
}
