package com.ruoki.scrolllayoutwithblur;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ruoki.scrolllayoutwithblur.adapter.HomeGridMoreAdapter;
import com.ruoki.scrolllayoutwithblur.bean.MainPageMenu;
import com.ruoki.scrolllayoutwithblur.utils.DoubleClickUtils;
import com.ruoki.scrolllayoutwithblurlib.ScrollLayoutWithBlur;
import com.ruoki.scrolllayoutwithblurlib.content.ContentScrollView;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.layout_container)
    ConstraintLayout layout_container;
    @Bind(R.id.scroll_down_layout)
    ScrollLayoutWithBlur mScrollLayout;

    @Bind(R.id.scroll_layout_content)
    ContentScrollView scroll_layout_content;

    @Bind(R.id.form_main_function_grid_more)
    GridView menuMore;
    private ArrayList<MainPageMenu> data_groups1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initScrollLayoutView();
    }


    public void initScrollLayoutView() {
        mScrollLayout.setRootView(layout_container);
        mScrollLayout.setPreBlurView(scroll_layout_content);
        mScrollLayout.setAssociatedScrollView(scroll_layout_content);
        mScrollLayout.init();

        data_groups1 = initMenu();
        HomeGridMoreAdapter mainPageAdapterMore = new HomeGridMoreAdapter(data_groups1, getApplicationContext());
        menuMore.setAdapter(mainPageAdapterMore);
        menuMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!DoubleClickUtils.isDoubleClick()) {
                    clickGridMoreItem(data_groups1.get(position).getId());
                }
            }
        });
    }

    private void clickGridMoreItem(int id) {
        switch (id) {
            case 101:
                Toast.makeText(this,"you clicked 101",Toast.LENGTH_LONG).show();
                break;
        }
    }


    private ArrayList<MainPageMenu> initMenu() {
        ArrayList<MainPageMenu> group1 = new ArrayList<>();
        MainPageMenu mMainPageModel = new MainPageMenu(101, R.string.expense_item0, R.drawable.func_expense_00);
        MainPageMenu mMainPageModel2 = new MainPageMenu(102, R.string.expense_item1, R.drawable.func_expense_01);
        MainPageMenu mMainPageModel3 = new MainPageMenu(103, R.string.expense_item2, R.drawable.func_expense_02);
        group1.add(mMainPageModel);
        group1.add(mMainPageModel2);
        group1.add(mMainPageModel3);
        return group1;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void hideScrollLayout() {
        if (mScrollLayout != null) {
            mScrollLayout.hideScrollLayout();
        }
    }

    public void showScrollLayout() {
        if (mScrollLayout != null) {
            mScrollLayout.showScrollLayout();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_content:
                showScrollLayout();
                break;
            case R.id.ibtn_bottom_close:
                hideScrollLayout();
                break;

        }
    }
}
