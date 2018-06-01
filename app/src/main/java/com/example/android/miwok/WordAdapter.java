package com.example.android.miwok;

import android.app.LauncherActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private static final String LOG_TAG=WordAdapter.class.getSimpleName();
    private int mCategoryColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words,int categoryColorResourceId)
    {
        super(context,0,words);
        mCategoryColorResourceId=categoryColorResourceId;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View wordsListItemView= convertView;
//        return super.getView(position, convertView, parent);
//        View listItemView = convertView;
        if(wordsListItemView == null) {
            wordsListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default translation
        TextView defaultTextView = (TextView) wordsListItemView.findViewById(R.id.default_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the  TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokTextView = (TextView) wordsListItemView.findViewById(R.id.miwok_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        miwokTextView.setText(currentWord.getMiwolTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) wordsListItemView.findViewById(R.id.icon_image_view);

        if (currentWord.isImageResourceIdSet())
        {
            iconView = (ImageView) wordsListItemView.findViewById(R.id.icon_image_view);
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceId());
        }
        else
        {
         iconView.setVisibility(View.GONE);
        }
        // define a final variable to access it from inner class
        // translate the color resource id into a color value
        int color = ContextCompat.getColor(getContext(),mCategoryColorResourceId);//(getContext(),mCategoryColorResourceId);
        // assign the value of the color to the textboxes container (the linear layout)
        wordsListItemView.findViewById(R.id.text_boxes_linear_layout).setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return wordsListItemView;
    }
}
