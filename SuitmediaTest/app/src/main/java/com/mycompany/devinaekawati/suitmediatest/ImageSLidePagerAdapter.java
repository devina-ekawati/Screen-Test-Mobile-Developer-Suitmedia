package com.mycompany.devinaekawati.suitmediatest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Devina Ekawati on 12/30/2016.
 */

public class ImageSlidePagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    int[] mResources = {
            R.drawable.first,
            R.drawable.second
    };
    private int pos = 0;

    public ImageSlidePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getPosition() {
        return pos;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_slide, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.event_image_view);
        imageView.setImageResource(mResources[pos]);

        TextView eventName = (TextView) itemView.findViewById(R.id.event_name);
        eventName.setText("Event-" + (pos + 1));

        container.addView(itemView);

        if (pos >= mResources.length - 1)
            pos = 0;
        else
            ++pos;

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
