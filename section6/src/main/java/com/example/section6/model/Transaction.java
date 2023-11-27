package com.example.section6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
public class Transaction {

    @Id
    UUID uuid;

    int accountId;

    int customerId;

    Date transactionDate;

    String transactionSummary;

    String transactionType;

    int transactionAmt;

    int closingBalance;

    Date createdAt;

}
