package com.aajju.bigmatch2.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aajju.bigmatch2.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aajju on 2017-03-31.
 */

public class ParentFragment extends Fragment {
    public boolean setImageView(ImageView imageView, TextView textView, Response<List<Match>> response, Call<List<Match>> data) {
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
