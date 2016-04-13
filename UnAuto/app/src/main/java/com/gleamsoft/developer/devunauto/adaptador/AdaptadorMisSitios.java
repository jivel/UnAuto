package com.gleamsoft.developer.devunauto.adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gleamsoft.developer.devunauto.R;
import com.gleamsoft.developer.devunauto.model.MisSitios;

import java.util.List;

/**
 * Created by Developer on 12/04/2016.
 */

public class AdaptadorMisSitios extends BaseAdapter {
    private Activity mContext;
    private ListView mSitios;
    private List<MisSitios> mLIstaSitios;
    private LayoutInflater mLayoutInflater = null;

    public AdaptadorMisSitios(Activity ctx, List<MisSitios> list) {
        this.mContext = ctx;
        mLIstaSitios = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mLIstaSitios.size();
    }

    @Override
    public Object getItem(int position) {
        return mLIstaSitios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View filaView = mContext.getLayoutInflater().inflate(R.layout.item_mis_sitios, null);
        MisSitios misSitios = mLIstaSitios.get(position);
        TextView txtNombreMiSitio = (TextView) filaView.findViewById(R.id.txtNombreSitio);
        txtNombreMiSitio.setText(misSitios.getNombre());
        return filaView;
    }
}
