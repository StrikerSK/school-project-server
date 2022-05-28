package com.charts.general.repository.coupon;

import com.charts.general.entity.nivo.bar.NivoBarCouponData;
import com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth;
import com.charts.general.entity.coupon.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	List<CouponEntity> getByCode(String code);

	@Query("SELECT new com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth(month,SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable), SUM(children)) FROM CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarCouponDataByMonth> getNivoBarData(@Param("validity") Collection<String> queryType,
	                                              @Param("sellType") Collection<String> sellType,
	                                              @Param("months") Collection<String> months,
	                                              @Param("year") Collection<Integer> year);

	@Query("SELECT new com.charts.general.entity.nivo.bar.NivoBarCouponDataByMonth(month,SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable), SUM(children)) FROM CouponEntity WHERE type IN :sellType AND validity = :validity AND month IN :months AND year IN :year GROUP BY code,month ORDER by code ASC")
	List<NivoBarCouponDataByMonth> getNivoBarDataByValidity(@Param("validity") String queryType,
	                                                        @Param("sellType") Collection<String> sellType,
	                                                        @Param("months") Collection<String> months,
	                                                        @Param("year") Collection<Integer> year);

	@Query("SELECT new com.charts.general.entity.nivo.bar.NivoBarCouponData(SUM(adults), SUM(seniors), SUM(junior), SUM(students), SUM(portable), SUM(children)) FROM CouponEntity WHERE type IN :sellType AND validity IN :validity AND month IN :months AND year IN :year GROUP BY year")
	NivoBarCouponData getNivoPieData(@Param("validity") Collection<String> queryType,
	                                 @Param("sellType") Collection<String> sellType,
	                                 @Param("months") Collection<String> months,
	                                 @Param("year") Collection<Integer> year);
}
