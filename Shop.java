package sqllite.sample.com.sqllitsampleapp;

/**
 * Created by galaxy-user on 30/01/17.
 */

class Shop {
  private int id;
  private String name;
  private String address;
  Shop()
  {
  }
  Shop(String name, String address)
  {
    this.name=name;
    this.address=address;
  }
  void setId(int id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }

  void setAddress(String address) {
    this.address = address;
  }
  int getId() {
    return id;
  }
  String getAddress() {
    return address;
  }
  public String getName() {
    return name;
  }
}
