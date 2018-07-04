package dev.task.dainius.androidparty;

/**
 * Server record representation.
 */
public class Server {
    private String name;
    private int distance;

    public Server(String title, int distance) {
        this.name = title;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


}
