package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Item;
import model.dao.ItemDAO;


public class ItemManager {
	private static ItemManager itemMan = new ItemManager();
	private ItemDAO itemDAO;
	
	
	private ItemManager() {
		try {
			itemDAO = new ItemDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static ItemManager getInstance() {
		return itemMan;
	}
	
	public int insert(Item item) throws SQLException, ExistingItemException {
		if (itemDAO.existingItem(item.getItem_addr()) == true) {
			throw new ExistingItemException("주소(" + item.getItem_addr() + ")는 존재하는 물건입니다.");
		}
		return itemDAO.insert(item);
	}

	public int update(Item item) throws SQLException {
		return itemDAO.update(item);
	}	
	
	public int remove(int item_no) throws SQLException {
		return itemDAO.remove(item_no);
	}
	
	public int findLastItemNo() throws SQLException {
		return itemDAO.findLastItemNo();
	}
	
	//메인에서 입력한 주소로 검색
	public List<Item> findItemList(String item_addr) throws SQLException, ItemNotFoundException {
		List<Item> item = itemDAO.findItemList(item_addr);
		
		if (item == null) {
			throw new ItemNotFoundException("주소(" + item_addr + ")는 존재하지 않는 물건입니다.");
		}		
		return item;
	}
	
	public List<Item> findItemList() throws SQLException, ItemNotFoundException {
		return itemDAO.findItemList();
	}
	
	public Item findItem(int item_no) throws SQLException {
		return itemDAO.findItem(item_no);
	}
	
	public Item findItemUpdateHits(int item_no) throws SQLException {
		return itemDAO.findItemUpdateHits(item_no);
	}

	public List<Item> findConditionItem(String item_addr, String dealType, String roomType, int costRange, int depositRange) throws SQLException, ItemNotFoundException {
		List<Item> item = itemDAO.findConditionItem(item_addr, dealType, roomType, costRange, depositRange);
		
		if (item == null) {
			throw new ItemNotFoundException("주소(" + item_addr + ")는 존재하지 않는 물건입니다.");
		}		
		return item;
	}
		
	public ItemDAO getItemDAO() {
		return this.itemDAO;
	}

	public List<Item> findUsersItemList(int a_no) throws SQLException {
		return itemDAO.findUsersItemList(a_no);
	}
}
