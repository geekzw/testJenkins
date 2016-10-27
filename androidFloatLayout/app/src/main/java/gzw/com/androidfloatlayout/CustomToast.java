package gzw.com.androidfloatlayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by gzw on 16/8/23.
 */
public class CustomToast {
    private Context mContext;
    private WindowManager wm;
    private int mDuration;
    private View mNextView;
    private int mGravity;
    private int xOffSet;
    private int yOffSet;
    private View mCurrentView;
    private static CustomToast mCustomToast;
    public static final int LENGTH_SHORT = 1500;
    public static final int LENGTH_LONG = 3000;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mNextView != null&&wm!=null) {
                wm.removeView(mCurrentView);
                mCurrentView = null;
            }

        }
    };

    public static CustomToast getToast(Context context){
        if(mCustomToast == null){
            mCustomToast = new CustomToast(context);
        }
        return mCustomToast;
    }

    private CustomToast(Context context) {
        mContext = context.getApplicationContext();
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        this.mGravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    }

    public void setGravity(int gravity,int xOffSet,int yOffSet){
        this.mGravity = gravity;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setView(View mNextView) {
        this.mNextView = mNextView;
    }

    public View getView() {
        return mNextView;
    }

    public static CustomToast makeText(Context context, CharSequence text,
                                       int duration) {
        CustomToast result = new CustomToast(context);
        LayoutInflater inflate = (LayoutInflater) context
                .getApplicationContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.common_toast, null);
        TextView tv = (TextView) v.findViewById(R.id.toast_text);
        tv.setText(text);
        result.mNextView = v;
        result.mDuration = duration;
        return result;
    }

    public static CustomToast makeText(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId),duration);
    }

    public void show() {
        if (mNextView != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.gravity = mGravity;
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = android.R.style.Animation_Toast;
            params.y = dip2px(yOffSet);

            params.x = dip2px(xOffSet);
            params.type = WindowManager.LayoutParams.TYPE_TOAST;

            if(mCurrentView!=null){
                wm.removeViewImmediate(mCurrentView);
                mHandler.removeMessages(1);
            }
            wm.addView(mNextView, params);
            mCurrentView = mNextView;
            mHandler.sendEmptyMessageDelayed(1,mDuration);

        }
    }

    public int dip2px(float dipValue) {
        float reSize = mContext.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }
}
