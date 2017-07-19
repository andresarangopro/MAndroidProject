package android.extend.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.extend.app.ActivityProxy.IActivityExtend;
import android.extend.app.ActivityProxy.OnActivityObserver;
import android.extend.app.ActivityProxy.State;
import android.extend.util.LogUtil;
import android.extend.util.ResourceUtil;
import android.extend.widget.MenuBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjianxionh on 2016/10/17.
 */

public class BaseFragmentActivity extends FragmentActivity implements IActivityExtend
{
    private class FragmentInfo
    {
        public FragmentInfo(Fragment fragment, int requestCode)
        {
            mFragment = fragment;
            mRequestCode = requestCode;
        }

        public Fragment mFragment;
        public int mRequestCode;
    }

    private List<FragmentInfo> mFragmentInfoList = new ArrayList<FragmentInfo>();

    public final String TAG = getClass().getSimpleName();

    final List<Fragment> mFragments = new ArrayList<Fragment>();

    private final ActivityProxy mActivityProxy = new ActivityProxy(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivityProxy.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mActivityProxy.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mActivityProxy.onStart();
    }

    @Override
    public void onFirstStart()
    {
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mActivityProxy.onResume();
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        mActivityProxy.onPostResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mActivityProxy.onPause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        mActivityProxy.onRestart();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mActivityProxy.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mActivityProxy.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        mActivityProxy.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mActivityProxy.onActivityResult(requestCode, resultCode, data);
        if (mFragmentInfoList.isEmpty())
            return;
        FragmentInfo[] array = new FragmentInfo[mFragmentInfoList.size()];
        array = mFragmentInfoList.toArray(array);
        for (FragmentInfo info : array)
        {
            // LogUtil.i(TAG, "FragmentInfo: " + info.mRequestCode + "; " + info.mFragment);
            if (info.mRequestCode == requestCode)
            {
                info.mFragment.onActivityResult(requestCode, resultCode, data);
                mFragmentInfoList.remove(info);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        mActivityProxy.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mActivityProxy.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mActivityProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mActivityProxy.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onLowMemory()
    {
        LogUtil.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level)
    {
        LogUtil.d(TAG, "onTrimMemory: " + level);
        super.onTrimMemory(level);
    }

    @Override
    public void finish()
    {
        super.finish();
        mActivityProxy.overrideFinishPendingTransition();
    }

    @Override
    public void finishFromChild(Activity child)
    {
        super.finishFromChild(child);
        mActivityProxy.overrideFinishPendingTransition();
    }

    @Override
    public void finishActivity(int requestCode)
    {
        super.finishActivity(requestCode);
        mActivityProxy.overrideFinishPendingTransition();
    }

    @Override
    public void finishActivityFromChild(Activity child, int requestCode)
    {
        super.finishActivityFromChild(child, requestCode);
        mActivityProxy.overrideFinishPendingTransition();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode)
    {
        super.startActivityForResult(intent, requestCode);
        mActivityProxy.overrideStartPendingTransition();
    }

    @Override
    public void startActivity(Intent intent)
    {
        super.startActivity(intent);
        mActivityProxy.overrideStartPendingTransition();
    }

    @Override
    public void startActivities(Intent[] intents)
    {
        super.startActivities(intents);
        mActivityProxy.overrideStartPendingTransition();
    }

    @Override
    public void startActivityFromChild(Activity child, Intent intent, int requestCode)
    {
        super.startActivityFromChild(child, intent, requestCode);
        mActivityProxy.overrideStartPendingTransition();
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode)
    {
        LogUtil.v(TAG, "startActivityFromFragment: " + fragment + "; " + requestCode);
        super.startActivityForResult(intent, requestCode);
        mActivityProxy.overrideStartPendingTransition();
        mFragmentInfoList.add(new FragmentInfo(fragment, requestCode));
    }

    @Override
    public ActivityProxy getActivityProxy()
    {
        return mActivityProxy;
    }

    @Override
    public Activity getRootActivity()
    {
        return mActivityProxy.getRootActivity();
    }

    @Override
    public State getActivityState()
    {
        return mActivityProxy.getActivityState();
    }

    @Override
    public View getDecorView()
    {
        return mActivityProxy.getDecorView();
    }

    @Override
    public ViewGroup getContentRootView()
    {
        return mActivityProxy.getContentRootView();
    }

    @Override
    public MenuBar getMenuBar()
    {
        return mActivityProxy.getMenuBar();
    }

    @Override
    public String getString(String resName)
    {
        return mActivityProxy.getString(resName);
    }

    @Override
    public void registerObserver(OnActivityObserver observer)
    {
        mActivityProxy.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(OnActivityObserver observer)
    {
        mActivityProxy.unregisterObserver(observer);
    }

    @Override
    public void setStartActivityAnimation(int enterAnim, int exitAnim)
    {
        mActivityProxy.setStartActivityAnimation(enterAnim, exitAnim);
    }

    @Override
    public void setFinishActivityAnimation(int enterAnim, int exitAnim)
    {
        mActivityProxy.setFinishActivityAnimation(enterAnim, exitAnim);
    }

    @Override
    public void showToast(String text)
    {
        mActivityProxy.showToast(text);
    }

    /**
     * @deprecated Use {@link #showLoadingIndicator} instead.
     */
    @Override
    public void showLoadingIndicatorDialog()
    {
        showLoadingIndicator();
    }

    // @Override
    // public void showLoadingindicatorDialog(int progress)
    // {
    // mActivityProxy.showLoadingindicatorDialog(progress);
    // }

    /**
     * @deprecated Use {@link #showLoadingIndicator(String message)} instead.
     */
    @Override
    public void showLoadingIndicatorDialog(String message)
    {
        showLoadingIndicator(message);
    }

    // @Override
    // public void showLoadingIndicatorDialog(String message, int theme)
    // {
    // mActivityProxy.showLoadingIndicatorDialog(message, theme);
    // }

    @Override
    public void showLoadingIndicator()
    {
        int resId = ResourceUtil.getStringId(getApplicationContext(), "default_loading_text");
        showLoadingIndicator(getString(resId));
    }

    @Override
    public void showLoadingIndicator(String message,long time)
    {
        showLoadingIndicator(message, 0, false, false, time);
    }

    @Override
    public void showLoadingIndicator(String message)
    {
        showLoadingIndicator(message, 0, false, false, -1);
    }

    @Override
    public void showLoadingIndicator(int theme)
    {
        int resId = ResourceUtil.getStringId(getApplicationContext(), "default_loading_text");
        showLoadingIndicator(getString(resId), theme, false, false, -1);
    }

    @Override
    public void showLoadingIndicator(boolean cancelable)
    {
        int resId = ResourceUtil.getStringId(getApplicationContext(), "default_loading_text");
        showLoadingIndicator(getString(resId), 0, cancelable, cancelable, -1);
    }

    @Override
    public void showLoadingIndicator(long showingTime)
    {
        int resId = ResourceUtil.getStringId(getApplicationContext(), "default_loading_text");
        showLoadingIndicator(getString(resId), 0, false, false, showingTime);
    }

    @Override
    public void showLoadingIndicator(String message, int theme, boolean cancelable, boolean canceledOnTouchOutside,
                                     long showingTime)
    {
        mActivityProxy.showLoadingIndicatorDialog(message, theme, cancelable, canceledOnTouchOutside, showingTime);
    }

    /**
     * @deprecated Use {@link #hideLoadingIndicator} instead.
     */
    @Override
    public void hideLoadingIndicatorDialog()
    {
        hideLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator()
    {
        mActivityProxy.hideLoadingIndicatorDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        LogUtil.i(TAG, "onKeyDown: " + keyCode + "; " + event.getRepeatCount());
        if (onFragmentsKeyDown(keyCode, event, getSupportFragmentManager()))
            return true;
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        LogUtil.i(TAG, "onKeyUp: " + keyCode);
        // if (onFragmentsKeyUp(keyCode, event, getSupportFragmentManager()))
        // return true;
        return false;
    }

    @Override
    public void onBackPressed()
    {
        if (popBackStackImmediate())
            return;
        super.onBackPressed();
    }

    protected boolean onFragmentsKeyDown(int keyCode, KeyEvent event, FragmentManager manager)
    {
        // List<Fragment> fragments = manager.getFragments();
        if (mFragments != null && !mFragments.isEmpty())
        {
            for (Fragment fragment : mFragments)
            {
                // Fragment fragment = fragments.get(i);
                if (fragment == null || !fragment.isResumed())
                    continue;
                // if (fragment.getChildFragmentManager() != null)
                // if (onFragmentsKeyDown(keyCode, event, fragment.getChildFragmentManager()))
                // {
                // return true;
                // }
                if (fragment instanceof BaseFragment)
                {
                    if (((BaseFragment)fragment).onKeyDown(keyCode, event))
                    {
                        return true;
                    }
                    if (((BaseFragment)fragment).isAddToBackStack() && keyCode == KeyEvent.KEYCODE_BACK
                            && event.getRepeatCount() == 0)
                    {
                        break;
                    }
                }
            }
        }
        return false;
    }

    // protected boolean onFragmentsKeyUp(int keyCode, KeyEvent event, FragmentManager manager)
    // {
    // // List<Fragment> fragments = manager.getFragments();
    // if (mFragments != null && !mFragments.isEmpty())
    // {
    // for (Fragment fragment : mFragments)
    // {
    // // Fragment fragment = fragments.get(i);
    // if (fragment == null || !fragment.isResumed())
    // continue;
    // // if (fragment.getChildFragmentManager() != null)
    // // if (onFragmentsKeyUp(keyCode, event, fragment.getChildFragmentManager()))
    // // {
    // // return true;
    // // }
    // if (fragment instanceof BaseFragment)
    // {
    // if (((BaseFragment)fragment).onKeyUp(keyCode, event))
    // {
    // return true;
    // }
    // if (((BaseFragment)fragment).isAddToBackStack() && keyCode == KeyEvent.KEYCODE_BACK
    // && event.getRepeatCount() == 0)
    // {
    // break;
    // }
    // }
    // }
    // }
    // return false;
    // }

    public boolean popBackStackImmediate()
    {
        if (getSupportFragmentManager() != null)
            return getSupportFragmentManager().popBackStackImmediate();
        return false;
    }
}
