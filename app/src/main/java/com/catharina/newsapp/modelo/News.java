package com.catharina.newsapp.modelo;

/**
 * Created by catharina on 03/10/16.
 */
public class News {

    private long id;
    private String titulo;
    private String corpo;
    private String date;
    private boolean favorite;

    public News(long id, String titulo, String corpo, String date) {
        this.id = id;
        this.titulo = titulo;
        this.corpo = corpo;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (favorite != news.favorite) return false;
        if (titulo != null ? !titulo.equals(news.titulo) : news.titulo != null) return false;
        if (corpo != null ? !corpo.equals(news.corpo) : news.corpo != null) return false;
        return date != null ? date.equals(news.date) : news.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (corpo != null ? corpo.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (favorite ? 1 : 0);
        return result;
    }
}
