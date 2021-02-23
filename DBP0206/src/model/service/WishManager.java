package model.service;
import model.dao.*;
import java.sql.SQLException;
import java.util.List;

import model.Basket;
import model.Item;
import model.dao.WishDAO;

public class WishManager {

		private static WishManager wishman = new WishManager();
		private static WishDAO wishDAO;
		private static Basket wish;
		private WishManager() {
			try {
				wishDAO = new WishDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		public static WishManager getInstance() {
			return wishman;
		}

			
		public int createWish(Basket wish) throws SQLException, ExistingException {
			
			return wishDAO.create(wish);
		}
	
		
		public int removeWish(Basket i) throws SQLException { 
				
			return wishDAO.remove(i);
		}
		public List<Item> findWishList(int c_no) throws SQLException,  ExistingException{
			List<Item> item = wishDAO.findwishList(c_no);
			
			return item;
		}
		public List<Item> recommand(int c_no) throws SQLException, ExistingException {

			return wishDAO.recommand(c_no);
		}
		public int countBasket(int c_no) throws SQLException, ExistingException {

			return wishDAO.countBasket(c_no);
		}
		
		public WishDAO getWishDAO() {
			return wishDAO;
		}

		
}
