/**
 * Created by joe on 20/09/2016.
 */
public class Message {
    public String message;

    public Message(String message){
        this.message=message;
    }

    @Override
    public String toString() {
        return message+"\n";
    }
}
