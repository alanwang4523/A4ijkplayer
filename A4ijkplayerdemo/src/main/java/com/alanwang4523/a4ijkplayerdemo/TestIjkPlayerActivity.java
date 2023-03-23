/*
 * Copyright (c) 2019-present AlanWang4523 <alanwang4523@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alanwang4523.a4ijkplayerdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Author: AlanWang4523.
 * Date: 2023/3/17 11:47.
 * Mail: alanwang4523@gmail.com
 */
public class TestIjkPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestIjkPlayerActivity";
    private TextureView mTextureView;
    private Surface mDisplaySurface;
    private IjkMediaPlayer mIjkPlayer;

    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, TestIjkPlayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ijkplayer);

        mTextureView = findViewById(R.id.textureView);
        findViewById(R.id.btnStartPlay).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStartPlay) {
            startPlay();
        }
    }

    private String getTestMediaPath() {
        String videoDir = this.getFilesDir().getAbsolutePath() + "/";
        String videoPath = videoDir + "horizontal_test_video_169.mp4";
//            String videoPath = videoDir + "qiaobianguniang.mp4";
//            String videoPath = videoDir + "hv_test_video_11.mp4";
        return videoPath;
    }

    public void startPlay() {
        String videoPath = getTestMediaPath();
        File videoFile = new File(videoPath);
        Log.e(TAG, "mVideoPath = " + videoFile);

        if (!videoFile.exists()) {
            Toast.makeText(this, "Video file not exist!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mDisplaySurface == null && mTextureView.isAvailable()) {
            mDisplaySurface = new Surface(mTextureView.getSurfaceTexture());
        }
        if (mDisplaySurface == null) {
            Log.e(TAG, "mDisplaySurface is null.");
            Toast.makeText(this, "Display surface is not available, please try again later.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (mIjkPlayer == null) {
                mIjkPlayer = new IjkMediaPlayer();
            } else {
                mIjkPlayer.reset();
            }

            mIjkPlayer.setSurface(mDisplaySurface);
            mIjkPlayer.setDataSource(videoPath);
            mIjkPlayer.setOnPreparedListener(mp -> mp.start());
            mIjkPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIjkPlayer != null) {
            mIjkPlayer.stop();
            mIjkPlayer.release();
        }
    }
}