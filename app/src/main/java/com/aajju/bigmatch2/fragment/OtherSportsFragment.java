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
import static com.aajju.bigmatch2.StaticAll.OTHER_BASKETBALL;
import static com.aajju.bigmatch2.StaticAll.OTHER_KOREA_TEAM;
import static com.aajju.bigmatch2.StaticAll.OTHER_SPORTS;
import static com.aajju.bigmatch2.StaticAll.OTHER_UFC;
import static com.aajju.bigmatch2.StaticAll.OTHER_VALLEYBALL;
import static com.aajju.bigmatch2.StaticAll.mDate;
import static com.aajju.bigmatch2.StaticAll.mFormat;

/**
 * Created by aajju on 2017-01-26.
 */

public class OtherSportsFragment extends MainFragment {
    private Api mApi;

    private ImageView mOtherBasketBallImageView;
    private ImageView mOtherValleyBallImageView;
    private ImageView mOtherUfcImageView;
    private ImageView mOtherKoreaTeamImageView;
    private ImageView mOtherSportsIamgeView;

    private boolean mOtherBasketBallChecked = false;
    private boolean mOtherValleyBallChecked = false;
    private boolean mOtherUfcChecked = false;
    private boolean mOtherKoreaTeamChecked = false;
    private boolean mOtherSportsChecked = false;

    private TextView mOtherBasketBallNumber;
    private TextView mOtherValleyBallNumber;
    private TextView mOtherUfcNumber;
    private TextView mOtherKoreaTeamNumber;
    private TextView mOtherSportsNumber;


    public static OtherSportsFragment newInstance(String data) {
        OtherSportsFragment fragment = new OtherSportsFragment();

        Bundle args = new Bundle();
        args.putString("data", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other_sports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mTextView = (TextView) view.findViewById(R.id.fragment_tv);
        final Intent intent = new Intent(getActivity(), MatchActivity.class);

        mOtherBasketBallImageView = (ImageView) view.findViewById(R.id.other_basketball_iv);
        mOtherValleyBallImageView = (ImageView) view.findViewById(R.id.other_valleyball_iv);
        mOtherUfcImageView = (ImageView) view.findViewById(R.id.other_ufc_iv);
        mOtherKoreaTeamImageView = (ImageView) view.findViewById(R.id.other_korea_team_iv);
        mOtherSportsIamgeView = (ImageView) view.findViewById(R.id.other_sports_iv);

        mOtherBasketBallNumber = (TextView) view.findViewById(R.id.other_basketball_text);
        mOtherValleyBallNumber = (TextView) view.findViewById(R.id.other_valleyball_text);
        mOtherUfcNumber = (TextView) view.findViewById(R.id.other_ufc_text);
        mOtherKoreaTeamNumber = (TextView) view.findViewById(R.id.other_korea_team_text);
        mOtherSportsNumber = (TextView) view.findViewById(R.id.other_sports_text);

        mApi = HttpHelper.getAPI();
        getMatchList(mFormat.format(mDate));

        /**
         * 리스트 클릭하기
         */
        // 농구 빅매치 클릭했을 때
        view.findViewById(R.id.other_basketball_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtherBasketBallChecked) {
                    intent.putExtra("league", OTHER_BASKETBALL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 배구 빅매치 클릭했을 때
        view.findViewById(R.id.other_valleyball_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtherValleyBallChecked) {
                    intent.putExtra("league", OTHER_VALLEYBALL);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // UFC 빅매치 클릭했을 때
        view.findViewById(R.id.other_ufc_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtherUfcChecked) {
                    intent.putExtra("league", OTHER_UFC);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 올림픽, 아시안게임 빅매치 클릭했을 때
        view.findViewById(R.id.other_korea_team_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtherKoreaTeamChecked) {
                    intent.putExtra("league", OTHER_KOREA_TEAM);
                    intent.putExtra("date", mDate);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), EMPTY_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 기타 스포츠 빅매치 클릭했을 때
        view.findViewById(R.id.other_sports_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOtherSportsChecked) {
                    intent.putExtra("league", OTHER_SPORTS);
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
        // 농구 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_BASKETBALL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherBasketBallChecked = setImageView(mOtherBasketBallImageView, mOtherBasketBallNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 배구 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_VALLEYBALL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherValleyBallChecked = setImageView(mOtherValleyBallImageView, mOtherValleyBallNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // ufc 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_UFC).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherUfcChecked = setImageView(mOtherUfcImageView, mOtherUfcNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 올림픽, 아시안게임 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_KOREA_TEAM).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherKoreaTeamChecked = setImageView(mOtherKoreaTeamImageView, mOtherKoreaTeamNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 기타 스포츠 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_SPORTS).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                mOtherSportsChecked = setImageView(mOtherSportsIamgeView, mOtherSportsNumber, response, call);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
            }
        });
    }

}
