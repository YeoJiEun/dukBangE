package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Item;

/**
 * 아이템 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * ITEM 테이블에 물건 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class ItemDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ItemDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	
	//InsertItemController에서 사용됨
	public int insert(Item item) throws SQLException {
		String sql = "INSERT INTO ITEM "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		
		//a_no은 로그인한 사용자의 정보를 끌어와서 그사용자의 no을 찾아야함
		
		Object[] param = new Object[] {item.getItem_no(), item.getA_no(), item.getItem_addr(),
				item.getItem_deal_type(), item.getItem_type(), item.getItem_cost(),
				item.getItem_deposit(), item.getItem_layer(), item.getItem_size(),
				item.getItem_manage_cost(), item.getItem_park_tf(), item.getItem_ele_tf(),
				item.getItem_pet_tf(), item.getItem_avail_date(), item.getItem_close_tf(), item.getItem_hits()};
			
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}


	//UpdateItemController에서 사용됨
	public int update(Item item) throws SQLException {
		String sql = "UPDATE ITEM "
					+ "SET item_addr=?, item_deal_type=?, item_type=?, item_cost=?, item_deposit=?, item_layer=?, item_size=?, item_manage_cost=?, item_park_tf=?, item_ele_tf=?, item_pet_tf=?, item_avail_date=?, item_close_tf=? "
					+ "WHERE item_no=?";		
		Object[] param = new Object[] {item.getItem_addr(), item.getItem_deal_type(), item.getItem_type(), 
				item.getItem_cost(), item.getItem_deposit(), item.getItem_layer(), item.getItem_size(),
				item.getItem_manage_cost(), item.getItem_park_tf(), item.getItem_ele_tf(),
				item.getItem_pet_tf(), item.getItem_avail_date(), item.getItem_close_tf(), item.getItem_no()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}


	//ListItemController에서 사용됨
	public List<Item> findItemList() throws SQLException {
        String sql = "SELECT * " 
        		   + "FROM ITEM ORDER BY item_no";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Item> itemList = new ArrayList<Item>();	// Item들의 리스트 생성
			while (rs.next()) {
				Item item = new Item(			// Item 객체를 생성하여 현재 행의 정보를 저장
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
				itemList.add(item);				// List에 Item 객체 저장
			}		
			return itemList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
	//ViewItemController에서 사용됨
	public Item findItemUpdateHits(int item_no) {
		//1.item_hits를 ++해준다.
		String sql1 = "UPDATE ITEM "
					+ "SET item_hits=item_hits+1 "
					+ "WHERE item_no=?";				
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {item_no});	// JDBCUtil에 update문과 매개 변수 설정
				
		try {				
			jdbcUtil.executeUpdate();	// update 문 실행
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		

		//2.item_no에 해당하는 물건 정보를 찾기
		String sql2 = "SELECT a.a_name, i.item_no, i.a_no, i.item_addr, i.item_deal_type, i.item_type, i.item_cost, i.item_deposit, i.item_layer, i.item_size"
					+ ", i.item_manage_cost, i.item_park_tf, i.item_ele_tf, i.item_pet_tf, i.item_avail_date, i.item_close_tf, i.item_hits "
	        		+ "FROM ITEM i, AGENT a "
	        		+ "WHERE i.a_no=a.a_no and i.item_no=?";
		jdbcUtil.setSqlAndParameters(sql2, new Object[] {item_no});		// JDBCUtil에 query문 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			if (rs.next()) {
				Item item = new Item(			// Item 객체를 생성하여 현재 행의 정보를 저장
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
				return item;				// List에 Item 객체 저장
			}						
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
	
		return null;
	}

	
	//ViewItemController에서 사용됨
	public Item findItem(int item_no) {
		String sql = "SELECT a.a_name, i.item_no, i.a_no, i.item_addr, i.item_deal_type, i.item_type, i.item_cost, i.item_deposit, i.item_layer, i.item_size"
				+ ", i.item_manage_cost, i.item_park_tf, i.item_ele_tf, i.item_pet_tf, i.item_avail_date, i.item_close_tf, i.item_hits "
        		+ "FROM ITEM i, AGENT a "
        		+ "WHERE i.a_no=a.a_no and i.item_no=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {item_no});		// JDBCUtil에 query문 설정
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			if (rs.next()) {
				Item item = new Item(			// Item 객체를 생성하여 현재 행의 정보를 저장
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
				return item;				// List에 Item 객체 저장
			}						
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
		
	
	//삭제해도 될듯?  주어진 주소에 해당하는 물건이 존재하는지 검사
	public boolean existingItem(String item_addr) {
		String sql = "SELECT count(*) FROM ITEM WHERE item_addr=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {item_addr});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count >= 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

	
	
	//DeleteItemController에서 사용됨
	public int remove(int item_no) {
		//1. 물건 삭제시 basket 테이블에 있는 물건 먼저 삭제
		String sql1 = "DELETE BASKET WHERE item_no=?";
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {item_no});
		
		try {
			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// resource 반환
		}
		
		//2. item 테이블에서 item_no에 해당하는 열을 삭제한다
		String sq2 = "DELETE FROM ITEM WHERE item_no=?";
		jdbcUtil.setSqlAndParameters(sq2, new Object[] {item_no});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	
	//ListItemController에서 사용됨
	public List<Item> findItemList(String item_addr) {
		Item item;
		String sql1 = "SELECT item_no, item_addr FROM ITEM";
		jdbcUtil.setSqlAndParameters(sql1, null);
		
		List<Item> itemList = new ArrayList<Item>();
		List<Item> itemSelectList = new ArrayList<Item>();
		List<Item> itemChoiceList = new ArrayList<Item>();
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			while (rs.next()) {						
				item = new Item(		// Item 객체를 생성하여 물건 정보를 저장
						rs.getInt("item_no"),
						rs.getString("item_addr"));	
				itemList.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}

		for(int i = 0; i < itemList.size(); i++) {
			
			if(itemList.get(i).getItem_addr().contains(item_addr))	{	//item_addr이 item의 주소와 일부분 일치
				item = new Item(itemList.get(i).getItem_no(), itemList.get(i).getItem_addr());	
				itemSelectList.add(item);
			}
		}
		
		for(int i = 0; i < itemSelectList.size(); i++) {
			String sql2 = "SELECT item_no, item_addr, item_deal_type, item_type, item_cost, item_deposit, item_hits "
						+ "FROM ITEM WHERE item_no=?";
			jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemSelectList.get(i).getItem_no()});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 실행
				while (rs.next()) {						// 물건 정보 발견
					item = new Item(		// Item 객체를 생성하여 물건 정보를 저장
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
				jdbcUtil.close();		// resource 반환
			}
		}
	
		return itemChoiceList;
	}

	
	//ListItemController에서 사용됨
	public List<Item> findConditionItem(String item_addr, String dealType, String roomType, int costRange, int depositRange) {
		Item item;
		
		//1. 검색값이 일부분 들어가는 물건의 정보들을 뽑아온다.
		List<Item> itemFindFirst = findItemList(item_addr);
		
		//2. 거래 종류와 방 종류에 일치하는 정보를 뽑아온다.	
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
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			while (rs.next()) {						// 물건 정보 발견
				findItemNo[i] = rs.getInt("item_no");
				item =  new Item(		// Item 객체를 생성하여 물건 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		  }
		}
		

		//3. (거래 종류가 월세면 costRange와 deposit / 거래 종류가 전세, 매매면 costRange) 조건에 맞는 item 정보를 뽑아온다.
		List<Item> itemFindThird = new ArrayList<Item>();
		for(int i = 0; i < findItemNo.length; i++) {
			String sql2 = "SELECT item_no, item_addr, item_deal_type, item_type, item_cost, item_deposit, item_hits FROM ITEM ";
		  if(dealType.equals("")) {
			if(roomType.equals("")) {//아무것도안됨
				sql2 += "WHERE item_addr=?";
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemFindFirst.get(i).getItem_addr()});
			}
			else {//방종류만 검색 가능
				sql2 += "WHERE item_addr=? AND item_type=?";
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {itemFindFirst.get(i).getItem_addr(), roomType});
			}
		  }
		  else {//다 검색가능
			if(dealType.equals("월세")) {
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
					
			  if(dealType.equals("전세")){
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
					
			  if(dealType.equals("매매")) {
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
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			while (rs.next()) {						
				item = new Item(		// Item 객체를 생성하여 물건 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		  }
		}
		System.out.println("세번째 : " + itemFindThird);
		return itemFindThird;
	}

	
	//InsertItemController에서 사용됨
	public int findLastItemNo() {
		String sql = "SELECT item_no FROM ITEM";
		jdbcUtil.setSqlAndParameters(sql, null);
		int item_no = 0;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			while (rs.next()) {						
				item_no = rs.getInt("item_no");	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return item_no + 1;
	}


	//UsersItemController에서 사용됨
	public List<Item> findUsersItemList(int a_no) {
		String sql = "SELECT * FROM ITEM WHERE a_no=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Item> itemList = new ArrayList<Item>();	// Item들의 리스트 생성
			while (rs.next()) {
				Item item = new Item(			// Item 객체를 생성하여 현재 행의 정보를 저장
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
				itemList.add(item);				// List에 Item 객체 저장
			}		
			return itemList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}
