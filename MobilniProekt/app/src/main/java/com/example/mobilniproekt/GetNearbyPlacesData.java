package com.example.mobilniproekt;

import android.os.AsyncTask;

import com.example.mobilniproekt.DataParser;
import com.example.mobilniproekt.DownloadUrl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlacesData extends AsyncTask<Object,String,String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        mMap=(GoogleMap) objects[0];
        url=(String) objects[1];

        DownloadUrl downloadUrl=new DownloadUrl();

        googlePlacesData=downloadUrl.readUrl(url);


        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> lista=null;
        DataParser parser =new DataParser();
        lista=parser.parse(s);
        showNearbyPlaces(lista);

    }

    private void showNearbyPlaces(List<HashMap<String,String>> nearbyPlaceList){
        for(int i=0;i<nearbyPlaceList.size();i++){
            MarkerOptions markerOptions=new MarkerOptions();
            HashMap<String,String> mapa=nearbyPlaceList.get(i);

            String placename=mapa.get("place_name");
            String vicinity=mapa.get("vicinity");
            double lat=Double.parseDouble(mapa.get("lat"));
            double lng=Double.parseDouble(mapa.get("lng"));

            LatLng latLng=new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(placename+":"+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            mMap.addMarker(markerOptions);
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }

}
