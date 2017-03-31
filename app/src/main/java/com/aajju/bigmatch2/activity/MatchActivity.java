package com.aajju.bigmatch2.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aajju.bigmatch2.Match;
import com.aajju.bigmatch2.R;
import com.aajju.bigmatch2.adapter.MatchAdapter;
import com.aajju.bigmatch2.ads.AdsConfig;
import com.aajju.bigmatch2.ads.SubAdlibAdViewAdmob;
import com.aajju.bigmatch2.retrofit.Api;
import com.aajju.bigmatch2.retrofit.HttpHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mocoplex.adlib.AdlibConfig;
import com.mocoplex.adlib.AdlibManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aajju.bigmatch2.StaticAll.adsCount;

public class MatchActivity extends AppCompatActivity {
    Calendar mCalendar = Calendar.getInstance();
    Date mSelectedDate;
    private int mYear, mMonth, mDay;

    private List<Match> mArrayList;
    private long mDate;
    private int mLeague;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private Api mApi;
    private MatchAdapter mAdapter;
    private ListView mListView;
    private TextView mTitleTextView;
    private TextView mDateTextView;

    private AdlibManager mManager;
    Handler h;//핸들러 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        adsCount++;

        FirebaseInstanceId.getInstance().getToken();
        initAds();

        mDateTextView = (TextView) findViewById(R.id.main_date_tv);
        mApi = HttpHelper.getAPI();

        mTitleTextView = (TextView) findViewById(R.id.match_action_title_tv);

        Intent intent = getIntent();
        mLeague = intent.getIntExtra("league", -1);
        mDate = intent.getLongExtra("date", -1);
        mDateTextView.setText(mFormat.format(mDate));
        mArrayList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.match_lv);

        switch (mLeague) {
            case 1:
                mTitleTextView.setText("English Premier League");
                break;
            case 2:
                mTitleTextView.setText("La Liga");
                break;
            case 3:
                mTitleTextView.setText("Bundesriga");
                break;
            case 4:
                mTitleTextView.setText("Seria A");
                break;
            case 5:
                mTitleTextView.setText("K League");
                break;
            case 6:
                mTitleTextView.setText("UEFA Champions League");
                break;
            case 7:
                mTitleTextView.setText("UEFA Europa League");
                break;
            case 8:
                mTitleTextView.setText("AFC Champions League");
                break;
            case 9:
                mTitleTextView.setText("대한민국 국가대표 경기");
                break;
            case 10:
                mTitleTextView.setText("다른 나라 국가대표 경기");
                break;
            case 11:
                mTitleTextView.setText("KBO");
                break;
            case 12:
                mTitleTextView.setText("코리안 메이저리거");
                break;
            case 13:
                mTitleTextView.setText("MLB");
                break;
            case 14:
                mTitleTextView.setText("대한민국 야구대표팀");
                break;
            case 15:
                mTitleTextView.setText("다른 나라 야구 경기");
                break;
            case 16:
                mTitleTextView.setText("롤챔스");
                break;
            case 17:
                mTitleTextView.setText("롤드컵");
                break;
            case 18:
                mTitleTextView.setText("기타 롤 경기");
                break;
            case 19:
                mTitleTextView.setText("오버워치");
                break;
            case 20:
                mTitleTextView.setText("스타크래프트");
                break;
            case 21:
                mTitleTextView.setText("기타 게임");
                break;
            case 22:
                mTitleTextView.setText("농구");
                break;
            case 23:
                mTitleTextView.setText("배구");
                break;
            case 24:
                mTitleTextView.setText("UFC");
                break;
            case 25:
                mTitleTextView.setText("올림픽, 아시안 게임 등");
                break;
            case 26:
                mTitleTextView.setText("기타 스포츠 빅매치");
                break;

        }

        /**
         *  해당 리그 경기들 서버에 요청함 --> list에 담음
         */
        getList(true);


        //액션바 백버튼
        findViewById(R.id.action_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //날짜 왼쪽버튼
        findViewById(R.id.main_left_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = mDate - 60 * 60 * 24 * 1000;
                /**
                 *  하루전날 날짜 받아와서 서버에 요청
                 */
                getList(false);
//                mDateTextView.setText(mFormat.format(mDate));
            }
        });

        //날짜 오른쪽 버튼
        findViewById(R.id.main_right_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = mDate + 60 * 60 * 24 * 1000;
                /**
                 * 다음날 날짜 받아와서 서버에 요청
                 */
                getList(true);
//                mDateTextView.setText(mFormat.format(mDate));
            }
        });

        // today 클릭시
        findViewById(R.id.main_today_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate = System.currentTimeMillis();
                mDateTextView.setText(mFormat.format(mDate));
                getList(false);
            }
        });


        // 캘린더 눌렀을 때,
        findViewById(R.id.main_calendar_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar calendar = new GregorianCalendar();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(MatchActivity.this, dateSetListener, mYear, mMonth, mDay).show();
            }
        });

        //리스트 클릭(경기 클릭)
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = Uri.parse(mArrayList.get(i).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1 + 1;
            mDay = i2;
            mCalendar.set(i, i1, i2, 0, 0, 0);
            mSelectedDate = mCalendar.getTime();
            mDate = mSelectedDate.getTime();

            mDateTextView.setText(mFormat.format(mDate));
            getList(false);
        }
    };


    private void getList(final boolean leftRight) {
        mApi.getMatchList(mFormat.format(mDate), mLeague).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
                    if (!leftRight) {
                        if (mFormat.format(mDate).equals("2017-03-15")) {
                            Toast.makeText(MatchActivity.this, "하이라이트 영상이 없습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        mDate = mDate - 60 * 60 * 24 * 1000;
                        getList(false);
                    } else {
                        if (mFormat.format(mDate).equals(mFormat.format(System.currentTimeMillis()))) {
                            Toast.makeText(MatchActivity.this, "하이라이트 영상이 없습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        mDate = mDate + 60 * 60 * 24 * 1000;
//                        mDateTextView.setText(mFormat.format(mDate));
                        getList(true);
                    }
                } else {
                    mDateTextView.setText(mFormat.format(mDate));
                    mArrayList = response.body();
                    mAdapter = new MatchAdapter(mArrayList);
                    mListView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });
    }


    private void initAds() {
        initAdlib();
    }

    private void initAdlib() {
        //파라미터는 애드립 인벤토리 아이디를 넣으면 됩니다.
        mManager = new AdlibManager(AdsConfig.ADLIB_ID);

        mManager.onCreate(this);


        //애드립 말고 다른 광고 플랫폼을 연결하기 위해 사용되는 코드
        AdlibConfig.getInstance().bindPlatform("ADMOB", "com.aajju.bigmatch2.ads.SubAdlibAdViewAdmob");
        AdlibConfig.getInstance().bindPlatform("ADMOB", SubAdlibAdViewAdmob.class.getCanonicalName());


        //애드립을 통해 출력되는 광고 모드 설정 true: 테스트
        mManager.setAdlibTestMode(true);
        //배너광고가 출력될 애드립 컨테이너 뷰 아이디
        mManager.setAdsContainer(R.id.main_ad);

        //애드립액티비티가 아니고 일반 액티비티(구글 순정) 사용할때 광고 출력하려면
        //애드립 매너저를 써야하는데, 애기비티 생명주기별로 관련메소드를 호출해야합니다.

        h = new Handler(); //딜래이를 주기 위해 핸들러 생성
        h.postDelayed(mrun, 300); // 딜레이 ( 런어블 객체는 mrun, 시간 2초)
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

    //     광고 딜레이
    Runnable mrun = new Runnable() {
        @Override
        public void run() {
            System.out.println(adsCount);
            if (adsCount % 4 == 2) {
                // 전면광고 띄우기
                mManager.loadFullInterstitialAd(getApplicationContext());
            }
        }
    };

}
