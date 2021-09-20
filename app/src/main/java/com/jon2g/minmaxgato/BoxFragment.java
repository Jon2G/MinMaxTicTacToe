package com.jon2g.minmaxgato;

import androidx.fragment.app.Fragment;

import com.example.minmaxgato.R;
import com.jon2g.minmaxgato.abstractions.Box;

public class BoxFragment extends Fragment {

    private com.jon2g.minmaxgato.abstractions.Box Box;

    public BoxFragment(Box Box) {
        super(R.layout.casilla_fragment);
        this.Box=Box;
    }
}