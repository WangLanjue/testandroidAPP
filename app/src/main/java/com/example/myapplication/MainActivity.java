package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager2 viewpager;
    private LinearLayout llchat,llcontact,llfind,lluser;
    private ImageView ivchat,ivcontact,ivfind,ivuser,ivcurrent;
    //ivcurrent保存当前页面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initTabView();
    }

    private void initTabView() {
        llchat=findViewById(R.id.tb_weixin);
        llcontact=findViewById(R.id.tb_contact);
        llfind=findViewById(R.id.tb_find);
        lluser=findViewById(R.id.tb_user);

        llchat.setOnClickListener(this);
        llcontact.setOnClickListener(this);
        llfind.setOnClickListener(this);
        lluser.setOnClickListener(this);

        ivchat=findViewById(R.id.tb_iv_weixin);
        ivcontact=findViewById(R.id.tb_iv_contact);
        ivfind=findViewById(R.id.tb_iv_find);
        ivuser=findViewById(R.id.tb_iv_user);
        ivchat.setSelected(true);
        ivcurrent=ivchat;

    }

    private void initPager() {
        viewpager=findViewById(R.id.viewpager1);
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(BlankFragment.newInstance("微信聊天"));
        fragments.add(BlankFragment.newInstance("通讯录"));
        fragments.add(BlankFragment.newInstance("发现"));
        fragments.add(BlankFragment.newInstance("我的"));
        MyFragmentPageAdapter myFragmentPageAdapter=new MyFragmentPageAdapter(
                getSupportFragmentManager(),getLifecycle(),fragments);
        viewpager.setAdapter(myFragmentPageAdapter);
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    private void changeTab(int position) {
        ivcurrent.setSelected(false);
        switch (position){
            case R.id.tb_weixin:
                viewpager.setCurrentItem(0);
            case 0:
                ivchat.setSelected(true);
                ivcurrent=ivchat;
                break;
            case R.id.tb_contact:
                viewpager.setCurrentItem(1);
            case 1:
                ivcontact.setSelected(true);
                ivcurrent=ivcontact;
                break;
            case R.id.tb_find:
                viewpager.setCurrentItem(2);
            case 2:
                ivfind.setSelected(true);
                ivcurrent=ivfind;
                break;
            case R.id.tb_user:
                viewpager.setCurrentItem(3);
            case 3:
                ivuser.setSelected(true);
                ivcurrent=ivuser;
                break;
                default:
                    break;
        }
    }


    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}