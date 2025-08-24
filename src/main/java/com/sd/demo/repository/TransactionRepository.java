package com.sd.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
