package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.MemberInfoEntity;
import com.peopletech.organization.entity.StringEntity;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:38
 */
public class HeaderMemberInfoAdapter extends IndexableHeaderAdapter<MemberInfoEntity> {
    private Activity mActivity;

    public HeaderMemberInfoAdapter(Activity activity, String index, String indexTitle, List<MemberInfoEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return IndexType.TYPE_HEADER_MEMBER_INFO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.ogz_item_info_top, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, MemberInfoEntity entity) {
        VH vh = (VH) holder;
        vh.name.setText(entity.getUserName());
        vh.gender.setText(entity.getSex());
    }

    private class VH extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView gender;

        public VH(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.ogz_item_info_top_avatar);
            name = (TextView) itemView.findViewById(R.id.ogz_item_info_top_name);
            gender = (TextView) itemView.findViewById(R.id.ogz_item_info_top_gender);
        }
    }
}
