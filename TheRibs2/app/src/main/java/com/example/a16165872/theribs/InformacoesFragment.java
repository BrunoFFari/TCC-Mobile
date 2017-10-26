package com.example.a16165872.theribs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class InformacoesFragment extends Fragment {

    Button btnLigar, btnLocalizar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =   inflater.inflate(R.layout.fragment_informacoes, container, false);

        btnLigar = (Button) v.findViewById(R.id.btn_ligar);
        btnLocalizar = v.findViewById(R.id.btnLocalizar);

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                String numero = "40028922";

                if(!numero.isEmpty()){
                    intent.setData(Uri.parse("tel:" + numero));
                }

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED){

                    Snackbar.make(getView(), "Conceda permissão para podermos realizar a ligação", Snackbar.LENGTH_LONG
                            ).show();

                }else{
                    startActivity(intent);
                }

            }
        });

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:47.4925,19.0513"));
                Intent chooser = Intent.createChooser(intent, "Lauch Maps");
                startActivity(chooser);
            }
        });

        return v;
    }



}
