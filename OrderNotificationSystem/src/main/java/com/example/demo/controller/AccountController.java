package com.example.demo.controller;


import com.example.demo.model.Account.Account;
import com.example.demo.model.Response;
import com.example.demo.service.Account.IAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    IAccService accService;

    @PostMapping("/add")
    public Response add(@RequestBody Account newAcc) {
        boolean status = accService.createUser(newAcc);

        Response response = new Response();
        if (status) {
            response.setStatus(true);
            response.setMessage("Account created");
        } else {
            response.setStatus(false);
            response.setMessage("Account with same name exists");
        }
        return response;
    }

    @GetMapping("/get/{username}")
    public Response get(@PathVariable String username) {
        Account acc = accService.getUser(username);

        Response response = new Response();
        if (acc != null) {
            response.setStatus(true);
            response.setMessage("Account found");
            acc.display();
        } else {
            response.setStatus(false);
            response.setMessage("Account with same name exists");
        }
        return response;
    }
}
