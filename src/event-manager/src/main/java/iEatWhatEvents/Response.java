package iEatWhatEvents;

public class Response {
    private boolean ok;
    private String eventType;
    private Object body;
    private String message;

    public Response(boolean ok, String message, Object body, String eventType) {
        this.setOk(ok);
        this.setMessage(message);
        this.setBody(body);
        this.setEvent(eventType);
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void setEvent(String eventType) {
        this.eventType = eventType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getEvent() {
        return this.eventType;
    }

    public Object getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOk() {
        return ok;
    }
}
