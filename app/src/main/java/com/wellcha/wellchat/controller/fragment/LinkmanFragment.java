package com.wellcha.wellchat.controller.fragment;


import android.content.Intent;
import android.view.View;

import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.wellcha.wellchat.R;
import com.wellcha.wellchat.controller.activity.AddLinkmanActivity;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
//联系人列表页面
public class LinkmanFragment extends EaseContactListFragment{
    @Override
    protected void initView() {
        super.initView();
        //显示 +
        titleBar.setRightImageResource(R.drawable.em_add);

        View headerView=View.inflate(getActivity(),R.layout.header_fragment_linkman,null);
        listView.addHeaderView(headerView);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        //添加按钮点击事件
        titleBar.setRightLayoutClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddLinkmanActivity.class);
                startActivity(intent);
            }
        });
    }
}
