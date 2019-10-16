package com.javapid.controller;

import com.javapid.entity.CouponEntity;
import com.javapid.service.PidService;
import com.javapid.service.graphql.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PidRestController {

	private final PidService pidService;

    private final GraphQLService graphQLService;

    public PidRestController(PidService pidService, GraphQLService graphQLService) {
        this.pidService = pidService;
        this.graphQLService = graphQLService;
    }

	@RequestMapping(name = "/getData")
	public List<CouponEntity> getData() {
		return pidService.getAllData();
	}

	@RequestMapping("/uploadJson")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		pidService.saveDataFromFile(file);
	}

	@RequestMapping("/getData/{code}")
	public List<CouponEntity> uploadFile(@PathVariable String code) {
		return pidService.getDataByCode(code);
	}

    @PostMapping("/graphql")
    public ResponseEntity<Object> getAllBarData(@RequestBody String query){
        ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
