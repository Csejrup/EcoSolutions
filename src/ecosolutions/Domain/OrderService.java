package ecosolutions.Domain;


import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//C:\Users\sejru\OneDrive\EASV\2nd Semester\2nd Semester Project Exam\projectexamreal\src\ecosolutions\persistence\DAO\OrderDao.java

import ecosolutions.persistence.DAO.*;
//TODO CLEAN UP
public class OrderService {
  
    private static boolean doesNotExist(Order order){
        OrderDao orderDao = new OrderDao();
        for(Order o : orderDao.getAll()){
            if(o.equals(order)){
                return false;
            }
        }
        return true;
    }
    public static Optional<Order> getOrder(int id){
        OrderDao orderDao = new OrderDao();
        return orderDao.checkorderbyID(id);
    }
    public static void addOrder(Order order){
        OrderDao orderDao = new OrderDao();
        orderDao.save(order);
    }
    public static void addOrderDetails(Order order)  {
        OrderDao orderDao = new OrderDao();
            orderDao.addOrderDetails(order);

    }
    public static void updateOrder(int id, String status){
        OrderDao orderDao = new OrderDao();
        orderDao.update(id, status);
    }
    public static void deleteOrder(Order order){
        OrderDao orderDao = new OrderDao();
        orderDao.delete(order);
    }
    public static List<Order>getOrders(){
        OrderDao orderDao = new OrderDao();
       return orderDao.getAll();
    }
    public static List<Order>getLWOrders(){
        OrderDao orderDao = new OrderDao();
        return orderDao.laundryworkerGetStatus();
    }
    public static List<Order>getDriverOrders(){
        OrderDao orderDao = new OrderDao();
        return orderDao.driverGetStatus();
    }

    public static List<Order>checkorder(int id){
        OrderDao orderDao = new OrderDao();
        return orderDao.checkOrder(id);
    }


    public static int getLastOrderID(){
       return OrderDao.getLastOrderID();
    }
}
