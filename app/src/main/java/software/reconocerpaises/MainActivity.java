package software.reconocerpaises;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mapa;
    PolylineOptions poligonos;
    LatLng[] puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        Spinner combobox = (Spinner)findViewById(R.id.combobox);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paises, R.layout.activity_main);
        combobox.setAdapter(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.poligonos = new PolylineOptions();
        this.mapa = googleMap;

        this.puntos = new LatLng[]{new LatLng(-1.831239, -78.183406)}; //Ecuador
        this.mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.mapa.getUiSettings().setZoomControlsEnabled(true);

        CameraUpdate camara = CameraUpdateFactory.newLatLngZoom(puntos[0], 6);
        this.mapa.moveCamera(camara);
    }

    public void cambiarLugar(int lugar){
        int zoom = 14;

        CameraPosition posicionCamara = new CameraPosition.Builder()
                .target(this.puntos[lugar])
                .zoom(zoom)
                .bearing(45)
                .build();

        CameraUpdate camara = CameraUpdateFactory.newCameraPosition(posicionCamara);
        this.mapa.animateCamera(camara);
    }
}