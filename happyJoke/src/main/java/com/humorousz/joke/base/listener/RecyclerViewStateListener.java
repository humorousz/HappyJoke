package com.humorousz.joke.base.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by zhangzhiquan on 2017/5/26.
 */

public abstract class RecyclerViewStateListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if(recyclerView == null || recyclerView.getChildCount() <= 0)
            return;
        if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            if (isSlideToMiddle(recyclerView)) {
                reachMiddle();
            }
            if (isSlideToBottom(recyclerView)) {
                reachBottom();
            }
            if(isSlideToTop(recyclerView)){
                reachTop();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    private boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null)
            return false;
        boolean isReachBottom = recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange();
        if (isReachBottom) {
            return isReachBottom;
        }
        return false;
    }

    private boolean isSlideToMiddle(RecyclerView recyclerView) {
        if (recyclerView == null)
            return false;
        boolean isReachMiddle = recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= (recyclerView.computeVerticalScrollRange() / 2);
        if (isReachMiddle) {
            return isReachMiddle;
        }
        return false;
    }

    private boolean isSlideToTop(RecyclerView recyclerView){
        if(recyclerView == null || recyclerView.getLayoutManager() == null){
            return false;
        }
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            return linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        }else if(manager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            return  gridLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        }else if(manager instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            //An array to put the results into. If you don't provide any, LayoutManager will create a new one.
            int[] pos = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(null);
            for(int i = 0; i< staggeredGridLayoutManager.getSpanCount(); i++){
                if(pos[i] == 0)
                    return true;
            }
        }
        return false;
    }

    public abstract void reachMiddle();

    public abstract void reachBottom();

    public abstract void reachTop();
}
