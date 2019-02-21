package com.peopletech.organization.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.UserEntity;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:33
 */
public class MemberAdapter extends IndexableAdapter<UserEntity> {
    private LayoutInflater mInflater;

    public MemberAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_index_contact, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;
        vh.tv.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, UserEntity entity) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(entity.getNick());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img;

        public ContentVH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvName = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
