package com.mzc.domain;

public class Directory {
    private int id;
    private int u_id;
    private String path;
    private String parent_path;
    private String dir;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParent_path() {
        return parent_path;
    }

    public void setParent_path(String parent_path) {
        this.parent_path = parent_path;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "id=" + id +
                ", u_id=" + u_id +
                ", path='" + path + '\'' +
                ", parent_path='" + parent_path + '\'' +
                ", dir='" + dir + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
