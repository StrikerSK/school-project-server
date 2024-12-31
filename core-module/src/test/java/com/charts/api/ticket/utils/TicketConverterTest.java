package com.charts.api.ticket.utils;

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
import java.util.Optional;
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
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getDiscounted(), ticketEntity.getDiscounted())));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getYear(), ticketEntity.getYear())));
        Assert.assertTrue(convertedValues.stream().allMatch(e -> Objects.equals(e.getMonth(), ticketEntity.getMonth())));

        Stream.of(
                Pair.of(TicketType.FIFTEEN_MINUTES, ticketEntity.getFifteenMinutes()),
                Pair.of(TicketType.ONE_DAY, ticketEntity.getOneDay()),
                Pair.of(TicketType.ONE_DAY_ALL, ticketEntity.getOneDayAll()),
                Pair.of(TicketType.TWO_ZONES, ticketEntity.getTwoZones()),
                Pair.of(TicketType.THREE_ZONES, ticketEntity.getThreeZones()),
                Pair.of(TicketType.FOUR_ZONES, ticketEntity.getFourZones()),
                Pair.of(TicketType.FIVE_ZONES, ticketEntity.getFiveZones()),
                Pair.of(TicketType.SIX_ZONES, ticketEntity.getSixZones()),
                Pair.of(TicketType.SEVEN_ZONES, ticketEntity.getSevenZones()),
                Pair.of(TicketType.EIGHT_ZONES, ticketEntity.getEightZones()),
                Pair.of(TicketType.NINE_ZONES, ticketEntity.getNineZones()),
                Pair.of(TicketType.TEN_ZONES, ticketEntity.getTenZones()),
                Pair.of(TicketType.ELEVEN_ZONES, ticketEntity.getElevenZones())
        ).forEach(p -> findTicketEntity(convertedValues, p.getLeft(), p.getRight()));
    }

    private void findTicketEntity(List<UpdateTicketEntity> entities, TicketType ticketType, Long value) {
        Optional<UpdateTicketEntity> entity = entities.stream().filter(e -> e.getTicketType() == ticketType).findFirst();
        Assert.assertTrue(entity.isPresent(), String.format("Entity not found for %s", ticketType));
        Assert.assertEquals(entity.get().getValue(), value.intValue(), String.format("Test failed for %s", ticketType));
    }

}
