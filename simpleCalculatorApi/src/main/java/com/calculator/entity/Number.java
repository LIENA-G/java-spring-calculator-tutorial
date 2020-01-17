package com.calculator.entity;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

@Table(name="number")
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int firstNum;
    private int secNum;
    private int result;
    private String operation;


    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecNum() {
        return secNum;
    }


    public void setSecNum(int secNum) {
        this.secNum = secNum;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", firstNum=" + firstNum +
                ", secNum=" + secNum +
                ", result=" + result +
                ", operation=" + operation +
                '}';
    }
}