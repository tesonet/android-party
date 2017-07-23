package com.example.testio.models;

/**
 * Created by mantas on 7/22/17.
 */

public class Server {

  private String name;
  private String distance;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDistance() {
    return distance;
  }

  public void setDistance(String distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return "Server{" + "name='" + name + '\'' + ", distance='" + distance + '\'' + '}';
  }
}
