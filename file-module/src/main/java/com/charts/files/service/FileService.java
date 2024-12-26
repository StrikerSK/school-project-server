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
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

	private final CouponV2Service couponService;
	private final TicketService ticketService;

	public void fetchCoupons(AbstractCSVWriter writer) {
		StatefulBeanToCsvBuilder<UpdateCouponEntity> builder = new StatefulBeanToCsvBuilder<>(writer);
        try {
            builder.build().write(couponService.findAll());
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }

	public void fetchTickets(AbstractCSVWriter writer) {
		StatefulBeanToCsvBuilder<UpdateTicketEntity> builder = new StatefulBeanToCsvBuilder<>(writer);
		try {
			builder.build().write(ticketService.findAll());
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			throw new RuntimeException(e);
		}
	}

	public void processCoupons(String payload) {
		CSVReader csvReader = new CSVReader(new StringReader(payload));
		CsvToBean<UpdateCouponEntity> csvToBean = new CsvToBeanBuilder<UpdateCouponEntity>(csvReader)
				.withType(UpdateCouponEntity.class)
				.withIgnoreLeadingWhiteSpace(true)
				.build();

		List<UpdateCouponEntity> coupons = csvToBean.parse();
		couponService.saveAll(coupons);
	}

	public void processTickets(String payload) {
		CSVReader csvReader = new CSVReader(new StringReader(payload));
		CsvToBean<UpdateTicketEntity> csvToBean = new CsvToBeanBuilder<UpdateTicketEntity>(csvReader)
				.withType(UpdateTicketEntity.class)
				.withIgnoreLeadingWhiteSpace(true)
				.build();

		List<UpdateTicketEntity> tickets = csvToBean.parse();
		ticketService.saveAll(tickets);
	}

}
