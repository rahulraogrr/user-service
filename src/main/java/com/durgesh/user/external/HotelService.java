package com.durgesh.user.external;

import com.durgesh.user.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "HOTEL-SERVICE", path = "/api/v1/hotels")
public interface HotelService {

    @GetMapping("/{hotelId}")
    Hotel getHotelById(@PathVariable(value = "hotelId") String hotelId);
}