package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MediaPlayerFacility {

    static private AudioManager mAudioManager;
    static private ArrayList<Word> mWords;
    static private MediaPlayer mMediaPlayer;
    static private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    public MediaPlayerFacility() {
    }

    static public void releaseMediaPlayer(MediaPlayer mediaPlayer) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    static public void releaseMediaPlayer(MediaPlayer mediaPlayer, AudioManager audioManager, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    static public void createOnItemClickListener(final Context context, MediaPlayer mediaPlayer, AudioManager audioManager, ArrayList<Word> words, AdapterView.OnItemClickListener onItemClickListener,AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener ) {
        mWords = words;
        mMediaPlayer = mediaPlayer;
        mAudioManager = audioManager;
        mOnAudioFocusChangeListener = onAudioFocusChangeListener;
        onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer(mMediaPlayer, mAudioManager, mOnAudioFocusChangeListener);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(context, mWords.get(position).getSoundResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer(mMediaPlayer, mAudioManager, mOnAudioFocusChangeListener);
                        }
                    });
                }
            }
        };


    }

    static public void createOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
        onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    mMediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer(mMediaPlayer);
                }
            }
        };

    }
}




//static void playMiwok( ){}

//    static void createOnItemClickListener1 (AdapterView.OnItemClickListener onItemClickListener , final Context context, ArrayList<Word> words,MediaPlayer mediaPlayer){
//
//        mMediaPlayer=mediaPlayer;
//        mWords=words;
//        onItemClickListener= new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MediaPlayerHelper.releaseMediaPlayer(mMediaPlayer);
//                mMediaPlayer= MediaPlayer.create(context,mWords.get(position).getSoundResourceId());
//                mMediaPlayer.start();
//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        MediaPlayerHelper.releaseMediaPlayer(mMediaPlayer);
//                    }
//                });
//            }
//        };
//
//    }

//static void createOnCompletionListener (){}

