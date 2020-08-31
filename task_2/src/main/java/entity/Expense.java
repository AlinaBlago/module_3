package entity;

public class Expense {
    public int id;
    public int sumOfExpenses;
    public String typeOfExpenses;

    public Expense(int id , int sumOfExpenses, String typeOfExpenses){
        this.sumOfExpenses = sumOfExpenses;
        this.typeOfExpenses = typeOfExpenses;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "sumOfExpenses=" + sumOfExpenses +
                ", typeOfExpenses='" + typeOfExpenses + '\'' +
                '}';
    }

    public String[] toCSVFormat(){
        String[] arr = new String[4];
        arr[0] = "Expense";
        arr[1] = "" + id;
        arr[2] = "" + sumOfExpenses;
        arr[3] = typeOfExpenses;

        return arr;
    }
}
