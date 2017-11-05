package com.henlinkeji.shenbian.base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.henlinkeji.shenbian.base.config.MyConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hjm on 2016/9/8.
 * 图片加载工具类，至少需要普通图片加载，圆形图片加载，圆角图片加载.......(以后用到的时候再添加)
 */
public class ImageUtils {

    public final static int NORMAL = 0;
    public final static int CIRCULAR = 1;
    public final static int CORNER = 2;

    public static void showImage(Activity context, String url, ImageView imageView) {
        showImage(context, url, imageView, NORMAL);
    }

    public static void showImage(Activity context, String url, ImageView imageView, int type) {
        switch (type) {
            case CIRCULAR:
                Glide.with(context).load(url).transform(new GlideCircleTransform(context)).crossFade().into(imageView);
                break;
            case CORNER:
                Glide.with(context).load(url).transform(new GlideRoundTransform(context, 10)).crossFade().into(imageView);
                break;
            default:
                Glide.with(context).load(url).crossFade().into(imageView);
                break;
        }
    }

    public static Bitmap decodeBitmapFromFile(String path, int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    public static File createImageFile(String suffix) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        String imageFileName = "IMG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName,  /* prefix */
                suffix,         /* suffix */
                storageDir      /* directory */);

        return image;
    }

    public static String getImageFilePath(Context context, Uri imageUri) {
        String realPath;
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) realPath = RealFilePathUtil.getRealPathFromURI_BelowAPI11(context, imageUri);

            // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19)
            realPath = RealFilePathUtil.getRealPathFromURI_API11to18(context, imageUri);

            // SDK > 19 (Android 4.4)
        else realPath = RealFilePathUtil.getRealPathFromURI_API19(context, imageUri);

        return realPath;
    }

    public static byte[] convertImageToBytes(String imageFileFullPath, double maxSize) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFileFullPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = 1;
        if (photoH > photoW && photoH > maxSize) {
            scaleFactor = (int) (photoH * 1.0 / maxSize);
        } else if (photoW > photoH && photoW > maxSize) {
            scaleFactor = (int) (photoW * 1.0 / maxSize);
        }

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(imageFileFullPath, bmOptions);
        } catch (OutOfMemoryError e) {
            //
        }

        boolean isJpg = imageFileFullPath.endsWith(MyConfig.IMAGE_JPG_SUFFIX);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        if (isJpg) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
        } else {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
        }
        byte[] b = byteStream.toByteArray();

        return b;
    }

}
