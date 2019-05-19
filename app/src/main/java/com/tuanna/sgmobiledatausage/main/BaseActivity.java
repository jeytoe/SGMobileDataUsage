package com.tuanna.sgmobiledatausage.main;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.tuanna.sgmobiledatausage.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @LayoutRes
    protected abstract int getLayoutResId();

    @StringRes
    protected abstract int getToolbarTitle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        setupToolbar();
        setupToolbarTitle(getToolbarTitle());
    }

    protected abstract void injectDependency();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateUp();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean enableBackButton() {
        return false;
    }

    protected boolean enableCloseButton() {
        return false;
    }

    protected void navigateUp() {
        NavUtils.navigateUpFromSameTask(this);
    }

    protected void setupToolbarTitle(@StringRes int string) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (string != 0) {
                actionBar.setTitle(string);
            }
        }
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (null != supportActionBar) {
            supportActionBar.setDisplayHomeAsUpEnabled(enableBackButton() || enableCloseButton());
            if (getToolbarTitle() != 0) {
                supportActionBar.setTitle(getString(getToolbarTitle()));
            } else {
                supportActionBar.setTitle(null);
            }
        }
    }

    protected void showSpinner() {

    }

    protected void hideSpinner() {

    }
}

