package com.gamzeuysal.fragmentlarlaviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.gamzeuysal.fragmentlarlaviewmodel.viewmodel.FragmentViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  static  final String TAG=MainActivity.class.getSimpleName();
Button firstFragmentButton;
Button secondFragmentButton;
TextView textView;
private  FragmentViewModel fragmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView3);


       // fragmentViewModel= ViewModelProviders.of


       // firstFragmentButton=findViewById(R.id.button2);
       // secondFragmentButton=findViewById(R.id.button3);

        addFragment();
       /* firstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout,new Fragment1(),"fragmentOne");
                fragmentTransaction.commit();

            }
        });*/

       /* secondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout,new Fragment2(),"fragmentSecond");
                fragmentTransaction.commit();
            }
        });*/


        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        FragmentViewModel model=new ViewModelProvider(this).get(FragmentViewModel.class);
        model.getNumbersLiveData().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                Log.i(TAG,"ViewModel LiveData çalişti");
                textView.setText(" "+integers.toString());
            }
        });


    }
    public void addFragment()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout1,new Fragment1(),"fragmentOne");
        fragmentTransaction.add(R.id.frameLayout2,new Fragment2(),"fragmentSecond");
        fragmentTransaction.commit();
    }
}