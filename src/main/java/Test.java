import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.modules.Order;

public class Test {

	public static void main(String[] args) {
		
		OrderDAO orderDAO = new OrderDAOImpl();
		List<Order> aceptedOrdersByRestaurantId = orderDAO.getAceptedOrdersByRestaurantId(4);
		System.out.println(aceptedOrdersByRestaurantId);
	}

}
