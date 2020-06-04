package ecosolutions.Domain;


import ecosolutions.persistence.DB;
import ecosolutions.presentation.models.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import ecosolutions.persistence.DAO.*;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
    public static List<Integer> getItemIDList(int orderID){
        return OrderDao.getItemsID(orderID);
    }
    public static List<String>getItemTypeByID(List<Integer> itemIDList){
        return OrderDao.getItemTypeByID(itemIDList);
    }public static List<Integer>getItemQuantityByID(List<Integer> itemIDList,int orderID){
        OrderDao orderdao = new OrderDao();
        return orderdao.getQuantityByID(itemIDList,orderID);
    }


    public static int getLastOrderID(){
       return OrderDao.getLastOrderID();
    }

    public static ObservableList<PieChart.Data> getGraph1(){
        OrderDao orderDao = new OrderDao();

        return orderDao.getOrderGraphStatistics();


    }
    public static ObservableList<PieChart.Data> getGraph2(){
        OrderDao orderDao = new OrderDao();

        return orderDao.getEmployeeStatistics();


    }



}
