package lt.bulevicius.tessonetapp.network.entities.data;

public final class Country {

    private String name;
    private long distance;

    public Country(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}
