package com.charts.files.controller;

import com.charts.api.coupon.entity.v2.UpdateCouponEntity;
import com.charts.api.coupon.service.CouponV2Service;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

	private final CouponV2Service couponService;

	@GetMapping("/coupon")
	public void exportToCsv(HttpServletResponse response) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=data.csv");


		try (CSVWriter writer = new CSVWriter(response.getWriter())) {
			StatefulBeanToCsvBuilder<UpdateCouponEntity> builder = new StatefulBeanToCsvBuilder<>(writer);
			builder
					.build()
					.write(couponService.findAll());
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate CSV", e);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

	@PostMapping("/coupon")
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
