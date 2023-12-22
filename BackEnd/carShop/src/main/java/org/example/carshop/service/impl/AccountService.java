package org.example.carshop.service.impl;

import org.example.carshop.entity.accout.Account;
import org.example.carshop.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements IAccountService {
    @Override
    public Optional<Account> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean existsAccountByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsAccountByUsername(String username) {
        return false;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public void save(Account account) {

    }
}
