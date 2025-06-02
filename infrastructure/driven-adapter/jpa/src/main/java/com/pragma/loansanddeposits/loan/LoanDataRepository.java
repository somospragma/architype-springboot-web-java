package com.pragma.loansanddeposits.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDataRepository extends JpaRepository<LoanData, String>, CrudRepository<LoanData, String>, QueryByExampleExecutor<LoanData> {
}

