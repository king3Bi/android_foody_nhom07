package hcmute.nhom7.foody.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class ImageUtils {

    public static int getIdImgFromDrawable(Context context, String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        return resId;
    }

    public static Bitmap loadImage(Context context, String resName) {
        int idImg = ImageUtils.getIdImgFromDrawable(context, resName);
        InputStream imgStream = context.getResources().openRawResource(idImg);
        return BitmapFactory.decodeStream(imgStream);
    }


    public static String encodeImg(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String encodeImgScale(Bitmap bitmap, int scale){
        int previewWidth = scale;
        int previewHeight = bitmap.getHeight() * previewWidth/bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap decodeImg(String imgEncodeStr) {
        try {
            byte [] encodeByte = Base64.decode(imgEncodeStr, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
