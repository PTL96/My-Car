package org.example.carshop.service;

import org.example.carshop.entity.accout.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByUsername(String username);

    boolean existsAccountByEmail(String email);

    boolean existsAccountByUsername(String username);

    Account findById(Long id);

    void save(Account account);
}
