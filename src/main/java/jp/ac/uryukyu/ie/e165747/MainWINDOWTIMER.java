package jp.ac.uryukyu.ie.e165747;

import javax.swing.*;

/**
 * Created by e165747 on 2017/02/05.
 */
//WINDWOTIMERクラスのメインメソッド
public class MainWINDOWTIMER { public static void main(String args[]) {

    WINDOWTIMER frame = new WINDOWTIMER("Watch");
    Thread TH2 = new Thread(frame);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    TH2.start();
}
}
