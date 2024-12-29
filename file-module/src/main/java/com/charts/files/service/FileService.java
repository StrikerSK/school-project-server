package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

	private final CouponV2Service couponService;
	private final TicketService ticketService;

	public List<UpdateCouponEntity> fetchCoupons(Integer count, Boolean random) {
		if (random != null && random) {
			return DataGenerator.generateCoupons(count);
		} else {
			return couponService.findAll(count);
		}
	}

	public List<UpdateTicketEntity> fetchTickets(Integer count, Boolean random) {
		if (random != null && random) {
			return DataGenerator.generateTickets(count);
		} else {
			return ticketService.findAll(count);
		}
	}

	public List<UpdateCouponEntity> processCoupons(MultipartFile payload) throws IOException {
		List<UpdateCouponEntity> coupons = processFile(payload, UpdateCouponEntity.class);
		couponService.saveAll(coupons);
		return coupons;
	}

	public List<UpdateTicketEntity> processTickets(MultipartFile payload) throws IOException {
		List<UpdateTicketEntity> tickets = processFile(payload, UpdateTicketEntity.class);
		ticketService.saveAll(tickets);
		return tickets;
	}

	private static <T> List<T> processFile(MultipartFile payload, Class<T> clazz) throws IOException {
		InputStreamReader inputStream = new InputStreamReader(payload.getInputStream(), StandardCharsets.UTF_8);
		BufferedReader fileReader = new BufferedReader(inputStream);
		String stringReader = IOUtils.toString(fileReader);
		CSVReader csvReader = new CSVReader(new StringReader(stringReader));
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
				.withType(clazz)
				.withIgnoreLeadingWhiteSpace(true)
				.build();
		return csvToBean.parse();
	}

}
