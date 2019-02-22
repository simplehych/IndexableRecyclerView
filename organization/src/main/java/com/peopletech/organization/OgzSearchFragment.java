package com.peopletech.organization;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peopletech.organization.adapter.FooterDesAdapter;
import com.peopletech.organization.adapter.HeaderDesAdapter;
import com.peopletech.organization.adapter.FooterOrganizationAdapter;
import com.peopletech.organization.adapter.MemberAdapter;
import com.peopletech.organization.entity.MemberEntity;
import com.peopletech.organization.entity.OrganizationEntity;
import com.peopletech.organization.entity.StringEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableFooterAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * 搜索结果显示 Fg
 *
 * @author hych
 * @date 2019/2/20 14:50
 */
public class OgzSearchFragment extends Fragment {

    private Activity mActivity;
    private IndexableLayout mSearchResultLayout;
    private HeaderDesAdapter mMemberDesAdapter;
    private MemberAdapter mMemberAdapter;
    private FooterDesAdapter mOrganizationDesAdapter;
    private FooterOrganizationAdapter mOrganizationAdapter;

    private List<OrganizationEntity> mOrganizationList;
    private List<MemberEntity> mMemberList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.ogz_fg_search, container, false);
        mActivity = getActivity();
        findView(parent);
        initView();
        return parent;
    }

    private void findView(View parent) {
        mSearchResultLayout = (IndexableLayout) parent.findViewById(R.id.ogz_fg_search_content);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mSearchResultLayout.setLayoutManager(new LinearLayoutManager(mActivity));
        mSearchResultLayout.setIndexBarVisibility(false);
        mSearchResultLayout.setCompareMode(IndexableLayout.MODE_NONE);

        initMemberAdapter();
        initMemberDesAdapter();
        initOrganizationDesAdapter();
        initOrganizationAdapter();

        mSearchResultLayout.setAdapter(mMemberAdapter);
        mSearchResultLayout.addHeaderAdapter(mMemberDesAdapter);
        mSearchResultLayout.addFooterAdapter(mOrganizationDesAdapter);
        mSearchResultLayout.addFooterAdapter(mOrganizationAdapter);

        bindData(null, null);
    }

    /**
     * 初始化成员视图
     */
    private void initMemberAdapter() {
        mMemberList = new ArrayList<>();
        mMemberAdapter = new MemberAdapter(mActivity);
        mMemberAdapter.hideIndexItem();
        mMemberAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<MemberEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, MemberEntity entity) {
                if (originalPosition >= 0) {
                    ToastUtil.showShort(mActivity, "选中:" + entity.getUserName() + "  当前位置:" + currentPosition + "  原始所在数组位置:" + originalPosition);
                } else {
                    ToastUtil.showShort(mActivity, "选中Header/Footer:" + entity.getUserName() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    /**
     * 初始化党员描述视图
     */
    private void initMemberDesAdapter() {
        List<StringEntity> mMemberDes = new ArrayList<>();
        StringEntity member = new StringEntity("党员");
        mMemberDes.add(member);
        mMemberDesAdapter = new HeaderDesAdapter(mActivity, null, null, mMemberDes);
    }

    /**
     * 初始化组织描述视图
     */
    private void initOrganizationDesAdapter() {
        List<StringEntity> mOrganizationDes = new ArrayList<>();
        StringEntity organization = new StringEntity("组织");
        mOrganizationDes.add(organization);
        mOrganizationDesAdapter = new FooterDesAdapter(mActivity, null, null, mOrganizationDes);
    }

    /**
     * 初始化组织视图
     */
    private void initOrganizationAdapter() {
        mOrganizationList = new ArrayList<>();
        mOrganizationAdapter = new FooterOrganizationAdapter(mActivity, null, null, mOrganizationList);
        mOrganizationAdapter.setOnItemFooterClickListener(new IndexableFooterAdapter.OnItemFooterClickListener<OrganizationEntity>() {
            @Override
            public void onItemClick(View view, int i, OrganizationEntity entity) {
                ToastUtil.showShort(mActivity, "选中:" + entity.getName());
            }
        });
    }

    /**
     * 绑定数据
     *
     * @param members       党员数据
     * @param organizations 组织数据
     */
    public void bindData(List<MemberEntity> members, final List<OrganizationEntity> organizations) {
        updateMemberView(members);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateOrganizationView(organizations);
            }
        }, 100);
    }

    /**
     * 更新党员视图
     *
     * @param members 党员数据
     */
    private void updateMemberView(List<MemberEntity> members) {

        if (members == null) {
            mMemberList.clear();
            mSearchResultLayout.removeHeaderAdapter(mMemberDesAdapter);
        } else {
            if (mMemberList.isEmpty()) {
                mSearchResultLayout.addHeaderAdapter(mMemberDesAdapter);
            }
            mMemberList = members;
        }

        mMemberAdapter.setDatas(mMemberList);
        mMemberAdapter.notifyDataSetChanged();
    }

    /**
     * 更新组织视图
     *
     * @param organizations 组织数据
     */
    private void updateOrganizationView(List<OrganizationEntity> organizations) {
        // 删除之前数据
        Iterator<OrganizationEntity> iterator = mOrganizationList.iterator();
        while (iterator.hasNext()) {
            OrganizationEntity organization = iterator.next();
            mOrganizationAdapter.removeData(organization);
            iterator.remove();
        }
        mOrganizationList.clear();
        // 取消原先视图
        mSearchResultLayout.removeFooterAdapter(mOrganizationDesAdapter);
        mSearchResultLayout.removeFooterAdapter(mOrganizationAdapter);

        if (organizations == null) {
            return;
        }

        // 添加新数据
        mOrganizationList = organizations;
        mOrganizationAdapter.addDatas(mOrganizationList);
        // 添加新视图
        mSearchResultLayout.addFooterAdapter(mOrganizationDesAdapter);
        mSearchResultLayout.addFooterAdapter(mOrganizationAdapter);
        mOrganizationAdapter.notifyDataSetChanged();
    }

}
