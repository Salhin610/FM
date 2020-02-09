package com.testBit68.fm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.testBit68.fm.ui.SplashSliderFragments.PageFragment1;
import com.testBit68.fm.ui.SplashSliderFragments.PageFragment2;
import com.testBit68.fm.ui.SplashSliderFragments.PageFragment3;
import com.testBit68.fm.ui.SplashSliderFragments.SlidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {


    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    int selectedIndex=0;
    Boolean mPageEnd=true;
    boolean callHappened=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        List<Fragment>list=new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());
        pager=findViewById(R.id.pager);

        pager.setOnPageChangeListener(mListener);


        pagerAdapter=new SlidePagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);


    }

    private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            selectedIndex = arg0;

        }
        boolean callHappened;
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            if( mPageEnd && arg0 == selectedIndex && !callHappened)
            {
                Log.d(getClass().getName(), "Okay");
                mPageEnd = false;//To avoid multiple calls.
                callHappened = true;
            }else
            {
                mPageEnd = false;
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            if(selectedIndex == pagerAdapter.getCount() - 1)
            {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
}
        }

    };

}
