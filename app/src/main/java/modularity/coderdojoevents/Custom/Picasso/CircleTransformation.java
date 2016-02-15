package modularity.coderdojoevents.Custom.Picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class CircleTransformation
        implements Transformation {

    @Override
    public Bitmap transform(Bitmap sourceBitmap) {

        int smallerOfHeightAndWidth = Math.min(sourceBitmap.getHeight(), sourceBitmap.getWidth());
        int x_firstSourcePixel = (sourceBitmap.getWidth() - smallerOfHeightAndWidth) / 2;
        int y_firstSourcePixel = (sourceBitmap.getHeight() - smallerOfHeightAndWidth) / 2;
        float radius = smallerOfHeightAndWidth / 2f;

        Bitmap sourceBitmapSquare = Bitmap.createBitmap(sourceBitmap,
                x_firstSourcePixel,
                y_firstSourcePixel,
                smallerOfHeightAndWidth,
                smallerOfHeightAndWidth);
        // recycle original bitmap if it's not the same as we no longer need it
        if (sourceBitmapSquare != sourceBitmap) {
            sourceBitmap.recycle();
        }

        // create the output bitmap structure
        Bitmap outputBitmap = Bitmap.createBitmap(smallerOfHeightAndWidth,
                smallerOfHeightAndWidth,
                sourceBitmap.getConfig());

        // prepare canvas + paint with all ingredients
        BitmapShader shader = new BitmapShader(sourceBitmapSquare,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);

        canvas.drawCircle(radius, radius, radius, paint);

        // recycle sourceBitmapSquare as we no longer need it
        sourceBitmapSquare.recycle();

        return outputBitmap;
    }

    @Override
    public String key() {
        return "co.kaush.mystarterapp.myappmodule.ui.transformations.CircleTransformation";
    }
}