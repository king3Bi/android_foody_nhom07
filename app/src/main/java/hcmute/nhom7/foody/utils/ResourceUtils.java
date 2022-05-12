package hcmute.nhom7.foody.utils;

import android.content.Context;
import android.util.Log;

import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class ResourceUtils {

    public static int getDrawableResIdByName(Context context, String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        return resId;
    }
}
