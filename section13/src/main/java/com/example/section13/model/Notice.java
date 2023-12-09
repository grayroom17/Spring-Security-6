package com.example.section13.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "notice_detail")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String noticeSummary;

    String noticeDetails;

    Date noticBegDt;

    Date noticEndDt;

    Date createdAt;

    Date updatedAt;

}
