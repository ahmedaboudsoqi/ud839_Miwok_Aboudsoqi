/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayerHelper mMediaPlayerHelper;
    private AudioManager mAudioManager;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        mMediaPlayerHelper=new MediaPlayerHelper();
        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mOnAudioFocusChangeListener=mMediaPlayerHelper.createOnAudioFocusChangeListener(mAudioManager);
        mOnItemClickListener=mMediaPlayerHelper.createOnItemClickListener(this,mAudioManager,words);
        listView.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayerHelper.releaseMediaPlayer(mAudioManager);

    }

}
