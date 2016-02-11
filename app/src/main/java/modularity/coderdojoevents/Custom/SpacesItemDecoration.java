package modularity.coderdojoevents.Custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    public static final int DEFAULT_SPACE = 20;

    private int spaceX;
    private int spaceY;

    public SpacesItemDecoration(Context context, int sx, int sy) {
        this.spaceX = (sx != 0) ? context.getResources().getDimensionPixelSize(sx) : 0;
        this.spaceY = (sy != 0) ? context.getResources().getDimensionPixelSize(sy) : 0;
    }

    public SpacesItemDecoration(Context context, int s) {
        this(context, s, s);
    }

    public SpacesItemDecoration(Context context) {
        this(context, DEFAULT_SPACE);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = outRect.right = spaceX;
        outRect.bottom = spaceY;
        if (parent.getChildLayoutPosition(view) == 0)
            outRect.top = spaceY;
    }

}