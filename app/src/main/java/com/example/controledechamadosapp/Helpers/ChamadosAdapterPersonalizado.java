package com.example.controledechamadosapp.Helpers;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.controledechamadosapp.DAO.ChamadoDAO;
import com.example.controledechamadosapp.Model.Chamado;
import com.example.controledechamadosapp.Model.ChamadoStatus;
import com.example.controledechamadosapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ChamadosAdapterPersonalizado extends BaseAdapter {

    private final List<Chamado> adpterList;
    private final Activity activity;

    public ChamadosAdapterPersonalizado(List<Chamado> adpterList, Activity activity) {
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
        return this.adpterList.get(position).id;
    }

    public Chamado getChamadoAtPosition(int pos)
    {
        return this.adpterList.get(pos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.activity.getLayoutInflater()
                .inflate(R.layout.activity_item_chamado_personalizado, parent, false);

        Chamado chamado = this.adpterList.get(position);
        try {
            chamado = new ChamadoDAO(this.activity).getChamadoById(chamado.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView viewId = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_id);
        TextView status = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_status);
        TextView assunto = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_assunto);
        TextView descricao = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_descricao);
        TextView destinatario = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_destinatario);
        TextView chamado_criacao = (TextView) view.findViewById(R.id.lvpersonalizada_chamado_criacao);

        switch (chamado.getStatus()) {
            case ABERTO: {
                status.setText("ABERTO");
                status.setBackgroundColor(Color.rgb(82, 179, 217));
                break;
            }

            case EM_ANDAMENTO: {
                status.setText("EM ANDAMENTO");
                status.setTextColor(Color.BLACK);
                status.setBackgroundColor(Color.rgb(245, 215, 110));
                break;
            }

            case FECHADO: {
                status.setText("FECHADO");
                status.setTextColor(Color.WHITE);
                status.setBackgroundColor(Color.rgb(214, 69, 65));
                break;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(chamado.getData_criacao());

        viewId.setText(String.valueOf(chamado.getId()));
        assunto.setText(chamado.getAssunto());

        String descricaoString = chamado.getDescricao();

        if(descricaoString.length() > 53)
        {
            descricao.setText(descricaoString.substring(0, 53) + "....");
        }
        else
        {
            descricao.setText(descricaoString);
        }

        chamado_criacao.setText(date);
        destinatario.setText(chamado.getUsuarioDestino().getNome());

        return view;
    }
}
