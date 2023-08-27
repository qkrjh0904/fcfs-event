package com.example.api.service;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("한 번만 쿠폰 발급")
    public void issueACoupon() {
        // given
        couponService.issueACoupon(1L);

        // when
        long count = couponRepository.count();

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("동시에 쿠폰 발급")
    public void concurrencyIssueACoupon() throws InterruptedException {
        // given
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; ++i) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    couponService.issueACoupon(userId);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        long count = couponRepository.count();

        assertThat(count).isEqualTo(100);
    }

}