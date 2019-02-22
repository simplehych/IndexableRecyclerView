package com.peopletech.organization.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peopletech.organization.R;
import com.peopletech.organization.entity.InfoItemEntity;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:33
 */
public class MemberInfoAdapter extends IndexableAdapter<InfoItemEntity> {
    public static String[] order = {"A", "B", "C"};
    public static String[] group = {"", "党籍信息", "个人信息"};

    private LayoutInflater mInflater;
    private Context mContext;

    public MemberInfoAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.ogz_item_description, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.ogz_item_info, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;

        if (order[0].equals(indexTitle)) {
            vh.hide();
        } else {
            vh.show();
        }

        for (int i = 0; i < order.length; i++) {
            if (order[i].equals(indexTitle)) {
                vh.title.setText(group[i]);
                return;
            }
        }
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, InfoItemEntity entity) {
        ContentVH vh = (ContentVH) holder;
        vh.des.setText(entity.getDes());
        vh.content.setText(entity.getContent());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        ImageView flag;
        TextView title;

        public IndexVH(View itemView) {
            super(itemView);
            flag = (ImageView) itemView.findViewById(R.id.ogz_item_description_flag);
            title = (TextView) itemView.findViewById(R.id.ogz_item_description_title);
        }

        public void show() {
            flag.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        }

        public void hide() {
            flag.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView des;
        TextView content;
        View divide;

        public ContentVH(View itemView) {
            super(itemView);
            des = (TextView) itemView.findViewById(R.id.ogz_item_info_des);
            content = (TextView) itemView.findViewById(R.id.ogz_item_info_content);
            divide = itemView.findViewById(R.id.ogz_item_info_divide);
        }
    }
}
