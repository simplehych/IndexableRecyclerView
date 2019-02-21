package com.peopletech.organization.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.MemberEntity;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:33
 */
public class MemberAdapter extends IndexableAdapter<MemberEntity> {
    private LayoutInflater mInflater;
    private Context mContext;
    private boolean hideIndex = false;

    public MemberAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void hideIndexItem() {
        hideIndex = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view;
        if (hideIndex) {
            view = mInflater.inflate(R.layout.ogz_item_index_no, parent, false);
        } else {
            view = mInflater.inflate(R.layout.ogz_item_index, parent, false);
        }
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.ogz_item_member, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        if (!hideIndex) {
            IndexVH vh = (IndexVH) holder;
            vh.tv.setText(indexTitle);
        }
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, MemberEntity entity) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(entity.getUserName());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.ogz_item_index_des);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img;

        public ContentVH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ogz_item_description_flag);
            tvName = (TextView) itemView.findViewById(R.id.ogz_item_description_title);
        }
    }
}
