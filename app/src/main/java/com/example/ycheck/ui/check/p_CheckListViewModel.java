package com.example.ycheck.ui.check;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class p_CheckListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public p_CheckListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is classList fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}