package com.example.gidro.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by Gidro on 01.06.2017.
 */

public class CustomEditText extends EditText {

    private  OnClickListener rightDrawableClick;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (getCompoundDrawables()[DRAWABLE_RIGHT] == null) {
            return super.onTouchEvent(event);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (getRight() - getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                if (rightDrawableClick != null) {
                    rightDrawableClick.onClick(null);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setRightDrawableClick(OnClickListener rightDrawableClick) {
        this.rightDrawableClick = rightDrawableClick;
    }
}
