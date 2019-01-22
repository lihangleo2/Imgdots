package com.lnyp.imgdots.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.view.ImageLayout;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.width;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;


    public ImgBrowsePagerAdapter(Activity context, List<ImgSimple> imgSimples) {

        this.mContext = context;
        this.imgSimples = imgSimples;

        this.views = new ArrayList<>();

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

    }

    @Override
    public int getCount() { // 获得size
        return imgSimples.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browse, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);

        try {

            Integer imgUrl = imgSimples.get(position).url;
            int pic_with = imgSimples.get(position).pic_with;
            int pic_height = imgSimples.get(position).pic_height;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;

            layoutContent.setPoints(pointSimples);
            layoutContent.setImgBg(pic_with,pic_height,imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((ViewPager) container).addView(view);

        return view;
    }
}