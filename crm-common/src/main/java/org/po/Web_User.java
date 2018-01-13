package org.po;

public class Web_User {
    private int id;
    private String uname;
    private String pwd;
    private byte age;
    private String nick;
    private String head;
    private String mood;


    public Web_User(int id, String uname, String pwd, byte age, String nick, String head, String mood) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.age = age;
        this.nick = nick;
        this.head = head;
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Web_User() {

    }
}

