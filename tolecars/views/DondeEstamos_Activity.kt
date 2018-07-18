package com.example.ruben.tolecars.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruben.tolecars.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.themedZoomControls
import org.jetbrains.anko.zoomControls

class DondeEstamos_Activity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donde_estamos)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val concesionario = LatLng(39.8742429, -3.9467855)
        mMap.addMarker(MarkerOptions().position(concesionario).title("Concesionario ToleCars"))
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(concesionario))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(concesionario,12.0f))
      //  mMap.moveCamera(CameraUpdateFactory.zoomBy(12.0f))
    }

}
