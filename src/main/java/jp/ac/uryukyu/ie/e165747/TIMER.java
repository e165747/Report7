package jp.ac.uryukyu.ie.e165747;
import java.util.Scanner;
import java.util.Calendar;



/**
 * Created by e165747 on 2017/01/21.
 */

public class TIMER implements Runnable {//Solverクラス1

    static TIMER TIMER = new TIMER();
    static Thread TH = new Thread(TIMER);

    public TIMER() {
    }


    public void NowTime() {
        Calendar calendar = Calendar.getInstance();
        int NowO_clock = calendar.getInstance().get(calendar.HOUR_OF_DAY);
        int NowMinute = calendar.get(calendar.MINUTE);
        System.out.printf("現在時刻:%d時%d分\n",NowO_clock,NowMinute);
    }


    public int SetWakeuptime(){
        Scanner scan = new Scanner(System.in);
        int Wakeuptime =0;
        try {
            Wakeuptime = scan.nextInt();
        }catch (java.util.InputMismatchException e) {
            System.out.println("もう一度入力してください");
            SetWakeuptime();
        }

        return Wakeuptime;
    }


    public void run() {
        System.out.println("起床時間をA時B分の順に設定してください");
        System.out.printf("A:");
        int wakeuphour =SetWakeuptime();
        System.out.printf("B:");
        int wakeupminute = SetWakeuptime();
        System.out.printf("起床時間: %d時%d分\n",wakeuphour,wakeupminute);

        while (true) {
            Calendar calendar = Calendar.getInstance();
            int O_clock = calendar.getInstance().get(calendar.HOUR_OF_DAY);
            int Minute = calendar.get(calendar.MINUTE);
            int Se_cond = calendar.get(calendar.SECOND);

            if (Minute == wakeupminute && O_clock == wakeuphour) {
                System.out.printf("時間です。おはようございます！\n現在時刻:%d時%d分",O_clock, Minute);
                break;
            }else if(Se_cond == 0){
                System.out.printf("現在時刻:%d時%d分\n", O_clock, Minute);
                try {
                    TH.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("エラーが発生しました。\n");
                    break;
                }
            }
        }
    }


}