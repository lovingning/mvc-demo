package com.knowledge.mnlin.znd;

import java.util.LinkedList;

/**
 * Created on 2018/11/16  11:55
 * function : 变量赋值方式为:调用 set***方法,而非反射
 *
 * @author mnlin
 */
public class AAA {
    private String attr;
    private LinkedList<String> init_key;
    private int i;

    public String getAttr() {
        return attr;
    }

    public AAA setAttr(String attr) {
        this.attr = attr;
        return this;
    }

    public LinkedList<String> getInit_key() {
        return init_key;
    }

    public AAA setInit_key(LinkedList<String> init_key) {
        this.init_key = init_key;
        return this;
    }

    public int getInt() {
        return i;
    }

    public AAA setInt(int i) {
        this.i = i;
        return this;
    }
}

