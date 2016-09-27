package com.itant.testultra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_advanced = (Button) findViewById(R.id.btn_advanced);
        btn_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查看更高级的下拉效果
                startActivity(new Intent(MainActivity.this, MoocActivity.class));
            }
        });


        final PtrMDHeader header = new PtrMDHeader(this);
        final RelativeLayout rl_content = (RelativeLayout) findViewById(R.id.rl_content);

        PtrFrameLayout pfl_content = (PtrFrameLayout) findViewById(R.id.pfl_content);
        pfl_content.setHeaderView(header);
        pfl_content.addPtrUIHandler(header);
        pfl_content.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, rl_content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 模仿延时3000毫秒
                        boolean refreshResult = false;
                        header.refreshComplete(refreshResult, frame);
                    }
                }, 3000);
            }
        });
    }
}
