package oswaldoadidas.hotmail.com.googlemaps;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mgoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(googleServicesAvailable()){
            Toast.makeText(this,"Se logró",Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
            iniciarMapa();
        }else{

        }

    }

    private void iniciarMapa() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int estaDisponible = apiAvailability.isGooglePlayServicesAvailable(this);
        if(estaDisponible == ConnectionResult.SUCCESS){
            return true;

        }else if(apiAvailability.isUserResolvableError(estaDisponible)){
            Dialog dialog = apiAvailability.getErrorDialog(this,estaDisponible,0);
            dialog.show();

        }else{
            Toast.makeText(this, "No se puede conectar",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        irLocalizadorZoom(19.390726,-99.1220992,15);
    }

    private void irLocalizador(double latitud, double longitd) {
        LatLng latLng = new LatLng(latitud,longitd);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mgoogleMap.moveCamera(cameraUpdate);
    }

    private void irLocalizadorZoom(double latitud, double longitd,float zoom) {
        LatLng latLng = new LatLng(latitud,longitd);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        mgoogleMap.moveCamera(cameraUpdate);
    }

}
