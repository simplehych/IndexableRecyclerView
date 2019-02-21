package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.StringEntity;

import java.util.List;

import me.yokeyword.indexablerv.IndexableFooterAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:38
 */
public class FootAdapter extends IndexableFooterAdapter<StringEntity> {
    private static final int TYPE = 4;
    private Activity mActivity;

    public FootAdapter(Activity activity, String index, String indexTitle, List<StringEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_foot, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, StringEntity count) {
        VH vh = (VH) viewHolder;
        String s = String.format("共%1$s人", count.getStr());
        vh.tv.setText(s);
    }

    private class VH extends RecyclerView.ViewHolder {
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
