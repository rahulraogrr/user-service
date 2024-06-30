package com.durgesh.user.external;

import com.durgesh.user.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "RATING-SERVICE", path = "/api/v1/ratings")
public interface RateService {

    @GetMapping("/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") String userId);
}