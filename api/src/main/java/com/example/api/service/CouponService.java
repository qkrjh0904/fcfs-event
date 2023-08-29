package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public void issueACoupon(Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        Coupon coupon = Coupon.create(userId);
        couponRepository.save(coupon);
    }

}
