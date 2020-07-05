package com.example.ycheck.ui.classList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClassListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is classList fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}