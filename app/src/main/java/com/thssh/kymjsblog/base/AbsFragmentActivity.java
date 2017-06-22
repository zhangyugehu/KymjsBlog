package com.thssh.kymjsblog.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public abstract class AbsFragmentActivity<T extends AbsActivityPresenter> extends AbsPresenterActivity<T>{

    protected void addFragmentToStrack(Fragment fragment, String tag){
        this.addFragment(fragment, tag, true);
    }

    protected void addFragment(Fragment fragment, String tag, boolean addToStrack){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(setFragmentContainer(), fragment, tag);
        if(addToStrack){
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }

    /**
     * 提供一个存放fragment的容器
     * @return
     */
    protected abstract int setFragmentContainer();
}
