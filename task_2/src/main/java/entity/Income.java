package entity;

public class Income {
    public int id;
    public int sumOfIncome;
    public String typeOfIncome;

    public Income(int id , String typeOfIncome, int sumOfIncome) {
        this.sumOfIncome = sumOfIncome;
        this.typeOfIncome = typeOfIncome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Income{" +
                "sumOfIncome=" + sumOfIncome +
                ", typeOfIncome='" + typeOfIncome + '\'' +
                '}';
    }

    public String[] toCSVFormat(){
        String[] arr = new String[4];
        arr[0] = "Income";
        arr[1] = "" + id;
        arr[2] = "" + sumOfIncome;
        arr[3] = typeOfIncome;

        return arr;
    }
}
