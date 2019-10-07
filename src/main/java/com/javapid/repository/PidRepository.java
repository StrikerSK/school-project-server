package com.javapid.repository;

import com.javapid.entity.PidData;
import com.javapid.entity.nivo.DataXY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PidRepository extends JpaRepository<PidData, Long> {

	List<PidData> getByCode(String code);

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(adults)) from PidData group by code,month order by code asc")
	List<DataXY> getAdultSum();

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(junior)) from PidData group by code,month order by code asc")
	List<DataXY> getJuniorSum();

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(seniors)) from PidData group by code,month order by code asc")
	List<DataXY> getSeniorSum();

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(portable)) from PidData group by code,month order by code asc")
	List<DataXY> getPortableSum();

	@Query("SELECT new com.javapid.entity.nivo.DataXY(month,sum(students)) from PidData group by code,month order by code asc")
	List<DataXY> getStudentSum();

}