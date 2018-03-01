package iEatWhatEvents;

public class Event {
    private String type;
    private String payload;

    public Event (String type, String payload) {
        this.setPayload(payload);
        this.setType(type);
    }

    private void setPayload(String payload) {
        this.payload = payload;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }
}
