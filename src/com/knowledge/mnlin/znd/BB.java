package com.knowledge.mnlin.znd;

import java.io.Serializable;

 /**
 * 测试bean
 */
public class BB implements Serializable {
    private String aaa = "222";
    private int bbb = 33;
    private transient double dd= 3.00003;
    private BB ob = this;

    private BB(){
    }

    public BB(boolean b){
    }

     public String getAaa() {
         return aaa;
     }

     public BB setAaa(String aaa) {
         this.aaa = aaa;
         return this;
     }

     public int getBbb() {
         return bbb;
     }

     public BB setBbb(int bbb) {
         this.bbb = bbb;
         return this;
     }

     public double getDd() {
         return dd;
     }

     public BB setDd(double dd) {
         this.dd = dd;
         return this;
     }

     public BB getOb() {
         return ob;
     }

     public BB setOb(BB ob) {
         this.ob = ob;
         return this;
     }
 }
