package iao.master.blanchisserie.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "clients")
public class Clients {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean subscribed;
    @Ignore
    public Clients() {
    }

    public Clients(String name, String email, String phone, boolean subscribed) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscribed = subscribed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}
