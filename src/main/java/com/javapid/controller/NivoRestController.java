package com.javapid.controller;

import com.javapid.entity.nivo.NivoLineAbstractData;
import com.javapid.entity.nivo.NivoBarData;
import com.javapid.service.NivoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nivo")
public class NivoRestController {

    @Autowired
    private NivoDataService nivoDataService;

    @RequestMapping("line")
    public List<NivoLineAbstractData> getData(){
        return nivoDataService.getNivoData();
    }

    @RequestMapping("bar")
    public List<NivoBarData> retrieveBarData(){
        return nivoDataService.getNivoBarData();
    }

}
