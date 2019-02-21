package com.peopletech.organization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import me.yokeyword.indexablerv.IndexableLayout;

/**
 * @author hych
 * @date 2019/2/20 14:50
 */
public class OgzSearchFragment extends Fragment {

    private IndexableLayout mSearchContent;
    private RelativeLayout mSearchBlank;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ogz_fg_search, container, false);
        mSearchContent = (IndexableLayout) view.findViewById(R.id.ogz_fg_search_content);
        mSearchBlank = (RelativeLayout) view.findViewById(R.id.ogz_fg_search_blank_layout);
        return view;
    }

    public void showData(){
    }
}
