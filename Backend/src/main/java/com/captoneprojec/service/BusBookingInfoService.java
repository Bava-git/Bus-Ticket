package com.captoneprojec.service;

import com.captoneprojec.entity.BusBookingInfo;
import com.captoneprojec.repository.BusBookingInfoRepository;
import com.captoneprojec.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusBookingInfoService {

    @Autowired
    private BusBookingInfoRepository bookingInfoRepository;

    public List<BusBookingInfo> listBBIR() {
        return bookingInfoRepository.findAll();
    }

    public BusBookingInfo findByBusBookingInfoId(String busId) {
        return bookingInfoRepository.findByBusBookingInfoId(busId);
    }

    public List<BusBookingInfo> findByBusId(String busId) {
        return bookingInfoRepository.findByBusId(busId);
    }

    public BusBookingInfo createBBIR(BusBookingInfo busBookingInfo) {
        return bookingInfoRepository.save(busBookingInfo);
    }

    public BusBookingInfo updateBBIR(String busId, BusBookingInfo updatebusBookingInfo) {
        BusBookingInfo existBus = bookingInfoRepository.findByBusBookingInfoId(busId);

        if (existBus != null) {
            existBus.setBusBookingInfoId((updatebusBookingInfo.getBusBookingInfoId()));
            existBus.setBusId(updatebusBookingInfo.getBusId());
            existBus.setBookingDate(updatebusBookingInfo.getBookingDate());
            existBus.setPassengerGender(updatebusBookingInfo.getPassengerGender());
            existBus.setBookedSeatNum(updatebusBookingInfo.getBookedSeatNum());
            return bookingInfoRepository.save(updatebusBookingInfo);
        }

        return null;
    }

    public int deleteAllByBusId(String busId) {
        return bookingInfoRepository.deleteAllByBusId(busId);
    }

    public int deleteByBusBookingInfoId(String BusBookingInfoId) {
        return bookingInfoRepository.deleteByBusBookingInfoId(BusBookingInfoId);
    }

}
