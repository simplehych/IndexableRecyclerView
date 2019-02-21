package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.entity.MemberEntity;
import com.peopletech.organization.R;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:37
 */
public class AttendantMemberAdapter extends IndexableHeaderAdapter<MemberEntity> {
    private static final int TYPE = 1;
    private Activity mActivity;


    public AttendantMemberAdapter(Activity activity, String index, String indexTitle, List<MemberEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(mActivity).inflate(R.layout.ogz_item_member, parent, false));
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, MemberEntity entity) {
        VH vh = (VH) holder;
        vh.tv.setText(entity.getUserName());
    }

    private class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.ogz_item_description_title);
            img = (ImageView) itemView.findViewById(R.id.ogz_item_description_flag);
        }
    }
}
