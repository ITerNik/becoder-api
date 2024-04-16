package com.example.becoderapi.services;

import com.example.becoderapi.model.dto.Request;
import com.example.becoderapi.model.dto.Response;
import com.example.becoderapi.model.exceptions.NoSuchAccountException;
import com.example.becoderapi.model.managers.AccountManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private AccountManager manager;

    public Response buy(Request request) throws NoSuchAccountException {
        var admin = manager.getAccountById(request.id());
        admin.setBalance(admin.getBalance() + request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", admin.getBalance(), admin.getId()
        ));
    }

    public Response sell(Request request) throws NoSuchAccountException {
        var admin = manager.getAccountById(request.id());
        admin.setBalance(admin.getBalance() - request.cost());
        return new Response(String.format(
                "Success transaction! Balance now is %s, user id %s", admin.getBalance(), admin.getId()
        ));
    }

    public Response info() {
        return new Response(
                manager.getAllAccounts()
        );
    }

    public Response createAccount() {
        return new Response(
                manager.createAccount()
        );
    }
}
