package com.example.section8.repository;

import com.example.section8.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByCustomerIdOrderByStartedAtDesc(int customerId);

}
