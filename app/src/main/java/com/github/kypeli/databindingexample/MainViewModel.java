package com.github.kypeli.databindingexample;

import android.databinding.ObservableField;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainViewModel {
    private TimeModel time = new TimeModel();
    private Subscription timeSubscription;

    public ObservableField<String> timeString = new ObservableField<>(timeStringFromModel(time));
    public ObservableField<Boolean> running = new ObservableField<>(false);

    public void updateStartStopState() {
        if (running.get()) {
            timeSubscription.unsubscribe();
        } else {
            timeSubscription = getTimeObservable()
                    .map(new Func1<Long, String>() {

                        @Override
                        public String call(Long newCentiTick) {
                            time.addTotalTimeCenti(1);
                            return timeStringFromModel(time);
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String newTime) {
                            timeString.set(newTime);
                        }
                    });
        }
        running.set(!running.get());
    }

    private Observable<Long> getTimeObservable() {
        return Observable.interval(10, TimeUnit.MILLISECONDS);
    }

    private String timeStringFromModel(TimeModel time) {
        return String.format(Locale.ENGLISH, "%02d:%02d:%02d", time.getMinutes(), time.getSeconds(), time.getCentiSecond());
    }
}
