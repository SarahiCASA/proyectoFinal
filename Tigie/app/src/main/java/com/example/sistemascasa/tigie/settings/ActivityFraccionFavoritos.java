package com.example.sistemascasa.tigie.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.FractionAdapter;
import com.example.sistemascasa.tigie.adapter.FractionFavAdapater;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fractions;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fractions_Fav;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentFractionFavPre;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentFractionPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentFractionFavPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentFractionPre;

import java.util.ArrayList;

public class ActivityFraccionFavoritos extends AppCompatActivity implements IRecyclerview_Fractions_Fav {

    private RecyclerView listFavFractions;
    public static ActivityFraccionFavoritos instance = null;
    private IRecyclerViewFragmentFractionFavPre presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraccion_favoritos);


        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listFavFractions = (RecyclerView) findViewById(R.id.rvFraccionFavourites);
        presenter    = new RecyclerViewFragmentFractionFavPre(this,  getApplicationContext());
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listFavFractions.setLayoutManager(llm);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public FractionFavAdapater crearAdaptador(ArrayList<Fractions> fractionses) {
        FractionFavAdapater adaptader = new FractionFavAdapater(fractionses, this);
        return adaptader;
    }

    @Override
    public void inicializarAdaptadorRV(FractionFavAdapater adapter) {
        listFavFractions.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        instance = this;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        instance = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        instance = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}
