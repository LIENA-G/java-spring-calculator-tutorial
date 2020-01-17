package com.calculator.services;


import com.calculator.entity.Number;
import com.calculator.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NumberService {
    @Autowired
    private NumberRepository numberRepository;

    public Number save(Number number) {
        return numberRepository.save(number);
    }

    public void delete(Number number) {
        numberRepository.delete(number);
    }

    public List<Number> findByOperation(String method) {
        return numberRepository.findByOperation(method);
    }


}
