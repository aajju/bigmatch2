package com.aajju.bigmatch2.retrofit;

import com.aajju.bigmatch2.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aajju on 2017-03-12.
 */

public interface Api {

    @GET("match/{match_day}/{category}")
    Call<List<Match>> getMatchList(@Path("match_day") String match_day, @Path("category") int category);

    @GET("match/{id}/{counts}")
    Call<List<Match>> throwCounts(@Path("id") String id, @Path("counts") int counts);
}
