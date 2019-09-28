package com.javapid.service;

import com.javapid.entity.PidData;
import com.javapid.entity.nivo.PieChartData;
import com.javapid.repository.PidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NivoDataService {

	@Autowired
	private PidRepository repository;

	public List<PieChartData> getNivoData(){
		List<PidData> pidDataList = repository.countTotal();


		return null;
	}

	public List<PieChartData> createData(PidData data){
		List<PieChartData> list = new ArrayList<>();
		return null;
	}
}
