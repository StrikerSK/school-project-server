package com.javapid.repository;

import com.javapid.entity.nivo.DataSumDTO;
import com.javapid.entity.PidData;
import com.javapid.entity.nivo.DataXY;
import com.javapid.entity.nivo.NivoBarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PidRepository extends JpaRepository<PidData, Long> {

	List<PidData> getByCode(String code);
	List<PidData> getAllByYear(Integer year);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(adults)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<DataXY> getAdultSum(@Param("validity") Collection<String> queryType,
                             @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(junior)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<DataXY> getJuniorSum(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(seniors)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<DataXY> getSeniorSum(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(portable)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<DataXY> getPortableSum(@Param("validity") Collection<String> queryType,
                                @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(students)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<DataXY> getStudentSum(@Param("validity") Collection<String> queryType,
                               @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.NivoBarData(month,sum(adults),sum(seniors),sum(junior),sum(students),sum(portable)) from PidData WHERE type IN :sellType AND validity IN :validity group by code,month order by code asc")
    List<NivoBarData> getNivoBarData(@Param("validity") Collection<String> queryType,
                                     @Param("sellType") Collection<String> sellType);

    @Query("SELECT new com.javapid.entity.nivo.DataSumDTO(SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM PidData WHERE type IN :sellType AND validity IN :validity GROUP BY year")
    DataSumDTO getNivoPieData(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType);
}
