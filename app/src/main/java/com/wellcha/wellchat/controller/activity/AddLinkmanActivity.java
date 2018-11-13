package com.wellcha.wellchat.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wellcha.wellchat.R;

public class AddLinkmanActivity extends Activity {
    private TextView tv_add_search;
    private EditText et_add_name;
    private TextView tv_add_name;
    private Button bt_add_add;
    private RelativeLayout rl_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_linkman);

        initView();

        initListener();
    }

    private void initListener() {
        //查找点击事件处理
        tv_add_search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                find();
            }
        });

        //添加按钮点击事件处理
        bt_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    //查找按钮处理
    private void find() {
        String name=et_add_name.getText().toString();
        //校验输入姓名
        if (TextUtils.isEmpty(name)){
            Toast.makeText(AddLinkmanActivity.this,"输入的用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //去服务器判断当前用户是否存在
    }
    //添加按钮处理
    private void add() {

    }

    private void initView() {
        tv_add_search=findViewById(R.id.tv_add_search);
        et_add_name=findViewById(R.id.et_add_name);
        tv_add_name=findViewById(R.id.tv_add_name);
        bt_add_add=findViewById(R.id.bt_add_add);
        rl_add=findViewById(R.id.rl_add);
    }
}
