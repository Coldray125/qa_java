package Sprint_3.pojo;

import java.util.List;
import java.util.Objects;

public class CreateOrderPojo {
    @Override
    public String toString() {
        return "CreateOrderPojo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", metroStation='" + metroStation + '\'' +
                ", phone='" + phone + '\'' +
                ", rentTime=" + rentTime +
                ", deliveryData='" + deliveryData + '\'' +
                ", comment='" + comment + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderPojo that = (CreateOrderPojo) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(metroStation, that.metroStation) && Objects.equals(phone, that.phone) && Objects.equals(rentTime, that.rentTime) && Objects.equals(deliveryData, that.deliveryData) && Objects.equals(comment, that.comment) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, metroStation, phone, rentTime, deliveryData, comment, color);
    }

    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phone;
    Long rentTime;
    String deliveryData;
    String comment;
    List color;


    public String getFirstName() {
        return firstName;
    }

    public CreateOrderPojo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateOrderPojo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CreateOrderPojo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public CreateOrderPojo setMetroStation(String metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CreateOrderPojo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Long getRentTime() {
        return rentTime;
    }

    public CreateOrderPojo setRentTime(Long rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public String getDeliveryData() {
        return deliveryData;
    }

    public CreateOrderPojo setDeliveryData(String deliveryData) {
        this.deliveryData = deliveryData;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CreateOrderPojo setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List getColor() {
        return color;
    }

    public CreateOrderPojo setColor(List color) {
        this.color = color;
        return this;
    }

    public CreateOrderPojo(String firstName, String lastName, String address,
                           String metroStation, String phone, Long rentTime,
                           String deliveryData, String comment, List color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryData = deliveryData;
        this.comment = comment;
        this.color = color;
    }

    public CreateOrderPojo() {
    }

    public CreateOrderPojo CreateOrderBuilder(){
        return new CreateOrderPojo(firstName,lastName,address,metroStation,phone,rentTime,deliveryData,comment,color);
    }

}