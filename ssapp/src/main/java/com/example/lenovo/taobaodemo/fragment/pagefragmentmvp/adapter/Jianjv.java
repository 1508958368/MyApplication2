package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Jianjv extends RecyclerView.ItemDecoration {

    int mSpace;


    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * <p>
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * <p>
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * <p>
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * <p>
     * before returning.
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * <p>
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * <p>
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */

    @Override

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mSpace;

//        outRect.right = mSpace;

     // outRect.bottom = mSpace;

//        if (parent.getChildAdapterPosition(view) >= 0) {
//
//            outRect.top = mSpace;
//
//        }


    }

    public Jianjv(int space) {

        this.mSpace = space;

    }

}
