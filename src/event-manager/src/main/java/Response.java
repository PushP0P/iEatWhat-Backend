public class Response {
    private boolean ok;
    private Event event;
    private String body;
    private String message;

    public Response(boolean ok, String message, String body, Event event) {
        this.setOk(ok);
        this.setMessage(message);
        this.setBody(body);
        this.setEvent(event);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Event getEvent() {
        return event;
    }

    public String getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOk() {
        return ok;
    }
}
