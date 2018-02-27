package com.globallogic.mtinterviewapp.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class CustomSwipeRefresh extends SwipeRefreshLayout {

  private int mActivePointerId;

  public CustomSwipeRefresh(Context context) {
    super(context);
  }

  public CustomSwipeRefresh(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
      int pointerCount = ev.getPointerCount();
      int index = ev.getActionIndex();
      mActivePointerId = ev.getPointerId(index);
      index = ev.findPointerIndex(mActivePointerId);
      if (index > -1 && index < pointerCount) {
        super.onInterceptTouchEvent(ev);
      } else {
        return true;
      }
    } else if (ev.getAction() == MotionEvent.ACTION_POINTER_DOWN && super.onInterceptTouchEvent(ev)) {
      final int index = ev.getActionIndex();
      mActivePointerId = ev.getPointerId(index);
      return false;
    } else if (ev.getAction() == MotionEvent.ACTION_POINTER_UP && super.onInterceptTouchEvent(ev)) {
      onSecondaryPointerUp(ev);
      return false;
    } else if (ev.getAction() == MotionEvent.ACTION_DOWN && super.onInterceptTouchEvent(ev)) {
      mActivePointerId = ev.getPointerId(0);
      return false;
    }
    return super.onInterceptTouchEvent(ev);
  }

  private void onSecondaryPointerUp(MotionEvent ev) {
    final int pointerIndex = ev.getActionIndex();
    final int pointerId = ev.getPointerId(pointerIndex);
    if (pointerId == mActivePointerId) {
      final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
      mActivePointerId = ev.getPointerId(newPointerIndex);
    }
  }
}