package com.example.sistemascasa.tigie;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.adapter.SubHeadingAdapter;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_SubHeading;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentSubheadingPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentSubheadingPre;
import com.example.sistemascasa.tigie.settings.ActivityAbout;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.util.ArrayList;

public class SubheadingsActivity extends AppCompatActivity implements IRecyclerview_SubHeading{
    private ArrayList<Subheadings> subheadingses;
    private RecyclerView listSubheadings;
    private IRecyclerViewFragmentSubheadingPre presenter;
    private ConstructorData constructorData;
    Context contexto;
    private Chapters chapters;
    private Headings headings;
    private Subheadings subheadings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subheadings);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        contexto = getApplicationContext();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getBooleanExtra("CLOSE", false)) {
            finish();
        }

        Bundle parametrosHeading   = getIntent().getExtras();
        String idHeading           = parametrosHeading.getString(getResources().getString(R.string.idTariffHeading));
        String codeHeading         = parametrosHeading.getString(getResources().getString(R.string.tariffHeadingCode));
        String descriptionHeading  = parametrosHeading.getString(getResources().getString(R.string.tariffHeadingDescription));
        int idHeadingInt           = parametrosHeading.getInt("idHeading");

        TextView idTariffheading = (TextView) findViewById(R.id.idTariffheading);
        TextView tariffSubheadingCode = (TextView) findViewById(R.id.tariffSubheadingCode);
        TextView tariffSuheadingDescription = (TextView) findViewById(R.id.tariffSuheadingDescription);

        idTariffheading.setText(idHeading);
        tariffSubheadingCode.setText(codeHeading);
        tariffSuheadingDescription.setText(descriptionHeading);

        listSubheadings = (RecyclerView) findViewById(R.id.rvSubheadings);
        presenter = new RecyclerViewFragmentSubheadingPre(this,  getApplicationContext(), idHeadingInt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);


        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            LinkerActivity mainActivity = new LinkerActivity();
            @Override
            public boolean onQueryTextSubmit(String query) {

                String text = "";
                if (Comunes.isNumeric(query)) {

                    switch (query.length()) {
                        case 2:

                            chapters = mainActivity.getChapters(query, contexto);
                            if(chapters != null) {
                                Intent intent_head = new Intent(SubheadingsActivity.this, HeadingsActivity.class);
                                intent_head.putExtra("iconChapter", chapters.getIcono());
                                intent_head.putExtra("idTariffChapter", String.valueOf(chapters.getId()));
                                intent_head.putExtra("tariffChapterCode", chapters.getCodigo());
                                intent_head.putExtra("tariffChapterDescription", chapters.getDescripcion());
                                intent_head.putExtra("idChapter", chapters.getId());
                                startActivity(intent_head);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(SubheadingsActivity.this, text2, Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case 4:
                            headings = mainActivity.getHeadings(query, contexto);

                            if(headings != null) {

                                Intent intentSubheading = new Intent(SubheadingsActivity.this, SubheadingsActivity.class);
                                intentSubheading.putExtra("iconChapter", "2130837583");
                                intentSubheading.putExtra("idTariffHeading", String.valueOf(headings.getIdTariffHeading()));
                                intentSubheading.putExtra("tariffHeadingCode", headings.getTariffHeadingCode());
                                intentSubheading.putExtra("tariffHeadingDescription", headings.getTariffHeadingDescription());
                                intentSubheading.putExtra("idHeading", headings.getIdTariffHeading());
                                startActivity(intentSubheading);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(SubheadingsActivity.this, text2, Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case 6:
                            subheadings = mainActivity.getSubheadings(query, contexto);

                            if(subheadings != null) {
                                Intent intentFraction = new Intent(SubheadingsActivity.this, FractionActivity.class);
                                intentFraction.putExtra("iconChapter", "2130837583");
                                intentFraction.putExtra("idTariffSubheading", String.valueOf(subheadings.getIdTariffSubheading()));
                                intentFraction.putExtra("tariffSubheadingCode", subheadings.getTariffSubheadingCode());
                                intentFraction.putExtra("tariffSubheadingDescription", subheadings.getTariffSubheadingDescription());
                                intentFraction.putExtra("idSubheading", subheadings.getIdTariffSubheading());

                                startActivity(intentFraction);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(SubheadingsActivity.this, text2, Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case 8:
                            getFractions(query, contexto);
                            break;
                    }

                } else {
                    getFractionWords(query, contexto);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.mAbout:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                startActivity(intent_about);
                break;

            case R.id.mChapters:
                Intent intent_chapters = new Intent(this, LinkerActivity.class);
                startActivity(intent_chapters);
                break;

            case R.id.mExit:

                Intent intent_exit = new Intent(SubheadingsActivity.this, LinkerActivity.class);
                intent_exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_exit.putExtra("EXIT", true);
                startActivity(intent_exit);
                this.finish();
                break;

            case R.id.mShowFavourites:
                Intent intent_showfav = new Intent(this, ActivityFraccionFavoritos.class);
                startActivity(intent_showfav);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listSubheadings.setLayoutManager(llm);
    }

    @Override
    public SubHeadingAdapter crearAdaptador(ArrayList<Subheadings> subheadingses) {
        SubHeadingAdapter adaptader = new SubHeadingAdapter(subheadingses, this);
        return adaptader;
    }

    @Override
    public void inicializarAdaptadorRV(SubHeadingAdapter adapter) {
        listSubheadings.setAdapter(adapter);
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
