package com.wellcha.wellchat.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.wellcha.wellchat.R;
import com.wellcha.wellchat.model.Model;

public class RegistActivity extends Activity {
    private EditText et_Regist_Id;
    private EditText et_Regist_Pwd;
    private EditText et_Regist_RePwd;
    private Button bt_Regist_Input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        bt_Regist_Input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
    }

    private void initView() {
        et_Regist_Id=(EditText)findViewById(R.id.et_Regist_id);
        et_Regist_Pwd=(EditText)findViewById(R.id.et_Regist_pwd);
        et_Regist_RePwd=(EditText)findViewById(R.id.et_Regist_repwd);
        bt_Regist_Input=(Button) findViewById(R.id.bt_Regist_input);
    }
    private void regist() {
        final String registId = et_Regist_Id.getText().toString();
        final String registPwd = et_Regist_Pwd.getText().toString();
        final String registRePwd = et_Regist_RePwd.getText().toString();
        if(TextUtils.isEmpty(registId)||TextUtils.isEmpty(registPwd)||TextUtils.isEmpty(registRePwd)){
            Toast.makeText(RegistActivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if(!registPwd.equals(registRePwd)){
            Toast.makeText(RegistActivity.this,"两次输入的密码不一样", Toast.LENGTH_SHORT).show();
            return;
        }
        //去环信服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(registId,registPwd);
                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistActivity.this,"注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
