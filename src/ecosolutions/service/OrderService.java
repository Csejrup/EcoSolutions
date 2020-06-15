package ecosolutions.service;
import ecosolutions.persistence.DAO.OrderDao;
import ecosolutions.Domain.Order;

import java.util.*;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * This Class is a service class for OrderDao and Connects to the View
 */
public class OrderService {

    public static void addOrder(Order order){
        OrderDao orderDao = new OrderDao();
        orderDao.save(order);
    }
    public static void addOrderDetails(Order order)  {
        OrderDao orderDao = new OrderDao();
            orderDao.addOrderDetails(order);
    }
    public static void updateOrderr(Order order){
        OrderDao orderDao = new OrderDao();
        orderDao.update(order);
    }
    public static void updateOrder(int id, String status){
        OrderDao orderDao = new OrderDao();
        orderDao.update(id, status);
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
    }
    public static List<Integer>getItemQuantityByID(List<Integer> itemIDList,int orderID){
        return OrderDao.getQuantityByID(itemIDList,orderID);
    }
    public static int getLastOrderID(){
       return OrderDao.getLastOrderID();
    }
    public static ObservableList<PieChart.Data> getGraph1(){
        return OrderDao.getOrderGraphStatistics();
    }
    public static ObservableList<PieChart.Data> getGraph2(){
        return OrderDao.getEmployeeStatistics();
    }
    public static ObservableList<XYChart.Data<Integer, Integer>> getGraph3(){
        return OrderDao.getBarChartData();
    }
    public static int getCustomerIDbyOrderID(int orderID){
       return OrderDao.getCustomerIDbyOrderID(orderID);
    }
    public static void issueStatus(int orderID){
        OrderDao.issueStatus(orderID);
    }
}
