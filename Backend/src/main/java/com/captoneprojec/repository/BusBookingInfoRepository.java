package com.captoneprojec.repository;

import com.captoneprojec.entity.BusBookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusBookingInfoRepository extends JpaRepository<BusBookingInfo, Long> {

    BusBookingInfo findByBusBookingInfoId(String busId);

    List<BusBookingInfo> findByBusId(String busId);

    int deleteAllByBusId(String busId);

    int deleteByBusBookingInfoId(String busBookingInfoId);
}
