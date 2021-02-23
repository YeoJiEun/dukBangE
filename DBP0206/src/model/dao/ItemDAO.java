package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;

/**
 * ������ ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * ITEM ���̺� ���� ������ �߰�, ����, ����, �˻� ���� 
 */
public class ItemDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ItemDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	
	//InsertItemController���� ����
	public int insert(Item item) throws SQLException {
		String sql = "INSERT INTO ITEM "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		
		//a_no�� �α����� ������� ������ ����ͼ� �׻������ no�� ã�ƾ���
		
		Object[] param = new Object[] {item.getItem_no(), item.getA_no(), item.getItem_addr(),
				item.getItem_deal_type(), item.getItem_type(), item.getItem_cost(),
				item.getItem_deposit(), item.getItem_layer(), item.getItem_size(),
				item.getItem_manage_cost(), item.getItem_park_tf(), item.getItem_ele_tf(),
				item.getItem_pet_tf(), item.getItem_avail_date(), item.getItem_close_tf(), item.getItem_hits()};
			
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}


	//UpdateItemController���� ����
	public int update(Item item) throws SQLException {
		String sql = "UPDATE ITEM "
					+ "SET item_addr=?, item_deal_type=?, item_type=?, item_cost=?, item_deposit=?, item_layer=?, item_size=?, item_manage_cost=?, item_park_tf=?, item_ele_tf=?, item_pet_tf=?, item_avail_date=?, item_close_tf=? "
					+ "WHERE item_no=?";		
		Object[] param = new Object[] {item.getItem_addr(), item.getItem_deal_type(), item.getItem_type(), 
				item.getItem_cost(), item.getItem_deposit(), item.getItem_layer(), item.getItem_size(),
				item.getItem_manage_cost(), item.getItem_park_tf(), item.getItem_ele_tf(),
				item.getItem_pet_tf(), item.getItem_avail_date(), item.getItem_close_tf(), item.getItem_no()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}


	//ListItemController���� ����
	public List<Item> findItemList() throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM ITEM ORDER BY item_no";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Item> itemList = new ArrayList<Item>();	// Item���� ����Ʈ ����
			while (rs.next()) {
				Item item = new Item(			// Item ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("item_no"),
					rs.getInt("a_no"),
					rs.getString("item_addr"),
					rs.getString("item_deal_type"),
					rs.getString("item_type"),
					rs.getInt("item_cost"),
					rs.getInt("item_deposit"),
					rs.getInt("item_layer"),
					rs.getInt("item_size"),
					rs.getInt("item_manage_cost"),
					rs.getString("item_park_tf"),
					rs.getString("item_ele_tf"),
					rs.getString("item_pet_tf"),
					rs.getDate("item_avail_date"),
					rs.getString("item_close_tf"),
					rs.getInt("item_hits"));	
				itemList.add(item);				// List�� Item ��ü ����
			}		
			return itemList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
	//ViewItemController���� ����
	public Item findItemUpdateHits(int item_no) {
		//1.item_hits�� ++���ش�.
		String sql1 = "UPDATE ITEM "
					+ "SET item_hits=item_hits+1 "
					+ "WHERE item_no=?";				
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {item_no});	// JDBCUtil�� update���� �Ű� ���� ����
				
		try {				
			jdbcUtil.executeUpdate();	// update �� ����
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		

		//2.item_no�� �ش��ϴ� ���� ������ ã��
		String sql2 = "SELECT a.a_name, i.item_no, i.a_no, i.item_addr, i.item_deal_type, i.item_type, i.item_cost, i.item_deposit, i.item_layer, i.item_size"
					+ ", i.item_manage_cost, i.item_park_tf, i.item_ele_tf, i.item_pet_tf, i.item_avail_date, i.item_close_tf, i.item_hits "
	        		+ "FROM ITEM i, AGENT a "
	        		+ "WHERE i.a_no=a.a_no and i.item_no=?";
		jdbcUtil.setSqlAndParameters(sql2, new Object[] {item_no});		// JDBCUtil�� query�� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			if (rs.next()) {
				Item item = new Item(			// Item ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("a_name"),
					item_no,
					rs.getInt("a_no"),
					rs.getString("item_addr"),
					rs.getString("item_deal_type"),
					rs.getString("item_type"),
					rs.getInt("item_cost"),
					rs.getInt("item_deposit"),
					rs.getInt("item_layer"),
					rs.getInt("item_size"),
					rs.getInt("item_manage_cost"),
					rs.getString("item_park_tf"),
					rs.getString("item_ele_tf"),
					rs.getString("item_pet_tf"),
					rs.getDate("item_avail_date"),
					rs.getString("item_close_tf"),
					rs.getInt("item_hits"));
				return item;				// List�� Item ��ü ����
			}						
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
	
		return null;
	}

	
	//ViewItemController���� ����
	public Item findItem(int item_no) {
		String sql = "SELECT a.a_name, i.item_no, i.a_no, i.item_addr, i.item_deal_type, i.item_type, i.item_cost, i.item_deposit, i.item_layer, i.item_size"
				+ ", i.item_manage_cost, i.item_park_tf, i.item_ele_tf, i.item_pet_tf, i.item_avail_date, i.item_close_tf, i.item_hits "
        		+ "FROM ITEM i, AGENT a "
        		+ "WHERE i.a_no=a.a_no and i.item_no=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {item_no});		// JDBCUtil�� query�� ����
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			if (rs.next()) {
				Item item = new Item(			// Item ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("a_name"),
						item_no,
						rs.getInt("a_no"),
						rs.getString("item_addr"),
						rs.getString("item_deal_type"),
						rs.getString("item_type"),
						rs.getInt("item_cost"),
						rs.getInt("item_deposit"),
						rs.getInt("item_layer"),
						rs.getInt("item_size"),
						rs.getInt("item_manage_cost"),
						rs.getString("item_park_tf"),
						rs.getString("item_ele_tf"),
						rs.getString("item_pet_tf"),
						rs.getDate("item_avail_date"),
						rs.getString("item_close_tf"),
						rs.getInt("item_hits"));	
				return item;				// List�� Item ��ü ����
			}						
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
		
	
	//�����ص� �ɵ�?  �־��� �ּҿ� �ش��ϴ� ������ �����ϴ��� �˻�
	public boolean existingItem(String item_addr) {
		String sql = "SELECT count(*) FROM ITEM WHERE item_addr=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {item_addr});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count >= 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

	
	
	//DeleteItemController���� ����
	public int remove(int item_no) {
		//1. ���� ������ basket ���̺� �ִ� ���� ���� ����
		String sql1 = "DELETE BASKET WHERE item_no=?";
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {item_no});
		
		try {
			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// resource ��ȯ
		}
		
		//2. item ���̺��� item_no�� �ش��ϴ� ���� �����Ѵ�
		String sq2 = "DELETE FROM ITEM WHERE item_no=?";
		jdbcUtil.setSqlAndParameters(sq2, new Object[] {item_no});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	
	//ListItemController���� ����
	public List<Item> findItemList(String item_addr) {
		Item item;
		String sql1 = "SELECT item_no, item_addr FROM ITEM";
		jdbcUtil.setSqlAndParameters(sql1, null);
		
		List<Item> itemList = new ArrayList<Item>();
		List<Item> itemSelectList = new ArrayList<Item>();
		List<Item> itemChoiceList = new ArrayList<Item>();
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {						
				item = new Item(		// Item ��ü�� �����Ͽ� ���� ������ ����
						rs.getInt("item_no"),
						rs.getString("item_addr"));	
				itemList.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}

		for(int i = 0; i < itemList.size(); i++) {
			
			if(itemList.get(i).getItem_addr().contains(item_addr))	{	//item_addr�� item�� �ּҿ� �Ϻκ� ��ġ
				item = new Item(itemList.get(i).getItem_no(), itemList.get(i).getItem_addr());	
				itemSelectList.add(item);
			}
		}
		
		for(int i = 0; i < itemSelectList.size(); i++) {
			String sql2 = "SELECT item_no, item_addr, item_deal_type, item_type, item_cost, item_deposit, item_hits "
						+ "FROM ITEM WHERE item_no=?";
			jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemSelectList.get(i).getItem_no()});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query ����
				while (rs.next()) {						// ���� ���� �߰�
					item = new Item(		// Item ��ü�� �����Ͽ� ���� ������ ����
							rs.getInt("item_no"),
							rs.getString("item_addr"),
							rs.getString("item_deal_type"),
							rs.getString("item_type"),
							rs.getInt("item_cost"),
							rs.getInt("item_deposit"),
							rs.getInt("item_hits"));	
					itemChoiceList.add(item);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
		}
	
		return itemChoiceList;
	}

	
	//ListItemController���� ����
	public List<Item> findConditionItem(String item_addr, String dealType, String roomType, int costRange, int depositRange) {
		Item item;
		
		//1. �˻����� �Ϻκ� ���� ������ �������� �̾ƿ´�.
		List<Item> itemFindFirst = findItemList(item_addr);
		
		//2. �ŷ� ������ �� ������ ��ġ�ϴ� ������ �̾ƿ´�.	
		int[] findItemNo = new int[itemFindFirst.size()];
		List<Item> itemFindSecond = new ArrayList<Item>();
		for(int i = 0; i < itemFindFirst.size(); i++) {
		  String sql1 = "SELECT item_no, item_addr, item_deal_type, item_type, item_cost, item_deposit, item_hits FROM ITEM ";
		  if(dealType.equals("")) {
			if(roomType.equals("")) {
				sql1 += "WHERE item_addr=?";
				jdbcUtil.setSqlAndParameters(sql1, new Object[] {itemFindFirst.get(i).getItem_addr()});
			}
			else {
				sql1 += "WHERE item_addr=? AND item_type=?";
				jdbcUtil.setSqlAndParameters(sql1, new Object[] {itemFindFirst.get(i).getItem_addr(), roomType});
			}
		  }
		  else {
			if(roomType.equals("")) {
				sql1 += "WHERE item_addr=? AND item_deal_type=?";
				jdbcUtil.setSqlAndParameters(sql1, new Object[] {itemFindFirst.get(i).getItem_addr(), dealType});
			}
			else {
				sql1 += "WHERE item_addr=? AND item_deal_type=? AND item_type=?";
				jdbcUtil.setSqlAndParameters(sql1, new Object[] {itemFindFirst.get(i).getItem_addr(), dealType, roomType});
			}
		  }
		
		  try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {						// ���� ���� �߰�
				findItemNo[i] = rs.getInt("item_no");
				item =  new Item(		// Item ��ü�� �����Ͽ� ���� ������ ����
						rs.getInt("item_no"),
						rs.getString("item_addr"),
						rs.getString("item_deal_type"),
						rs.getString("item_type"),
						rs.getInt("item_cost"),
						rs.getInt("item_deposit"),
						rs.getInt("item_hits"));	
				itemFindSecond.add(item);
			}
		  } catch (Exception ex) {
			ex.printStackTrace();
		  } finally {
			jdbcUtil.close();		// resource ��ȯ
		  }
		}
		

		//3. (�ŷ� ������ ������ costRange�� deposit / �ŷ� ������ ����, �ŸŸ� costRange) ���ǿ� �´� item ������ �̾ƿ´�.
		List<Item> itemFindThird = new ArrayList<Item>();
		for(int i = 0; i < findItemNo.length; i++) {
			String sql2 = "SELECT item_no, item_addr, item_deal_type, item_type, item_cost, item_deposit, item_hits FROM ITEM ";
		  if(dealType.equals("")) {
			if(roomType.equals("")) {//�ƹ��͵��ȵ�
				sql2 += "WHERE item_addr=?";
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemFindFirst.get(i).getItem_addr()});
			}
			else {//�������� �˻� ����
				sql2 += "WHERE item_addr=? AND item_type=?";
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemFindFirst.get(i).getItem_addr(), roomType});
			}
		  }
		  else {//�� �˻�����
			if(dealType.equals("����")) {
				if(costRange == 30) {
					if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 30) AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 30) AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 30) AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 30) AND (item_deposit >= 15001)";
					else if(depositRange == 0)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 30)";
				}
				else if(costRange == 50) {
					if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 31 AND 50) AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 31 AND 50) AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 31 AND 50) AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 31 AND 50) AND (item_deposit >= 15001)";
					else if(depositRange == 0)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 31 AND 50)";
				}
				else if(costRange == 70) {
					if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 51 AND 70) AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 51 AND 70) AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 51 AND 70) AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 51 AND 70) AND (item_deposit >= 15001)";
					else if(depositRange == 0)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 51 AND 70)";
				}
				else if(costRange == 90) {
					if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 71 AND 90) AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 71 AND 90) AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 71 AND 90) AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 71 AND 90) AND (item_deposit >= 15001)";
					else if(depositRange == 0)
						sql2 += "WHERE item_no=? AND (item_cost BETWEEN 71 AND 90)";
				}
				else if(costRange == 91) {
					if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_cost >= 91) AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000)
						sql2 += "WHERE item_no=? AND (item_cost >= 91) AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000)
						sql2 += "WHERE item_no=? AND (item_cost >= 91) AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001)
						sql2 += "WHERE item_no=? AND (item_cost >= 91) AND (item_deposit >= 15001)";
					else if(depositRange == 0)
						sql2 += "WHERE item_no=? AND (item_cost >= 91)";	
				}
				else if(costRange == 0) {
					if(depositRange == 0) 
						sql2 += "WHERE item_no=?";
					else if(depositRange == 5000) 
						sql2 += "WHERE item_no=? AND (item_deposit BETWEEN 0 AND 5000)";
					else if(depositRange == 10000)
						sql2 += "WHERE item_no=? AND (item_deposit BETWEEN 5001 AND 10000)";
					else if(depositRange == 15000)
						sql2 += "WHERE item_no=? AND (item_deposit BETWEEN 10001 AND 15000)";
					else if(depositRange == 15001)
						sql2 += "WHERE item_no=? AND (item_deposit >= 15001)";
				}
			  }
					
			  if(dealType.equals("����")){
				if(costRange == 10000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 10000)";
				else if(costRange == 20000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 10001 AND 20000)";
				else if(costRange == 30000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 20001 AND 30000)";
				else if(costRange == 40000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 30001 AND 40000)";
				else if(costRange == 50000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 40001 AND 50000)";
				else if(costRange == 50001) 
					sql2 += "WHERE item_no=? AND item_cost >= 50001";
				else if(costRange == 0)
					sql2 += "WHERE item_no=?";
			  }
					
			  if(dealType.equals("�Ÿ�")) {
				if(costRange == 50000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 0 AND 50000)";
				else if(costRange == 100000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 50001 AND 100000)";
				else if(costRange == 150000) 
					sql2 += "WHERE item_no=? AND (item_cost BETWEEN 100001 AND 150000)";
				else if(costRange == 150001) 
					sql2 += "WHERE item_no=? AND item_cost >= 150001";
				else if(costRange == 0)
					sql2 += "WHERE item_no=?";
			  }
				
			  jdbcUtil.setSqlAndParameters(sql2, new Object[] {findItemNo[i]});
		  }
		
		  try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {						
				item = new Item(		// Item ��ü�� �����Ͽ� ���� ������ ����
					rs.getInt("item_no"),
					rs.getString("item_addr"),
					rs.getString("item_deal_type"),
					rs.getString("item_type"),
					rs.getInt("item_cost"),
					rs.getInt("item_deposit"),
					rs.getInt("item_hits"));	
				itemFindThird.add(item);
			}
		  } catch (Exception ex) {
			ex.printStackTrace();
		  } finally {
			jdbcUtil.close();		// resource ��ȯ
		  }
		}
		System.out.println("����° : " + itemFindThird);
		return itemFindThird;
	}

	
	//InsertItemController���� ����
	public int findLastItemNo() {
		String sql = "SELECT item_no FROM ITEM";
		jdbcUtil.setSqlAndParameters(sql, null);
		int item_no = 0;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {						
				item_no = rs.getInt("item_no");	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		
		return item_no + 1;
	}


	//UsersItemController���� ����
	public List<Item> findUsersItemList(int a_no) {
		String sql = "SELECT * FROM ITEM WHERE a_no=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Item> itemList = new ArrayList<Item>();	// Item���� ����Ʈ ����
			while (rs.next()) {
				Item item = new Item(			// Item ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("item_no"),
					rs.getInt("a_no"),
					rs.getString("item_addr"),
					rs.getString("item_deal_type"),
					rs.getString("item_type"),
					rs.getInt("item_cost"),
					rs.getInt("item_deposit"),
					rs.getInt("item_layer"),
					rs.getInt("item_size"),
					rs.getInt("item_manage_cost"),
					rs.getString("item_park_tf"),
					rs.getString("item_ele_tf"),
					rs.getString("item_pet_tf"),
					rs.getDate("item_avail_date"),
					rs.getString("item_close_tf"),
					rs.getInt("item_hits"));	
				itemList.add(item);				// List�� Item ��ü ����
			}		
			return itemList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}
