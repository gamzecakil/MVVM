package com.gamzeuysal.fragmentlarlaviewmodel.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class  FragmentViewModel  extends ViewModel {
    private static  final String TAG=FragmentViewModel.class.getSimpleName();

  /* public FragmentViewModel(Repository repository)
    {
        int sayi=random.nextInt(5);
    }*/

    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private MutableLiveData<List<Integer>> numbers = new MutableLiveData<>();
    //private MutableLiveData<QrEvent> qrLiveData = new MutableLiveData<QrEvent>();

    public FragmentViewModel() {
    }

    public LiveData<List<Integer>> getNumbersLiveData()
    {
        return numbers;
    }

    public void loadNumbers()
    {
        //Do on ansynchronous operation to fetch numbers.
        //Surekli arka planda sayılar uretelim.
        Random random = new Random();
        randomNumbers=new ArrayList<Integer>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while(i < 5) {
                        Log.e(TAG,"The number will be generated");
                        int number=random.nextInt(5);
                        System.out.println("Generated number : "+number);
                        Log.e(TAG,"Number generated");
                        //ArrayListe elemanları ekledik
                        randomNumbers.add(number);
                        Thread.sleep(1000);
                        i++;
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                //MutableLiveDatayı set ettik
                               // numbers.setValue(randomNumbers);
                                numbers.postValue(randomNumbers);
                            }
                        });
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void onQrEvent(UiEvent.QrEvent event) {
        Debug.log(TAG, "onQrEvent -> instance of " + event.getClass().getSimpleName());
        Debug.log(TAG, "Qr Code: " + event.text);
        qrEvent = event;
        qrLiveData.setValue(qrEvent);


        Navigation.findNavController(this, R.id.my_nav_host_fragment).navigate(CardDetectionFragmentDirections.
                actionCardDetectionToQrFragment(event.text, event.amount));
    }*/
}
