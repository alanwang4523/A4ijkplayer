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
package com.alanwang4523.a4ijkplayerdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import com.alanwang4523.a4ijkplayerdemo.R;

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

    private IjkMediaPlayer createMediaPlayer() {
        IjkMediaPlayer ijkPlayer = new IjkMediaPlayer();
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
        // 精准 seek
//        ijkPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        return ijkPlayer;
    }

    private Uri getTestMediaUri() {
//        return Uri.parse("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear4/prog_index.m3u8");
//        return Uri.parse("https://media.w3.org/2010/05/sintel/trailer.mp4");
//        return Uri.parse("http://vjs.zencdn.net/v/oceans.mp4");
        return Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
    }

    public void startPlay() {
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
                mIjkPlayer = createMediaPlayer();
            } else {
                mIjkPlayer.reset();
            }

            Uri testUri = getTestMediaUri();
            mIjkPlayer.setDataSource(this, testUri);
            mIjkPlayer.setSurface(mDisplaySurface);
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