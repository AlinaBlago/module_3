package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "expenses")
public class Expense extends AbstractEntity {

    Integer sumOfExpenses;
    String typeOfExpenses;

    public Expense() {
    }

    @Column(name = "sum_of_expenses", nullable = false)
    public Integer getSumOfExpenses() {
        return sumOfExpenses;
    }

    @Column(name = "type_of_expenses", nullable = false)
    public String getTypeOfExpenses() {
        return typeOfExpenses;
    }

    public void setSumOfExpenses(Integer sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }

    public void setTypeOfExpenses(String typeOfExpenses) {
        this.typeOfExpenses = typeOfExpenses;
    }
}
