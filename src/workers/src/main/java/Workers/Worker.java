package Workers;

import io.reactivex.Observer;

import java.util.Map;

public class Worker {

    private String id;
    private String[] tasks;
    private Observer<?> receiver;
    private String middleWare;
    private Map<String, ?>  payload;

    public Worker(String[] tasks, Observer<?> receiver) {

    }
}