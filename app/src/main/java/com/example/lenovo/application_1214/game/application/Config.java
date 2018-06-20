package com.example.lenovo.application_1214.game.application;

import android.app.Application;

import com.example.lenovo.application_1214.game.utils.Constances;
import com.example.lenovo.application_1214.game.utils.SpUtils;

import org.litepal.LitePalApplication;

public class Config extends LitePalApplication {

    /**
     * 游戏目标
     */
    public static int mGameGoal;

    /**
     * GameView行列数
     */
    public static int mGameLines;

    /**
     * Item宽高
     */
    public static int mItemSize;

    /**
     * 记录分数
     */
    public static int SCORE = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mGameGoal = SpUtils.getInt(this, Constances.GAME_GOAL, 2048);
        mGameLines = SpUtils.getInt(this, Constances.GAME_LINES, 4);
        mItemSize = 0;
    }
}
