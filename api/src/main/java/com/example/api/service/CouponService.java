package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public void issueACoupon(Long userId) {

        long count = couponRepository.count();

        if (count > 100) {
            return;
        }

        Coupon coupon = Coupon.create(userId);
        couponRepository.save(coupon);
    }

}
