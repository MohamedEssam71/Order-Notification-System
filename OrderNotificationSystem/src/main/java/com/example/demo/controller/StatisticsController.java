package com.example.demo.controller;


import com.example.demo.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    IStatisticsService statisticsService;

    @RequestMapping("/get/mostNotifiedEmail")
    public String getMostNotifiedEmail() {
        return statisticsService.getMostNotifiedEmail();
    }

    @RequestMapping("/get/mostNotifiedSMS")
    public String getMostNotifiedSMS() {
        return statisticsService.getMostNotifiedSMS();
    }

    @RequestMapping("/get/mostUsedTemplate")
    public String getMostUsedTemplate() {
        return statisticsService.getMostUsedTemplate();
    }
}
