package com.example.section6.repository;

import com.example.section6.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByCustomerIdOrderByTransactionDateDesc(int customerId);

}
