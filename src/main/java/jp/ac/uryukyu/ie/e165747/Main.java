package jp.ac.uryukyu.ie.e165747;

 /**
 ** Created by e165747 on 2017/01/18.
 */

public class Main {
    public static void main(String args[]){
        TIME TIME = new TIME();
        Thread TH = new Thread(TIME);
        TH.start();
    }
}
