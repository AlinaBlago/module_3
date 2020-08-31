package entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "operations")
public class Operation extends AbstractEntity {

    Balance balanceId;
    Instant dateOfOperation;

    public Operation() {}

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id", nullable = false)
    public Balance getBalanceId() {
        return balanceId;
    }

    @Column(name = "date_of_operation", nullable = false)
    public Instant getDateOfOperation() {
        return dateOfOperation;
    }

    public void setBalanceId(Balance balanceId) {
        this.balanceId = balanceId;
    }

    public void setDateOfOperation(Instant dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }
}

