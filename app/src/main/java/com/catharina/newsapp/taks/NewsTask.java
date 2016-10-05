package com.catharina.newsapp.taks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.catharina.newsapp.adapter.NewsAdapter;
import com.catharina.newsapp.com.github.kevinsawicki.http.HttpRequest;
import com.catharina.newsapp.modelo.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catharina on 03/10/16.
 */
public class NewsTask extends AsyncTask<String, Void, String[]> {
    ProgressDialog dialog;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    Context context;

    public NewsTask(RecyclerView recyclerView, NewsAdapter adapter, Context context) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        //exibir um Process Dialog
        dialog = new ProgressDialog(context);
        dialog.setTitle("Buscando noticias");
        dialog.setMessage("Buscando novas notícias.");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String[] result) {
        //TODO adicionar ao layout as informações
        if(result != null) {

            List<News> notices = new ArrayList<>();

            for(int i = 0; i < result.length; i++){
                String aux = result[i];
                String[] split = aux.split(";");
                News n = new News(Long.parseLong(split[0]),split[1],split[2],split[3]);
                notices.add(n);
            }
            if(adapter == null) {
                adapter = new NewsAdapter(context, notices);
            }
            else{
                adapter.getNews().addAll(notices);
            }

            recyclerView.setAdapter(adapter);
        }
        dialog.dismiss();
    }




    @Override
    protected String[] doInBackground(String... params) {

        String[] notices = null;
        List<News> ns = new ArrayList<>();

        try {
            String path = "https://mynotice.herokuapp.com/notices.json";

            String conteudo = HttpRequest.get(path).body();
            JSONArray resultados = new JSONArray(conteudo);

            notices = new String[resultados.length()];
            for (int i = 0; i < resultados.length(); i++) {
                JSONObject notice = resultados.getJSONObject(i);
                Long id = notice.getLong("id");
                String titulo = notice.getString("title");
                String corpo = notice.getString("body");
                String dataCriacao = notice.getString("criation");

                notices[i] = id+";"+titulo+ ";"+ corpo +";"+dataCriacao;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return notices;
    }
}
