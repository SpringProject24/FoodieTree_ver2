package org.nmfw.foodietree.domain.reservation.dto.resp;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.reservation.entity.ReservationStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
public class ReservationDetailDto {

    private int reservationId;
    private int productId;
    private String customerId;
    private LocalDateTime reservationTime; // 고객이 예약한 시간
    private LocalDateTime cancelReservationAt; // 고객이 얘약을 취소한 시간 null 가능, 값이 존재한다면 예약취소 된 것
    private LocalDateTime pickedUpAt; // 고객이 픽업한 시간
    private String storeId;
    private LocalDateTime pickupTime; // 가게에서 지정한 픽업가능 시간
    private String storeName;
    private String category;
    private String address;
    private int price;
    private String storeImg;
    private String nickname;
    private String profileImage;
    private ReservationStatus status;

    // 시간 관련 필드 포멧팅
    private String reservationTimeF;
    private String cancelReservationAtF;
    private String pickedUpAtF;
    private String pickupTimeF;

    // 포멧팅 함수
    public void formatTimes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월dd일 HH시mm분");
        if (reservationTime != null) {
            this.reservationTimeF = reservationTime.format(formatter);
        }
        if (cancelReservationAt != null) {
            this.cancelReservationAtF = cancelReservationAt.format(formatter);
        }
        if (pickedUpAt != null) {
            this.pickedUpAtF = pickedUpAt.format(formatter);
        }
        if (pickupTime != null) {
            this.pickupTimeF = pickupTime.format(formatter);
        }
    }
}