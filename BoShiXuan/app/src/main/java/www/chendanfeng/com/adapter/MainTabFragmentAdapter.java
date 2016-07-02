package www.chendanfeng.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import www.chendanfeng.com.boishixuan.R;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class MainTabFragmentAdapter implements RadioGroup.OnCheckedChangeListener{
    private List<Fragment> mFragmentList;
    private RadioGroup mRadioGroup;
    private FragmentActivity mFragmentActivity;
    private int mFragmentContentId;

    private int mCurrentTab;

    public MainTabFragmentAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.mFragmentList = fragments;
        this.mRadioGroup = rgs;
        this.mFragmentActivity = fragmentActivity;
        this.mFragmentContentId = fragmentContentId;
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commit();
        rgs.setOnCheckedChangeListener(this);


    }
   @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       for (int i = 0; i < this.mRadioGroup.getChildCount(); i++) {
           RadioButton button = (RadioButton) this.mRadioGroup.getChildAt(i);
           if (this.mRadioGroup.getChildAt(i).getId() == checkedId) {
               button.setTextColor(this.mFragmentActivity.getResources().getColor(R.color.coffee));
               Fragment fragment = this.mFragmentList.get(i);
               FragmentTransaction ft = obtainFragmentTransaction(i);


               getCurrentFragment().onPause();
               if (fragment.isAdded()) {
                   fragment.onResume();
               } else {
                   ft.add(this.mFragmentContentId, fragment);
               }
               showTab(i);
               ft.commit();


           } else {
               button.setTextColor(this.mFragmentActivity.getResources().getColor(R.color.white));
           }
       }
    }
    private void showTab(int idx) {
        for (int i = 0; i < this.mFragmentList.size(); i++) {
            Fragment fragment = this.mFragmentList.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);

            if (idx == i) {
                ft.show(fragment);
                fragment.setUserVisibleHint(true);
            } else {
                ft.hide(fragment);
                fragment.setUserVisibleHint(false);
            }
            ft.commit();
        }
        this.mCurrentTab = idx;
    }

    public int getCurrentTab() {
        return this.mCurrentTab;
    }

    public Fragment getCurrentFragment() {
        return this.mFragmentList.get(this.mCurrentTab);
    }
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = this.mFragmentActivity.getSupportFragmentManager().beginTransaction();
        return ft;
    }
}
