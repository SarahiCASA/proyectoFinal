package com.example.sistemascasa.tigie;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.adapter.ChapterAdapter;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fragment;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentChapterPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentChapterPre;
import com.example.sistemascasa.tigie.settings.ActivityAbout;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.util.ArrayList;

public class LinkerActivity extends AppCompatActivity  implements IRecyclerview_Fragment {

    private Context contexto;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView listChapters;
    private IRecyclerViewFragmentChapterPre presenter;
    private RecyclerView listHeadings;
    private ConstructorData constructorData;
    private Chapters chapters;
    private Headings headings;
    private Subheadings subheadings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

         contexto = getApplicationContext();

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        if (toolbar != null)
            setSupportActionBar(miActionBar);

        if (getIntent().getBooleanExtra("CLOSE", false)) {
            finish();
        }

        listChapters    = (RecyclerView) findViewById(R.id.rvChapters);
        presenter       = new RecyclerViewFragmentChapterPre(this, getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        inflater.inflate(R.menu.menu_favourites, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                String text = "";
                if (Comunes.isNumeric(query)) {

                    switch (query.length()) {
                        case 2:
                            chapters = getChapters(query, contexto);

                            if(chapters != null){
                                Intent intent_head = new Intent(LinkerActivity.this, HeadingsActivity.class);
                                intent_head.putExtra("iconChapter", chapters.getIcono());
                                intent_head.putExtra("idTariffChapter", String.valueOf(chapters.getId()));
                                intent_head.putExtra("tariffChapterCode", chapters.getCodigo());
                                intent_head.putExtra("tariffChapterDescription", chapters.getDescripcion());
                                intent_head.putExtra("idChapter", chapters.getId());
                                startActivity(intent_head);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(LinkerActivity.this, text2, Toast.LENGTH_SHORT).show();
                            }

                            break;

                        case 4:
                            headings = getHeadings(query, contexto);

                            if(headings != null) {
                                Intent intentSubheading = new Intent(LinkerActivity.this, SubheadingsActivity.class);
                                intentSubheading.putExtra("iconChapter", "2130837583");
                                intentSubheading.putExtra("idTariffHeading", String.valueOf(headings.getIdTariffHeading()));
                                intentSubheading.putExtra("tariffHeadingCode", headings.getTariffHeadingCode());
                                intentSubheading.putExtra("tariffHeadingDescription", headings.getTariffHeadingDescription());
                                intentSubheading.putExtra("idHeading", headings.getIdTariffHeading());
                                startActivity(intentSubheading);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(LinkerActivity.this, text2, Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case 6:
                            subheadings = getSubheadings(query, contexto);

                            if(subheadings != null) {
                                Intent intentFraction = new Intent(LinkerActivity.this, FractionActivity.class);
                                intentFraction.putExtra("iconChapter", "2130837583");
                                intentFraction.putExtra("idTariffSubheading", String.valueOf(subheadings.getIdTariffSubheading()));
                                intentFraction.putExtra("tariffSubheadingCode", subheadings.getTariffSubheadingCode());
                                intentFraction.putExtra("tariffSubheadingDescription", subheadings.getTariffSubheadingDescription());
                                intentFraction.putExtra("idSubheading", subheadings.getIdTariffSubheading());

                                startActivity(intentFraction);
                            } else {
                                String text2 = "ERROR! La Búsqueda fue incorrecta.";
                                Toast.makeText(LinkerActivity.this, text2, Toast.LENGTH_SHORT).show();
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
                return true;
            }
        });

        return true;
    }

    public Chapters getChapters(String chapterCode, Context contexto) {
        constructorData = new ConstructorData(contexto);
        chapters = constructorData.getChaptersInf(chapterCode);

        if (chapters.getId() != 0)
            return chapters;
        else
            return null;
    }

    public Headings getHeadings(String headingCode, Context contexto) {
        constructorData = new ConstructorData(contexto);
        headings = constructorData.getHeadingsInf(headingCode);

        if (headings.getIdTariffHeading() != 0)
            return headings;
        else
            return null;

    }

    public Subheadings getSubheadings(String subheadingCode, Context contexto) {
        constructorData = new ConstructorData(contexto);
        subheadings = constructorData.getSubheadingsInf(subheadingCode);

        if (subheadings.getIdTariffSubheading() != 0)
            return subheadings;
        else
            return null;
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_search:
                return true;

            case R.id.mAbout:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                startActivity(intent_about);
                break;

            case R.id.mExit:
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
    /*
    ** Se comporte como un Layout
    ** Seteamos la lista en un formato de lista vertical
     */
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listChapters.setLayoutManager(llm);

    }

    @Override
    public ChapterAdapter crearAdaptador(ArrayList<Chapters> chapters) {
        ChapterAdapter adapter = new ChapterAdapter(chapters, this);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(ChapterAdapter adapter) {
        listChapters.setAdapter(adapter);

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        listChapters.setLayoutManager(glm);
    }

}
