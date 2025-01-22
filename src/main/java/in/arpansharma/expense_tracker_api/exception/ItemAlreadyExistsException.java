package in.arpansharma.expense_tracker_api.exception;

public class ItemAlreadyExistsException extends RuntimeException{

    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
