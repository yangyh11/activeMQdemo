package com.yamgyh.activemq;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/7/1
 * @time 14:12
 */
public class producterThread implements Runnable{

    private Producter producter=new Producter();
    private String queuename;

    public producterThread(String queuename) {
        this.queuename = queuename;
    }

    public void run() {
        producter.init();
        producter.sendMessage(queuename);
    }
}
