package entity;

import javax.persistence.*;

@Entity
@Table(name = "expenses_operations")
public class ExpensesOperations extends AbstractEntity {

    Operation idOperation;
    Expense idExpense;

    public ExpensesOperations() {}

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_of_operation", nullable = false)
    public Operation getIdOperation() {
        return idOperation;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_of_expense", nullable = false)
    public Expense getIdExpense() {
        return idExpense;
    }

    public void setIdOperation(Operation idOperation) {
        this.idOperation = idOperation;
    }

    public void setIdExpense(Expense idExpense) {
        this.idExpense = idExpense;
    }
}
