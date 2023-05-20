package com.example.photoapp.repository;

import com.example.photoapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(o.price) FROM Order o WHERE o.orderDateTime BETWEEN :startDateTime AND :endDateTime")
    Optional<Double> calculateRevenueByDateTimeRange(@Param("startDateTime") LocalDateTime startDateTime,
                                                     @Param("endDateTime") LocalDateTime endDateTime);

    List<Order> findByOrderDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query(value = "SELECT o.order_date_time FROM orders o WHERE o.gift = true LIMIT 1", nativeQuery = true)
    LocalDateTime findLastWinTime();
}
