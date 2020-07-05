package com.example.ycheck.ui.memo;

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

public class MemoFragment extends Fragment {

    private MemoViewModel memoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        memoViewModel =
                ViewModelProviders.of(this).get(MemoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_memo, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        memoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}