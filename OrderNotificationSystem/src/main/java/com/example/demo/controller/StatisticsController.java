package com.example.demo.controller;


import com.example.demo.service.Notification.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    IStatisticsService statisticsService;

    @GetMapping("/get/mostNotifiedEmail")
    public String getMostNotifiedEmail() {
        return statisticsService.getMostNotifiedEmail();
    }

    @GetMapping("/get/mostNotifiedSMS")
    public String getMostNotifiedSMS() {
        return statisticsService.getMostNotifiedSMS();
    }

    @GetMapping("/get/mostUsedTemplate")
    public String getMostUsedTemplate() {
        return statisticsService.getMostUsedTemplate();
    }
}
