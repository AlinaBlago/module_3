package entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "balances")
public class Balance extends AbstractEntity {

    String nameOfBalance;
    User userId;

    public Balance() {}

    public Balance(String value){
        this.nameOfBalance = value;
    }

    @Column(name = "name_of_balance", nullable = false)
    public String getNameOfBalance() {
        return nameOfBalance;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUserId() {
        return userId;
    }

    public void setNameOfBalance(String nameOfBalance) {
        this.nameOfBalance = nameOfBalance;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Id of balance: " + getId() + ", name of balance: " + getNameOfBalance();
    }
}
