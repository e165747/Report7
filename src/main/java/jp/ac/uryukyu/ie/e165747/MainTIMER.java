package jp.ac.uryukyu.ie.e165747;

 /**
 ** Created by e165747 on 2017/01/18.
 */

 //TIMERクラスのメインメソッド
public class MainTIMER {
    public static void main(String args[]){
        TIMER TIMER = new TIMER();
        Thread TH = new Thread(TIMER);

        TIMER.NowTime();
        TH.start();
    }
}

