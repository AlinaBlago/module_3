package entity;

import java.time.Instant;

public class Operation {
    public int id;
    public Instant date;
    public int balanceId;

    public Operation(int id, Instant date, int balanceId) {
        this.id = id;
        this.date = date;
        this.balanceId = balanceId;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date=" + date +
                ", balanceId=" + balanceId +
                '}';
    }
}
