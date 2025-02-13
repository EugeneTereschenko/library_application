package com.library.utility;
//Computes fines for overdue books.
import com.library.model.Loan;

import java.util.Date;

public class PenaltyCalculator {
    private static final double DAILY_FINE_RATE = 1.0;

    public static double calculateFine(Loan loan) {
        Date dueDate = loan.getDueDate();
        Date returnDate = new Date(); // Assuming the book is returned today
        long diffInMillies = returnDate.getTime() - dueDate.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
        return diffInDays > 0 ? diffInDays * DAILY_FINE_RATE : 0;
    }
}
