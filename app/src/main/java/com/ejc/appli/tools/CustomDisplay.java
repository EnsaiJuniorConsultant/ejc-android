package com.ejc.appli.tools;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.Display;
import android.view.Surface;

public class CustomDisplay {

    public static int getScreenOrientation(Activity activity){
        Display getOrient = activity.getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if(getOrient.getRotation() == Surface.ROTATION_0){
            orientation = Configuration.ORIENTATION_PORTRAIT;
        } else if(getOrient.getRotation() == Surface.ROTATION_90 || getOrient.getRotation() == Surface.ROTATION_270){
            orientation = Configuration.ORIENTATION_LANDSCAPE;
        }
        return orientation;
    }

}
