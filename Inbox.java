import java.time.LocalTime;
import java.util.ArrayList;

public class Inbox {

    private String firstPerson;
    private String secondPerson;
    private ArrayList<String> messaging;

    public Inbox(String firstPerson, String secondPerson) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.messaging = new ArrayList<>();
    }

    public String getFirstPerson() {
        return this.firstPerson;
    }

    public String getSecondPerson() {
        return this.secondPerson;
    }

    public void addMessaging(String name, String messaging) {
        LocalTime currentTime = LocalTime.now();
        this.messaging
                .add("#" + name + " *" + currentTime.getHour() + ":" + currentTime.getMinute() + "\n" + messaging);
    }

    public boolean conversationHistory(String firstPerson, String secondPerson) {
        return (this.firstPerson.equals(firstPerson) && this.secondPerson.equals(secondPerson))
                || (this.firstPerson.equals(secondPerson) && this.secondPerson.equals(firstPerson));
    }

    public void printMessage() {
        if (this.messaging.isEmpty()) {
            System.out.println("No messaging at the moment");
        } else {
            for (String messaging : this.messaging) {
                System.out.println(messaging);
            }
        }
    }

}