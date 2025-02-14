package com.food.ordering.system.order.service.application.rest;

import java.io.File;
public class HelloWorld {
    public static void main(String[] args) {
        String xx= "{\"orderIdvvv\":\"33f4a294-d4bd-4c70-a0ce-b00658b3a3cc\",\"userId\":\"d215b5f8-0249-4dc5-89a3-51fd148cfb41\",\"price\":200.00,\"createdAt\":\"2024-06-25T06:30:18.791533308Z\",\"paymentOrderStatus\":\"PENDING\"}";
        //xx = xx.replaceAll(File.separator,"");
        System.out.println(xx);
    }
}