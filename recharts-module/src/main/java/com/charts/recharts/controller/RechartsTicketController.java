package com.charts.recharts.controller;

import com.charts.general.entity.parameters.TicketsParameters;
import com.charts.recharts.entity.RechartsDataObject;
import com.charts.recharts.service.RechartsTicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
@AllArgsConstructor
public class RechartsTicketController {

    private final RechartsTicketService ticketService;

    @RequestMapping({"/tickets/bar", "/tickets/pie", "/tickets/line", "/tickets/data"})
    public List<List<RechartsDataObject>> getMonthlyDataByTicketType(@RequestParam(required = false) List<Boolean> discounted,
                                                                     @RequestParam(required = false) List<String> month,
                                                                     @RequestParam(required = false) List<Integer> year,
                                                                     @RequestParam(required = false) List<String> ticket) {
        return ticketService.getMonthlyDataByTicketType(new TicketsParameters(month, year, discounted, ticket));
    }

}
