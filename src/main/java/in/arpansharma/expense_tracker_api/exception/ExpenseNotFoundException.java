package in.arpansharma.expense_tracker_api.exception;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(String message){
        super(message);
    }
}
