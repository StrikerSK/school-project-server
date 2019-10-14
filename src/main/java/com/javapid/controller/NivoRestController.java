package com.javapid.controller;

import com.javapid.entity.nivo.NivoJizdenkyBarData;
import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.entity.nivo.NivoBarData;
import com.javapid.entity.nivo.pie.NivoPieAbstractData;
import com.javapid.service.NivoDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nivo")
public class NivoRestController {

    private final NivoDataService nivoDataService;

    public NivoRestController(NivoDataService nivoDataService) {
        this.nivoDataService = nivoDataService;
    }

    @RequestMapping("line")
    public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<String> validity,
                                              @RequestParam(required = false) List<String> type,
                                              @RequestParam(required = false) List<String> month) {
        return nivoDataService.getNivoLineData(validity, type, month);
    }

    @RequestMapping("bar")
    public List<NivoBarData> retrieveBarData(@RequestParam(required = false) List<String> validity,
                                             @RequestParam(required = false) List<String> type,
                                             @RequestParam(required = false) List<String> month) {
        return nivoDataService.getNivoBarData(validity, type, month);
    }

    @RequestMapping({"pie", "waffle"})
    public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<String> validity,
                                                     @RequestParam(required = false) List<String> type,
                                                     @RequestParam(required = false) List<String> month) {
        return nivoDataService.getNivoPieData(validity, type, month);
    }

    @RequestMapping("/jizdenky/line")
    public List<NivoLineAbstractData> getData(@RequestParam(required = false) List<Boolean> discounted,
                                              @RequestParam(required = false) List<String> month) {
        return nivoDataService.getJizdenyLineData(discounted, month);
    }

    @RequestMapping("/jizdenky/bar")
    public List<NivoJizdenkyBarData> retrieveBarData(@RequestParam(required = false) List<Boolean> discounted,
                                                     @RequestParam(required = false) List<String> month) {
        return nivoDataService.getJizdenkyBarData(discounted, month);
    }

    @RequestMapping({"/jizdenky/pie", "/jizdenky/waffle"})
    public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false) List<Boolean> discounted,
                                                     @RequestParam(required = false) List<String> month) {
        return nivoDataService.getJizdenkyPieData(discounted, month);
    }
}
