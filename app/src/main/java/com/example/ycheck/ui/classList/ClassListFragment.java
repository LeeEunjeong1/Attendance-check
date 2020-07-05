package com.example.ycheck.ui.classList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ycheck.R;

public class ClassListFragment extends Fragment {

    private ClassListViewModel classListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        classListViewModel =
                ViewModelProviders.of(this).get(ClassListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_class_list, container, false);

        return root;
    }
}