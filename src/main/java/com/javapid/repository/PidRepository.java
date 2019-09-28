package com.javapid.repository;

import com.javapid.entity.PidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PidRepository extends JpaRepository<PidData, Long> {

	List<PidData> getByCode(String code);

	@Query("SELECT SUM(data) from PidData as data group by code")
	List<PidData> countTotal();

}