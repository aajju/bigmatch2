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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aajju.bigmatch2.StaticAll.EMPTY_MESSAGE;
import static com.aajju.bigmatch2.StaticAll.LCK;
import static com.aajju.bigmatch2.StaticAll.LOL_WORLD_CUP;
import static com.aajju.bigmatch2.StaticAll.OTHER_BIG_MATCH_LOL;
import static com.aajju.bigmatch2.StaticAll.OTHER_GAME;
import static com.aajju.bigmatch2.StaticAll.OVERWATCH;
import static com.aajju.bigmatch2.StaticAll.STARCRAFT;
import static com.aajju.bigmatch2.StaticAll.mDate;
import static com.aajju.bigmatch2.StaticAll.mFormat;

/**
 * Created by aajju on 2017-01-26.
 */

public class ESportsFragment extends ParentFragment {
    private Api mApi;

    private ImageView mLolChampionsImageView;
    private ImageView mLolWorldCupImageView;
    private ImageView mLolOtherImageView;
    private ImageView mOverwatchImageView;
    private ImageView mStarcraftImageView;
    private ImageView mOtherGameImageView;

    private boolean mLolChampionsChecked = false;
    private boolean mLolWorldCupChecked = false;
    private boolean mLolOtherChecked = false;
    private boolean mOverwatchChecked = false;
    private boolean mStarcraftChecked = false;
    private boolean mOtherGameChecked = false;

    private TextView mLolChampionsNumber;
    private TextView mLolWorldCupNumber;
    private TextView mLolOtherNumber;
    private TextView mOverwatchNumber;
    private TextView mStarcraftNumber;
    private TextView mOtherGameNumber;


    public static ESportsFragment newInstance(String data){
        ESportsFragment fragment = new ESportsFragment();

        Bundle args = new Bundle();
        args.putString("data", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_esports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLolChampionsImageView = (ImageView) view.findViewById(R.id.lol_champions_iv);
        mLolWorldCupImageView = (ImageView) view.findViewById(R.id.lol_world_cup_iv);
        mLolOtherImageView = (ImageView) view.findViewById(R.id.lol_other_iv);
        mOverwatchImageView = (ImageView) view.findViewById(R.id.overwatch_iv);
        mStarcraftImageView = (ImageView) view.findViewById(R.id.starcraft_iv);
        mOtherGameImageView = (ImageView) view.findViewById(R.id.other_game_iv);

        mLolChampionsNumber = (TextView) view.findViewById(R.id.lol_champions_text);
        mLolWorldCupNumber = (TextView) view.findViewById(R.id.lol_world_cup_text);
        mLolOtherNumber = (TextView) view.findViewById(R.id.lol_other_text);
        mOverwatchNumber = (TextView) view.findViewById(R.id.overwatch_text);
        mStarcraftNumber = (TextView) view.findViewById(R.id.starcraft_text);
        mOtherGameNumber = (TextView) view.findViewById(R.id.other_game_text);


        final Intent intent = new Intent(getActivity(), MatchActivity.class);

        mApi = HttpHelper.getAPI();
        getMatchList(mFormat.format(mDate));

        /**
         * 리스트 클릭하기
         */
        // 롤챔스 클릭했을 때
        view.findViewById(R.id.lol_champions_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLolChampionsChecked){
                    intent.putExtra("league", LCK);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 롤드컵 클릭했을 때
        view.findViewById(R.id.lol_world_cup_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLolWorldCupChecked){
                    intent.putExtra("league", LOL_WORLD_CUP);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 롤 기타 경기 클릭했을 때
        view.findViewById(R.id.lol_other_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLolOtherChecked){
                    intent.putExtra("league", OTHER_BIG_MATCH_LOL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 오버워치 클릭했을 때
        view.findViewById(R.id.overwatch_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOverwatchChecked){
                    intent.putExtra("league", OVERWATCH);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 스타크래프트 클릭했을 때
        view.findViewById(R.id.starcraft_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mStarcraftChecked){
                    intent.putExtra("league", STARCRAFT);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 기타 게임 클릭했을 때
        view.findViewById(R.id.other_game_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOtherGameChecked){
                    intent.putExtra("league", OTHER_GAME);
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

    // 이미지뷰 가져오기 ( 목록 가져오기)
    private void getMatchList(String matchDay) {
        // 롤챔스 목록 숫자 가져오기
        mApi.getMatchList(matchDay, LCK).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mLolChampionsChecked = setImageView(mLolChampionsImageView, mLolChampionsNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 롤드컵 경기 목록 가져오기
        mApi.getMatchList(matchDay, LOL_WORLD_CUP).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mLolWorldCupChecked = setImageView(mLolWorldCupImageView, mLolWorldCupNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 롤 기타 경기 목록 가져오기
        mApi.getMatchList(matchDay, OTHER_BIG_MATCH_LOL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mLolOtherChecked = setImageView(mLolOtherImageView, mLolOtherNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 오버워치 경기 목록 가져오기
        mApi.getMatchList(matchDay, OVERWATCH).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOverwatchChecked = setImageView(mOverwatchImageView, mOverwatchNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 스타 크래프트 경기 목록 가져오기
        mApi.getMatchList(matchDay, STARCRAFT).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mStarcraftChecked = setImageView(mStarcraftImageView, mStarcraftNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 기타 e스포츠 경기 목록 가져오기
        mApi.getMatchList(matchDay, OTHER_GAME).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherGameChecked = setImageView(mOtherGameImageView, mOtherGameNumber, response, call);

            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

    }
}
