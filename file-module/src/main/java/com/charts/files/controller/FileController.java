package com.charts.files.controller;

import com.charts.files.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

	private final FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@RequestMapping("/uploadJson")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		fileService.saveDataFromFile(file);
	}
}
