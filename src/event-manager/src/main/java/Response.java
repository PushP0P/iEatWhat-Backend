public class Response {
    private boolean ok;
    private String eventType;
    private String body;
    private String message;

    public Response(boolean ok, String message, String body, String eventType) {
        this.setOk(ok);
        this.setMessage(message);
        this.setBody(body);
        this.setEvent(eventType);
    }

    public void setBody(String body) {
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
