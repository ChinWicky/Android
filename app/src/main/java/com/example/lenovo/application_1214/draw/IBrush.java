package com.example.lenovo.application_1214.draw;

import android.graphics.Path;

public interface IBrush {

    void down(Path path, float x, float y);
    void move(Path path, float x, float y);
    void up(Path path, float x, float y);

}
