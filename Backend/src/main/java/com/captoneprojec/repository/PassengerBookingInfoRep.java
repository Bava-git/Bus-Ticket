package com.captoneprojec.repository;

import com.captoneprojec.entity.PassengerBookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerBookingInfoRep extends JpaRepository<PassengerBookingInfo, Long> {
    PassengerBookingInfo findByPassengerBookingInfoId(String passengerBookingInfoId);

    PassengerBookingInfo findByPassengerId(String passengerId);

    int deleteByPassengerBookingInfoId(String passengerBookingInfoId);

    int deleteAllByPassengerId(String passengerId);
}
