package com.cos.nomadapp.model.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaySaveReqDto {

    private String pay_method;  	// 결제수단
    private String imp_uid;			// 고유주문번호
    private String merchant_uid;	// 고유 주문번호
    private int paid_amount;			// 가격
    private String name;			// 주문명
    private String pg_tid;			// 카드사 승인번호
    private String buyer_name;		// 구매자명
    private String buyer_email;		// 구매자 이메일
    private String currency;		// 화폐단위
    private int card_quota;			// 할부
    private String status;			// 상태
    private Long courseId;
    private Long userId;
}
