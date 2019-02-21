package com.peopletech.organization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.peopletech.organization.adapter.AttendantMemberAdapter;
import com.peopletech.organization.adapter.BannerHeaderAdapter;
import com.peopletech.organization.adapter.DescriptionAdapter;
import com.peopletech.organization.adapter.FootAdapter;
import com.peopletech.organization.adapter.MemberAdapter;
import com.peopletech.organization.entity.StringEntity;
import com.peopletech.organization.entity.MenuEntity;
import com.peopletech.organization.entity.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableFooterAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import me.yokeyword.indexablerv.SimpleHeaderAdapter;

/**
 * @author hych
 * @date 2019/2/19 14:17
 */
public class PartyMemberActivity extends AppCompatActivity {

    private MemberAdapter mMemberAdapter;
    private BannerHeaderAdapter mHeaderMenuAdapter;
    private DescriptionAdapter mAttendantDesAdapter;
    private FootAdapter mFooterDesAdapter;
    private List<StringEntity> mFooterCount;
    private AttendantMemberAdapter mAttendantMemberAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ogz_act_party_member);

        IndexableLayout indexableLayout = (IndexableLayout) findViewById(R.id.ogz_indexableLayout);

        initMemberAdapter();
        initHeaderMenuAdapter();
        initAttendantDesAdapter();
        initAttendantMemberAdapter();
        initFooterDesAdapter();

        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        indexableLayout.setAdapter(mMemberAdapter);
        indexableLayout.setIndexBarVisibility(false);
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);

        indexableLayout.addHeaderAdapter(mAttendantMemberAdapter);
        indexableLayout.addHeaderAdapter(mAttendantDesAdapter);
        indexableLayout.addHeaderAdapter(mHeaderMenuAdapter);
        indexableLayout.addFooterAdapter(mFooterDesAdapter);

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


    }

    /**
     * 初始化所有成员
     */
    private void initMemberAdapter() {
        mMemberAdapter = new MemberAdapter(this);
        mMemberAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if (originalPosition >= 0) {
                    ToastUtil.showShort(PartyMemberActivity.this, "选中:" + entity.getNick() + "  当前位置:" + currentPosition + "  原始所在数组位置:" + originalPosition);
                } else {
                    ToastUtil.showShort(PartyMemberActivity.this, "选中Header/Footer:" + entity.getNick() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    /**
     * 初始化顶部菜单条目
     */
    private void initHeaderMenuAdapter() {
        List<MenuEntity> menuList = new ArrayList<>();
        List<String> menuDesList = Arrays.asList(getResources()
                .getStringArray(R.array.organization_header));
        int[] menuIcons = {R.drawable.ogz_ic_organization,
                R.drawable.ogz_ic_wx_invit};

        for (int i = 0; i < menuDesList.size(); i++) {
            MenuEntity entity = new MenuEntity(i, menuDesList.get(i), menuIcons[i]);

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

        mHeaderMenuAdapter = new BannerHeaderAdapter(this, null, null, menuList);
        mHeaderMenuAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener<MenuEntity>() {
            @Override
            public void onItemClick(View view, int i, MenuEntity menuEntity) {
                mMemberAdapter.setDatas(initDatas());

            }
        });
    }

    /**
     * 初始化管理员描述条目
     */
    private void initAttendantDesAdapter() {
        final ArrayList<StringEntity> desList = new ArrayList<>();
        StringEntity ogz = new StringEntity("组织管理");
        desList.add(ogz);
        mAttendantDesAdapter = new DescriptionAdapter(this, null, null, desList);
    }

    /**
     * 初始化管理员成员
     */
    private void initAttendantMemberAdapter() {
        final List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity("张三", "10000"));
        list.add(new UserEntity("李四", "10001"));
        mAttendantMemberAdapter = new AttendantMemberAdapter(this, null, null, list);
        mAttendantMemberAdapter.setOnItemHeaderClickListener(new IndexableHeaderAdapter.OnItemHeaderClickListener<UserEntity>() {
            @Override
            public void onItemClick(View view, int i, UserEntity userEntity) {
                ToastUtil.showShort(PartyMemberActivity.this, "" + i);

                for (int i1 = 1; i1 < list.size(); i1++) {
                    mAttendantMemberAdapter.removeData(list.get(i1));
                }

                list.get(0).setNick("women");
                for (int i1 = 1; i1 < list.size(); i1++) {
                    mAttendantMemberAdapter.removeData(list.get(i1));
                }

                List<UserEntity> list1 = new ArrayList<>();
                for (int i1 = 0; i1 < 3; i1++) {
                    UserEntity ww = new UserEntity("王五" + i1, "222");
                    list1.add(ww);
                }
                mAttendantMemberAdapter.notifyDataSetChanged();

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
     * 更新组织管理成员
     *
     * @param count
     */
    private void updateAttendantMember(String count) {

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

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(getResources().getStringArray(R.array.contact_array));
        List<String> mobileStrings = Arrays.asList(getResources().getStringArray(R.array.mobile_array));
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }
}
