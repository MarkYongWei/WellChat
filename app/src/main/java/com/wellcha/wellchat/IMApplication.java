package com.wellcha.wellchat;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.wellcha.wellchat.model.Model;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class IMApplication extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        EMOptions options= new EMOptions();
        options.setAcceptInvitationAlways(false);//设置需要同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//设置需要同意后才能群接受邀请


        EaseUI.getInstance().init(this,options);

        //初始化数据模型层类
        Model.getInstance().init(this);

        // 初始化全局上下文对象
        mContext = this;
    }
    // 获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}
