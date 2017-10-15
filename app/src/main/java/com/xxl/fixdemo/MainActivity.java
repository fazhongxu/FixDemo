package com.xxl.fixdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.btn_fix).setOnClickListener(this);
    }

    private void initData() {
        //1.联网获取补丁包 （这里为了简单，直接把生成的补丁包放到手机上测试,直接在本地读取补丁包）
        //2.保存补丁包到本地sd卡中
        File file = new File(Environment.getExternalStorageDirectory(), "fix.apatch");
        if (file.exists()) {
            Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
            //3.添加补丁包
            try {
                MApp.mPatchManager.addPatch(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "修复失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fix:
                //这里为了制造出bug，模拟出bug,还是已经发布到用户手上的bug,人为使用了1/0打包给用户，命名为old.apk
//                Toast.makeText(this, "" + 1 / 0, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "这是打过补丁的app,Bug已修复", Toast.LENGTH_SHORT).show();//修复了bug,打包为new.apk
                //这里的old.apk 和 new.apk文件需要正式打包签名，然后去阿里热修复官网下载工具包，工具包里面运行命令生成
                //old.apk和new.apk两个包的差分xxx.aptch文件
                //这个xxx.aptch文件需要上传给服务器，然后用户联网就下载xxx.apatch差分包，供阿里热修复添加补丁使用
                break;
        }
    }
}
