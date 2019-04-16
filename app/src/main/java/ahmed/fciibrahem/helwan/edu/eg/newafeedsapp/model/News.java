package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class News {
    @Expose
    private String status;

    @Expose
    private String source;

    @Expose
    private String sortBy;

    @Expose
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
