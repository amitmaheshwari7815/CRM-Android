package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.woxthebox.draglistview.sample.app.AppController;

import java.util.List;

/**
 * Created by amit on 30/4/18.
 */

public class OppTimelineAdapter extends RecyclerView.Adapter<OppTimelineAdapter.MyHolder>{

    Context context;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public OppTimelineAdapter(Context context, List<FeedItem> feedItems){
        this.context = context;
        this.feedItems = feedItems;
    }

    @NonNull
    @Override
    public OppTimelineAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
        View v = inflater.inflate(R.layout.opp_timeline_adapter, parent, false);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        OppTimelineAdapter.MyHolder myHolder = new OppTimelineAdapter.MyHolder(v);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OppTimelineAdapter.MyHolder holder, int position) {
//        holder.textView.setText(note[position]);
//        holder.imageView.setImageResource(img[position]);
        FeedItem item = feedItems.get(position);

        holder.name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            holder.statusMsg.setText(item.getStatus());
            holder.statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            holder.url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
            holder.url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            holder.url.setVisibility(View.GONE);
        }

        // user profile pic
        holder.profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImge() != null) {
            holder.feedImageView.setImageUrl(item.getImge(), imageLoader);
            holder.feedImageView.setVisibility(View.VISIBLE);
            holder.feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            holder.feedImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, timestamp,statusMsg, url;
        NetworkImageView profilePic;
        FeedImageView feedImageView;
        public MyHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.opp_name);
            timestamp = (TextView) itemView
                    .findViewById(R.id.opp_timestamp);
            statusMsg = (TextView) itemView
                    .findViewById(R.id.opp_txtStatusMsg);
            url = (TextView) itemView.findViewById(R.id.opp_txtUrl);
            profilePic = (NetworkImageView) itemView
                    .findViewById(R.id.opp_profilePic);
            feedImageView = (FeedImageView) itemView
                    .findViewById(R.id.opp_feedImage1);
        }
    }
}
