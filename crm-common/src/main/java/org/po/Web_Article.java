package org.po;

import java.util.Date;

public class Web_Article {
    private int id;
    private String title;
    private String content;
    private Date create_date;
    private Date update_date;
    private int uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Web_Article() {

    }

    public Web_Article(int id, String title, String content, Date create_date, Date update_date, int uid) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.create_date = create_date;
        this.update_date = update_date;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Web_Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                ", uid=" + uid +
                '}';
    }
}
