package entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    String login;
    String password;

    public User() {}

    @NaturalId
    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
