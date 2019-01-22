package com.lnyp.imgdots.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lnyp.imgdots.R;
import com.lnyp.imgdots.adapter.ImgBrowsePagerAdapter;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointSimple;

import java.util.ArrayList;
import java.util.List;

public class ImageBrowseActivity extends AppCompatActivity {

    private ViewPager viewPagerImgs;

    private List<ImgSimple> imgSimples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browse);


        viewPagerImgs = (ViewPager) this.findViewById(R.id.viewPagerImgs);
        viewPagerImgs.setOffscreenPageLimit(2);

        initData();

        PagerAdapter adapter = new ImgBrowsePagerAdapter(this, imgSimples);
        viewPagerImgs.setAdapter(adapter);

    }

    private void initData() {

        imgSimples = new ArrayList<>();

        ImgSimple imgSimple1 = new ImgSimple();
        imgSimple1.url = R.mipmap.test_1;
        imgSimple1.pic_with = 900;
        imgSimple1.pic_height = 1200;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.pic_x = 900;
        pointSimple1.pic_y = 1200;
        pointSimple1.pointLeft_x = 320;
        pointSimple1.pointLeft_y = 30;
        pointSimple1.pointRight_x = 600;
        pointSimple1.pointRight_y = 380;
        pointSimples.add(pointSimple1);


        imgSimple1.pointSimples = pointSimples;


        ImgSimple imgSimple2 = new ImgSimple();
        imgSimple2.url = R.mipmap.test_3;
        imgSimple2.pic_with = 566;
        imgSimple2.pic_height = 800;
        ArrayList<PointSimple> pointSimples2 = new ArrayList<>();
        PointSimple pointSimple7 = new PointSimple();
        pointSimple7.pic_x = 566;
        pointSimple7.pic_y = 800;
        pointSimple7.pointLeft_x = 160;
        pointSimple7.pointLeft_y = 165;
        pointSimple7.pointRight_x = 400;
        pointSimple7.pointRight_y = 470;
        pointSimples2.add(pointSimple7);
        imgSimple2.pointSimples = pointSimples2;


        ImgSimple imgSimple3 = new ImgSimple();
        imgSimple3.url = R.mipmap.test_2;
        imgSimple3.pic_with = 580;
        imgSimple3.pic_height = 435;
        ArrayList<PointSimple> pointSimples3 = new ArrayList<>();
        PointSimple pointSimple11 = new PointSimple();
        pointSimple11.pic_x = 580;
        pointSimple11.pic_y = 435;
        pointSimple11.pointLeft_x = 300;
        pointSimple11.pointLeft_y = 50;
        pointSimple11.pointRight_x = 500;
        pointSimple11.pointRight_y = 250;
        pointSimples3.add(pointSimple11);



        imgSimple3.pointSimples = pointSimples3;
        imgSimples.add(imgSimple1);
        imgSimples.add(imgSimple2);
        imgSimples.add(imgSimple3);
    }
}
