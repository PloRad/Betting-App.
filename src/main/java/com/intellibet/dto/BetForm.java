package com.intellibet.dto;

import lombok.Data;


@Data
public class BetForm {

    private String value;
    private String option;
    private String eventId;

    public BetForm(String value) {
        this.value = value;
    }
}
