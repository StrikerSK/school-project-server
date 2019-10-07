package com.javapid.controller;

import com.javapid.entity.nivo.NivoAbstractLineData;
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
    public List<NivoAbstractLineData> getData(){
        return nivoDataService.getNivoData();
    }

}
