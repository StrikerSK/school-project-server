package com.charts.api.coupon.repository;

import com.charts.api.coupon.entity.CouponsParameters;
import com.charts.api.coupon.enums.types.PersonType;
import com.charts.api.coupon.enums.types.SellType;
import com.charts.api.coupon.enums.types.Validity;
import com.charts.api.coupon.service.CouponV2Service;
import com.charts.general.entity.coupon.AbstractCouponTest;
import com.charts.general.entity.enums.EnumUtils;
import com.charts.general.entity.enums.types.Months;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class CouponRepositoryTest extends AbstractCouponTest {

    @InjectMocks
    private CouponRepository defaultCouponRepository;

    @Mock
    private CouponV2Service couponService;

    @Captor
    private ArgumentCaptor<CouponsParameters> captor = ArgumentCaptor.forClass(CouponsParameters.class);

    @Test
    public void TestFindAll() {
        Assert.assertEquals(defaultCouponRepository.getUpdateCouponList().size(), 18);
    }

    @Test
    public void testParameterPassing() {
        CouponsParameters parameters = new CouponsParameters(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        couponService.findCouponEntities(parameters);
        verify(couponService, atLeastOnce()).findCouponEntities(captor.capture());

        CouponsParameters inputParams = captor.getValue();
        Assert.assertEquals(inputParams.getValidity().size(), 4);
        Assert.assertEquals(inputParams.getValidity(), EnumUtils.getValueList(Validity.class));

        Assert.assertEquals(inputParams.getSellTypes().size(), 3);
        Assert.assertEquals(inputParams.getSellTypes(), EnumUtils.getValueList(SellType.class));

        Assert.assertEquals(inputParams.getPersonTypeList().size(), 6);
        Assert.assertEquals(inputParams.getPersonTypeList(), EnumUtils.getValueList(PersonType.class));

        Assert.assertEquals(inputParams.getMonths().size(), 12);
        Assert.assertEquals(inputParams.getMonths(), EnumUtils.getValueList(Months.class));
    }

    @Test
    public void testParameterPassing_oneParam() {
        PersonType personType = PersonType.SENIOR;
        Validity validity = Validity.MONTHLY;
        SellType sellType = SellType.COUPON;
        Months month = Months.JANUARY;

        CouponsParameters parameters = new CouponsParameters(
                Collections.singletonList(validity.getValue()),
                Collections.singletonList(sellType.getValue()),
                Collections.singletonList(month.getValue()),
                Collections.emptyList(),
                Collections.singletonList(personType.getValue())
        );

        couponService.findCouponEntities(parameters);
        verify(couponService, atLeastOnce()).findCouponEntities(captor.capture());

        CouponsParameters inputParams = captor.getValue();
        Assert.assertEquals(inputParams.getValidity().size(), 1);
        Assert.assertEquals(inputParams.getValidity().get(0), validity);

        Assert.assertEquals(inputParams.getSellTypes().size(), 1);
        Assert.assertEquals(inputParams.getSellTypes().get(0), sellType);

        Assert.assertEquals(inputParams.getPersonTypeList().size(), 1);
        Assert.assertEquals(inputParams.getPersonTypeList().get(0), personType);

        Assert.assertEquals(inputParams.getMonths().size(), 1);
        Assert.assertEquals(inputParams.getMonths().get(0), month);
    }

}
