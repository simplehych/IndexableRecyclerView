package com.peopletech.organization;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.peopletech.organization.adapter.HeaderMemberInfoAdapter;
import com.peopletech.organization.adapter.MemberInfoAdapter;
import com.peopletech.organization.entity.InfoItemEntity;
import com.peopletech.organization.entity.MemberInfoEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableLayout;

import static com.peopletech.organization.adapter.MemberInfoAdapter.order;

/**
 * @author hych
 * @date 2019/2/22 12:43
 */
public class MemberInfoActivity extends AppCompatActivity {

    private IndexableLayout mIndexableLayout;
    private List<InfoItemEntity> mInfoItemList;
    private MemberInfoAdapter mMemberInfoAdapter;
    private HeaderMemberInfoAdapter mInfoTopAdapter;
    private List<MemberInfoEntity> memberInfoEntities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ogz_act_member_info);
        findView();
        initView();
    }

    private void findView() {
        mIndexableLayout = (IndexableLayout) findViewById(R.id.ogz_act_member_info_indexable_layout);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberInfoEntity memberInfoEntity = new MemberInfoEntity(11, "username", "avatar", "sex", "ethicitname", "birthday", "edu", 130, 11, "orgName", "status", "partyTyep", "toDate", "patyday", "idcart", "address", "postNAme");
                updateInfo(memberInfoEntity);
            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mIndexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mIndexableLayout.setIndexBarVisibility(false);
        mIndexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        mIndexableLayout.setStickyEnable(false);

        initMemberInfoAdapter();
        initTopAdapter();

        mIndexableLayout.setAdapter(mMemberInfoAdapter);
        mIndexableLayout.addHeaderAdapter(mInfoTopAdapter);

        initData();
    }

    /**
     * 初始化成员视图
     */
    private void initMemberInfoAdapter() {
        mInfoItemList = new ArrayList<>();
        mMemberInfoAdapter = new MemberInfoAdapter(this);
    }


    private void initTopAdapter() {
        memberInfoEntities = new ArrayList<>();
        MemberInfoEntity memberInfoEntity = new MemberInfoEntity();
        memberInfoEntities.add(memberInfoEntity);
        mInfoTopAdapter = new HeaderMemberInfoAdapter(this, null, null, memberInfoEntities);
    }

    private void initData() {
        mInfoItemList = new ArrayList<>();
        mMemberInfoAdapter.setDatas(mInfoItemList);
        updateInfo(null);
    }

    private void updateInfo(MemberInfoEntity newInfo) {
        updateTopInfoData(newInfo);
        updateListData(newInfo);
    }

    private void updateTopInfoData(MemberInfoEntity newInfo) {
        if (newInfo == null) {
            return;
        }
        MemberInfoEntity memberInfoEntity = memberInfoEntities.get(0);
        memberInfoEntity.setUserName(newInfo.getUserName());
        memberInfoEntity.setAvatar(newInfo.getAvatar());
        memberInfoEntity.setSex(newInfo.getSex());
        mMemberInfoAdapter.notifyDataSetChanged();
    }

    private void updateListData(MemberInfoEntity newInfo) {

        List<String> desBaseList = Arrays.asList(getResources().getStringArray(R.array.member_info_des_base));
        List<String> desPartyList = Arrays.asList(getResources().getStringArray(R.array.member_info_des_party));
        List<String> desPriList = Arrays.asList(getResources().getStringArray(R.array.member_info_des_pri));

        if (newInfo == null) {
            setInfoItemList(desBaseList, desPartyList, desPriList, null, null, null);
            return;
        }

        List<String> contentBaseList = new ArrayList<>();
        contentBaseList.add(String.valueOf(newInfo.getPhone()));
        contentBaseList.add(newInfo.getOrgName());

        List<String> contentPartyList = new ArrayList<>();

        contentPartyList.add(newInfo.getPartyType());
        contentPartyList.add(newInfo.getPartyday());
        contentPartyList.add(newInfo.getToNormalDate());
        contentPartyList.add(newInfo.getPartyStatus());

        List<String> contentPriList = new ArrayList<>();
        contentPriList.add(newInfo.getEthnicityName());
        contentPriList.add(newInfo.getEducationName());
        contentPriList.add(newInfo.getBirthday());
        contentPriList.add(newInfo.getIdCard());
        contentPriList.add(newInfo.getPostName());
        contentPriList.add(newInfo.getAddress());

        setInfoItemList(desBaseList, desPartyList, desPriList, contentBaseList, contentPartyList, contentPriList);
    }

    private void setInfoItemList(List<String> desBaseList, List<String> desPartyList, List<String> desPriList,
                                 List<String> contentBaseList, List<String> contentPartyList, List<String> contentPriList) {
        mInfoItemList.clear();

        mInfoItemList.addAll(convertData(order[0], desBaseList, contentBaseList));
        mInfoItemList.addAll(convertData(order[1], desPartyList, contentPartyList));
        mInfoItemList.addAll(convertData(order[2], desPriList, contentPriList));

        mMemberInfoAdapter.setDatas(mInfoItemList);
    }

    private List<InfoItemEntity> convertData(String index, List<String> desList, List<String> contentList) {
        List<InfoItemEntity> infoItemList = new ArrayList<>();
        for (int i = 0; i < desList.size(); i++) {
            String content = "";
            if (contentList != null && contentList.size() > i) {
                content = contentList.get(i);
            }
            InfoItemEntity item = new InfoItemEntity(index, desList.get(i), content);
            infoItemList.add(item);
        }
        return infoItemList;
    }
}
