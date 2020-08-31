package entity;

import javax.persistence.*;

@Entity
@Table(name = "incomes_operations")
public class IncomesOperations extends AbstractEntity {

    Operation operationId;
    Income incomeId;

    public IncomesOperations() {}

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_of_operation", nullable = false)
    public Operation getOperationId() {
        return operationId;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_of_income", nullable = false)
    public Income getIncomeId() {
        return incomeId;
    }

    public void setOperationId(Operation operationId) {
        this.operationId = operationId;
    }

    public void setIncomeId(Income incomeId) {
        this.incomeId = incomeId;
    }
}
