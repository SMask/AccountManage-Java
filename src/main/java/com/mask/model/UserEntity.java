package com.mask.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "accountmanagemaven", catalog = "")
public class UserEntity {
    private int id;

    // message 直接提供错误信息
    // @NotBlank(message = "Username不能为空")
    // message 使用 {} 代表错误内容，从 resources 目录下的 ValidationMessages.properties 文件中读取
    // @NotBlank(message = "{user.username.illegal}")
    @Pattern(regexp = "[a-zA-Z0-9_]{6,12}", message = "{user.username.illegal}")
    private String username;

    @Size(min = 6, max = 12, message = "{user.password.illegal}")
    private String password;

    private String passwordAgain;

    @NotBlank(message = "{user.nickname.illegal}")
    private String nickname;

    private String firstName;
    private String lastName;

    private long birthday;
    private String birthdayStr;// 不使用Date，因为如果网页不选日期解析会出错
    private Date birthdayDate;// 需要是 java.sql.Date ，否则会为 null

    private long registerTime;
    private long updateTime;
    private String token;
    private Collection<BlogEntity> blogsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 设置 @Transient 表示不在数据库生成此字段
    @Transient
    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    @Transient
    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    @Transient
    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Basic
    @Column(name = "register_time", nullable = false)
    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "token", nullable = false, length = 45)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                registerTime == that.registerTime &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, nickname, birthday, firstName, lastName, registerTime, updateTime, token);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<BlogEntity> getBlogsById() {
        return blogsById;
    }

    public void setBlogsById(Collection<BlogEntity> blogsById) {
        this.blogsById = blogsById;
    }
}
