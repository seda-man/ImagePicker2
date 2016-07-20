package com.example.seda.scrolling;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by seda on 7/20/16.
 */
public class CustomScrollingView extends HorizontalScrollView {
    private Context context;
    private int selectedIndex = 0;
    private TextView previewText;
    private ArrayList<String> contents;

    public CustomScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setSmoothScrollingEnabled(true);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }

    public void setPreviewText(TextView previewText) {
        this.previewText = previewText;
    }

    public void setContents(final ArrayList<String> contents) {
        if (getChildCount() == 0 || contents.size() == 0)
            return;

        this.contents = contents;
        ViewGroup parent = (ViewGroup) getChildAt(0);

        parent.removeAllViews();

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < contents.size(); i++) {
            TextView scrollViewText = (TextView) layoutInflater.inflate(R.layout.scroll_view_text, null);
            scrollViewText.setText(contents.get(i));
            final int index = i;
            scrollViewText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCenter(index);
                    selectedIndex = index;

                }
            });
            parent.addView(scrollViewText);
        }
        setCenter(0);
        previewText.setText(contents.get(0));
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setCenter(int selectedIndex) {
        ViewGroup parent = (ViewGroup) getChildAt(0);
        View preView = parent.getChildAt(this.selectedIndex);
        android.widget.LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        preView.setLayoutParams(lp);
        preView.setBackgroundColor(Color.BLACK);
        View view = parent.getChildAt(selectedIndex);
        view.setBackgroundColor(Color.RED);

        int screenWidth = ((Activity) context).getWindowManager()
                .getDefaultDisplay().getWidth();

        int scrollX = (view.getLeft() - (screenWidth / 2))
                + (view.getWidth() / 2);
        this.smoothScrollTo(scrollX, 0);
        this.selectedIndex = selectedIndex;
        previewText.setText(contents.get(selectedIndex));
    }


}
