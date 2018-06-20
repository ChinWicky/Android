package com.example.lenovo.application_1214.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawPath implements IDraw {

    public Path path;
    public Paint paint;

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public void undo() {

    }
}
