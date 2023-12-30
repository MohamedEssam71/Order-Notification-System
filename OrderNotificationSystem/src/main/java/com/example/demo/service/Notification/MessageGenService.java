package com.example.demo.service.Notification;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

@Service
public class MessageGenService {
    private String format(String template, Map<String, Object> parameters) {
        StringBuilder newTemplate = new StringBuilder(template);
        List<Object> valueList = new ArrayList<>();

        Matcher matcher = Pattern.compile("[$][{](\\w+)}").matcher(template);

        while (matcher.find()) {
            String key = matcher.group(1);

            String paramName = "${" + key + "}";
            int index = newTemplate.indexOf(paramName);
            if (index != -1) {
                newTemplate.replace(index, index + paramName.length(), "%s");
                valueList.add(parameters.get(key));
            }
        }

        return String.format(newTemplate.toString(), valueList.toArray());
    }

    public String generate(String type, Map<String, Object> params, String lang) {
        String template = null;

        switch (type) {
            case "order-created" -> {
                if(lang.equals("en")){
                    template = "Order ${id} created. Price = ${price}";
                }
                else if(lang.equals("ar")){
                    template = "الاوردر ${id} تم انشائه. السعر = ${price}";
                }
            }
            case "order-shipped" -> {
                if(lang.equals("en")){
                    template = "Shipping order ${id}. Price = ${price}";
                }
                else if(lang.equals("ar")){
                    template = "الاوردر ${id} يتم شحنه. السعر = ${price}";
                }
            }
        }
        return format(template, params);
    }
}
