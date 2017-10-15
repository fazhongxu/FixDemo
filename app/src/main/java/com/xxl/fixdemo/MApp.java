package com.xxl.fixdemo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by xxl on 2017/10/15.
 */

public class MApp extends Application {

    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initAliFix();
    }

    /**
     * 初始化阿里热修复
     */
    private void initAliFix() {
        //1.初始化阿里热修复PatchManager
        mPatchManager = new PatchManager(this);
        //2.初始化热修复
        mPatchManager.init(getVersionName());
        //3.加载已有的patch补丁包
        mPatchManager.loadPatch();

        //4.在应用启动的时候获取添加应用补丁包即可，这一步放在MainActivity中实现
    }

    /**
     * 获取应用版本名称
     */
    private String getVersionName() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
