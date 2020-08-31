package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incomes")
public class Income extends AbstractEntity {

    Integer sumOfIncomes;
    String typeOfIncomes;

    public Income() {}

    @Column(name = "sum_of_incomes", nullable = false)
    public Integer getSumOfIncomes() {
        return sumOfIncomes;
    }

    @Column(name = "type_of_incomes", nullable = false)
    public String getTypeOfIncomes() {
        return typeOfIncomes;
    }

    public void setSumOfIncomes(Integer sumOfIncomes) {
        this.sumOfIncomes = sumOfIncomes;
    }

    public void setTypeOfIncomes(String typeOfIncomes) {
        this.typeOfIncomes = typeOfIncomes;
    }
}
