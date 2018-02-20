public class EventManager {
    static void main(String... args) {

    }

    // Our Event.type should follow a DOMAIN:MANAGER-NAME_TYPE convention
    public static Response dispatchEvent(Event event) throws Exception {
        System.out.println("\n\nEventType \n" + event.getType());
        String domain = event.getType().split(":")[0];

        switch(domain) {
            case "TEST":
                // Write the return value to a string for transport in the response object.
                return TestDomain(event);
            case "PRIVATE":
                break;
            default:
                System.out.println("Event Not Recognized " + event.getType());
        }
        Response response = new Response(false, "No Event Found", "No Body", event.getType());
        return response;
    }

    /**
     * Filter Events into domains.
     * Setup managers to handle the event in a domain.
     * @param evt
     * @return Response
     * @throws Exception
     */
    private static Response TestDomain(Event evt) throws Exception {
        // Instantiate your manager to consume the Events payload.
        FakeManager fm = new FakeManager();
        String managerDone = fm.doSomething(evt.getPayload());

        // Managers that return a response should use the Response.class
        // if no response from manager still send a Response.ok == true
        Response response = new Response(
                true,
                "Manager was here.",
                managerDone,
                evt.getType()
        );

        return response;
        // TODO Wire up Managers
    }
}
