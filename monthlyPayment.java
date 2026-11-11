public class monthlyPayment {
    public static double getMonthlyPayment(double annualInterestRate, double numberOfYears, double loanAmount){
        double interest = annualInterestRate/100/12; //splitting interest across months to reflect real world interest rates    
        double monthlyPayment = (loanAmount * interest) / (1 - Math.pow(1 + interest, -numberOfYears*12));
        return monthlyPayment;
    }

    public static double getTotalPayment(double annualInterestRate, double numberOfYears, double loanAmount){
        //reuse function to simplify code
        double totalPayment = getMonthlyPayment(annualInterestRate, numberOfYears, loanAmount) * numberOfYears * 12;
        return totalPayment; 
    }
}

//Aayan Khan - Assignment 3
