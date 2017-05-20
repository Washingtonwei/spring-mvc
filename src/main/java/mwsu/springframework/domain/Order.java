package mwsu.springframework.domain;

import mwsu.springframework.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bingyang.wei on 5/9/2017.
 */
@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainObject{

    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    private Date dateShipped;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderLines(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public void addToOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }
    public void removeOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }
}
