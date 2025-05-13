package com.captoneprojec.controller;

import com.captoneprojec.entity.BusBookingInfo;
import com.captoneprojec.service.BusBookingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/busbookinginfo")
@CrossOrigin("http://localhost:3000")
public class BusBookingInfoController {

    @Autowired
    private BusBookingInfoService busBookingInfoSer;

    @GetMapping
    public List<BusBookingInfo> listBBIR() {
        return busBookingInfoSer.listBBIR();
    }

    @GetMapping("/id/{bookingInfoId}")
    public ResponseEntity<?> findByBusBookingInfoId(@PathVariable String busBookingInfoId) {
        BusBookingInfo busBookingInfo = busBookingInfoSer.findByBusBookingInfoId(busBookingInfoId);
        if (busBookingInfo != null) {
            return ResponseEntity.ok(busBookingInfo); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BusBookingInfo not found, " + busBookingInfoId); // 404 NOT_FOUND
        }
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<?> findByBusId(@PathVariable String busId) {
        List<BusBookingInfo> busBookingInfo = busBookingInfoSer.findByBusId(busId);
        if (busBookingInfo != null && !busBookingInfo.isEmpty()) {
            return ResponseEntity.ok(busBookingInfo); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "BusBookingInfo not found", "busId", busId)); // 404 NOT_FOUND
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createBIR(@RequestBody BusBookingInfo busBookingInfo) {

        BusBookingInfo isExist = busBookingInfoSer.findByBusBookingInfoId(busBookingInfo.getBusBookingInfoId());
        if (isExist != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body("ID already exist " + busBookingInfo.getBusBookingInfoId());
        }

        BusBookingInfo bir = busBookingInfoSer.createBBIR(busBookingInfo);
        if (bir != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bir);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/update/{bookingInfoId}")
    public ResponseEntity<?> updateBIR(@PathVariable String bookingInfoId, @RequestBody BusBookingInfo updateBookingInfo) {
        BusBookingInfo bookingInfo = busBookingInfoSer.updateBBIR(bookingInfoId, updateBookingInfo);
        System.out.println(bookingInfo);
        if (bookingInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully."); // 200 OK
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BusBookingInfo not found, " + bookingInfoId); // 404 NOT_FOUND
    }

    @Transactional
    @DeleteMapping("/delete/{busBookingInfo}")
    public ResponseEntity<?> deleteByBusBookingInfoId(@PathVariable String busBookingInfo) {
        int isDeleted = busBookingInfoSer.deleteByBusBookingInfoId(busBookingInfo);
        if (isDeleted > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BusBookingInfo not found, " + busBookingInfo); // 404 NOT_FOUND
        }
    }
}
