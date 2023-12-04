package com.example.section10.repository;

import com.example.section10.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @PreAuthorize("hasRole('USER')")
    List<Loan> findAllByCustomerIdOrderByStartedAtDesc(int customerId);

}
