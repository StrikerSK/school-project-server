package com.charts.general.utils.converter;

import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import com.charts.general.entity.ticket.TicketEntity;
import com.charts.general.entity.ticket.updated.UpdateTicketEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class TicketConverterTest extends AbstractTestNGSpringContextTests {

    protected TicketEntity ticketEntity;

    @BeforeClass
    public void setUp() {
        ticketEntity = TicketEntity.builder()
                .id(123L)
                .month(Months.JANUARY)
                .year(2015)
                .code("012015")
                .discounted(true)
                .fifteenMinutes(100L)
                .oneDay(200L)
                .oneDayAll(300L)
                .twoZones(400L)
                .threeZones(500L)
                .fourZones(600L)
                .fiveZones(700L)
                .sixZones(800L)
                .sevenZones(900L)
                .eightZones(1000L)
                .nineZones(1100L)
                .tenZones(1200L)
                .elevenZones(1300L)
                .build();
    }

    @Test
    public void TestTicketConverter() {
        List<UpdateTicketEntity> convertedValues = TicketConverter.convertTicketEntity(ticketEntity);

        Assert.assertEquals(convertedValues.size(), 13);
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getCode(), "012015")));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getDiscounted(), true)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getYear(), 2015)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getMonth(), Months.JANUARY)));

        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.FIFTEEN_MINUTES).getValue(), 100);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.ONE_DAY).getValue(), 200);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.ONE_DAY_ALL).getValue(), 300);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.TWO_ZONES).getValue(), 400);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.THREE_ZONES).getValue(), 500);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.FOUR_ZONES).getValue(), 600);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.FIVE_ZONES).getValue(), 700);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.SIX_ZONES).getValue(), 800);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.SEVEN_ZONES).getValue(), 900);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.EIGHT_ZONES).getValue(), 1000);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.NINE_ZONES).getValue(), 1100);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.TEN_ZONES).getValue(), 1200);
        Assert.assertEquals(findTicketEntity(convertedValues, TicketTypes.ELEVEN_ZONES).getValue(), 1300);
    }

    private UpdateTicketEntity findTicketEntity(List<UpdateTicketEntity> entities, TicketTypes ticketType) {
        return entities.stream().filter(e -> e.getTicketType() == ticketType).findFirst().orElseThrow(() -> new RuntimeException("Test failed!"));
    }

}
