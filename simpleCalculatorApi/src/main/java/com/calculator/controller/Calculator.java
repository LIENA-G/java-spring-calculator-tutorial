package com.calculator.controller;


import com.calculator.entity.Number;
import com.calculator.services.NumberService;
import com.calculator.services.calcuteService;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Calculator {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NumberService numberService;

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Number addNum(@RequestBody Number number) {
        number.setResult(calcuteService.add(number.getFirstNum(), number.getSecNum()));
        System.out.println(number.getFirstNum());
        System.out.println(number.toString());
        number.setOperation("add");
        numberService.save(number);
        return number;
    }

    @RequestMapping(value = "subtract", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Number subtractNum(@RequestBody Number number) {
        number.setResult(calcuteService.subtract(number.getFirstNum(), number.getSecNum()));
        System.out.println(number.toString());
        number.setOperation("subtract");
        numberService.save(number);
        return number;
    }
    @RequestMapping(value = "multiply", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Number multiplyNum(@RequestBody Number number) {
        number.setResult(calcuteService.multiply(number.getFirstNum(), number.getSecNum()));
        System.out.println(number.toString());
        number.setOperation("multiply");
        numberService.save(number);
        return number;
    }
    @RequestMapping(value = "divide", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Number devideNum(@RequestBody Number number) {
        number.setResult(calcuteService.divide(number.getFirstNum(), number.getSecNum()));
        System.out.println(number.toString());
        number.setOperation("divide");
        numberService.save(number);
        return number;
    }

    @RequestMapping(value = "/getOperation",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    List<Number> getOrganizedCards(@RequestBody String operation) {
        List<Number> numbers =numberService.findByOperation(operation);
        return numbers;
    }




}
