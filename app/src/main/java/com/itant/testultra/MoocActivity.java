package com.itant.testultra;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 慕课网下拉
 */
public class MoocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mooc);

        final RelativeLayout rl_content = (RelativeLayout) findViewById(R.id.rl_content);

        final PtrFrameLayout pfl_content = (PtrFrameLayout) findViewById(R.id.pfl_content);
        final View header = LayoutInflater.from(this).inflate(R.layout.header_mooc, null);
        pfl_content.setHeaderView(header);
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
                        pfl_content.refreshComplete();
                    }
                }, 3000);
            }
        });
    }
}
