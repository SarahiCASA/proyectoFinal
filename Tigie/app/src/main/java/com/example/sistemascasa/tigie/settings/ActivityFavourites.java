package com.example.sistemascasa.tigie.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sistemascasa.tigie.R;


public class ActivityFavourites extends FragmentActivity {

        private Fragment contentFragment;
        FavListFragment pdtListFragment;
        FavoriteListFragment  favListFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_favourite);

            FragmentManager fragmentManager = getSupportFragmentManager();

            if (savedInstanceState != null) {
                if (savedInstanceState.containsKey("content")) {
                    String content = savedInstanceState.getString("content");
                    if (content.equals(FavoriteListFragment.ARG_ITEM_ID)) {
                        if (fragmentManager.findFragmentByTag(FavoriteListFragment.ARG_ITEM_ID) != null) {
                            setFragmentTitle(R.string.favorites);
                            contentFragment = fragmentManager
                                    .findFragmentByTag(FavoriteListFragment.ARG_ITEM_ID);
                        }
                    }
                }
                if (fragmentManager.findFragmentByTag(FavListFragment.ARG_ITEM_ID) != null) {
                    pdtListFragment = (FavListFragment) fragmentManager.findFragmentByTag(FavListFragment.ARG_ITEM_ID);
                    contentFragment = pdtListFragment;
                }
            } else {
                pdtListFragment = new FavListFragment();
                setFragmentTitle(R.string.app_name);
                switchContent(pdtListFragment, FavListFragment.ARG_ITEM_ID);
            }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            if (contentFragment instanceof FavoriteListFragment) {
                outState.putString("content", FavoriteListFragment.ARG_ITEM_ID);
            } else {
                outState.putString("content", FavListFragment.ARG_ITEM_ID);
            }
            super.onSaveInstanceState(outState);
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_favourites, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mFavourite:
                    setFragmentTitle(R.string.favorites);
                    favListFragment = new FavoriteListFragment();
                    switchContent(favListFragment, FavoriteListFragment.ARG_ITEM_ID);

                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.popBackStackImmediate());

        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);

            if (!(fragment instanceof FavListFragment)) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
            contentFragment = fragment;
        }
    }

    protected void setFragmentTitle(int resourseId) {
        setTitle(resourseId);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else if (contentFragment instanceof FavListFragment  || fm.getBackStackEntryCount() == 0) {
            finish();
        }
    }
}




