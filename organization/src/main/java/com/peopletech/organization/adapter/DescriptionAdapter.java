package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.StringEntity;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:38
 */
public class DescriptionAdapter extends IndexableHeaderAdapter<StringEntity> {
    private static final int TYPE = 3;
    private Activity mActivity;

    public DescriptionAdapter(Activity activity, String index, String indexTitle, List<StringEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.ogz_item_description, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, StringEntity entity) {
        VH vh = (VH) holder;
        vh.tv.setText(entity.getStr());
    }

    private class VH extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;

        public VH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ogz_item_description_flag);
            tv = (TextView) itemView.findViewById(R.id.ogz_item_description_title);
        }
    }
}
