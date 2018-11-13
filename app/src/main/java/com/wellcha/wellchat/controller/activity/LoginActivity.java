package com.wellcha.wellchat.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.wellcha.wellchat.R;
import com.wellcha.wellchat.model.Model;
import com.wellcha.wellchat.model.bean.UserInfo;

public class LoginActivity extends Activity {
    private EditText et_Login_id;
    private EditText et_Login_pwd;
    private Button bt_Login_login;
    private Button bt_Login_regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();

        //初始化监听
        initListener();
    }

    private void initListener() {
        //注册按钮点击事件
        bt_Login_regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                newRegist();
            }
        });
        //登陆按钮点击事件
        bt_Login_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    //登陆
    private void login() {
        final String loginId = et_Login_id.getText().toString();
        final String loginPwd = et_Login_pwd.getText().toString();
        if(TextUtils.isEmpty(loginPwd)||TextUtils.isEmpty(loginId)){
            Toast.makeText(LoginActivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //登陆逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(loginId,loginPwd,new EMCallBack(){
                    //登陆成功后处理
                    @Override
                    public void onSuccess() {
//                        Model.getInstance().loginSuccess();

                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginId));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                    //登陆失败处理
                    @Override
                    public void onError(int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登陆失败"+s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
//                    登陆过程中处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    //注册业务逻辑
    private void newRegist(){
        Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
        startActivity(intent);
    }
    private void initView() {
        et_Login_id=findViewById(R.id.et_Login_id);
        et_Login_pwd=findViewById(R.id.et_Login_pwd);
        bt_Login_login=findViewById(R.id.bt_Login_login);
        bt_Login_regist=findViewById(R.id.bt_Login_regist);
    }

}
