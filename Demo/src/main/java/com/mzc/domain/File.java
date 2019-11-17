package com.mzc.domain;

public class File {
    int id;
    int u_id;
    int d_id;
    String filename;
    String path;
    long size;
    String upload_time;

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

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", u_id=" + u_id +
                ", d_id=" + d_id +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", upload_time='" + upload_time + '\'' +
                '}';
    }
}
