package com.javapid.service;

import com.javapid.entity.PidData;
import com.javapid.objects.TicketSellData;
import com.javapid.objects.recharts.AreaChartData;
import com.javapid.repository.PidRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.javapid.service.FileParser.getEmployeeFromJson;

@Service
public class PidService {

	private final PidRepository pidRepository;

	public PidService(PidRepository pidRepository) {
		this.pidRepository = pidRepository;
	}

	public void saveData(PidData data){
		pidRepository.save(data);
	}

	public void saveDataFromFile(MultipartFile file) throws IOException {
		PidData data = getEmployeeFromJson(file);
		saveData(data);
	}

	public List<PidData> getAllData(){
		return pidRepository.findAll();
	}

	public List<PidData> getDataByCode(String code){
		return pidRepository.getByCode(code);
	}

	public List<TicketSellData> getTicketSellData(){
		List<PidData> pidData = pidRepository.findAll();
		return pidData.stream()
				.filter(e -> "Čipová karta".equals(e.getType()))
				.map(DataCreator::createStreamData)
				.collect(Collectors.toList());
	}

	public List<AreaChartData> getAreaChartData(){
		List<PidData> dataList = pidRepository.getAllByYear(2017);
		System.out.println("Called method");
		return dataList.stream()
                .filter(e -> "Čipová karta".equals(e.getType()))
				.map(DataCreator::createAreaChartData)
				.collect(Collectors.toList());
	}

}
