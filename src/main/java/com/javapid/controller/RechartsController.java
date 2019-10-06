package com.javapid.controller;

import com.javapid.entity.SellType;
import com.javapid.objects.recharts.PersonAbstractClass;
import com.javapid.objects.recharts.AreaChartData;
import com.javapid.service.RechartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recharts")
public class RechartsController {

	@Autowired
    private RechartsService rechartsService;

	@RequestMapping("/area")
	public List<AreaChartData> getAreaChart(){
		return rechartsService.getAreaChartData(2017, SellType.COUPON.getValue());
	}

	@RequestMapping("/bar")
    public List<List<PersonAbstractClass>> getBarData(){
	    return rechartsService.getPersonData(2017,SellType.COUPON.getValue());
    }
}
