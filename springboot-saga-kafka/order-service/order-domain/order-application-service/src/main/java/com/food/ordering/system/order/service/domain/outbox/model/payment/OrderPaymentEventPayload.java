package com.food.ordering.system.order.service.domain.outbox.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class OrderPaymentEventPayload {

    @JsonProperty
    private String orderId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private String paymentOrderStatus;

    public OrderPaymentEventPayload() {

    }
}