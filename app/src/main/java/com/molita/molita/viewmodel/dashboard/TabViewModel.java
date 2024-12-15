package com.molita.molita.viewmodel.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabViewModel extends ViewModel {
    MutableLiveData<String> content = new MutableLiveData<>();

    public LiveData<String> getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content.setValue(content);
    }
}
