package com.example.spaceapplication;

import android.app.Application;
import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.api.Data;
import com.example.api.NasaApi;
import com.example.spaceapplication.ui.Item;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SpaceViewModel extends AndroidViewModel {

    private MutableLiveData<List<Item>> uiItems = new MutableLiveData<>();
    private NasaApi nasaApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Application application;

    public SpaceViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        nasaApi = ((SpaceApplication) application).getNasaApi();
        getData();
    }

    private void getData() {
        compositeDisposable.add(nasaApi.getInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((data, throwable) -> {
                    uiItems.postValue(parsedDataToUiItem(data));
                }));
    }

    public MutableLiveData<List<Item>> getUiItems() {
        return uiItems;
    }

    private List<Item> parsedDataToUiItem(List<Data> parsedData) {
        List<Item> items = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(parsedData.size());
        new Thread(() -> {
            for (Data data : parsedData) {
                ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(application));
                ImageLoader.getInstance().loadImage(data.getImageUrl(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        items.add(new Item(String.format("%s #%s", "Earth", parsedData.indexOf(data) + 1), data.getCaption(), ImageSource.cachedBitmap(loadedImage), data.getDate()));
                        latch.countDown();
                    }
                });
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return items;
    }
}
