package com.sd.demo.controller;

import org.checkerframework.common.reflection.qual.GetClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.demo.model.BankAccount;
import com.sd.demo.service.BankAccountService;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {
    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable String id) {
        BankAccount account = service.getBankAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdAccount = service.createBankAccount(bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount updatedAccount = service.updateBankAccount(id, bankAccount);
        if (updatedAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable String id) {
        if (service.deleteBankAccount(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<Void> transferFunds(@PathVariable String id, @RequestBody TransferRequest request) {
        try {
            service.transferFunds(id, request.getToAccountId(), request.getAmount());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> depositFunds(@PathVariable String id, @RequestBody DepositRequest request) {
        try {
            service.depositFunds(id, request.getAmount());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdrawFunds(@PathVariable String id, @RequestBody WithdrawRequest request) {
        try {
            service.withdrawFunds(id, request.getAmount());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/check-balance")
    public ResponseEntity<Double> checkBalance(@PathVariable String id) {
        try {
            double balance = service.checkBalance(id);
            return ResponseEntity.ok(balance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
