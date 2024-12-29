package com.charts.files.service;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.api.ticket.service.TicketService;
import com.charts.files.utils.CsvProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileService {

	private final CouponV2Service couponService;
	private final TicketService ticketService;
	private final IDataGenerator dataGenerator;

	public List<UpdateCouponEntity> fetchCoupons(Integer count, Boolean random) {
		if (random != null && random) {
			return dataGenerator.generateCoupons(count);
		} else {
			return couponService.findAll(count);
		}
	}

	public List<UpdateTicketEntity> fetchTickets(Integer count, Boolean random) {
		if (random != null && random) {
			return dataGenerator.generateTickets(count);
		} else {
			return ticketService.findAll(count);
		}
	}

	public List<UpdateCouponEntity> processCoupons(MultipartFile payload) throws IOException {
		List<UpdateCouponEntity> coupons = CsvProcessor.readEntries(payload, UpdateCouponEntity.class);
		couponService.saveAll(coupons);
		return coupons;
	}

	public List<UpdateTicketEntity> processTickets(MultipartFile payload) throws IOException {
		List<UpdateTicketEntity> tickets = CsvProcessor.readEntries(payload, UpdateTicketEntity.class);
		ticketService.saveAll(tickets);
		return tickets;
	}

}
