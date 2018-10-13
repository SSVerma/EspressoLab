package com.ssverma.espressolab.idlingresources;

import android.os.Handler;
import android.support.test.espresso.idling.CountingIdlingResource;

public class MessageProcessor {

    public static void processMessage(final String message, final CountingIdlingResource idlingResource, final MessageProcessListener processListener) {
        if (idlingResource != null) {
            idlingResource.increment();
        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idlingResource != null) {
                    idlingResource.decrement();
                }
                if (processListener != null) {
                    processListener.onProcessed(message);
                }
            }
        }, 3000);

    }

    interface MessageProcessListener {
        void onProcessed(String text);
    }

}
