package com.fineos.appplugin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PluginBean> mListPlugin;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);

        mListPlugin = findPluginList();
        attachPlugin(mListPlugin);
    }

    //查找插件
    private List<PluginBean> findPluginList(){
        List<PluginBean> pluginBeans = new ArrayList<PluginBean>();
        //
        int count = 0;
        PackageManager pm = getPackageManager();
        List<PackageInfo> pkgs = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(PackageInfo pkg : pkgs){
            String packageName = pkg.packageName;
            String sharedUserId = pkg.sharedUserId;
            if (!"com.fineos.appplugin.sharedUserId".equals(sharedUserId) || "com.fineos.appplugin".equals(packageName)){
                continue;
            }
            count++;
            Log.d("liubo", "----- 插件个数:" + count);
            String lable = pm.getApplicationLabel(pkg.applicationInfo).toString();
            PluginBean pluginBean = new PluginBean(packageName, lable);
            pluginBeans.add(pluginBean);
        }
        return pluginBeans;
    }

    /**
     * 加载插件列表
     * @param plugins
     */
    private void attachPlugin(final List<PluginBean> plugins){
        Log.d("liubo", "----- 列出插件");
        this.mListPlugin = plugins;
        for(final PluginBean plugin : mListPlugin){
            Log.d("liubo", "" + plugin.packageName);
            Button btn = new Button(this);
            btn.setTextColor(Color.RED);
            btn.setText(plugin.label);
            mRelativeLayout.addView(btn);
            //添加事件
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent();
                    it.setAction(plugin.packageName);
                    startActivity(it);
                }
            });
        }
    }
}
