package entity;

public class Balance {
    public int id;
    public String nameOfBalance;
    public int userId;

    public Balance(String nameOfBalance){
        this.nameOfBalance = nameOfBalance;
    }

    public Balance(int id, String nameOfBalance, int userId) {
        this.nameOfBalance = nameOfBalance;
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", nameOfBalance='" + nameOfBalance + '\'' +
                ", userId=" + userId +
                '}';
    }
}
