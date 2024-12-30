package com.charts.general.utils.converter;

import com.charts.api.ticket.utils.TicketConverter;
import com.charts.general.entity.enums.types.Months;
import com.charts.api.ticket.enums.TicketType;
import com.charts.api.ticket.entity.v1.TicketEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getDiscounted(), true)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getYear(), 2015)));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getMonth(), Months.JANUARY)));

        Stream.of(
                Pair.of(TicketType.FIFTEEN_MINUTES, 100L),
                Pair.of(TicketType.ONE_DAY, 200L),
                Pair.of(TicketType.ONE_DAY_ALL, 300L),
                Pair.of(TicketType.TWO_ZONES, 400L),
                Pair.of(TicketType.THREE_ZONES, 500L),
                Pair.of(TicketType.FOUR_ZONES, 600L),
                Pair.of(TicketType.FIVE_ZONES, 700L),
                Pair.of(TicketType.SIX_ZONES, 800L),
                Pair.of(TicketType.SEVEN_ZONES, 900L),
                Pair.of(TicketType.EIGHT_ZONES, 1000L),
                Pair.of(TicketType.NINE_ZONES, 1100L),
                Pair.of(TicketType.TEN_ZONES, 1200L),
                Pair.of(TicketType.ELEVEN_ZONES, 1300L)
        ).forEach(p -> Assert.assertEquals(findTicketEntity(convertedValues, p.getLeft()).getValue().longValue(), p.getRight(), String.format("Test failed for %s", p.getLeft())));
    }

    private UpdateTicketEntity findTicketEntity(List<UpdateTicketEntity> entities, TicketType ticketType) {
        return entities.stream().filter(e -> e.getTicketType() == ticketType).findFirst().orElseThrow(() -> new RuntimeException("Test failed!"));
    }

}
