/**
  * Copyright 2017 JessYan
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package com.onetarget.common.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * ================================================
 * {@link Application.ActivityLifecycleCallbacks} 默认实现类
 *
 * @see <a href="http://www.jianshu.com/p/75a5c24174b2">ActivityLifecycleCallbacks 分析文章</a>
 * Created by JessYan on 21/02/2017 14:23
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private AppManager mAppManager;
    private Application mApplication;


    public ActivityLifecycle(AppManager appManager, Application application) {
        this.mAppManager = appManager;
        this.mApplication = application;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        //如果 intent 包含了此字段,并且为 true 说明不加入到 list 进行统一管理
        boolean isNotAdd = false;
        if (activity.getIntent() != null)
            isNotAdd = activity.getIntent().getBooleanExtra(AppManager.IS_NOT_ADD_ACTIVITY_LIST, false);

        if (!isNotAdd)
            mAppManager.addActivity(activity);
    }


    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mAppManager.setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (mAppManager.getCurrentActivity() == activity) {
            mAppManager.setCurrentActivity(null);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mAppManager.removeActivity(activity);
    }


}
