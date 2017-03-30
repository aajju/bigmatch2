package com.aajju.bigmatch2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class OtherSportsFragment extends Fragment {
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
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherBasketBallImageView.setVisibility(View.GONE);
                    mOtherBasketBallChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherBasketBallImageView.setVisibility(View.VISIBLE);
                mOtherBasketBallChecked = true;
                if (numOfList == 0) {
                    return;
                } else if (numOfList == 1) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number1);
                } else if (numOfList == 2) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number2);
                } else if (numOfList == 3) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number3);
                } else if (numOfList == 4) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number4);
                } else if (numOfList == 5) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number5);
                } else if (numOfList == 6) {
                    mOtherBasketBallImageView.setImageResource(R.drawable.number6);
                }


            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 배구 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_VALLEYBALL).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherValleyBallImageView.setVisibility(View.GONE);
                    mOtherValleyBallChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherValleyBallImageView.setVisibility(View.VISIBLE);
                mOtherValleyBallChecked = true;
                if (numOfList == 0) {
                    return;
                } else if (numOfList == 1) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number1);
                } else if (numOfList == 2) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number2);
                } else if (numOfList == 3) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number3);
                } else if (numOfList == 4) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number4);
                } else if (numOfList == 5) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number5);
                } else if (numOfList == 6) {
                    mOtherValleyBallImageView.setImageResource(R.drawable.number6);
                }

            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // ufc 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_UFC).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherUfcImageView.setVisibility(View.GONE);
                    mOtherUfcChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherUfcImageView.setVisibility(View.VISIBLE);
                mOtherUfcChecked = true;
                if (numOfList == 0) {
                    return;
                } else if (numOfList == 1) {
                    mOtherUfcImageView.setImageResource(R.drawable.number1);
                } else if (numOfList == 2) {
                    mOtherUfcImageView.setImageResource(R.drawable.number2);
                } else if (numOfList == 3) {
                    mOtherUfcImageView.setImageResource(R.drawable.number3);
                } else if (numOfList == 4) {
                    mOtherUfcImageView.setImageResource(R.drawable.number4);
                } else if (numOfList == 5) {
                    mOtherUfcImageView.setImageResource(R.drawable.number5);
                } else if (numOfList == 6) {
                    mOtherUfcImageView.setImageResource(R.drawable.number6);
                }

            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });


        // 올림픽, 아시안게임 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_KOREA_TEAM).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherKoreaTeamImageView.setVisibility(View.GONE);
                    mOtherKoreaTeamChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherKoreaTeamImageView.setVisibility(View.VISIBLE);
                mOtherKoreaTeamChecked = true;
                if (numOfList == 0) {
                    return;
                } else if (numOfList == 1) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number1);
                } else if (numOfList == 2) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number2);
                } else if (numOfList == 3) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number3);
                } else if (numOfList == 4) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number4);
                } else if (numOfList == 5) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number5);
                } else if (numOfList == 6) {
                    mOtherKoreaTeamImageView.setImageResource(R.drawable.number6);
                }

            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });

        // 기타 스포츠 빅매치 목록 숫자 가져오기
        mApi.getMatchList(matchDay, OTHER_SPORTS).enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
                    mOtherSportsIamgeView.setVisibility(View.GONE);
                    mOtherSportsChecked = false;
                    return;
                }
                int numOfList = response.body().size();
                mOtherSportsIamgeView.setVisibility(View.VISIBLE);
                mOtherSportsChecked = true;
                if (numOfList == 0) {
                    return;
                } else if (numOfList == 1) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number1);
                } else if (numOfList == 2) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number2);
                } else if (numOfList == 3) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number3);
                } else if (numOfList == 4) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number4);
                } else if (numOfList == 5) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number5);
                } else if (numOfList == 6) {
                    mOtherSportsIamgeView.setImageResource(R.drawable.number6);
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
            }
        });

    }

    public boolean setImageView(ImageView imageView, TextView textView, boolean isChecked, Response<List<Match>> response, Call<List<Match>> data) {
        if (!response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this, "가져올 메모 리스트가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
            imageView.setVisibility(View.GONE);
            return false;
        }
        int numOfList = response.body().size();
        imageView.setVisibility(View.VISIBLE);
        textView.setText("" + numOfList);
        return true;
    }


}
