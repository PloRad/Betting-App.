package com.intellibet.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerA;
    private String playerB;
    private EventCategory category;
    private Float oddA;
    private Float oddB;
    private Float oddX;
    private LocalDateTime dateTime;

}
