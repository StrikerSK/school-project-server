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

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(adults)) from PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER BY code ASC")
    List<DataXY> getAdultSum(@Param("validity") Collection<String> queryType,
                             @Param("sellType") Collection<String> sellType,
                             @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(junior)) from PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER BY code ASC")
    List<DataXY> getJuniorSum(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType,
                              @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(seniors)) from PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER BY code ASC")
    List<DataXY> getSeniorSum(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType,
                              @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(portable)) from PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER BY code ASC")
    List<DataXY> getPortableSum(@Param("validity") Collection<String> queryType,
                                @Param("sellType") Collection<String> sellType,
                                @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.DataXY(month,SUM(students)) from PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER BY code ASC")
    List<DataXY> getStudentSum(@Param("validity") Collection<String> queryType,
                               @Param("sellType") Collection<String> sellType,
                               @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.NivoBarData(month,SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY code,month ORDER by code ASC")
    List<NivoBarData> getNivoBarData(@Param("validity") Collection<String> queryType,
                                     @Param("sellType") Collection<String> sellType,
                                     @Param("months") Collection<String> months);

    @Query("SELECT new com.javapid.entity.nivo.DataSumDTO(SUM(adults),SUM(seniors),SUM(junior),SUM(students),SUM(portable)) FROM PidData WHERE type IN :sellType AND validity IN :validity AND month IN :months GROUP BY year")
    DataSumDTO getNivoPieData(@Param("validity") Collection<String> queryType,
                              @Param("sellType") Collection<String> sellType,
                              @Param("months") Collection<String> months);
}
