package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String password; // Lưu mật khẩu dạng plaintext cho bài tập

    // Constructors
    public User() {}

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password; // Lưu mật khẩu
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password; // Lưu mật khẩu
    }

    public void setPassword(String password) {
        this.password = password; // Cập nhật mật khẩu
    }
}