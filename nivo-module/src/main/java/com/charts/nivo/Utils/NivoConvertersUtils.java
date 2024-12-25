package com.charts.nivo.Utils;

import com.charts.general.entity.GroupingEntity;
import com.charts.general.entity.AbstractUpdateEntity;
import com.charts.general.entity.enums.IEnum;
import com.charts.nivo.entity.NivoBubbleData;
import com.charts.nivo.entity.NivoDataXY;
import com.charts.nivo.entity.NivoLineData;
import com.charts.nivo.entity.NivoPieData;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NivoConvertersUtils {

    /**
     *
     * @param groupingList List of grouped entities retrieved from database
     * @return List of processed data into Nivo Pie data structure
     * @param <T> Application defined enumeration implementing {@link IEnum}
     */
    public static <T extends IEnum> List<NivoPieData> createPieData(List<GroupingEntity<T>> groupingList) {
        groupingList.sort(Comparator.comparingInt(e -> e.getKey().getOrderValue()));
        return groupingList
                .stream()
                .map(e -> new NivoPieData(e.getKey().getValue(), e.getValue().intValue()))
                .collect(Collectors.toList());
    }

    /**
     * Method gets data for displaying bar chart. It is divided by two grouping upper grouping and nested grouping.
     *
     * @param input all entries obtained from database
     * @param upperGrouping top level grouping of data
     * @param nestedGrouping nested grouping within upper grouping
     * @param aggregator aggregation function
     */
    public static <T extends IEnum, R extends IEnum, E> List<Map<String, Object>> createBarData(
            List<E> input,
            Function<List<E>, Map<T, List<E>>> upperGrouping,
            Function<List<E>, Map<R, List<E>>> nestedGrouping,
            Function<List<E>, Integer> aggregator
    ) {
        List<Map<String, Object>> outputMapList = new ArrayList<>();
        upperGrouping.apply(input)
                .forEach((key, entities) -> {
                    Map<String, Object> outputMap = nestedGrouping.apply(entities)
                            .entrySet()
                            .stream()
                            .map(e -> new AbstractMap.SimpleEntry<>(e.getKey().getValue(), aggregator.apply(e.getValue())))
                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
                    outputMap.put("month", key.getValue());
                    outputMap.put("label", key.getValue());
                    outputMapList.add(outputMap);
                });
        return outputMapList;
    }

    /**
     * Method creates two level grouping. Level one grouping contains id and list of data that is composed by level two grouping.
     *
     * @param input all entries obtained from database
     * @param upperGrouping top level grouping of data that which key value is used as 'id' parameter
     * @param nestedGrouping grouping of listed data in 'data' parameter
     * @param aggregator aggregation function
     */
    public static <T extends IEnum, R extends IEnum, E extends AbstractUpdateEntity> List<NivoLineData> createLineData(
            List<E> input,
            Function<List<E>, Map<T, List<E>>> upperGrouping,
            Function<List<E>, Map<R, List<E>>> nestedGrouping,
            Function<List<E>, Integer> aggregator
    ) {
        return upperGrouping.apply(input).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(T::getOrderValue)))
                .map(e -> calculateLineData(e, nestedGrouping, aggregator))
                .collect(Collectors.toList());
    }

    private static  <T extends IEnum, R extends IEnum, E extends AbstractUpdateEntity> NivoLineData calculateLineData(
            Map.Entry<T, List<E>> mapEntry,
            Function<List<E>, Map<R, List<E>>> nestedGrouping,
            Function<List<E>, Integer> aggregator
    ) {
        List<NivoDataXY> summarizedGroups = nestedGrouping.apply(mapEntry.getValue()).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
                .map(entry -> new NivoDataXY(entry.getKey(), aggregator.apply(entry.getValue()).longValue()))
                .collect(Collectors.toList());
        return new NivoLineData(mapEntry.getKey(), summarizedGroups);
    }

    /**
     * Method gets data for displaying bubble chart. It is divided by two grouping upper grouping and nested grouping.
     *
     * @param input all entries obtained from database
     * @param upperGrouping top level grouping of data
     * @param lowerGrouping nested grouping of withing upper grouping
     * @param aggregator aggregation function
     */
    public static <T extends IEnum, R extends IEnum, E extends AbstractUpdateEntity> NivoBubbleData createBubbleData(
            List<E> input,
            Function<List<E>, Map<T, List<E>>> upperGrouping,
            Function<List<E>, Map<R, List<E>>> lowerGrouping,
            Function<List<E>, Integer> aggregator
    ) {
        List<NivoBubbleData> middleNivoBubbleDataList = new ArrayList<>();
        upperGrouping.apply(input).forEach((key, entity) -> {
            List<NivoBubbleData> nestedNivoBubbleDataList = lowerGrouping.apply(entity)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(R::getOrderValue)))
                    .map(e -> new NivoBubbleData(e.getKey(), aggregator.apply(e.getValue())))
                    .collect(Collectors.toList());
            middleNivoBubbleDataList.add(new NivoBubbleData(key, nestedNivoBubbleDataList));
        });
        middleNivoBubbleDataList.sort(Comparator.comparingInt(NivoBubbleData::getOrderValue));
        return new NivoBubbleData("PID predaj", middleNivoBubbleDataList);
    }

}
