package com.javapid.repository;

import com.javapid.entity.nivo.bar.NivoBarDataSumDTO;
import com.javapid.entity.CouponEntity;
import com.javapid.entity.nivo.DataXY;
import com.javapid.entity.nivo.bar.NivoBarDataByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PidCouponsRepository extends JpaRepository<CouponEntity, Long> {

	List<CouponEntity> getByCode(String code);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(adults)) from CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getAdultSum(@Param("validity") Collection<String> queryType,
	                         @Param("sellType") Collection<String> sellType,
	                         @Param("months") Collection<String> months,
	                         @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(junior)) from CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getJuniorSum(@Param("validity") Collection<String> queryType,
	                          @Param("sellType") Collection<String> sellType,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(seniors)) from CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getSeniorSum(@Param("validity") Collection<String> queryType,
	                          @Param("sellType") Collection<String> sellType,
	                          @Param("months") Collection<String> months,
	                          @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(portable)) from CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getPortableSum(@Param("validity") Collection<String> queryType,
	                            @Param("sellType") Collection<String> sellType,
	                            @Param("months") Collection<String> months,
	                            @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(students)) from CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER BY code ASC")
	List<DataXY> getStudentSum(@Param("validity") Collection<String> queryType,
	                           @Param("sellType") Collection<String> sellType,
	                           @Param("months") Collection<String> months,
	                           @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.bar.NivoBarDataByMonth(month,SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarDataByMonth> getNivoBarData(@Param("validity") Collection<String> queryType,
	                                        @Param("sellType") Collection<String> sellType,
	                                        @Param("months") Collection<String> months,
	                                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.bar.NivoBarDataByMonth(month,SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM CouponEntity WHERE type IN :sellType AND validity = :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarDataByMonth> getNivoBarDataByValidity(@Param("validity") String queryType,
	                                                  @Param("sellType") Collection<String> sellType,
	                                                  @Param("months") Collection<String> months,
	                                                  @Param("year") Collection<Integer> year);

	@Query("SELECT new com.javapid.entity.nivo.bar.NivoBarDataSumDTO(SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY year")
	NivoBarDataSumDTO getNivoPieData(@Param("validity") Collection<String> queryType,
	                                 @Param("sellType") Collection<String> sellType,
	                                 @Param("months") Collection<String> months,
	                                 @Param("year") Collection<Integer> year);
}
