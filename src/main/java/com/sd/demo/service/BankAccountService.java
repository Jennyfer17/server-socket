package com.sd.demo.service;

import java.util.List;

import com.sd.demo.model.BankAccount;
import com.sd.demo.repository.BankAccountRepository;

public class BankAccountService {
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    public BankAccount getBankAccountById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<BankAccount> getAllBankAccounts() {
        return repository.findAll();
    }

    public BankAccount updateBankAccount(String id, BankAccount bankAccount) {
        if (!repository.existsById(id)) {
            return null;
        }
        bankAccount.setAccountNumber(id);
        return repository.save(bankAccount);
    }

    public boolean deleteBankAccount(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public void transferFunds(String fromAccountId, String toAccountId, double amount) {
        BankAccount fromAccount = getBankAccountById(fromAccountId);
        BankAccount toAccount = getBankAccountById(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account IDs");
        }

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        updateBankAccount(fromAccountId, fromAccount);
        updateBankAccount(toAccountId, toAccount);
    }

    public void deposit(String accountId, double amount) {
        BankAccount account = getBankAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }
        account.setBalance(account.getBalance() + amount);
        updateBankAccount(accountId, account);
    }

    public void withdraw(String accountId, double amount) {
        BankAccount account = getBankAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        updateBankAccount(accountId, account);
    }
    public double checkBalance(String accountNumber) {
        BankAccount account = getBankAccountById(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account ID");
        }
        return account.getBalance();
    }
}
