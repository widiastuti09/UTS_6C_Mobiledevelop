package com.diyas.ecommerceuts.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.diyas.ecommerceuts.R
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_maps)

        //pakai supportMapFragment
        val maps: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment

        maps.getMapAsync(this)

    }

    override fun onMapReady(maps: GoogleMap) {
        val lok = LatLng(-6.259412, 106.878688)
        val update = CameraUpdateFactory.newLatLng(lok)
        val zoom = CameraUpdateFactory.zoomTo(17f)
        maps.moveCamera(update)
        maps.animateCamera(zoom)
        maps.addMarker(
            MarkerOptions()
                .position(lok)
                .title("Toko Sepatu").snippet("Alamatnya berada disini"))

    }
}
