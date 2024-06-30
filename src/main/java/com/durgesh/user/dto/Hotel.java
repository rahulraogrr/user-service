package com.durgesh.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String phone;
    private String email;
    private HotelStatus hotelStatus;
    private ZonedDateTime createTs;
    private ZonedDateTime updateTs;
}