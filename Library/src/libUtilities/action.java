package libUtilities;

import java.util.Date;

public class action {
    private String operation;
    private Date timestamp;

    public action(String operation) {
        this.operation = operation;
        this.timestamp = new Date();
    }

    public action(){}

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "action{" +
                "operation=\'" + operation + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toCSVFormat()
    {
        return "" + operation + "," + timestamp;
    }
}
