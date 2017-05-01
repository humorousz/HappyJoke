package com.humorousz.joke;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String jsonString = "{\"code\":200,\"msg\":\"success\",\"newslist\":[{\"id\":2394,\"title\":\"正点火车\",\"content\":\"<br\\/>商人吉米在铁路上做了多年的买卖，这天偶然发现一列火车<br\\/>准时到了站。<br\\/>他连忙跑到列车员跟前说：“请吸烟，我祝贺你！我在这条<br\\/>铁路上跑了15年，这还是第一次见火车正点到站。”<br\\/>“留着你的烟吧，”列车员说，“这是昨天的列车！”<br\\/>\"}]}";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

}