/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitesquad.ministore.repository;

import com.sitesquad.ministore.model.UserShift;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Long>, JpaSpecificationExecutor<UserShift> {
    public UserShift findByUserShiftId(Long userShiftId);
    public UserShift findTop1ByOrderByEndTimeDesc();
    public List<UserShift> findByIsPaidFalseOrIsPaidNull();
    public List<UserShift> findByUserId(Long id);
    public List<UserShift> findByAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(ZonedDateTime startTime, ZonedDateTime endTime);

    @Query("SELECT us FROM UserShift us WHERE YEAR(us.startTime) = :year " +
            "AND MONTH(us.startTime) = :month " +
            "AND DAY(us.startTime) = :day " +
            "AND DATEPART(HOUR, us.startTime) = :hour " +
            "AND DATEPART(MINUTE, us.startTime) = :minute")
    List<UserShift> findUserShiftsByStartTime(@Param("year") Integer year, @Param("month") Integer month, @Param("day") Integer day, @Param("hour") Integer hour, @Param("minute") Integer minute);

    public List<UserShift> findByAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(ZonedDateTime startTime, ZonedDateTime endTime);

    @Query(value = "select user_id, count(user_id) as 'total_shift_count'\n" +
            "from user_shift\n" +
            "where user_id is not null and month(start_time) = month(getdate()) and year(start_time) = year(getdate())\n" +
            "group by user_id\n" +
            "order by count(user_id) desc", nativeQuery = true)
    List<Map<String, Object>> findByUserRank();
}
