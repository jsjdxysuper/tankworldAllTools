package com.newdingwen.tankworldtool;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TankFragment.OnFragmentInteractionListener{

    // 三个选项卡
    private LinearLayout heavyTankLayout, mediumTankLayout, lightTankLayout,tankDestroyerLayout,selfPropelledGunLayout;
    // 默认选中第一个tab
    private int index = 1;
    // fragment管理类
    private FragmentManager fragmentManager;
    // 三个fragment
    private TankFragment heavyTankFragment, mediumTankFragment, lightTankFragment,tankDestroyerFragment,selfPropelledGunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fragmentManager =  getFragmentManager();
        init();
    }


////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_second);
////        fragmentManager = getSupportFragmentManager();
////        init();
////    }
//
    /**
     * 初始化控件
     */
    private void init() {

        heavyTankLayout = (LinearLayout) findViewById(R.id.heavyTankLinearLay);
        mediumTankLayout = (LinearLayout) findViewById(R.id.mediumTankLinearLay);
        lightTankLayout = (LinearLayout) findViewById(R.id.lightTankLinearLay);
        tankDestroyerLayout = (LinearLayout) findViewById(R.id.tankDestroyerLinearLay);
        selfPropelledGunLayout = (LinearLayout) findViewById(R.id.selfPropelledGunLinearLay);

        heavyTankLayout.setOnClickListener(this);
        mediumTankLayout.setOnClickListener(this);
        lightTankLayout.setOnClickListener(this);
        tankDestroyerLayout.setOnClickListener(this);
        selfPropelledGunLayout.setOnClickListener(this);
        //
        setDefaultFragment();
    }

    /**
     * 设置默认显示的fragment
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        heavyTankFragment = TankFragment.newInstance("重坦","");
        transaction.replace(R.id.tankList, heavyTankFragment);
//        transaction.show(heavyTankFragment);
//        transaction.add(R.id.tankList,heavyTankFragment);
        transaction.commit();
    }

//    @Override
//    public void onClick(View view) {
//        TextView textv = (TextView)(((ViewGroup)view).getChildAt(0));
//
//        Log.i("ding", textv.getText().toString());
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     *切换fragment
     * @param newFragment
     */
    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!newFragment.isAdded()) {
            transaction.replace(R.id.tankList, newFragment);
            transaction.commit();
        } else {
            transaction.show(newFragment);
        }
    }

    /**
     * 改变现象卡的选中状态
     */
    private void clearStatus() {
        if (index == 1) {
            heavyTankLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (index == 2) {
            mediumTankLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (index == 3) {
            lightTankLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else if (index == 4) {
            tankDestroyerLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else if (index == 5) {
            selfPropelledGunLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    //heavyTankLayout, mediumTankLayout, lightTankLayout,tankDestroyerLayout,selfPropelledGunLayout
    //heavyTankFragment, mediumTankFragment, lightTankFragment,tankDestroyerFragment,selfPropelledGunFragment;
    @Override
    public void onClick(View v) {
        clearStatus();
        switch (v.getId()) {
            case R.id.heavyTankLinearLay:
                if (heavyTankFragment == null) {
                    heavyTankFragment = new TankFragment();
                    View fragView = heavyTankFragment.getView();
                    TextView textv = fragView.findViewById(R.id.headTV);
                    textv.setText(R.string.heavyTank);
                }
                replaceFragment(heavyTankFragment);
                heavyTankLayout.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                index = 1;
                break;
            case R.id.mediumTankLinearLay:
                if (mediumTankFragment == null) {
                    mediumTankFragment = new TankFragment();
                }
                replaceFragment(mediumTankFragment);
                mediumTankLayout.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                index = 2;
                break;
            case R.id.lightTankLinearLay:
                if (lightTankFragment == null) {
                    lightTankFragment = new TankFragment();
                }
                replaceFragment(lightTankFragment);
                lightTankLayout.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                index = 3;
                break;
            case R.id.tankDestroyerLinearLay:
                if (tankDestroyerFragment == null) {
                    tankDestroyerFragment = new TankFragment();
                }
                replaceFragment(tankDestroyerFragment);
                tankDestroyerLayout.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                index = 4;
                break;
            case R.id.selfPropelledGunLinearLay:
                if (selfPropelledGunFragment == null) {
                    selfPropelledGunFragment = new TankFragment();
                }
                replaceFragment(selfPropelledGunFragment);
                selfPropelledGunLayout.setBackgroundColor(getResources().getColor(
                        R.color.colorPrimaryDark));
                index = 5;
                break;
        }
    }
}
