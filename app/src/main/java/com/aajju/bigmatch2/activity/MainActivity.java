package com.aajju.bigmatch2.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.aajju.bigmatch2.R;
import com.aajju.bigmatch2.adapter.TestPagerAdapter;
import com.aajju.bigmatch2.ads.AdsConfig;
import com.aajju.bigmatch2.ads.SubAdlibAdViewAdmob;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mocoplex.adlib.AdlibConfig;
import com.mocoplex.adlib.AdlibManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.aajju.bigmatch2.StaticAll.BACK_BUTTON_MESSAGE;
import static com.aajju.bigmatch2.StaticAll.mDate;
import static com.aajju.bigmatch2.StaticAll.mFormat;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TestPagerAdapter mTestPagerAdapter;

    private AdlibManager mManager;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private int mYear, mMonth, mDay;
    private Calendar mCalendar = Calendar.getInstance();
    private Date mSelectedDate;

    TextView mDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mTestPagerAdapter= new TestPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTestPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tl);
        mTabLayout.setupWithViewPager(mViewPager);

        mDateTextView = (TextView) findViewById(R.id.main_date_tv);

        final Intent intent = new Intent(MainActivity.this, MatchActivity.class);

        //광고
        FirebaseInstanceId.getInstance().getToken();
        initAds();
        ///////

        mDateTextView.setText(mFormat.format(mDate));

        // 액션바 메뉴 버튼
        findViewById(R.id.main_action_btn_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });

        //날짜 왼쪽버튼
        findViewById(R.id.main_left_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = mDate - 60*60*24*1000;
                mDateTextView.setText(mFormat.format(mDate));
                /**
                 *  날짜가 하루 전날로 바뀌면
                 *  우선 경기 개수(이미지 뷰)가 날라간다 (gone)
                 *    - 이미지뷰는 뷰페이져 안의 프래그먼트에 있다
                 *
                 *  그다음 하루 전날의 경기 개수(이미지 뷰)를 셋팅한다
                 *    - 이미지뷰는 뷰페이져 안의 프래그먼트에 있다
                 *
                 */
                mViewPager.getAdapter().notifyDataSetChanged();
//                init();
//                getMatchList(mFormat.format(mDate));
            }
        });

        //날짜 오른쪽 버튼
        findViewById(R.id.main_right_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = mDate + 60*60*24*1000;
                mDateTextView.setText(mFormat.format(mDate));
                /**
                 * 다음날 날짜 받아와서 서버에 요청
                 */
                mViewPager.getAdapter().notifyDataSetChanged();
//                init();
//                getMatchList(mFormat.format(mDate));
            }
        });

        // today 클릭시
        findViewById(R.id.main_today_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = System.currentTimeMillis();
                mDateTextView.setText(mFormat.format(mDate));
                mViewPager.getAdapter().notifyDataSetChanged();
//                init();
//                getMatchList(mFormat.format(mDate));
            }
        });

        // 캘린더 클릭시
        findViewById(R.id.main_calendar_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar calendar = new GregorianCalendar();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(MainActivity.this, dateSetListener, mYear, mMonth, mDay).show();
            }
        });
    } // onCreate();

    /**
     * 광고 연습
     */
    private void initAds(){
        initAdlib();
//        initViews();
    }

    private void initAdlib(){
        //파라미터는 애드립 인벤토리 아이디를 넣으면 됩니다.
        mManager = new AdlibManager(AdsConfig.ADLIB_ID);

        mManager.onCreate(this);

        //애드립 말고 다른 광고 플랫폼을 연결하기 위해 사용되는 코드
        AdlibConfig.getInstance().bindPlatform("ADMOB","com.aajju.bigmatch2.ads.SubAdlibAdViewAdmob");
        AdlibConfig.getInstance().bindPlatform("ADMOB", SubAdlibAdViewAdmob.class.getCanonicalName());

        //애드립을 통해 출력되는 광고 모드 설정 true: 테스트
        mManager.setAdlibTestMode(true);
        //배너광고가 출력될 애드립 컨테이너 뷰 아이디
        mManager.setAdsContainer(R.id.main_ad);

        //애드립액티비티가 아니고 일반 액티비티(구글 순정) 사용할때 광고 출력하려면
        //애드립 매너저를 써야하는데, 애기비티 생명주기별로 관련메소드를 호출해야합니다.
    }



    @Override
    protected void onResume() {
        mManager.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mManager.onPause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mManager.onDestroy(this);
        super.onDestroy();
    }
    /////////////////////////////////////////////////////////// 광고

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1 + 1;;
            mDay = i2;

            mCalendar.set(i, i1, i2, 0, 0, 0);
            mSelectedDate = mCalendar.getTime();
            mDate = mSelectedDate.getTime();

            mDateTextView.setText(mFormat.format(mDate));
            mViewPager.getAdapter().notifyDataSetChanged();

        }
    };

    // 안드로이드 백버튼 2번 눌러 종료하기
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), BACK_BUTTON_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }
}
