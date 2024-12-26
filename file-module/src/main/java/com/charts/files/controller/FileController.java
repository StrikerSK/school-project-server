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

	@PostMapping("/upload")
	public ResponseEntity<List<UpdateCouponEntity>> uploadCsv(@RequestBody String payload) {
		try {
			CSVReader csvReader = new CSVReader(new StringReader(payload));
			CsvToBean<UpdateCouponEntity> csvToBean = new CsvToBeanBuilder<UpdateCouponEntity>(csvReader)
					.withType(UpdateCouponEntity.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			List<UpdateCouponEntity> users = csvToBean.parse();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
