package com.charts.files.controller;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.files.service.CsvGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

	private final CsvGenerator csvGeneratorUtil;
	private final CouponV2Service couponService;

	@GetMapping("/coupons")
	public ResponseEntity<String> generateCsv() {
		List<String[]> data = couponService.findAll().stream().map(
				coupon -> new String[]{
						coupon.getId().toString(),
						coupon.getYear().toString(),
						coupon.getMonth().getValue(),
						coupon.getPersonType().getValue(),
						coupon.getSellType().getValue(),
						coupon.getValidity().getValue(),
						coupon.getValue().toString()
				}
		).collect(Collectors.toList());

		StringWriter stringWriter = new StringWriter();
		try (PrintWriter writer = new PrintWriter(stringWriter)) {
			data.forEach(row -> {
				writer.println(String.join(",", row));
			});
		}

		String csvContent = stringWriter.toString();

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + String.format("coupons-%s.csv", UUID.randomUUID()));
		headers.add(HttpHeaders.CONTENT_TYPE, "text/csv; charset=UTF-8");

		return new ResponseEntity<>(csvContent, headers, HttpStatus.OK);
	}

}
