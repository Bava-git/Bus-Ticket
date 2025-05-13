package com.captoneprojec.service;

import com.captoneprojec.entity.BookingInfo;
import com.captoneprojec.repository.BookingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingInfoService {

    @Autowired
    private BookingInfoRepository bookingInfoRepository;

    public List<BookingInfo> listPBIR() {
        return bookingInfoRepository.findAll();
    }

    public BookingInfo findByBookingInfoId(String bookingInfoId) {
        return bookingInfoRepository.findByBookingInfoId(bookingInfoId);
    }

    public List<BookingInfo> findByBusId(String busId) {
        return bookingInfoRepository.findByBusId(busId);
    }

    public List<BookingInfo> findByRouteInfoId(String routeInfoId) {
        return bookingInfoRepository.findByRouteInfoId(routeInfoId);
    }

    public BookingInfo createBIR(BookingInfo bookingInfo) {
        return bookingInfoRepository.save(bookingInfo);
    }

    public int deleteBIR(String bookingInfoId) {
        return bookingInfoRepository.deleteByBookingInfoId(bookingInfoId);
    }

}
