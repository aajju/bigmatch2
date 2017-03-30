package com.aajju.bigmatch2;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by aajju on 2017-03-18.
 */

public class StaticAll {
    public static long mDate = System.currentTimeMillis();
    public static final SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    //축구 탭
    public static final int PREMIER_LEAGUE = 1;
    public static final int LA_LIGA = 2;
    public static final int BUNDESRIGA = 3;
    public static final int SERIE_A = 4;
    public static final int K_LEAGUE = 5;
    public static final int UEFA_CHAMPIONS = 6;
    public static final int UEFA_EUROPA = 7;
    public static final int AFC_CHAMPIONS = 8;
    public static final int KOREA_NATIONAL = 9;
    public static final int OTHER_NATIONAL = 10;

    //야구 탭
    public static final int KBO = 11;
    public static final int KOREAN_MAJOR = 12;
    public static final int MLB = 13;
    public static final int KOREA_BASEBALL = 14;
    public static final int OTHER_BIG_MATCH_BASEBALL = 15;

    //E스포츠 탭
    public static final int LCK = 16;
    public static final int LOL_WORLD_CUP = 17;
    public static final int OTHER_BIG_MATCH_LOL = 18;
    public static final int OVERWATCH = 19;
    public static final int STARCRAFT = 20;
    public static final int OTHER_GAME = 21;

    //기타 탭
    public static final int OTHER_BASKETBALL = 22;
    public static final int OTHER_VALLEYBALL = 23;
    public static final int OTHER_UFC = 24;
    public static final int OTHER_KOREA_TEAM = 25;
    public static final int OTHER_SPORTS = 26;


    // 메시지
    public static final String EMPTY_MESSAGE = "빅매치가 존재하지 않습니다";
    public static final String BACK_BUTTON_MESSAGE = "'뒤로'버튼 한번 더 누르시면 종료됩니다";

    // 광고 주기
    public static int adsCount = 1;

}
