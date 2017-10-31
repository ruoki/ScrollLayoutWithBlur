package com.ruoki.scrolllayoutwithblurlib.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;

/**
 * @description 图片工具类
 */
public class BitmapUtil {

    /**
     * 将Drawable对象转化为Bitmap对象
     *
     * @param drawable Drawable对象
     * @return 对应的Bitmap对象
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        //如果本身就是BitmapDrawable类型 直接转换即可
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        //取得Drawable固有宽高
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            //创建一个1x1像素的单位色图
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            //直接设置一下宽高和ARGB
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        //重新绘制Bitmap
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 截取当前屏幕
     * @param context
     * @return
     */
    public static Bitmap getCurrentScreenView(Activity context){
        View view = context.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        /**
         * 获取当前窗口快照，相当于截屏
         */
        Bitmap bmp1 = view.getDrawingCache();
        int height = getOtherHeight(context);
        /**
         * 除去状态栏和标题栏
         */
        Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, height,bmp1.getWidth(), bmp1.getHeight() - height);
        view.destroyDrawingCache();
        return bmp2;
    }


    /**
     * 截取当前view
     * @param view
     * @return
     */
    public static Bitmap takeShotBy(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bmp1 = view.getDrawingCache();
        Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, view.getTop(),bmp1.getWidth(), bmp1.getHeight());
        view.destroyDrawingCache();
        return bmp2;
    }

    /**
     * 获取系统状态栏和软件标题栏
     * @param context
     * @return
     */
    public static int getOtherHeight(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int contentTop = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;
        return statusBarHeight + titleBarHeight;
    }
}