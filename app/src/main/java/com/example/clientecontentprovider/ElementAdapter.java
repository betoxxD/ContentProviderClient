package com.example.clientecontentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {

    private Vector<Usuario> vectorUsuarios;
    private Context context;
    private LayoutInflater inflater;

    private View.OnClickListener onclickListener;

    public void setOnclickListener(View.OnClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    public ElementAdapter(Context context,Vector<Usuario> vectorUsuarios) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.vectorUsuarios = vectorUsuarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.element_selector,null);
        v.setOnClickListener(this.onclickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = vectorUsuarios.get(position);
        holder.nombre.setText(usuario.getName());
        holder.pass.setText(usuario.getPass());
        holder.correo.setText(usuario.getEmail());
        holder.tel.setText(usuario.getTel());
    }

    @Override
    public int getItemCount() {
        return vectorUsuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView nombre;
        public TextView pass;
        public TextView correo;
        public TextView tel;

        public ViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cvContainter);
            nombre = itemView.findViewById(R.id.txtNameSelector);
            pass = itemView.findViewById(R.id.txtPassSelector);
            correo = itemView.findViewById(R.id.txtEmailSelector);
            tel = itemView.findViewById(R.id.txtTelSelector);
        }
    }
}
