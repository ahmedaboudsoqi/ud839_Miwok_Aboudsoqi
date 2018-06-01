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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayerHelper mMediaPlayerHelper;
    private AudioManager mAudioManager;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        final ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going ));
        words.add(new Word("What is your name?" + "","tinnә oyaase'nә",R.raw.phrase_my_name_is));
        words.add(new Word("My name is..." ,"oyaaset...",R.raw.phrase_my_name_is ));
        words.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));
        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_phrases);

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
