package com.example.heartx.greendao_exercies.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * greenDao实体类
 * Created by HeartX on 2017/10/9.
 */

@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "USERNAME")
    private String username;

    @Property(nameInDb = "PASSWORD")
    private String password;

    @Generated(hash = 1681958521)
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
