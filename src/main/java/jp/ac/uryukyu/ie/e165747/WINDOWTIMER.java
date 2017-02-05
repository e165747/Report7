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

//WINDOWTIMERクラスはTIMERクラスをウィンドウ上で実行できるように改良したもの
public class WINDOWTIMER extends JFrame implements ActionListener,Runnable {

    static WINDOWTIMER frame = new WINDOWTIMER("Watch");    //画面上に表示するウィンドウ(の骨組み)を作成
    static Thread TH2 = new Thread(frame);

    private JPanel  p = new JPanel();   //いろいろな文字を表示させるのに必要なパネルを作成
    private JLabel nowTime = new JLabel();  //現在時刻を表示させるラベルを作成
    private JTextField wakeuphour = new JTextField();  //セットする時間を入力するテキストフィールドを作成
    private JTextField wakeupminite = new JTextField();
    private JLabel settimeprint = new JLabel(); //タイマー関係のメッセージを表示させるラベルを作成
    private JLabel colon = new JLabel();
    static int TimerO_clock;    //セットした時間を保存する変数
    static int TimerMinute;
    static boolean setTIMER;   //タイマーがセットされているかどうかを判定する



    public WINDOWTIMER(String title) {
        //ウィンドウのタイトル,位置,サイズを設定
        setTitle(title);
        setBounds(300, 200, 500, 400);
        //Xを押すとシステムが終了するように設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ボタンなどを自由に設置するためにレイアウトの初期設定をなしにする
        p.setLayout(null);

        //ボタン1(set),ボタン2(stop)を作成し，サイズと位置を設定
        JButton button1 = new JButton("set");
        button1.addActionListener(this);
        button1.setBounds(90,290,130,40);
        p.add(button1);
        JButton button2 = new JButton("stop");
        button2.addActionListener(this);
        button2.setBounds(290,290,130,40);
        p.add(button2);

        //4つのラベル，テキストフィールドそれぞれの位置，サイズを設定して追加
        settimeprint.setBounds(160,170,300,60);
        wakeuphour.setBounds(150,230,70,50);
        wakeupminite.setBounds(280,230,70,50);
        nowTime.setBounds(170,50,200,100);
        p.add(settimeprint);
        p.add(wakeuphour);
        p.add(wakeupminite);
        p.add(nowTime);

        settimeprint.setText("タイマーを設定してください");

        //現在時刻表示の文字サイズ，フォントを設定
        Font font1 = new Font("MS 明朝", Font.BOLD, 40);
        nowTime.setFont(font1);

        //colonはwakeuphourとwakeuptimeの間に:をつけるだけのラベル
        colon.setBounds(245,229,10,50);
        colon.setText(":");
        colon.setFont(font1);
        p.add(colon);

        Container contentPane = getContentPane();
        getContentPane().add(p);
        contentPane.add(p, BorderLayout.CENTER);


    }

    public void run() {
        //基本的にずっと動き続ける
        while (true) {
            //現在時刻を取得する
            Calendar calendar = Calendar.getInstance();
            int NowO_clock = calendar.get(Calendar.HOUR_OF_DAY);
            int NowMinute = calendar.get(Calendar.MINUTE);
            int NowSecond = calendar.get(Calendar.SECOND);

            //タイマーがセットされていない場合TimerMinuteを現在時刻+1分に設定してずっと動き続けるようにする
            if (!setTIMER) {
                TimerO_clock = NowO_clock + 1;
            }

            //現在時刻を表示する
            String NOW[] = new String[3];
            NOW[0] = String.valueOf(NowO_clock);
            NOW[1] = String.valueOf(NowMinute);
            NOW[2] = String.valueOf(NowSecond);
            String printNOWTIME = NOW[0] + ":" + NOW[1] + " " + NOW[2];
            nowTime.setText(printNOWTIME);

            //セットした時間になった場合，ビープ音を鳴らしてメッセージを表示する
            if(NowO_clock == TimerO_clock&& NowMinute == TimerMinute &&setTIMER){
                Toolkit.getDefaultToolkit().beep();
                settimeprint.setText("時間です！おはようございます！");
            }

            //スレッドが1秒間に一回動作するようにす設定し，エラーが出たらシステムを終了する
            try {
                TH2.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("エラーが発生しました。\n");
                System.exit(1);
            }
        }
    }


    //ボタンがクリックされた時の処理
    public void actionPerformed(ActionEvent e) {
        //クリックされたボタンの名前をbtMameに格納する
        String btName = e.getActionCommand();
        //setがクリックされた場合にタイマーをセットする
        if ("set".equals(btName)) {

            String TIMERmessage = "タイマーを" + wakeuphour.getText() + "時" + wakeupminite.getText() + "分にセットしました";
            settimeprint.setText(TIMERmessage);

            //セットしようとした時間がint型ではない，もしくは24時間60分の範囲外の場合もう一度入力させる
            try {
                TimerO_clock = Integer.parseInt(wakeuphour.getText());
                TimerMinute = Integer.parseInt(wakeupminite.getText());
            } catch (NumberFormatException e2) {
                settimeprint.setText("もう一度入力してください");
            }

            if (TimerO_clock >= 24 || TimerO_clock < 0 || TimerMinute >= 60 || TimerMinute < 0) {
                settimeprint.setText("もう一度入力してください");
            }

            setTIMER = true;

            //stopがクリックされた場合はセットした時間をリセットする
        } else if ("stop".equals(btName)) {
            wakeuphour.setText("");
            wakeupminite.setText("");
            settimeprint.setText("タイマーをセットしてください");
            setTIMER = false;
        }
    }
}


