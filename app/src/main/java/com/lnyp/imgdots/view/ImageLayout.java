package com.lnyp.imgdots.view;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.OnMatrixChangedListener;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.utils.UIUtil;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    PhotoView imgBg;

    Context mContext;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (PhotoView) imgPointLayout.findViewById(R.id.imgBg);
        imgBg.enable();
        imgBg.disableRotate();
        imgBg.setOnClickListener(this);
        imgBg.setOnMatrixChangeListener(new OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rect) {
                Log.e("是不是有了",rect.toString()+"");
                Info info = PhotoView.getImageViewInfo(imgBg);
                int left = (int) info.getmImgRect().left;
                int top = (int) info.getmImgRect().top;
                int right = (int) info.getmImgRect().right;
                int bottom = (int) info.getmImgRect().bottom;
                addPoints(left,top,right,bottom);
            }
        });


        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setImgBg(int pic_with, int pic_height, String imgUrl) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = UIUtil.getWidth(getContext());
        lp.height = UIUtil.getHeight(getContext());
        imgBg.setLayoutParams(lp);


        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = UIUtil.getWidth(getContext());
        lp1.height = UIUtil.getHeight(getContext());
        layouPoints.setLayoutParams(lp1);
        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);

//        addPoints(UIUtil.getWidth(getContext()), UIUtil.getHeight(getContext()));

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {

            int point_left_x = points.get(i).pointLeft_x;
            int point_left_y = points.get(i).pointLeft_y;
            int pic_with = points.get(i).pic_x;
            int pic_height = points.get(i).pic_y;

            int point_rigth_x = points.get(i).pointRight_x;
            int point_right_y = points.get(i).pointRight_y;

            int view_x = point_rigth_x - point_left_x;
            int view_y = point_right_y - point_left_y;


            RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_point);
            relativeLayout.setTag(i);

            RelativeLayout.LayoutParams linlayout = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            linlayout.width = (width * view_x / pic_with);
            linlayout.height = UIUtil.getWidth(getContext()) * view_y / pic_with;


            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (width * point_left_x / pic_with);
            layoutParams.topMargin = (int) (UIUtil.getWidth(getContext()) * point_left_y / pic_with);
            relativeLayout.setOnClickListener(this);
            layouPoints.addView(view, layoutParams);
        }
    }


    private void addPoints(int left, int top, int right, int bottom) {

        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {

            int point_left_x = points.get(i).pointLeft_x;
            int point_left_y = points.get(i).pointLeft_y;
            int pic_with = points.get(i).pic_x;
            int pic_height = points.get(i).pic_y;
            int point_rigth_x = points.get(i).pointRight_x;
            int point_right_y = points.get(i).pointRight_y;


            //相对窗口宽度
            int screenWith = right - left;
            int screenHeight = bottom - top;


            int tempX = point_left_x * screenWith/ pic_with ;
            Log.e("我来看看是什么请撒",point_left_x+"==============="+pic_with+"`````````````"+screenWith);

            int tempY = point_left_y * screenHeight/ pic_height ;
            Log.e("我来看看是什么请撒",point_left_y+"==============="+pic_height+"`````````````"+screenHeight);

            //正确的坐标  左上角
            int trueX = left + tempX;
            int trueY = top + tempY;
            Log.e("这个Y轴是什么鬼",top+"----------------"+tempY);
            Log.e("我来看看是什么请撒",tempX+"==============="+tempY);

            //长宽
            int chaX = point_rigth_x - point_left_x;
            int chaY = point_right_y - point_left_y;

            int viewX = screenWith*chaX/pic_with;
            int viewY = screenHeight*chaY/pic_height;


            RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_point);
            relativeLayout.setTag(i);

            RelativeLayout.LayoutParams linlayout = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            linlayout.width = viewX;
            linlayout.height= viewY;


            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = trueX;
            layoutParams.topMargin = trueY;
            relativeLayout.setOnClickListener(this);


            //左上角的点
            ImageView imageView_top_left = new ImageView(getContext());
            LayoutParams leoParams_top_left = new LayoutParams(layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
            leoParams_top_left.leftMargin = trueX;
            leoParams_top_left.topMargin = trueY;
            imageView_top_left.setImageResource(R.mipmap.left_top);

            //左下角的点
            ImageView imageView_bottom_left = new ImageView(getContext());
            LayoutParams leoParams_bottom_left = new LayoutParams(layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
            leoParams_bottom_left.leftMargin = trueX;
            leoParams_bottom_left.topMargin = trueY+viewY-60;//减去图片自身高度
            imageView_bottom_left.setImageResource(R.mipmap.left_bottom);


            //右上角的点
            ImageView imageView_top_right = new ImageView(getContext());
            LayoutParams leoParams_top_right = new LayoutParams(layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
            leoParams_top_right.leftMargin = trueX+viewX-65;
            leoParams_top_right.topMargin = trueY;
            imageView_top_right.setImageResource(R.mipmap.rigth_top);


            //右下角的点
            ImageView imageView_bottom_right = new ImageView(getContext());
            LayoutParams leoParams_bottom_right = new LayoutParams(layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
            leoParams_bottom_right.leftMargin = trueX+viewX-65;
            leoParams_bottom_right.topMargin = trueY+viewY-60;
            imageView_bottom_right.setImageResource(R.mipmap.right_bottom);


            layouPoints.addView(view, layoutParams);
            layouPoints.addView(imageView_top_left, leoParams_top_left);
            layouPoints.addView(imageView_bottom_left, leoParams_bottom_left);
            layouPoints.addView(imageView_top_right, leoParams_top_right);
            layouPoints.addView(imageView_bottom_right, leoParams_bottom_right);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBg:
                Toast.makeText(getContext(), "点击了其他地方" + ((PhotoView) view).getScale(), Toast.LENGTH_SHORT).show();
//                float f = ((PhotoView)view).getScale();
//                addPoints((int) (UIUtil.getWidth(getContext())*f), (int) (UIUtil.getHeight(getContext())*f));

                break;

            case R.id.relative_point:
                int pos = (int) view.getTag();
                Toast.makeText(getContext(), "pos : " + pos, Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
