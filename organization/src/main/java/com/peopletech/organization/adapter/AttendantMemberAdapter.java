package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.entity.MenuEntity;
import com.peopletech.organization.R;
import com.peopletech.organization.entity.UserEntity;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:37
 */
public class AttendantMemberAdapter extends IndexableHeaderAdapter<UserEntity> {
    private static final int TYPE = 1;
    private Activity mActivity;


    public AttendantMemberAdapter(Activity activity, String index, String indexTitle, List<UserEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(mActivity).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, UserEntity entity) {
        VH vh = (VH) holder;
        vh.tv.setText(entity.getNick());
    }

    private class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
