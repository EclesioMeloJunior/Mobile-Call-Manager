package com.example.controledechamadosapp.Helpers;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.controledechamadosapp.Model.Usuario;
import com.example.controledechamadosapp.R;

import java.util.List;

public class UsuariosAdpaterPersonalizado extends BaseAdapter {

    private final List<Usuario> adpterList;
    private final Activity activity;

    public UsuariosAdpaterPersonalizado(List<Usuario> adpterList, Activity activity)
    {
        this.adpterList = adpterList;

        this.activity = activity;
    }


    @Override
    public int getCount() {
        return this.adpterList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.adpterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.adpterList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.activity.getLayoutInflater()
                .inflate(R.layout.activity_item_usuario_personalizado, parent, false);

        Usuario usuario = this.adpterList.get(position);

        TextView nome = (TextView) view.findViewById(R.id.lvpersonalizada_usuario_nome);
        TextView email = (TextView) view.findViewById(R.id.lvpersonalizada_usuario_email);
        TextView telefone = (TextView) view.findViewById(R.id.lvpersonalizada_usuario_telefone);

        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());
        telefone.setText(usuario.getTelefone());

        return view;
    }
}
