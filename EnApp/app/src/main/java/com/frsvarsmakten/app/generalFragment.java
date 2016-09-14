package com.frsvarsmakten.app;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Christoffers on 2014-04-09.
 */
public class generalFragment extends Fragment {

    private View button;
    private EditText generalTxt, dateTxt;
    public generalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generalinfo, container, false);


        button = rootView.findViewById(R.id.button);
        generalTxt = (EditText) rootView.findViewById(R.id.generalTextfield);
        dateTxt = (EditText) rootView.findViewById(R.id.dateTextField);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getActivity(), "Information sparad", Toast.LENGTH_SHORT).show();

            }
        });


        generalTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(!generalTxt.getText().toString().trim().isEmpty() &&
                        !generalTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dateTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                button.setEnabled(!dateTxt.getText().toString().trim().isEmpty() &&
                        !dateTxt.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }
}
