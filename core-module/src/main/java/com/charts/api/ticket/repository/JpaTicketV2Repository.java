package com.charts.api.ticket.repository;

import com.charts.api.coupon.entity.GroupingEntity;
import com.charts.api.ticket.entity.v2.UpdateTicketEntity;
import com.charts.general.entity.enums.Months;
import com.charts.general.entity.enums.TicketTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTicketV2Repository extends JpaRepository<UpdateTicketEntity, Long> {

    List<UpdateTicketEntity> findAllByMonthInAndYearInAndTicketTypeInAndDiscountedIn(
            List<Months> monthList,
            List<Integer> yearList,
            List<TicketTypes> ticketTypeList,
            List<Boolean> discountedList
    );

    @Query(
            "SELECT new com.charts.api.coupon.entity.GroupingEntity(c.ticketType, SUM(c.value)) "
                    + "FROM UpdateTicketEntity AS c "
                    + "WHERE c.month IN ?1 AND c.discounted IN ?2 AND c.ticketType IN ?3 AND c.year IN ?4 "
                    + "GROUP BY c.ticketType"
    )
    List<GroupingEntity<TicketTypes>> findGroupedByTicketType (
            List<Months> months,
            List<Boolean> discounted,
            List<TicketTypes> ticketTypes,
            List<Integer> years
    );

}
