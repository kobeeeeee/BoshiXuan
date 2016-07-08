package www.chendanfeng.com.boishixuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import www.chendanfeng.com.util.LogUtil;


/**
 * Created by yangln10784 on 2016/7/8.
 */
public class StartupActivity extends BaseActivity{

    private ViewPager viewPager;
    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    /**
     * 图片资源id
     */
    private int[] imgIdArray;
    private ViewPager.LayoutParams mParams;
    private int oldPosition = 0;// 记录上一次点的位置
    private int currentItem; // 当前页面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.mParams = new ViewPager.LayoutParams();
        this.mParams.width = 10;
        this.mParams.height = 10;

        //载入图片资源ID
        imgIdArray = new int[]
                {R.drawable.startpage_1, R.drawable.startpage_2, R.drawable.startpage_3, R.drawable.startpage_4};

        //将点点加入到ViewGroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.point_sel);
            } else {
                tips[i].setBackgroundResource(R.drawable.point_nor);
            }
            LogUtil.i(this,"test");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }

        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要设置点点的北背景
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
        viewPager.setCurrentItem((mImageViews.length) * 100);
    }

    public  class  MyAdapter extends PagerAdapter{

        @Override
        public  int getCount(){
            return Integer.MAX_VALUE;
        }

        @Override
        public  boolean isViewFromObject(View arg0, Object arg1){
            return arg0 == arg1;
        }

        public  void destroyItem(ViewGroup container, int position, Object object){
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
        }

        /**
         * 载入图片进去，用当前position 除以图片数组长度取余数是关键
         */
        public  Object instantiateItem(ViewGroup container, int position){
            try {
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            }catch (Exception e){

            }
                return mImageViews[position % mImageViews.length];
        }
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

   public class GuidePageChangeListener implements OnPageChangeListener{

       @Override
       public  void onPageScrollStateChanged(int arg0){

       }

       @Override
       public  void onPageScrolled(int arg0, float arg1, int arg2){

       }

       @Override
       public  void onPageSelected(int arg0){
           setImageBackground(arg0 % mImageViews.length);
       }

       /**
        * 设置选中的tips的背景
        */
       private  void setImageBackground(int selectItems){
           for(int i = 0; i < tips.length; i++){
               if(i == selectItems){
                   tips[i].setBackgroundResource(R.drawable.point_sel);
               }else{
                   tips[i].setBackgroundResource(R.drawable.point_nor);
               }
           }
       }
   }

}
