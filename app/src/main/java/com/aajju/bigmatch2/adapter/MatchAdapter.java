package com.aajju.bigmatch2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aajju.bigmatch2.Match;
import com.aajju.bigmatch2.R;

import java.util.List;

/**
 * Created by aajju on 2017-03-07.
 */

public class MatchAdapter extends BaseAdapter {
    private List<Match> mData;

    public MatchAdapter(List<Match> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_first, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Match item = mData.get(i);
        holder.mTimeTextView.setText(item.getMatch_time());
        holder.mTeamTextView.setText(item.getHome_team() + "  vs  " + item.getAway_team());
        holder.mSmallCategoryTextView.setText(item.getSmall_category());
        holder.mCommentsTextView.setText(item.getComments());
        return view;
    }

    public void swap(List<Match> matches) {
        mData = matches;
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView mTimeTextView, mTeamTextView, mSmallCategoryTextView, mCommentsTextView;
        public ViewHolder(View view){
            mTimeTextView = (TextView) view.findViewById(R.id.item_time);
            mTeamTextView = (TextView) view.findViewById(R.id.item_team);
            mSmallCategoryTextView = (TextView) view.findViewById(R.id.item_small_category_tv);
            mCommentsTextView = (TextView) view.findViewById(R.id.item_comments_tv);
        }
    }
}
