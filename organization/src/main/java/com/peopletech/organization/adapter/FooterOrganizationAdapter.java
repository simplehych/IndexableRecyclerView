package com.peopletech.organization.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.OrganizationEntity;

import java.util.List;

import me.yokeyword.indexablerv.IndexableFooterAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:38
 */
public class FooterOrganizationAdapter extends IndexableFooterAdapter<OrganizationEntity> {
    private Activity mActivity;

    public FooterOrganizationAdapter(Activity activity, String index, String indexTitle, List<OrganizationEntity> datas) {
        super(index, indexTitle, datas);
        mActivity = activity;
    }

    @Override
    public int getItemViewType() {
        return IndexType.TYPE_FOOTER_OGZ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.ogz_item_menu, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, OrganizationEntity entity) {
        VH vh = (VH) viewHolder;
        vh.tv.setText(entity.getName());
        vh.topSpacing.setVisibility(View.GONE);
    }

    private class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;
        View bottomDivide;
        View topSpacing;

        public VH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ogz_item_menu_icon);
            tv = (TextView) itemView.findViewById(R.id.ogz_item_menu_des);
            topSpacing = itemView.findViewById(R.id.ogz_item_menu_top_spacing);
            bottomDivide = itemView.findViewById(R.id.ogz_item_menu_divide);
        }
    }
}
