package modularity.coderdojoevents.Custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    public static final int DEFAULT_SPACE = 20;
    private int space;

    public SpacesItemDecoration(Context context, int space) {
        this.space = context.getResources().getDimensionPixelSize(space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = outRect.right = outRect.bottom = space;
        if (parent.getChildLayoutPosition(view) == 0) outRect.top = space;
    }

}