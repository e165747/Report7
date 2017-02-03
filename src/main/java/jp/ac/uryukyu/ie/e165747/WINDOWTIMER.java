package jp.ac.uryukyu.ie.e165747;

import java.awt.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Calendar;

/*/
 * Created by e165747 on 2017/01/24.
 **/

public class WINDOWTIMER extends JFrame implements ActionListener,Runnable {

    static WINDOWTIMER frame = new WINDOWTIMER("Watch");
    static Thread TH2 = new Thread(frame);
    private JLabel label = new JLabel();
    private JPanel p = new JPanel();
    private JLabel nowTime = new JLabel();
    private JTextField text1 = new JTextField();
    private JTextField text2 = new JTextField();
    private JLabel settimeprint = new JLabel();
    private static int TimerO_clock;
    private static int TimerMinute;
    boolean setTIMER;
    private JLabel colon = new JLabel();

    public static void main(String args[]) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        TH2.start();
    }


    public WINDOWTIMER(String title) {

        setTitle(title);
        setBounds(300, 200, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        p.setLayout(null);

        JButton button1 = new JButton("set");
        button1.addActionListener(this);
        button1.setBounds(90,290,130,40);
        p.add(button1);
        JButton button2 = new JButton("close");
        button2.addActionListener(this);
        button2.setBounds(290,290,130,40);
        p.add(button2);


        settimeprint.setBounds(160,170,300,60);
        text1.setBounds(150,230,70,50);
        text2.setBounds(280,230,70,50);


        nowTime.setBounds(160,50,200,100);

        p.add(label);
        p.add(settimeprint);
        p.add(text1);
        p.add(text2);
        p.add(nowTime);

        settimeprint.setText("タイマーを設定してください");

        Font font1 = new Font("MS 明朝", Font.BOLD, 40);
        nowTime.setFont(font1);

        colon.setBounds(245,229,10,50);
        colon.setText(":");
        colon.setFont(font1);
        p.add(colon);

        Container contentPane = getContentPane();
        getContentPane().add(p);contentPane.add(p, BorderLayout.CENTER);


    }

    public void run() {

        while (true) {
            Calendar calendar = Calendar.getInstance();
            int NowO_clock = calendar.get(Calendar.HOUR_OF_DAY);
            int NowMinute = calendar.get(Calendar.MINUTE);
            int NowSecond = calendar.get(Calendar.SECOND);

            if (!setTIMER) {
                TimerMinute = NowMinute + 1;
            }
            String NOW[] = new String[3];

            NOW[0] = String.valueOf(NowO_clock);
            NOW[1] = String.valueOf(NowMinute);
            NOW[2] = String.valueOf(NowSecond);
            String printNOWTIME = NOW[0] + ":" + NOW[1] + " " + NOW[2];

            nowTime.setText(printNOWTIME);

            if(NowO_clock == TimerO_clock&& NowMinute == TimerMinute){
                Toolkit.getDefaultToolkit().beep();
                settimeprint.setText("時間です！おはようございます！");
            }
            try {
                TH2.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("エラーが発生しました。\n");
                System.exit(1);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String cmdName = e.getActionCommand();

        if ("set".equals(cmdName)) {

            String TIMERmessage = "タイマーを" + text1.getText() + "時" + text2.getText() + "分にセットしました";
            settimeprint.setText(TIMERmessage);
            try {
                TimerO_clock = Integer.parseInt(text1.getText());
                TimerMinute = Integer.parseInt(text2.getText());
            } catch(NumberFormatException e2){
                settimeprint.setText("もう一度入力してください");
            }
            if(TimerO_clock >=24 || TimerO_clock <=0 || TimerMinute >=60 || TimerMinute <=0){
                settimeprint.setText("もう一度入力してください");
            }

            setTIMER = true;
        }else if("close".equals(cmdName)) {
            System.exit(0);
        }
    }
}



