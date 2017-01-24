package jp.ac.uryukyu.ie.e165747;
import java.util.Scanner;
import java.util.Calendar;

/**
 * Created by e165747 on 2017/01/21.
 */

public class TIME implements Runnable {//Solverクラス1
    static TIME TIME = new TIME();
    static Thread TH = new Thread(TIME);

    public TIME() {
    }

    public void run() {
        System.out.println("起床時間をN時N分の順に設定してください");
        //System.out.println("起床時間: N時N分\n");
        Scanner scan = new Scanner(System.in);
        int wakeuphour = scan.nextInt();
        System.out.printf("起床時間: %d時N分\n",wakeuphour);
        int wakeupminute = scan.nextInt();
        System.out.printf("起床時間: %d時%d分\n",wakeuphour,wakeupminute);
        while (true) {
            Calendar calendar = Calendar.getInstance();
            int O_clock = calendar.getInstance().get(calendar.HOUR_OF_DAY);
            int Minute = calendar.get(calendar.MINUTE);
            int Se_cond = calendar.get(calendar.SECOND);
            if (Minute == wakeupminute && O_clock == wakeuphour) {
                System.out.println("時間です。おはようございます！\n");
                System.exit(0);
            }else {
                System.out.printf("現在時刻は%d時%d分%d秒です\n", O_clock, Minute, Se_cond);
                try {
                    TH.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("エラーが発生しました。\n");
                    System.exit(1);
                }
            }
        }
    }
}