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
        imgSimple1.url = "http://v2.faceso.com.cn:8003/fs/HotImage/2018050418/B48.jpg";
        imgSimple1.pic_with = 580;
        imgSimple1.pic_height = 739;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.pic_x = 580;
        pointSimple1.pic_y = 739;
        pointSimple1.pointLeft_x = 200;
        pointSimple1.pointLeft_y = 165;
        pointSimple1.pointRight_x = 441;
        pointSimple1.pointRight_y = 493;
        pointSimples.add(pointSimple1);


        imgSimple1.pointSimples = pointSimples;


        ImgSimple imgSimple2 = new ImgSimple();
        imgSimple2.url = "http://v2.faceso.com.cn:8003/fs/IMAGE/21/f0/21f0751650a611e8b3b2f01fafe1f0b2.jpg";
        imgSimple2.pic_with = 574;
        imgSimple2.pic_height = 535;
        ArrayList<PointSimple> pointSimples2 = new ArrayList<>();
        PointSimple pointSimple7 = new PointSimple();
        pointSimple7.pic_x = 574;
        pointSimple7.pic_y = 535;
        pointSimple7.pointLeft_x = 162;
        pointSimple7.pointLeft_y = 143;
        pointSimple7.pointRight_x = 430;
        pointSimple7.pointRight_y = 507;


        pointSimples2.add(pointSimple7);

        imgSimple2.pointSimples = pointSimples2;


        ImgSimple imgSimple3 = new ImgSimple();
        imgSimple3.url = "http://v2.faceso.com.cn:8003/fs/HotImage/2018050418/A37.jpg";
        imgSimple3.pic_with = 580;
        imgSimple3.pic_height = 1031;
        ArrayList<PointSimple> pointSimples3 = new ArrayList<>();
        PointSimple pointSimple11 = new PointSimple();
        pointSimple11.pic_x = 580;
        pointSimple11.pic_y = 1031;
        pointSimple11.pointLeft_x = 174;
        pointSimple11.pointLeft_y = 139;
        pointSimple11.pointRight_x = 520;
        pointSimple11.pointRight_y = 607;


        pointSimples3.add(pointSimple11);

        imgSimple3.pointSimples = pointSimples3;

        imgSimples.add(imgSimple1);
        imgSimples.add(imgSimple2);
        imgSimples.add(imgSimple3);
    }
}
