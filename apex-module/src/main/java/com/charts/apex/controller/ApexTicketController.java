package com.charts.apex.controller;

import com.charts.apex.entity.ApexObject;
import com.charts.apex.service.ApexTicketService;
import com.charts.api.ticket.entity.TicketsParameters;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apex")
@AllArgsConstructor
public class ApexTicketController {

    private final ApexTicketService apexTicketService;

    @GetMapping(value = "/ticket", produces = "application/json")
    public List<ApexObject> getTicketData(
            @RequestParam(required = false) List<Boolean> discounted,
            @RequestParam(required = false) List<String> month,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<String> ticket,
            @RequestParam String upperGroup,
            @RequestParam String lowerGroup
    ) {
        return apexTicketService.getTicketData(upperGroup, lowerGroup, new TicketsParameters(month, year, discounted, ticket));
    }

}
