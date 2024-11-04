// MapsActivity.java
package com.example.PonticMap;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ImageView btnPopup;

    private ImageView infoButton;
    private GoogleMap mMap;
    private int[] attractionPhotos = {R.drawable.soymela, R.drawable.agiasofia , R.drawable.frontistirio , R.drawable.mouseio , R.drawable.kastro , R.drawable.tafoi};

    private Map<Marker, String> markerUrls;

    private Marker selectedMarker;

    private List<Marker> allMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoButton = findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMarker != null) {
                    String url = markerUrls.get(selectedMarker);
                    if (url != null && !url.isEmpty()) {
                        vibrateDevice();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                }
            }
        });
        markerUrls = new HashMap<>();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        // Initialize markers list
        allMarkers = new ArrayList<>();
        btnPopup = findViewById(R.id.btn_popup);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomWindow();
                vibrateDevice();
            }
        });

        ImageView  btnPopup = findViewById(R.id.btn_popup);
        Animation circularImageViewAnimation = AnimationUtils.loadAnimation(this , R.anim.scale_anim);
        btnPopup.startAnimation(circularImageViewAnimation);


    }

    private void vibrateDevice() {
        // Get instance of Vibrator service
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Vibrate for 500 milliseconds
        if (vibrator != null) {
            vibrator.vibrate(300);
        }
    }
    private void showCustomWindow() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_window_layout);
        dialog.show();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add markers to the map with different titles and snippets
        LatLng religionLocation = new LatLng(40.6900948, 39.6583668);
        Marker religionMarker = mMap.addMarker(new MarkerOptions()
                .position(religionLocation)
                .title("Μοναστήρι Παναγίας Σουμελά")
                .snippet("Η Ιερά Μονή Παναγίας Σουμελά είναι χριστιανικό μνημείο της Τουρκίας. Λειτουργούσε ως μοναστήρι έως τη Μικρασιατική Καταστροφή του 1922.\n" +
                        "\n" +
                        "Είναι χτισμένη μέσα σε σπηλιά σε απόκρημνη πλαγιά του όρους Μελά, στην ενδοχώρα της Τραπεζούντας, από όπου έχει πάρει και το όνομά της. Παναγία \"εις του Μελά\", \"σου Μελά\" στην Ποντιακή διάλεκτο."));
        ((Marker)religionMarker).setTag("Religion");
        markerUrls.put(religionMarker, "https://muze.gov.tr/muze-detay?SectionId=SML01&DistId=MRK");
        religionMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pray_small));



        LatLng religionLocation1 = new LatLng(41.0030919 , 39.6961084);
        Marker religionMarker1 = mMap.addMarker(new MarkerOptions()
                .position(religionLocation1)
                .title("Αγία Σοφία (Τραπεζούντα)")
                .snippet("Η Αγία Σοφία είναι μια πρώην ορθόδοξη εκκλησία στην Τραπεζούντα, στο βορειοανατολικό τμήμα της Τουρκίας. Μετατράπηκε σε τζαμί το 1584, σε μουσείο το 1964 και πάλι σε τζαμί το 2013. Χρονολογείται στον δέκατο τρίτο αιώνα, όταν η Τραπεζούντα ήταν πρωτεύουσα της αυτοκρατορίας της Τραπεζούντας\n"+
                        "\nΒρίσκεται κοντά στην ακτή, τρία χιλιόμετρα δυτικά των ορίων της μεσαιωνικής πόλης. Είναι ένα από τα δεκάδες βυζαντινά αξιοθέατα που υπάρχουν στην περιοχή. Έχει περιγραφεί ως «ένα από τα καλύτερα δείγματα βυζαντινής αρχιτεκτονικής»."));
        ((Marker) religionMarker1).setTag("Religion");
        markerUrls.put(religionMarker1, "https://karadeniz.gov.tr/trabzon-ayasofya-muzesi/");
        religionMarker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pray_small));


        LatLng educationLocation = new LatLng(41.0078101 , 39.7287159);
        Marker educationMarker = mMap.addMarker(new MarkerOptions()
                .position(educationLocation)
                .title("Φροντιστήριο Τραπεζούντας")
                .snippet("Το Ελληνικόν Φροντιστήριον Τραπεζούντος ήταν σχολείο της ελληνικής παροικίας της Τραπεζούντας. Στο σχολείο αυτό η χρήση της ποντιακής διαλέκτου ήταν απαγορευμένη.\n"+
                        "Το σχολείο άρχισε να λειτουργεί τον 17ο αιώνα, δεν ήταν όμως αρχικά σε υψηλή εκτίμηση, πλην όμως ήταν το μοναδικό σε όλη τη περιοχή. Με τον καιρό όμως καθιερώθηκε και άρχισε η αναγνώρισή του, ενώ πήρε την επωνυμία ως Φροντιστήριο. Στην καθιέρωσή του συνετέλεσαν μεταξύ άλλων οι ηγεμόνες της Μολδοβλαχίας Μουρούζες και Υψηλάντες, που στις αρχές του 18ου αιώνα άρχισαν να το επιχορηγούν οικονομικά, με σκοπό τη γενικότερη ανάδειξη και βελτίωσή του. Το όνομα Φροντιστήριο φέρεται να το έλαβε περί το 1682."));
        ((Marker) educationMarker).setTag("Education");
        markerUrls.put(educationMarker, "https://kanunianadolu.meb.k12.tr/");
        educationMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.learning_small));


        LatLng educationLocation1 = new LatLng(41.004966735839844 , 39.721168518066406);
        Marker educationMarker1 = mMap.addMarker(new MarkerOptions()
                .position(educationLocation1)
                .title("Μουσείο της Τραπεζούντας")
                .snippet("Το Μουσείο της Τραπεζούντας, γνωστό και ως Μέγαρο Κωστάκη (Kostaki Konağı), είναι ιστορικό σπίτι μουσείο με αρχαιολογικές και εθνογραφικές εκθέσεις, που βρίσκεται στην Τραπεζούντα της Τουρκίας\n" +
                        "\nΤο αρχοντικό χτίστηκε στις αρχές του 1900 ως ιδιωτική κατοικία του Κωστάκη Θεοφύλακτου, διακεκριμένου τραπεζίτη ελληνικής καταγωγής. Είναι γνωστό ότι ο αρχιτέκτονας ήταν ιταλικής καταγωγής και πολλά υλικά που χρησιμοποιήθηκαν στο κτήριο μεταφέρθηκαν από την Ιταλία. Ωστόσο, το όνομα του αρχιτέκτονα είναι άγνωστο."));
        ((Marker) educationMarker1).setTag("Education");
        educationMarker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.learning_small));
        markerUrls.put(educationMarker1, "https://www.google.com/travel/hotels/entity/ChYIos_m7-iB9vFIGgovbS8wNDdwcHEyEAQ?utm_campaign=sharing&utm_medium=link&utm_source=htls&ved=0CAAQ5JsGahcKEwiQztnwmK-GAxUAAAAAHQAAAAAQBQ&ts=CAEaBAoCGgAqBAoAGgA");


        LatLng archaeologicalLocation = new LatLng(42.0246097 , 35.1424343);
        Marker archaeologicalMarker = mMap.addMarker(new MarkerOptions()
                .position(archaeologicalLocation)
                .title("Το κάστρο της Σινώπης")
                .snippet("Η Σινώπη είναι η πρώτη αποικία που ίδρυσαν οι Μιλήσιοι, κατά τον εποικιστικό τους πλου στα νερά του  Ευξείνου Πόντου, τον 8ο αιώνα π. Χ.. Σύμφωνα με όσα αναφέρουν οι αρχαίοι ιστορικοί, οι Μιλήσιοι ίδρυσαν τη Σινώπη, αφού ήρθαν σε συμφωνία με τους γηγενείς κατοίκους της. Αφού, λοιπόν, οργανώθηκε σε ελληνική πολιτεία, αποτέλεσε η ίδια τη Μητρόπολη των άλλων ελληνικών πόλεων που ιδρύθηκαν στα παράλια του Ευξείνου Πόντου, όπως Τραπεζούς, Κερασούς, Κοτύωρα, Αμισός, Οινόη, Φαδισάνη (Φάτσα), Αθήνα, Βαθύς Λιμήν."));
        ((Marker) archaeologicalMarker ).setTag("Archaeological Sites");
        archaeologicalMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.archeology_small));
        markerUrls.put(archaeologicalMarker, "https://www.tripadvisor.com/Attraction_Review-g652366-d8785312-Reviews-Sinop_Castle-Sinop_Sinop_Province_Turkish_Black_Sea_Coast.html");


        LatLng archaeologicalLocation1 = new LatLng(40.653333 , 35.830278);
        Marker archaeologicalMarker1 = mMap.addMarker(new MarkerOptions()
                .position(archaeologicalLocation1)
                .title("Βασιλικοί τάφοι του Πόντου")
                .snippet("Το όρος Harşena και οι τάφοι στους βράχους, των Ποντίων βασιλέων, στη βόρεια επαρχία Αμάσεια της Τουρκίας, έχουν κερδίσει το δικαίωμα να προστεθούν από τον Εκπαιδευτικό, Επιστημονικό και Πολιτιστικό Οργανισμό (UNESCO) των Ηνωμένων Εθνών, στον κατάλογο της Παγκόσμιας Πολιτιστικής Κληρονομιάς.\n" +
                        "\nΟι τάφοι στους βράχους των ηγετών του βασιλείου του Πόντου (3ος αιώνας π.Χ. – 1ος αιώνας μ.Χ.) είναι από τους μεγαλύτερους τάφους σε βράχο στην Ανατολία και μερικά από τα καλύτερα παραδείγματα βασιλικών τάφων στον κόσμο."));
        ((Marker) archaeologicalMarker1 ).setTag("Archaeological Sites");
        archaeologicalMarker1.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.archeology_small));
        markerUrls.put(archaeologicalMarker1, "https://www.kulturportali.gov.tr/turkiye/amasya/gezilecekyer/amasya-kales");

        Animation circularImageViewAnimation = AnimationUtils.loadAnimation(this , R.anim.scale_anim);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                selectedMarker = marker;
                infoButton.startAnimation(circularImageViewAnimation);
                infoButton.setVisibility(View.VISIBLE);
                return false; // Returning false to keep default behavior (showing info window)
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                infoButton.setVisibility(View.GONE);
            }
        });

        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {

                infoButton.setVisibility(View.GONE);
            }
        });

        allMarkers.add(religionMarker);
        allMarkers.add(religionMarker1);
        allMarkers.add(educationMarker);
        allMarkers.add(educationMarker1);
        allMarkers.add(archaeologicalMarker);
        allMarkers.add(archaeologicalMarker1);

        // Move camera to show all markers
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(religionLocation, 10));
        mMap.setInfoWindowAdapter(new InfoWindowAdapter(this, attractionPhotos));

    }

    // Method to filter markers based on category
    private void filterMarkers(String category) {
        for (Marker marker : allMarkers) {
            if (marker.getTag().equals(category)) {
                marker.setVisible(true); // Show markers of the selected category
            } else {
                marker.setVisible(false); // Hide markers of other categories
            }
        }
    }

    public void onFilterSelected(View view) {
        int id = view.getId();
        if (id == R.id.filter_all) {
            showAllMarkers();
            vibrateDevice();
        } else if (id == R.id.filter_education) {
            filterMarkers("Education");
            vibrateDevice();
        } else if (id == R.id.filter_religion) {
            filterMarkers("Religion");
            vibrateDevice();
        } else if (id == R.id.filter_archaeological_sites) {
            filterMarkers("Archaeological Sites");
            vibrateDevice();
        }
    }
    // Method to show all markers
    private void showAllMarkers() {
        for (Marker marker : allMarkers) {
            marker.setVisible(true); // Show all markers
        }
    }

}


