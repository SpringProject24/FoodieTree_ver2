package org.nmfw.foodietree.domain.reservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nmfw.foodietree.domain.customer.dto.resp.MyPageReservationDetailDto;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationDetailDto;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationFoundStoreIdDto;
import org.nmfw.foodietree.domain.reservation.dto.resp.ReservationStatusDto;

import java.util.List;

@Mapper
public interface ReservationMapper {

    //예약 목록 조회
    List<MyPageReservationDetailDto> findAll(@Param("customerId") String customerId);

    // 예약 시간 상세 조회
    ReservationStatusDto findByReservationId(@Param("reservationId") int reservationId);

    // 예약 취소
    void cancelReservation(@Param("reservationId") int reservationId);

    // 픽업 완료
    void completePickup(@Param("reservationId") int reservationId);

    // 예약 상세 정보 조회
    ReservationDetailDto findReservationById(@Param("reservationId") int reservationId);

    // 예약 새엇ㅇ
    boolean createReservation(@Param("customerId") String customerId, @Param("productId") long productId);

    // 가게 ID와 제한 수에 따라 예약 가능 제품 조회
    List<ReservationFoundStoreIdDto> findByStoreIdLimit(@Param("storeId") String storeId, @Param("cnt") int cnt);
}