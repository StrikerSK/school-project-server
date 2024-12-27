package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.opencsv.AbstractCSVWriter;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

	private final CouponV2Service couponService;
	private final TicketService ticketService;

	public void fetchCoupons(AbstractCSVWriter writer) {
		fetchEntities(writer, couponService.findAll());
    }

	public void fetchTickets(AbstractCSVWriter writer) {
		fetchEntities(writer, ticketService.findAll());
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

	private <T> List<T> processFile(MultipartFile payload, Class<T> clazz) throws IOException {
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

	private <T> void fetchEntities(AbstractCSVWriter writer, List<T> data) {
		StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
		try {
			builder.build().write(data);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			throw new RuntimeException(e);
		}
	}

}
