package jp.ac.uryukyu.ie.e165747;

import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * Created by e165747 on 2017/02/05.
 */
public class WINDOWTIMERTest {
    @Test

    //タイマーに時間がちゃんとセットされているかを調べるテスト
    public void run() throws Exception {
        Calendar calendar = Calendar.getInstance();

        WINDOWTIMER frame = new WINDOWTIMER("Watch");
        Thread TH2 = new Thread(frame);
        TH2.start();
        WINDOWTIMER.setTIMER =true;

        WINDOWTIMER.TimerO_clock = calendar.get(Calendar.HOUR_OF_DAY);
        WINDOWTIMER.TimerMinute = calendar.get(Calendar.MINUTE);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY),WINDOWTIMER.TimerO_clock);
        assertEquals(calendar.get(Calendar.MINUTE),WINDOWTIMER.TimerMinute);
    }

}

