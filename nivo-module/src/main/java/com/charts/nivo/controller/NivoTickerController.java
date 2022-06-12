package com.charts.nivo.controller;

import com.charts.general.entity.ticket.TicketsParameters;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;
import com.charts.nivo.service.NivoTicketsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nivo")
public class NivoTickerController {

    private final NivoTicketsService nivoTicketsService;

    public NivoTickerController(NivoTicketsService nivoTicketsService) {
        this.nivoTicketsService = nivoTicketsService;
    }

    @RequestMapping({"/tickets/line", "/tickets/line/monthly/type"})
    public List<NivoLineData> getTicketTypesByMonth(@RequestParam(required = false) List<Boolean> discounted,
                                                    @RequestParam(required = false) List<String> month,
                                                    @RequestParam(required = false) List<Integer> year,
                                                    @RequestParam(required = false) List<String> ticket) {
        return nivoTicketsService.getTicketTypesByMonth(new TicketsParameters(month, year, discounted, ticket));
    }

    @RequestMapping("/tickets/bar")
    public List<Map<String, Object>> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
                                                     @RequestParam(required = false) List<String> month,
                                                     @RequestParam(required = false) List<Integer> year,
                                                     @RequestParam(required = false) List<String> ticket) {
        return nivoTicketsService.getTicketBarData(new TicketsParameters(month, year, discounted, ticket));
    }

    @RequestMapping({"/tickets/pie", "/tickets/waffle"})
    public List<NivoPieData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
                                             @RequestParam(required = false) List<String> month,
                                             @RequestParam(required = false) List<Integer> year,
                                             @RequestParam(required = false) List<String> ticket) {
        return nivoTicketsService.getTicketTypePieData(new TicketsParameters(month, year, discounted, ticket));
    }
}
