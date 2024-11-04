package com.example.PonticMap;// CustomInfoWindowAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;
    private int[] attractionPhotos; // Array to store attraction photos
    public InfoWindowAdapter(Context context, int[] photos) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.info_window_layout, null);
        attractionPhotos = photos;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null; // Return null to use default info window
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker, mWindow);
        return mWindow;
    }

    private void render(Marker marker, View view) {
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);

        // Set title and description
        titleTextView.setText(marker.getTitle());
        descriptionTextView.setText(marker.getSnippet());

        // Load photo based on marker's position
        int position = getPositionForMarker(marker);
        if (position != -1 && position < attractionPhotos.length) {
            imageView.setImageResource(attractionPhotos[position]);
        } else {
            imageView.setImageResource(R.drawable.soymela); // Use default photo if position is invalid
        }


    }
    // Method to get position of marker
    private int getPositionForMarker(Marker marker) {
        // Add your logic to determine position based on marker title, snippet, or other criteria
        // For example, if marker title corresponds to index in attractionPhotos array
        if (marker.getTitle().equals("Μοναστήρι Παναγίας Σουμελά")) {
            return 0;
        } else if (marker.getTitle().equals("Αγία Σοφία (Τραπεζούντα)")) {
            return 1;
        } else if (marker.getTitle().equals("Φροντιστήριο Τραπεζούντας")) {
            return 2;
        } else if (marker.getTitle().equals("Μουσείο της Τραπεζούντας")) {
            return 3;
        } else if (marker.getTitle().equals("Το κάστρο της Σινώπης")) {
            return 4;
        } else if (marker.getTitle().equals("Βασιλικοί τάφοι του Πόντου")) {
            return 5;
        }
        // Add more conditions as needed
        return -1; // Default position if marker title does not match any condition
    }
}
