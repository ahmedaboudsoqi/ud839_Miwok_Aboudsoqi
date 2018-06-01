package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwolTranslation;
    private int mImageResourceId;
    private boolean mIsImageResourceIdSet;
    private int mSoundResourceId;
    public Word(String defaultTranslation,String miwolTranslation,int imageResourceId ,int soundResourceId){
        mDefaultTranslation=defaultTranslation;
        mMiwolTranslation=miwolTranslation;
        mSoundResourceId=soundResourceId;
        mImageResourceId=imageResourceId;
        mIsImageResourceIdSet=true;
    }
    public Word(String defaultTranslation,String miwolTranslation,int soundResourceId ){
        mDefaultTranslation=defaultTranslation;
        mMiwolTranslation=miwolTranslation;
        mSoundResourceId=soundResourceId;
        mIsImageResourceIdSet=false;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public boolean isImageResourceIdSet() {
        return mIsImageResourceIdSet;
    }

    public String getMiwolTranslation() {
        return mMiwolTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getSoundResourceId() {
        return mSoundResourceId;
    }
}
