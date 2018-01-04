package com.example.a16165872.theribs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Bruno on 11/11/2017.
 */

public class Internet {

    public static Boolean VerificarConexao(Context context){
        Boolean conexao;

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            conexao = true;
        }else{
            conexao = false;
        }

        return  conexao;
    }

}
