package com.example.minmaxgato;

import androidx.fragment.app.Fragment;

import com.example.minmaxgato.abstractions.Box;

public class BoxFragment extends Fragment {

    private com.example.minmaxgato.abstractions.Box Box;

    public BoxFragment(Box Box) {
        super(R.layout.casilla_fragment);
        this.Box=Box;
    }
}