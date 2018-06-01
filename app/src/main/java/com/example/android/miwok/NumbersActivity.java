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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayerHelper mMediaPlayerHelper;
    private AudioManager mAudioManager;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

         ArrayList<Word> words=new ArrayList<Word>();

        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));
        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_numbers);

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





//    // the old implementation of the numbers activity
//    private AudioManager mAudioManager;
//    private MediaPlayer mMediaPlayer;
//    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=
//            new AudioManager.OnAudioFocusChangeListener() {
//                @Override
//                public void onAudioFocusChange(int focusChange) {
//                    if (focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                        mMediaPlayer.pause();
//                        mMediaPlayer.seekTo(0);
//                    }
//                    else if (focusChange==AudioManager.AUDIOFOCUS_GAIN)
//                    {
//                        mMediaPlayer.start();
//                    }
//                    else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
//                        MediaPlayerFacility.releaseMediaPlayer(mMediaPlayer);
//                    }
//                }
//            };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dictionary);
//        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        final ArrayList<Word> words=new ArrayList<Word>();
//        words.add(new Word("one","Miwok lutti",R.drawable.number_one,R.raw.number_one));
//        words.add(new Word("two","Miwok otiiko",R.drawable.number_two,R.raw.number_two));
//        words.add(new Word("three","Miwok tolookosu",R.drawable.number_three,R.raw.number_three));
//        words.add(new Word("four","Miwok oyyisa",R.drawable.number_four,R.raw.number_four));
//        words.add(new Word("five","Miwok massokka",R.drawable.number_five,R.raw.number_five));
//        words.add(new Word("six","Miwok temmokka",R.drawable.number_six,R.raw.number_six));
//        words.add(new Word("seven","Miwok kenekaku",R.drawable.number_seven,R.raw.number_seven));
//        words.add(new Word("eight","Miwok kawinta",R.drawable.number_eight,R.raw.number_eight));
//        words.add(new Word("nine","Miwok wo’e",R.drawable.number_nine,R.raw.number_nine));
//        words.add(new Word("ten","Miwok na’aacha",R.drawable.number_ten,R.raw.number_ten));
//        words.add(new Word("one","Miwok lutti",R.drawable.number_one,R.raw.number_one));
//        words.add(new Word("two","Miwok otiiko",R.drawable.number_two,R.raw.number_two));
//        words.add(new Word("three","Miwok tolookosu",R.drawable.number_three,R.raw.number_three));
//        words.add(new Word("four","Miwok oyyisa",R.drawable.number_four,R.raw.number_four));
//        words.add(new Word("five","Miwok massokka",R.drawable.number_five,R.raw.number_five));
//        words.add(new Word("six","Miwok temmokka",R.drawable.number_six,R.raw.number_six));
//        words.add(new Word("seven","Miwok kenekaku",R.drawable.number_seven,R.raw.number_seven));
//        words.add(new Word("eight","Miwok kawinta",R.drawable.number_eight,R.raw.number_eight));
//        words.add(new Word("nine","Miwok wo’e",R.drawable.number_nine,R.raw.number_nine));
//        words.add(new Word("ten","Miwok na’aacha",R.drawable.number_ten,R.raw.number_ten));
//        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_numbers);
//
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(wordAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MediaPlayerFacility.releaseMediaPlayer(mMediaPlayer,mAudioManager,mOnAudioFocusChangeListener);
//               int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//               if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
//               {
//                   mMediaPlayer= MediaPlayer.create(NumbersActivity.this,words.get(position).getSoundResourceId());
//                   mMediaPlayer.start();
//                   mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                       @Override
//                       public void onCompletion(MediaPlayer mp) {
//                           MediaPlayerFacility.releaseMediaPlayer(mMediaPlayer,mAudioManager,mOnAudioFocusChangeListener);
//                       }
//                   });
//               }
//            }
//        });
//
//
//    }
//    /**
//     * Clean up the media player by releasing its resources.
//     */
//    @Override
//    protected void onStop() {
//        super.onStop();
//        MediaPlayerFacility.releaseMediaPlayer(mMediaPlayer,mAudioManager,mOnAudioFocusChangeListener);
//
//    }
}
