package com.wellcha.wellchat.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hyphenate.chat.EMClient;
import com.wellcha.wellchat.R;
import com.wellcha.wellchat.model.Model;
import com.wellcha.wellchat.model.bean.UserInfo;

public class SplashActivity extends Activity {

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //如果当前Activity已经退出，就不处理handle中的消息
            if (isFinishing()) {
                return;
            }
            //判断进入主页面还是登陆页面
            toMainOrLogin();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //发送2s的延时消息
        handler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁消息
        handler.removeCallbacksAndMessages(null);
    }

    //判断进入登陆界面还是主界面
    private void toMainOrLogin() {

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断当前帐号是否已经登陆过
                if (EMClient.getInstance().isLoggedInBefore()) {
                    //登陆过 获取当前登陆用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxid(EMClient.getInstance().getCurrentUser());
                    if (account==null){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    Model.getInstance().loginSuccess(account);
                    //跳到主页面
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //没登陆过
                    //跳转到登录页面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //结束
                finish();
            }
        });
    }
}
