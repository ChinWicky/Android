package com.example.lenovo.application_1214.draw;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.application_1214.R;

public class MainDrawActivity extends Activity implements View.OnClickListener {

    private DrawCanvas mCanvas;
    private DrawPath mPath;
    private Paint mPaint;
    private IBrush mBrush;

    private Button btn_red;
    private Button btn_green;
    private Button btn_blue;
    private Button btn_normal;
    private Button btn_circle;
    private Button btn_undo;
    private Button btn_redo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_main);
        init();
    }

    private void init() {
        mCanvas = findViewById(R.id.draw_canvas);
        mCanvas.setOnTouchListener(new DrawTouchListener());

        mPaint = new Paint();
        mPaint.setColor(0xFFFFFFFF);
        mPaint.setStrokeWidth(3.0f);

        mBrush = new NormalBrush();

        btn_red = findViewById(R.id.btn_red);
        btn_red.setOnClickListener(this);

        btn_green = findViewById(R.id.btn_green);
        btn_green.setOnClickListener(this);

        btn_blue = findViewById(R.id.btn_blue);
        btn_blue.setOnClickListener(this);

        btn_normal = findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(this);

        btn_circle = findViewById(R.id.btn_circle);
        btn_circle.setOnClickListener(this);

        btn_undo = findViewById(R.id.btn_undo);
        btn_undo.setOnClickListener(this);
        btn_undo.setEnabled(false);

        btn_redo = findViewById(R.id.btn_redo);
        btn_redo.setOnClickListener(this);
        btn_redo.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                mPaint = new Paint();
                mPaint.setColor(0xFFFF0000);
                mPaint.setStrokeWidth(3.0f);
                break;
            case R.id.btn_green:
                mPaint = new Paint();
                mPaint.setColor(0xFF00FF00);
                mPaint.setStrokeWidth(3.0f);
                break;
            case R.id.btn_blue:
                mPaint = new Paint();
                mPaint.setColor(0xFF0000FF);
                mPaint.setStrokeWidth(3.0f);
                break;
            case R.id.btn_undo:
                mCanvas.undo();
                if (!mCanvas.canUndo()) {
                    btn_undo.setEnabled(false);
                }
                btn_redo.setEnabled(true);
                break;
            case R.id.btn_redo:
                mCanvas.redo();
                if (!mCanvas.canRedo()) {
                    btn_redo.setEnabled(false);
                }
                btn_undo.setEnabled(true);
                break;
            case R.id.btn_normal:
                mBrush = new NormalBrush();
                break;
            case R.id.btn_circle:
                mBrush = new CircleBrush();
                break;
            default:
                break;
        }
    }

    private class DrawTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPath = new DrawPath();
                    mPath.paint = mPaint;
                    mPath.path = new Path();
                    mBrush.down(mPath.path, event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    mBrush.move(mPath.path, event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    mBrush.up(mPath.path, event.getX(), event.getY());
                    mCanvas.add(mPath);
                    mCanvas.isDrawing = true;
                    btn_undo.setEnabled(true);
                    btn_redo.setEnabled(false);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
