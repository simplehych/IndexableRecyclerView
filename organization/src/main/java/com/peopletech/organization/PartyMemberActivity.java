package com.peopletech.organization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import com.peopletech.organization.adapter.AttendantMemberAdapter;
import com.peopletech.organization.adapter.HeaderMenuAdapter;
import com.peopletech.organization.adapter.DescriptionAdapter;
import com.peopletech.organization.adapter.FootAdapter;
import com.peopletech.organization.adapter.MemberAdapter;
import com.peopletech.organization.entity.MemberEntity;
import com.peopletech.organization.entity.StringEntity;
import com.peopletech.organization.entity.HeaderMenuEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * @author hych
 * @date 2019/2/19 14:17
 */
public class PartyMemberActivity extends AppCompatActivity {

    private static String TAG = "PartyMemberActivity";

    private MemberAdapter mMemberAdapter;
    private HeaderMenuAdapter mHeaderMenuAdapter;
    private DescriptionAdapter mAttendantDesAdapter;
    private FootAdapter mFooterDesAdapter;
    private List<StringEntity> mFooterCount;
    private AttendantMemberAdapter mAttendantMemberAdapter;
    private List<MemberEntity> mAttendantMemberList;
    private ArrayList<StringEntity> mAttendantMemberDes;
    private IndexableLayout mIndexableLayout;


    /**
     * 初始化拼音
     * 处理多音字
     */
    public void initPinyin() {
        Pinyin.init(Pinyin.newConfig()
                .with(new PinyinMapDict() {
                    @Override
                    public Map<String, String[]> mapping() {
                        HashMap<String, String[]> map = new HashMap<String, String[]>();
                        map.put("重庆", new String[]{"CHONG", "QING"});
                        return map;
                    }
                }));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPinyin();

        setContentView(R.layout.ogz_act_party_member);
        mIndexableLayout = (IndexableLayout) findViewById(R.id.ogz_act_party_member_indexable_layout);

        initMemberAdapter();
        initHeaderMenuAdapter();
        initAttendantDesAdapter();
        initAttendantMemberAdapter();
        initFooterDesAdapter();

        mIndexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mIndexableLayout.setAdapter(mMemberAdapter);
        mIndexableLayout.setIndexBarVisibility(false);
        mIndexableLayout.setStickyEnable(true);
        mIndexableLayout.showAllLetter(false);
        mIndexableLayout.setCompareMode(IndexableLayout.MODE_FAST);

        addHeaderView(true, true);
        addFootView();

        final View searchIcon = findViewById(R.id.ogz_search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(PartyMemberActivity.this, searchIcon, searchIcon.getTransitionName());
                    startActivity(new Intent(PartyMemberActivity.this, OgzSearchActivity.class), optionsCompat.toBundle());
                } else {
                    startActivity(new Intent(PartyMemberActivity.this, OgzSearchActivity.class));
                }
            }
        });

        mMemberAdapter.setDatas(initDatas());
    }

    /**
     * 增加顶部视图
     */
    private void addHeaderView(boolean menu, boolean attendant) {
        if (attendant) {
            mIndexableLayout.addHeaderAdapter(mAttendantMemberAdapter);
            mIndexableLayout.addHeaderAdapter(mAttendantDesAdapter);
        }
        if (menu) {
            mIndexableLayout.addHeaderAdapter(mHeaderMenuAdapter);
        }
    }

    /**
     * 移除顶部视图
     */
    private void removeHeaderView() {
        mIndexableLayout.removeHeaderAdapter(mAttendantMemberAdapter);
        mIndexableLayout.removeHeaderAdapter(mAttendantDesAdapter);
        mIndexableLayout.removeHeaderAdapter(mHeaderMenuAdapter);
    }

    /**
     * 增加底部视图
     */
    private void addFootView() {
        mIndexableLayout.addFooterAdapter(mFooterDesAdapter);
    }

    /**
     * 初始化所有成员
     */
    private void initMemberAdapter() {
        mMemberAdapter = new MemberAdapter(this);
        mMemberAdapter.hideIndexItem();
        mMemberAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<MemberEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, MemberEntity entity) {
                if (originalPosition >= 0) {
                    ToastUtil.showShort(PartyMemberActivity.this, "选中:" + entity.getUserName() + "  当前位置:" + currentPosition + "  原始所在数组位置:" + originalPosition);
                } else {
                    ToastUtil.showShort(PartyMemberActivity.this, "选中Header/Footer:" + entity.getUserName() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    /**
     * 初始化顶部菜单条目
     */
    private void initHeaderMenuAdapter() {
        List<HeaderMenuEntity> menuList = new ArrayList<>();
        List<String> menuDesList = Arrays.asList(getResources()
                .getStringArray(R.array.organization_header));
        int[] menuIcons = {R.drawable.ogz_ic_organization,
                R.drawable.ogz_ic_wx_invit};

        for (int i = 0; i < menuDesList.size(); i++) {
            HeaderMenuEntity entity = new HeaderMenuEntity(i, menuDesList.get(i), menuIcons[i]);

            // 判断第一个
            if (i == 0) {
                entity.setFirst(true);
            }

            // 判断最后一个
            if (i == menuDesList.size() - 1) {
                entity.setLast(true);
            }

            menuList.add(entity);
        }

        mHeaderMenuAdapter = new HeaderMenuAdapter(this, null, null, menuList);
        mHeaderMenuAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener<HeaderMenuEntity>() {
            @Override
            public void onItemClick(View view, int i, HeaderMenuEntity menuEntity) {
                if (i == 0) {

                    List<MemberEntity> list1 = new ArrayList<>();
                    for (int i1 = 0; i1 < 3; i1++) {
                        MemberEntity ww = new MemberEntity(i1, "王五" + i1);
                        list1.add(ww);
                    }
                    updateAttendantMemberView(list1);

                } else {
                    updateAttendantMemberView(null);
                }
            }
        });
    }

    /**
     * 初始化管理员描述条目
     */
    private void initAttendantDesAdapter() {
        mAttendantMemberDes = new ArrayList<>();
        StringEntity ogz = new StringEntity("组织管理");
        mAttendantMemberDes.add(ogz);
        mAttendantDesAdapter = new DescriptionAdapter(this, null, null, mAttendantMemberDes);
    }

    /**
     * 初始化管理员成员
     */
    private void initAttendantMemberAdapter() {
        mAttendantMemberList = new ArrayList<>();
        mAttendantMemberList.add(new MemberEntity(0, "管理员"));
        mAttendantMemberAdapter = new AttendantMemberAdapter(this, null, null, mAttendantMemberList);
        mAttendantMemberAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener<MemberEntity>() {
            @Override
            public void onItemClick(View view, int i, MemberEntity memberEntity) {
                ToastUtil.showShort(PartyMemberActivity.this, memberEntity.getUserName());
            }
        });
    }

    /**
     * 初始化底部描述显示条目
     */
    private void initFooterDesAdapter() {
        mFooterCount = new ArrayList<>();
        mFooterCount.add(new StringEntity("0"));
        mFooterDesAdapter = new FootAdapter(PartyMemberActivity.this, null, null, mFooterCount);
    }

    /**
     * 更新组织管理成员视图
     *
     * @param newAttendantMemberList
     */
    private void updateAttendantMemberView(List<MemberEntity> newAttendantMemberList) {
        removeHeaderView();
        if (newAttendantMemberList == null) {
            addHeaderView(true, false);
        } else {
            updateAttendantMemberData(newAttendantMemberList);
            addHeaderView(true, true);
        }
    }

    /**
     * 更新组织管理成员数据
     *
     * @param newAttendantMemberList
     */
    private void updateAttendantMemberData(List<MemberEntity> newAttendantMemberList) {
        Iterator<MemberEntity> iterator = mAttendantMemberList.iterator();
        while (iterator.hasNext()) {
            MemberEntity memberEntity = iterator.next();
            mAttendantMemberAdapter.removeData(memberEntity);
        }
        mAttendantMemberList = newAttendantMemberList;
        mAttendantMemberAdapter.addDatas(mAttendantMemberList);
        mAttendantMemberAdapter.notifyDataSetChanged();
    }

    /**
     * 更新底部显示个数
     *
     * @param count
     */
    private void updateFooterCount(String count) {
        mFooterCount.get(0).setStr(count);
        mFooterDesAdapter.notifyDataSetChanged();
    }

    private List<MemberEntity> initDatas() {
        List<MemberEntity> list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(getResources().getStringArray(R.array.contact_array));
        for (int i = 0; i < contactStrings.size(); i++) {
            MemberEntity contactEntity = new MemberEntity(0, contactStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }
}
