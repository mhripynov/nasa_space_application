package com.example.spaceapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.spaceapplication.ui.NewsFragment;

public class SpaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpaceViewModel viewModel = new ViewModelProvider(this).get(SpaceViewModel.class);
        viewModel.getUiItems().observe(this, items -> {
            replaceFragment(new NewsFragment(items));
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, fragment)
                .commit();
    }
}
