package modularity.coderdojoevents.Custom.Picasso;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class GradientTransformation implements Transformation {
    int startColor = Color.argb(240, 0, 0, 0);
    int endColor = Color.TRANSPARENT;

    @Override
    public Bitmap transform(Bitmap source) {

        int x = source.getWidth();
        int y = source.getHeight();

        Bitmap grandientBitmap = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(grandientBitmap);

        LinearGradient grad = new LinearGradient(x / 2, y, x / 2, y / 2, startColor, endColor, Shader.TileMode.CLAMP);
        Paint p = new Paint(Paint.DITHER_FLAG);
        p.setShader(null);
        p.setDither(true);
        p.setFilterBitmap(true);
        p.setShader(grad);
        canvas.drawPaint(p);
        source.recycle();
        return grandientBitmap;
    }

    @Override
    public String key() {
        return "Gradient";
    }
}