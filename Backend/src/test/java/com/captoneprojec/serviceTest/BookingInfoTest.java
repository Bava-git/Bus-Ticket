package com.captoneprojec.serviceTest;

import com.captoneprojec.entity.BookingInfo;
import com.captoneprojec.repository.BookingInfoRepository;
import com.captoneprojec.service.BookingInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BookingInfoTest {

    @Mock
    private BookingInfoRepository bookingInfoRepository;
    @InjectMocks
    private BookingInfoService bookingInfoService;

    private BookingInfo bookingInfo1;
    private BookingInfo bookingInfo2;

    @BeforeEach
    void setup() {
        bookingInfo1 = new BookingInfo(
                "BI1", "B1", "KKR Travels", 20, 10, "RI1",
                LocalDateTime.of(2025, 5, 10, 17, 0),
                LocalDateTime.of(2025, 5, 10, 17, 0), LocalTime.of(10, 0), "Tirunelveli", "Chennai", 625
        );
        bookingInfo2 = new BookingInfo(
                "BI2", "B1", "OMG Travels", 20, 10, "RI2",
                LocalDateTime.of(2025, 5, 10, 17, 0),
                LocalDateTime.of(2025, 5, 10, 17, 0), LocalTime.of(10, 0), "Tirunelveli", "Chennai", 625)
        ;
    }

    @Test
    void listPBIR() {
        when(bookingInfoRepository.findAll()).thenReturn(Arrays.asList(bookingInfo1, bookingInfo2));
        List<BookingInfo> patientList = bookingInfoService.listPBIR();

        // validate
        assertEquals(2, patientList.size());
        verify(bookingInfoRepository, times(1)).findAll();
    }

    @Test
    void createBIR() {
        when(bookingInfoRepository.save(any(BookingInfo.class))).thenReturn(bookingInfo1);
        BookingInfo bookingInfo = bookingInfoService.createBIR(bookingInfo1);

        // validate
        assertNotNull(bookingInfo);
        assertEquals("KKR Travels", bookingInfo.getBusName());
        verify(bookingInfoRepository, times(1)).save(bookingInfo1);
    }

    @Test
    void getBookingInfoId() {
        when(bookingInfoRepository.findByBookingInfoId(bookingInfo2.getBookingInfoId())).thenReturn(bookingInfo2);
        BookingInfo bookingInfo = bookingInfoService.findByBookingInfoId(bookingInfo2.getBookingInfoId());

        assertEquals("OMG Travels", bookingInfo.getBusName());
        verify(bookingInfoRepository, times(1)).findByBookingInfoId(bookingInfo2.getBookingInfoId());
    }

    @Test
    void deleteByBookingInfoId() {
        bookingInfoService.deleteBIR("BI1");
        verify(bookingInfoRepository, times(1)).deleteByBookingInfoId("BI1");
    }
}
