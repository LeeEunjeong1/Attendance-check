package com.example.ycheck.ui.schedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ycheck.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                ViewModelProviders.of(this).get(ScheduleViewModel.class);
       final View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar progressBar = (ProgressBar)root.findViewById(R.id.spin_kit);
                Sprite wandering = new WanderingCubes();
                progressBar.setIndeterminateDrawable(wandering);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yongin.ac.kr/cmn/wvtex/nibr/academic/academicMain.do")));
            }
        });
        return root;
    }
}