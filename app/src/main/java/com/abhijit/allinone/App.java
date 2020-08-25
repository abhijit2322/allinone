package com.abhijit.allinone;

import android.app.Application;

import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationEvent;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
      /*  new Instabug.Builder(this, "166ce8a8bd637dc649c580b7e8be5c7c")
                .setInvocationEvents(
                        InstabugInvocationEvent.SHAKE,
                        InstabugInvocationEvent.FLOATING_BUTTON)
                .build();*/
    }
}