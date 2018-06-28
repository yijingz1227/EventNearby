package com.yijingz.eventnearby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private List<Event> eventData;

    public EventAdapter(Context context) {
        this.context = context;
        eventData = DataService.getEventData();
    }

    @Override
    public int getCount() {
        return eventData.size();
    }

    @Override
    public Event getItem(int pos) {
        return eventData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_item,
                    parent, false);
        }

        TextView eventTitle =  convertView.findViewById(R.id.event_title);
        TextView eventAddress =  convertView.findViewById(R.id.event_address);
        TextView eventDescription =  convertView.findViewById(R.id.event_description);
        ImageView eventThumbnail = convertView.findViewById(R.id.event_thumbnail);

        Event e = getItem(pos);

        eventTitle.setText(e.getTitle());
        eventAddress.setText(e.getAddress());
        eventDescription.setText(e.getDescription());
        Picasso.get().load(e.getUrl()).into(eventThumbnail);

        return convertView;
    }
}
