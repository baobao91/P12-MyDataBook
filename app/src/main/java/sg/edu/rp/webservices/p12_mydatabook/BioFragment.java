package sg.edu.rp.webservices.p12_mydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {
    Button btnEdit;
    TextView bioResult;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);
        settings = getActivity().getSharedPreferences("JSON", MODE_PRIVATE);
        editor = settings.edit();

        btnEdit = (Button) view.findViewById(R.id.buttonEdit);
        bioResult = (TextView) view.findViewById(R.id.bioResult);
        editFunc();

        String sharedResult = settings.getString("bioResult", "");

        if(sharedResult != null){
            bioResult.setText(sharedResult);
        } else {
            bioResult.setText("");
        }

        return view;
    }

    public void editFunc(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout editBio =
                        (LinearLayout) inflater.inflate(R.layout.edit_bio, null);
                final EditText etResult = (EditText) editBio
                        .findViewById(R.id.editTextPassPhrase);

                etResult.setText(settings.getString("bioResult", ""));

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Edit Bio")
                        .setView(editBio)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                editor.putString("bioResult", etResult.getText().toString());
                                editor.apply();
                                bioResult.setText(settings.getString("bioResult", ""));
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

}
