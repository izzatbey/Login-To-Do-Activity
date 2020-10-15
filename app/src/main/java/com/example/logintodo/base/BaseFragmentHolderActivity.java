package com.example.logintodo.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.logintodo.R;


public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected TextView tvToolbarTitle;
    protected FrameLayout flFragmentContainer;
    protected ImageButton btOptionMenu;
    protected ImageView ivIcon;
    protected ImageButton btBack;
    protected View vMenuBarShadow;
    protected RelativeLayout rlActivityFragmentHolder;

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        flFragmentContainer = (FrameLayout) findViewById(R.id.flFragmentContainer);
        btOptionMenu = (ImageButton) findViewById(R.id.btn_option_menu);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        btBack = (ImageButton) findViewById(R.id.btn_back);
        vMenuBarShadow = findViewById(R.id.v_menu_bar_shadow);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rl_activity_fragment_holder);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setTitle(String title) {
        this.tvToolbarTitle.setText(title);
    }


}
