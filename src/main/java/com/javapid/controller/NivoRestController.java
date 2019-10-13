package com.javapid.controller;

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
    public List<NivoLineAbstractData> getData(@RequestParam(required = false)List<String> validity){
        return nivoDataService.getNivoLineData(validity);
    }

    @RequestMapping("bar")
    public List<NivoBarData> retrieveBarData(@RequestParam(required = false)List<String> validity){
        return nivoDataService.getNivoBarData(validity);
    }

    @RequestMapping({"pie","waffle"})
    public List<NivoPieAbstractData> retrievePieData(@RequestParam(required = false)List<String> validity){
        return nivoDataService.getNivoPieData(validity);
    }
}
