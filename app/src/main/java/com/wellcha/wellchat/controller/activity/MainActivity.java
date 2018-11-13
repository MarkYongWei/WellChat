package com.wellcha.wellchat.controller.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.wellcha.wellchat.R;
import com.wellcha.wellchat.controller.fragment.ChatFragment;
import com.wellcha.wellchat.controller.fragment.LinkmanFragment;
import com.wellcha.wellchat.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private ChatFragment chatFragment;
    private LinkmanFragment linkmanFragment;
    private SettingFragment settingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //初始化数据
        initData();
        //监听
        initListener();
    }

    private void initListener() {
//        RadioGroup选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                Fragment fragment=null;
                switch (i){
                    case R.id.rb_main_chat:
                        fragment=chatFragment;
                        break;
                    case R.id.rb_main_linkman:
                        fragment=linkmanFragment;
                        break;
                    case R.id.rb_main_setting:
                        fragment=settingFragment;
                        break;
                }
                //fragment切换
                switchFragment(fragment);
                
            }
        });
        //默认会话列表
        rg_main.check(R.id.rb_main_chat);
    }
    //实现fragment切换的方法
    private void switchFragment(Fragment fragment) {
        FragmentManager FragmentManager = getSupportFragmentManager();
        FragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }
    private void initData() {
//        创建fragment
         chatFragment=new ChatFragment();
         linkmanFragment=new LinkmanFragment();
         settingFragment=new SettingFragment();
    }

    private void initView() {
        rg_main = findViewById(R.id.rg_main);
    }
}
