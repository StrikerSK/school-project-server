package com.javapid.entity.nivo;

public class NivoJizdenkyBarData extends DataAbstractJizdenky {

    private String month;

    public NivoJizdenkyBarData(String month, Long fifteenMinutes, Long oneDay, Long oneDayAll, Long twoZones, Long threeZones, Long fourZones, Long fiveZones, Long sixZones, Long sevenZones, Long eightZones, Long nineZones, Long tenZones, Long elevenZones) {
        this.month = month;
        setFifteenMinutes(fifteenMinutes);
        setOneDay(oneDay);
        setOneDayAll(oneDayAll);
        setTwoZones(twoZones);
        setThreeZones(threeZones);
        setFourZones(fourZones);
        setFiveZones(fiveZones);
        setSixZones(sixZones);
        setSevenZones(sevenZones);
        setEightZones(eightZones);
        setNineZones(nineZones);
        setTenZones(tenZones);
        setElevenZones(elevenZones);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
