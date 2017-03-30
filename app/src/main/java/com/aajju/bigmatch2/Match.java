package com.aajju.bigmatch2;

import java.io.Serializable;

/**
 * Created by aajju on 2017-03-04.
 */

public class Match implements Serializable {
    private String category, match_day, match_time, home_team, away_team, url, small_category, comments;
    private int counts;

    public Match(String category, String match_day, String match_time, String home_team, String away_team, String url, String small_category, String comments, int counts) {
        this.category = category;
        this.match_day = match_day;
        this.match_time = match_time;
        this.home_team = home_team;
        this.away_team = away_team;
        this.url = url;
        this.small_category = small_category;
        this.comments = comments;
        this.counts = counts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMatch_day() {
        return match_day;
    }

    public void setMatch_day(String match_day) {
        this.match_day = match_day;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmall_category() {
        return small_category;
    }

    public void setSmall_category(String small_category) {
        this.small_category = small_category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "Match{" +
                "category='" + category + '\'' +
                ", match_day='" + match_day + '\'' +
                ", match_time='" + match_time + '\'' +
                ", home_team='" + home_team + '\'' +
                ", away_team='" + away_team + '\'' +
                ", url='" + url + '\'' +
                ", small_category='" + small_category + '\'' +
                ", comments='" + comments + '\'' +
                ", counts=" + counts +
                '}';
    }
}
