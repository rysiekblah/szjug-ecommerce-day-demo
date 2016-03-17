package com.cloudsimple.demo_android;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.ServiceConfigurationError;

/**
 * Created by tomek on 3/17/16.
 */
public class DemoApplication extends Application {
    public final Bus bus = new Bus(ThreadEnforcer.MAIN);
    public final DemoPlayService service = new DemoPlayService();

    private static DemoApplication appContext;

    public static DemoApplication getInstance() {
        return appContext;
    }

    public DemoApplication() {
    }

    public DemoApplication(Context context) {
        this();
        attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
