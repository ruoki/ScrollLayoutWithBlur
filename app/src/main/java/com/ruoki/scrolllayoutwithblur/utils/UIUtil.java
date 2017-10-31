package com.ruoki.scrolllayoutwithblur.utils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class UIUtil {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (dpValue * scale + 0.5f);

        return px;
    }


    /**
     * 为textview设置图标
     * @param mContext
     * @param drawableRecource
     * @param rect
     * @param text
     * @param top
     */
    public static void setDrawableWithView(Context mContext, int drawableRecource, Rect rect, TextView text, int top) {
        Drawable drawable = mContext.getResources().getDrawable(drawableRecource);
        //第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable.setBounds(UIUtil.dip2px(mContext, rect.left), UIUtil.dip2px(mContext, rect.top), UIUtil.dip2px(mContext, rect.right), dip2px(mContext, rect.bottom));
        switch (top){
            case Derection.LEFT:
                text.setCompoundDrawables(drawable, null, null, null);//只放左边
                break;
            case Derection.TOP:
                text.setCompoundDrawables(null, drawable, null, null);//只放上边
                break;
            case Derection.RIGHT:
                text.setCompoundDrawables(null, null,drawable,  null);//只放右边
                break;
            case Derection.BOTTOM:
                text.setCompoundDrawables(null, null,  null,drawable);//只放下边
                break;
        }
    }


    /**
     * 为textview设置图标
     * @param mContext
     * @param drawableRecource
     * @param rect
     * @param text
     */
    public static void setDrawableWithView(Context mContext, int[] drawableRecource, Rect[] rect, TextView text) {
        Drawable drawableLeft = drawableRecource[0] == 0?null:mContext.getResources().getDrawable(drawableRecource[0]);
        Drawable drawableTop = drawableRecource[1] == 0?null:mContext.getResources().getDrawable(drawableRecource[1]);
        Drawable drawableRight = drawableRecource[2] == 0?null:mContext.getResources().getDrawable(drawableRecource[2]);
        Drawable drawableBottom = drawableRecource[3] == 0?null:mContext.getResources().getDrawable(drawableRecource[3]);
        if(drawableLeft !=null) {
            drawableLeft.setBounds(rect[0].left, rect[0].top, UIUtil.dip2px(mContext, rect[0].right), dip2px(mContext, rect[0].bottom));
        }
        if(drawableTop !=null) {
            drawableTop.setBounds(rect[1].left, rect[1].top, UIUtil.dip2px(mContext, rect[1].right), dip2px(mContext, rect[1].bottom));
        }
        if(drawableRight !=null) {
            drawableRight.setBounds(rect[2].left, rect[2].top, UIUtil.dip2px(mContext, rect[2].right), dip2px(mContext, rect[2].bottom));
        }
        if(drawableBottom !=null) {
            drawableBottom.setBounds(rect[3].left, rect[3].top, UIUtil.dip2px(mContext, rect[3].right), dip2px(mContext, rect[3].bottom));
        }
        text.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }


    public class Derection {
        public static final int LEFT = 1;
        public static final int TOP = 2;
        public static final int RIGHT = 3;
        public static final int BOTTOM = 4;
    }

}
