package com.library.dto;

import lombok.Data;

@Data
public class CardDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String cardName;
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvv;
    private String company;
    private String address;
    private String phone;
    private String type;
    private String userId;
}
