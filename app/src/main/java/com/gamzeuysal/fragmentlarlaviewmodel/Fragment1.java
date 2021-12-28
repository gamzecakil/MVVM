package com.gamzeuysal.fragmentlarlaviewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gamzeuysal.fragmentlarlaviewmodel.viewmodel.FragmentViewModel;

import java.util.List;

public class Fragment1 extends Fragment {

    TextView textView;

    private FragmentViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment,container,false);
        textView=view.findViewById(R.id.textView);

        model = new ViewModelProvider(requireActivity()).get(FragmentViewModel.class);
        model.getNumbersLiveData().observe(requireActivity(), new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
               textView.setText(" "+integers.toString());
            }
        });
        model.loadNumbers();
        return view;
    }
}
