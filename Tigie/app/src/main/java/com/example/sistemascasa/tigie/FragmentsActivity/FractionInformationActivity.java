package com.example.sistemascasa.tigie.FragmentsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.FractionActivity;
import com.example.sistemascasa.tigie.LinkerActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SearchFractionWords;
import com.example.sistemascasa.tigie.SubheadingsActivity;
import com.example.sistemascasa.tigie.adapter.PageAdapter;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.util.ArrayList;

public class FractionInformationActivity extends AppCompatActivity implements Comunicador {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private ViewPager viewPager;
    Comunicador comunicador;
    Context contexto;
    private ConstructorData constructorData;
    private Chapters chapters;
    private Headings headings;
    private Subheadings subheadings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction_information);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros   = getIntent().getExtras();
        String fractionCode = parametros.getString("fractionCode");

        toolbar             = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);
        viewPager           = (ViewPager) findViewById(R.id.viewPager);

        TextView fraccion = (TextView) findViewById(R.id.tvFraccion);
        fraccion.setText(fractionCode);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        setupViewPager();

    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new AnnexFragment());
        fragments.add(new CompensatoryFragment());
        fragments.add(new BorderStripFragment());
        return fragments;
    }

    private void setupViewPager () {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_world);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_barco);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_world);
    }

    @Override
    public void responder(String codigo) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);
        getMenuInflater().inflate(R.menu.menu_subheadings, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        getMenuInflater().inflate(R.menu.menu_star, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Boolean success;

        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();

                return true;

            case R.id.mChapters:
                Intent intent_chapters = new Intent(this, LinkerActivity.class);
                startActivity(intent_chapters);
                break;

            case R.id.mHeadings:
                Intent intent_close = new Intent(FractionInformationActivity.this, SubheadingsActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(FractionInformationActivity.this, FractionActivity.class);
                intent_sh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_sh.putExtra("CLOSE", true);
                startActivity(intent_sh);
                this.finish();
                break;

            case R.id.mShowFavourites:
                Intent intent_showfav = new Intent(this, ActivityFraccionFavoritos.class);
                startActivity(intent_showfav);
                break;

            case R.id.mFavourite:
                Bundle parametros   = getIntent().getExtras();
                String fractionCode = parametros.getString("fractionCode");

                System.out.println(fractionCode);
                constructorData = new ConstructorData(getApplicationContext());
                success = constructorData.insertFavouites(fractionCode);

                if(success) {
                    Toast.makeText(getApplicationContext(), "La fracción "+  fractionCode + " fue agregada ", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getFractions(String fractionCode, Context contexto) {
        Intent intent = new Intent(this, FractionInformationActivity.class);
        intent.putExtra("fractionCode", fractionCode);
        startActivity(intent);
    }

    public void getFractionWords(String words, Context contexto) {

        constructorData = new ConstructorData(getApplicationContext());

        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);

        startActivity(intent);
    }
}
