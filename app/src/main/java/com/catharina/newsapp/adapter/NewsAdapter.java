package com.catharina.newsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.catharina.newsapp.R;
import com.catharina.newsapp.modelo.News;

import java.text.ParseException;
import java.util.List;

/**
 * Created by catharina on 03/10/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private List<News> news;

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News new1 = news.get(position);

        holder.titulo.setText(new1.getTitulo());
        holder.dataCriacao.setText(new1.getDate());

        holder.corpo.setText(new1.getCorpo());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titulo;
        TextView dataCriacao;
        TextView corpo;
        Switch favorite;

        public NewsViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            dataCriacao = (TextView) itemView.findViewById(R.id.data_criacao);
            corpo = (TextView) itemView.findViewById(R.id.corpo);
            favorite = (Switch) itemView.findViewById(R.id.favorite);

            favorite.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            news.get(getAdapterPosition()).setFavorite(favorite.isChecked());
            /*if(favorite.isChecked()){
                Toast.makeText(context, ""+favorite.isChecked(), Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, ""+favorite.isChecked(), Toast.LENGTH_SHORT).show();
                news.get(getAdapterPosition()).setFavorite(favorite.isChecked());
            }*/
        }
    }
}
