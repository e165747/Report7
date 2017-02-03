package jp.ac.uryukyu.ie.e165747;

 /**
 ** Created by e165747 on 2017/01/18.
 */

public class Main {
    public static void main(String args[]){
        TIMER TIMER = new TIMER();
        Thread TH = new Thread(TIMER);

        TIMER.NowTime();
        TH.start();
    }
}

