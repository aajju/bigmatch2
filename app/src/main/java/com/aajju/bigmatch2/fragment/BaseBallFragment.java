package com.aajju.bigmatch2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import static com.aajju.bigmatch2.StaticAll.KBO;
import static com.aajju.bigmatch2.StaticAll.KOREAN_MAJOR;
import static com.aajju.bigmatch2.StaticAll.KOREA_BASEBALL;
import static com.aajju.bigmatch2.StaticAll.MLB;
import static com.aajju.bigmatch2.StaticAll.OTHER_BIG_MATCH_BASEBALL;
import static com.aajju.bigmatch2.StaticAll.mDate;
import static com.aajju.bigmatch2.StaticAll.mFormat;

/**
 * Created by aajju on 2017-01-26.
 */

public class BaseBallFragment extends Fragment {
    private Api mApi;

    private ImageView mKboImageView;
    private ImageView mKoreanMajorImageView;
    private ImageView mMlbImageView;
    private ImageView mKoreaTeamImageView;
    private ImageView mOtherBigMatchImageView;

    private boolean mKboChecked = false;
    private boolean mKoreanMajorChecked = false;
    private boolean mMlbChecked = false;
    private boolean mKoreaTeamChecked = false;
    private boolean mOtherBigMatchChecked = false;

    public static BaseBallFragment newInstance(String data){
        BaseBallFragment fragment = new BaseBallFragment();

        Bundle args = new Bundle();
        args.putString("data", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baseball, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mKboImageView = (ImageView) view.findViewById(R.id.baseball_kbo_iv);
        mKoreanMajorImageView = (ImageView) view.findViewById(R.id.baseball_korean_major_iv);
        mMlbImageView = (ImageView) view.findViewById(R.id.baseball_mlb_iv);
        mKoreaTeamImageView = (ImageView) view.findViewById(R.id.baseball_korea_team_iv);
        mOtherBigMatchImageView = (ImageView) view.findViewById(R.id.baseball_other_bigmatch_iv);

        final Intent intent = new Intent(getActivity(), MatchActivity.class);

        mApi = HttpHelper.getAPI();
        getMatchList(mFormat.format(mDate));


        // KBO 버튼 눌렀을때
        view.findViewById(R.id.baseball_kbo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mKboChecked){
                    intent.putExtra("league", KBO);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 해외파 하이라이트 버튼 눌렀을때
        view.findViewById(R.id.baseball_korean_major_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mKoreanMajorChecked){
                    intent.putExtra("league", KOREAN_MAJOR);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // MLB 버튼 눌렀을때
        view.findViewById(R.id.baseball_mlb_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMlbChecked){
                    intent.putExtra("league", MLB);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 한국 야구 국대 버튼 눌렀을때
        view.findViewById(R.id.baseball_korea_team_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mKoreaTeamChecked){
                    intent.putExtra("league", KOREA_BASEBALL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 기타 야구 빅매치 눌렀을때
        view.findViewById(R.id.baseball_other_bigmatch_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOtherBigMatchChecked){
                    intent.putExtra("league", OTHER_BIG_MATCH_BASEBALL);
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
        // kbo 목록 숫자 가져오기
        mApi.getMatchList(matchDay, KBO).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mKboImageView.setVisibility(View.GONE);
                    mKboChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mKboImageView.setVisibility(View.VISIBLE);
                mKboChecked = true;
                if(numOfList == 0){
                    return;
                } else if(numOfList == 1){
                    mKboImageView.setImageResource(R.drawable.number1);
                } else if(numOfList == 2){
                    mKboImageView.setImageResource(R.drawable.number2);
                }  else if(numOfList == 3){
                    mKboImageView.setImageResource(R.drawable.number3);
                } else if(numOfList == 4){
                    mKboImageView.setImageResource(R.drawable.number4);
                } else if(numOfList == 5){
                    mKboImageView.setImageResource(R.drawable.number5);
                } else if(numOfList == 6){
                    mKboImageView.setImageResource(R.drawable.number6);
                }

            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 해외파 경기 목록 가져오기
        mApi.getMatchList(matchDay, KOREAN_MAJOR).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mKoreanMajorImageView.setVisibility(View.GONE);
                    mKoreanMajorChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mKoreanMajorImageView.setVisibility(View.VISIBLE);
                mKoreanMajorChecked = true;
                if(numOfList == 0){
                    return;
                } else if(numOfList == 1){
                    mKoreanMajorImageView.setImageResource(R.drawable.number1);
                } else if(numOfList == 2){
                    mKoreanMajorImageView.setImageResource(R.drawable.number2);
                }  else if(numOfList == 3){
                    mKoreanMajorImageView.setImageResource(R.drawable.number3);
                } else if(numOfList == 4){
                    mKoreanMajorImageView.setImageResource(R.drawable.number4);
                } else if(numOfList == 5){
                    mKoreanMajorImageView.setImageResource(R.drawable.number5);
                } else if(numOfList == 6){
                    mKoreanMajorImageView.setImageResource(R.drawable.number6);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 메이저리그 경기 목록 가져오기
        mApi.getMatchList(matchDay, MLB).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mMlbImageView.setVisibility(View.GONE);
                    mMlbChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mMlbImageView.setVisibility(View.VISIBLE);
                mMlbChecked = true;
                if(numOfList == 0){
                    return;
                } else if(numOfList == 1){
                    mMlbImageView.setImageResource(R.drawable.number1);
                } else if(numOfList == 2){
                    mMlbImageView.setImageResource(R.drawable.number2);
                }  else if(numOfList == 3){
                    mMlbImageView.setImageResource(R.drawable.number3);
                } else if(numOfList == 4){
                    mMlbImageView.setImageResource(R.drawable.number4);
                } else if(numOfList == 5){
                    mMlbImageView.setImageResource(R.drawable.number5);
                } else if(numOfList == 6){
                    mMlbImageView.setImageResource(R.drawable.number6);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 한국 국대 경기 목록 가져오기
        mApi.getMatchList(matchDay, KOREA_BASEBALL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mKoreaTeamImageView.setVisibility(View.GONE);
                    mKoreaTeamChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mKoreaTeamImageView.setVisibility(View.VISIBLE);
                mKoreaTeamChecked = true;
                if(numOfList == 0){
                    return;
                } else if(numOfList == 1){
                    mKoreaTeamImageView.setImageResource(R.drawable.number1);
                } else if(numOfList == 2){
                    mKoreaTeamImageView.setImageResource(R.drawable.number2);
                }  else if(numOfList == 3){
                    mKoreaTeamImageView.setImageResource(R.drawable.number3);
                } else if(numOfList == 4){
                    mKoreaTeamImageView.setImageResource(R.drawable.number4);
                } else if(numOfList == 5){
                    mKoreaTeamImageView.setImageResource(R.drawable.number5);
                } else if(numOfList == 6){
                    mKoreaTeamImageView.setImageResource(R.drawable.number6);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 기타 경기 목록 가져오기
        mApi.getMatchList(matchDay, OTHER_BIG_MATCH_BASEBALL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherBigMatchImageView.setVisibility(View.GONE);
                    mOtherBigMatchChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherBigMatchImageView.setVisibility(View.VISIBLE);
                mOtherBigMatchChecked = true;
                if(numOfList == 0){
                    return;
                } else if(numOfList == 1){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number1);
                } else if(numOfList == 2){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number2);
                }  else if(numOfList == 3){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number3);
                } else if(numOfList == 4){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number4);
                } else if(numOfList == 5){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number5);
                } else if(numOfList == 6){
                    mOtherBigMatchImageView.setImageResource(R.drawable.number6);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

    }

}
