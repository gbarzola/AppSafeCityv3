package app.upc.com.appsafecity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.upc.com.appsafecity.R;
import app.upc.com.appsafecity.models.Noticia;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {
    private Context context;
    private List<Noticia> noticias;
    private LayoutInflater inflador;

    public NoticiasAdapter(Context context, List<Noticia> noticias) {
        this.context = context;
        this.noticias = noticias;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.item_news,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.titulo.setText(noticias.get(position).getTitulo());
        holder.Contenido.setText(noticias.get(position).getContenido());
        holder.FechaPublicacion.setText(noticias.get(position).getFecha_publicacion());
        holder.Fuente.setText(noticias.get(position).getFuente());
        Glide.with(context).load(noticias.get(position).getFotos()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }


    class ViewHolder extends  RecyclerView.ViewHolder{

        TextView titulo, Contenido, FechaPublicacion, Fuente;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_titulo);
            Contenido = itemView.findViewById(R.id.tv_contenido);
            FechaPublicacion = itemView.findViewById(R.id.tv_fecha_publicacion);
            imageView = itemView.findViewById(R.id.foto_news);
            Fuente = itemView.findViewById(R.id.tv_fuente);
        }


    }
}
