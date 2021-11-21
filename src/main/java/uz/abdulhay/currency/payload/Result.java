package uz.abdulhay.currency.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private String message;
    private boolean success;
    private Object data;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public static Result ok(String message) {
        return new Result(message, true);
    }
    
    public static Result ok(Object o) {
        return new Result("Success", true, o);
    }

    public static Result deleted() {
        return new Result("Deleted", true);
    }

    public static Result deleted(boolean b) {
        return new Result("Deleted", b);
    }

    public static Result saved(Object obj) {
        return new Result("Saved", true, obj);
    }

    public static Result updated(Object obj) {
        return new Result("Updated", true, obj);
    }


}
