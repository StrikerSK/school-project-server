package com.charts.files.controller;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.files.service.FileService;
import com.opencsv.AbstractCSVWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

	private final FileService fileService;

	private final String contentType = "text/csv;charset=UTF-8";

	@GetMapping(value = "/coupon", produces = "text/csv")
	public void exportCouponsCsv(
			@RequestParam(name = "random", required = false) Boolean random,
			@RequestParam(name = "count", required = false, defaultValue = "100") Integer count,
			HttpServletResponse response
	) {
		try (CSVWriter writer = new CSVWriter(response.getWriter())) {
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=coupons_%s.csv", UUID.randomUUID()));
			response.setHeader(HttpHeaders.CONTENT_ENCODING, "UTF-8");
			response.setHeader(HttpHeaders.CONTENT_TYPE, "text/csv");
			response.setStatus(HttpServletResponse.SC_OK);

			List<UpdateCouponEntity> couponList = fileService.fetchCoupons(count, random);
			writeEntries(writer, couponList);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value = "/ticket", produces = "text/csv")
	public void exportTicketsCsv(
			@RequestParam(name = "random", required = false) Boolean random,
			@RequestParam(name = "count", required = false, defaultValue = "100") Integer count,
			HttpServletResponse response
	) {
		try (CSVWriter writer = new CSVWriter(response.getWriter())) {
			response.setContentType(contentType);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=tickets_%s.csv", UUID.randomUUID()));
			response.setHeader(HttpHeaders.CONTENT_ENCODING, "UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);

			List<UpdateTicketEntity> ticketList = fileService.fetchTickets(count, random);
			writeEntries(writer, ticketList);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/coupon", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadCouponsCsv(@RequestParam MultipartFile payload) {
		try {
			fileService.processCoupons(payload);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping(value = "/ticket", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadTicketsCsv(@RequestParam MultipartFile payload) {
		try {
			fileService.processTickets(payload);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	private static <T> void writeEntries(AbstractCSVWriter writer, List<T> data) {
		StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
		try {
			builder.build().write(data);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			throw new RuntimeException(e);
		}
	}

}
