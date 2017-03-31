package com.aajju.bigmatch2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aajju.bigmatch2.Match;
import com.aajju.bigmatch2.R;
import com.aajju.bigmatch2.activity.MatchActivity;
import com.aajju.bigmatch2.retrofit.Api;
import com.aajju.bigmatch2.retrofit.HttpHelper;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aajju.bigmatch2.StaticAll.AFC_CHAMPIONS;
import static com.aajju.bigmatch2.StaticAll.BUNDESRIGA;
import static com.aajju.bigmatch2.StaticAll.EMPTY_MESSAGE;
import static com.aajju.bigmatch2.StaticAll.KOREA_NATIONAL;
import static com.aajju.bigmatch2.StaticAll.K_LEAGUE;
import static com.aajju.bigmatch2.StaticAll.LA_LIGA;
import static com.aajju.bigmatch2.StaticAll.OTHER_NATIONAL;
import static com.aajju.bigmatch2.StaticAll.PREMIER_LEAGUE;
import static com.aajju.bigmatch2.StaticAll.SERIE_A;
import static com.aajju.bigmatch2.StaticAll.UEFA_CHAMPIONS;
import static com.aajju.bigmatch2.StaticAll.UEFA_EUROPA;
import static com.aajju.bigmatch2.StaticAll.mDate;
import static com.aajju.bigmatch2.StaticAll.mFormat;


/**
 * Created by aajju on 2017-01-26.
 */

public class MainFragment extends ParentFragment {
    private Api mApi;

    private ImageView mPremierLeagueImageView;
    private ImageView mLaligaImageView;
    private ImageView mBundesrigaImageView;
    private ImageView mSerieAImageView;
    private ImageView mKLeagueImageView;
    private ImageView mUefaChampionsImageView;
    private ImageView mUefaEuropaImageView;
    private ImageView mAfcChampionsImageView;
    private ImageView mKoreaNationalImageView;
    private ImageView mOtherNationalImageView;

    private boolean mPremierLeagueChecked = false;
    private boolean mLaligaChecked = false;
    private boolean mBundesrigaChecked = false;
    private boolean mSerieAChecked = false;
    private boolean mKLeagueChecked = false;
    private boolean mUefaChampionsChecked = false;
    private boolean mUefaEuropaChecked = false;
    private boolean mAfcChampionsChecked = false;
    private boolean mKoreaNationalChecked = false;
    private boolean mOtherNationalChecked = false;

    private TextView mPremierLeagueNumber;
    private TextView mLaligaNumber;
    private TextView mBundesrigaNumber;
    private TextView mSerieANumber;
    private TextView mKLeagueNumber;
    private TextView mUefaChampionsNumber;
    private TextView mUefaEuropaNumber;
    private TextView mAfcChampionsNumber;
    private TextView mKoreaNationalNumber;
    private TextView mOtherNationalNumber;

    public static MainFragment newInstance(String data){
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        args.putString("data", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mTextView = (TextView) view.findViewById(R.id.fragment_tv);
        mPremierLeagueImageView = (ImageView) view.findViewById(R.id.main_premier_league_iv);
        mLaligaImageView = (ImageView) view.findViewById(R.id.main_la_liga_iv);
        mBundesrigaImageView = (ImageView) view.findViewById(R.id.main_bundesriga_iv);
        mSerieAImageView = (ImageView) view.findViewById(R.id.main_serie_a_iv);
        mKLeagueImageView = (ImageView) view.findViewById(R.id.main_k_league_iv);
        mUefaChampionsImageView = (ImageView) view.findViewById(R.id.main_champions_league_iv);
        mUefaEuropaImageView = (ImageView) view.findViewById(R.id.main_europa_league_iv);
        mAfcChampionsImageView = (ImageView) view.findViewById(R.id.main_afc_champions_league_iv);
        mKoreaNationalImageView = (ImageView) view.findViewById(R.id.main_a_match_korea_iv);
        mOtherNationalImageView = (ImageView) view.findViewById(R.id.main_a_match_others_iv);

        mPremierLeagueNumber = (TextView) view.findViewById(R.id.main_premier_league_text);
        mLaligaNumber = (TextView) view.findViewById(R.id.main_la_liga_text);
        mBundesrigaNumber = (TextView) view.findViewById(R.id.main_bundesriga_text);
        mSerieANumber = (TextView) view.findViewById(R.id.main_serie_a_text);
        mKLeagueNumber = (TextView) view.findViewById(R.id.main_k_league_text);
        mUefaChampionsNumber = (TextView) view.findViewById(R.id.main_champions_league_text);
        mUefaEuropaNumber = (TextView) view.findViewById(R.id.main_europa_league_text);
        mAfcChampionsNumber = (TextView) view.findViewById(R.id.main_afc_champions_league_text);
        mKoreaNationalNumber = (TextView) view.findViewById(R.id.main_a_match_korea_text);
        mOtherNationalNumber = (TextView) view.findViewById(R.id.main_a_match_others_text);


        final Intent intent = new Intent(getActivity(), MatchActivity.class);


        mApi = HttpHelper.getAPI();
        getMatchList(mFormat.format(mDate));

        /**
         *  유럽 4대리그 + k리그 눌렀을때
         */
        // 프리미어리그 눌렀을 때
        view.findViewById(R.id.main_premier_league).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPremierLeagueChecked){
                    intent.putExtra("league", PREMIER_LEAGUE);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 라리가 눌렀을 때
        view.findViewById(R.id.main_la_liga).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLaligaChecked){
                    intent.putExtra("league", (Serializable) LA_LIGA);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 분데스리가 눌렀을 때
        view.findViewById(R.id.main_bundesriga).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBundesrigaChecked){
                    intent.putExtra("league", BUNDESRIGA);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 세리에 눌렀을 때
        view.findViewById(R.id.main_serie_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSerieAChecked){
                    intent.putExtra("league", SERIE_A);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // k리그 눌렀을 때
        view.findViewById(R.id.main_k_league).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mKLeagueChecked){
                    intent.putExtra("league", K_LEAGUE);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });


        /**
         *  대륙간 클럽 경기 눌렀을 때
         */
        // 챔피언스리그 눌렀을 때
        view.findViewById(R.id.main_champions_league).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUefaChampionsChecked){
                    intent.putExtra("league", UEFA_CHAMPIONS);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 유로파리그 눌렀을 때
        view.findViewById(R.id.main_europa_league).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUefaEuropaChecked){
                    intent.putExtra("league", UEFA_EUROPA);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 아챔 눌렀을 때
        view.findViewById(R.id.main_afc_champions_league).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAfcChampionsChecked){
                    intent.putExtra("league", AFC_CHAMPIONS);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });


        /**
         *  a매치 눌렀을 때
         */
        // 대한민국 경기 눌렀을 때
        view.findViewById(R.id.main_a_match_korea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mKoreaNationalChecked){
                    intent.putExtra("league", KOREA_NATIONAL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 다른나라 경기 눌렀을 때
        view.findViewById(R.id.main_a_match_others).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOtherNationalChecked){
                    intent.putExtra("league", OTHER_NATIONAL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();

                }
            }
        });



//        Bundle bundle = getArguments();
//        String data = bundle.getString("data");
//        mTextView.setText(data);
    }

    private void getMatchList(String matchDay) {
        // 프리미어리그 목록 숫자 가져오기
        mApi.getMatchList(matchDay, PREMIER_LEAGUE).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mPremierLeagueChecked = setImageView(mPremierLeagueImageView, mPremierLeagueNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 라리가 경기 목록 가져오기
        mApi.getMatchList(matchDay, LA_LIGA).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mLaligaChecked = setImageView(mLaligaImageView, mLaligaNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 분데스리가 경기 목록 가져오기
        mApi.getMatchList(matchDay, BUNDESRIGA).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mBundesrigaChecked = setImageView(mBundesrigaImageView, mBundesrigaNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 세리에 경기 목록 가져오기
        mApi.getMatchList(matchDay, SERIE_A).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mSerieAChecked = setImageView(mSerieAImageView, mSerieANumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 케이리그 경기 목록 가져오기
        mApi.getMatchList(matchDay, K_LEAGUE).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mKLeagueChecked = setImageView(mKLeagueImageView, mKLeagueNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 챔피언스리그 경기 목록 가져오기
        mApi.getMatchList(matchDay, UEFA_CHAMPIONS).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mUefaChampionsChecked = setImageView(mUefaChampionsImageView, mUefaChampionsNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 유로파리그 경기 목록 가져오기
        mApi.getMatchList(matchDay, UEFA_EUROPA).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mUefaEuropaChecked = setImageView(mUefaEuropaImageView, mUefaEuropaNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 아챔 경기 목록 가져오기
        mApi.getMatchList(matchDay, AFC_CHAMPIONS).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mAfcChampionsChecked = setImageView(mAfcChampionsImageView, mAfcChampionsNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 대한민국 국대 경기 목록 가져오기
        mApi.getMatchList(matchDay, KOREA_NATIONAL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mKoreaNationalChecked = setImageView(mKoreaNationalImageView, mKoreaNationalNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 기타 국대 경기 목록 가져오기
        mApi.getMatchList(matchDay, OTHER_NATIONAL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherNationalChecked = setImageView(mOtherNationalImageView, mOtherNationalNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

    }


}
