package org.po;

public class Tb_user {
    private int id;
    private String name;
    private String pwd;
    private byte age;
    private String nick;
    private String head;
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Tb_user() {

    }

    public Tb_user(int id, String name, String pwd, byte age, String nick, String head, String label) {

        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.age = age;
        this.nick = nick;
        this.head = head;
        this.label = label;
    }
}
