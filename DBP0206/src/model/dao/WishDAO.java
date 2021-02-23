package model.dao;
import model.Basket;
import java.util.Random;
import model.dao.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Item;
public class WishDAO {
	private JDBCUtil jdbcUtil = null;
	public WishDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	public int random_num(int a)
	{
		Random r = new Random();
		return r.nextInt(a);
	}
	public String BestRoomType(int c_no) {
			String sql = "SELECT item.item_type FROM item, basket WHERE basket.c_no=? and basket.item_no=item.item_no";
	    	String[] list = new String[10];
	    	for(int i = 0; i < 10; i++)
	    	{
	    		list[i] = "";
	    	}
	        Object[] param = new Object[] {c_no};				
	    	jdbcUtil.setSqlAndParameters(sql, param);			
	    	int i = 0, o=0,a=0,or=0;
			try {
				ResultSet rs = jdbcUtil.executeQuery();				
					
				while (rs.next()) {
					list[i++] =rs.getString("ITEM_TYPE");	
				}							
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			i =0;
			while(list[i] !="")
			{
				if(list[i].equals("���ǽ���")==true)
					o++;
				else if(list[i].equals("����Ʈ"))
					a++;
				else if(list[i].equals("����"))
					or++;
				i++;
			}
			for(int d = 0; list[d] != ""; d++)
				System.out.println(list[d]);
			System.out.println("o,a,or= "+o+a+or);
			if(o > a && o > or)
				return "���ǽ���";
			else if(a > o && a > or)
				return "����Ʈ";
			else if(or > o && or > a)
				return "����";
			else if((or == o && a < o))
			{
				String[] t = {"����","���ǽ���"};
				return t[random_num(2)];
			}
			else if(or == o && o == a && or == a) {
				String[] list_type = {"����","���ǽ���","����Ʈ"};
				return list_type[random_num(3)];
			}
			else if(or == a && o < a)	{
				String[] t = {"����","����Ʈ"};
				return t[random_num(2)];
			}
			else if(a == o && or < a)	{
				String[] t = {"���ǽ���","����Ʈ"};
				return t[random_num(2)];
			}	
			return null;
	}
	public String BestAddr(int c_no) {
		String sql = "SELECT item.item_addr FROM item, basket WHERE basket.c_no=? and basket.item_no=item.item_no";
    	String[] list = new String[10];
    	for(int i = 0; i < 10; i++)
    	{
    		list[i] = "";
    	}
        Object[] param = new Object[] {c_no};				
    	jdbcUtil.setSqlAndParameters(sql, param);			
    	int i =0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
				
			while (rs.next()) {
				list[i++] =rs.getString("ITEM_ADDR");	
			}							
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		List<String> addr = new ArrayList<String>();
		int num = 0;
		for(i = 0; list[i] != ""; i++) {
			String[] tmp = list[i].split(" ");
			addr.add(tmp[0]);
			num++;
		}
		
		for(i = 0; list[i] != ""; i++) {
			for(int j = i; j < num; j++)
				if(addr.get(i).equals(addr.get(j)))
				{
					return addr.get(i);
				}
		}
		return addr.get(random_num(num));
	}
	public List<Item> recommand(int c_no) throws SQLException {
		// TODO Auto-generated method stub
		List<Item> list = findwishList(c_no);
		
		int cost_total_M = 0;
		int size_total_M = 0;
		int cost_total_J = 0;
		int size_total_J = 0;
		int cost_total_W = 0;
		int size_total_W = 0;
		int num_w = 0;
		int num_j = 0;
		int num_m = 0;
		int depo_total = 0;
		int num = list.size();
		for (int i = 0 ; i < num; i++)
		{
			if(list.get(i).getItem_deal_type().equals("����"))
			{
				num_w++;
				cost_total_W += list.get(i).getItem_cost();
				size_total_W += list.get(i).getItem_size();
			}
			else if(list.get(i).getItem_deal_type().equals("����"))
			{
				num_j++;
				cost_total_J += list.get(i).getItem_cost();
				size_total_J += list.get(i).getItem_size();
			}
			else
			{
				num_m++;
				cost_total_M += list.get(i).getItem_cost();
				size_total_M += list.get(i).getItem_size();
			}
		}
		for(int i = 0; i < num; i++) {
			depo_total += list.get(i).getItem_deposit();
		}
		double avg_size_w = (double)size_total_W / num_w;
		double avg_cost_w = (double)cost_total_W / num_w;
		double avg_size_j = (double)size_total_J / num_j;
		double avg_cost_j = (double)cost_total_J / num_j;
		double avg_size_m = (double)size_total_M / num_m;
		double avg_cost_m = (double)cost_total_M / num_m;
		double avg_depo =  (double)depo_total / num_w;
		
		String sql = "";
		List<Item> recomm = new ArrayList<Item>();
		for(int i = 0; i < list.size(); i++) {

			if(list.get(i).getItem_deal_type().equals("����"))
				sql = "SELECT item.item_no, item.item_addr, item.item_deal_type, item.item_size, item.item_cost, NVL(item.item_deposit,0) AS item_deposit from item "+
						"where  item_cost < "+ avg_cost_w * 1.3
						+ " and item_cost > "+avg_cost_w* 0.7+" and item_deposit <"+ avg_depo * 1.3
						+ " and item_deposit > "+ avg_depo * 0.7+ " and item_size >"+ avg_size_w * 0.7 +" and item_size < "+avg_size_w * 1.3 +" and item_type ='"+ BestRoomType(c_no)+"'"
						+ "and item_addr like '%"+BestAddr(c_no)+"%'" ;
			
			else if(list.get(i).getItem_deal_type().equals("����")) {
				sql = "SELECT item.item_no, item.item_addr, item.item_deal_type, item.item_size, item.item_cost, NVL(item.item_deposit,0) AS item_deposit from item "+
						"where  item_cost < "+ avg_cost_j * 1.3 
						+ " and item_cost > "+avg_cost_j*0.7+ " and item_size >"+ avg_size_j * 0.7 +" and item_size < "+avg_size_j * 1.3 +" and item_type ='"+ BestRoomType(c_no)+"'"
				+ "and item_addr like '%"+BestAddr(c_no)+"%'" ;
			}
			else{
				sql = "SELECT item.item_no, item.item_addr, item.item_deal_type, item.item_size, item.item_cost, NVL(item.item_deposit,0) AS item_deposit from item "+
						"where  item_cost < "+ avg_cost_m * 1.3 
						+ " and item_cost > "+avg_cost_m * 0.7+ " and item_size >"+ avg_size_m * 0.7 +" and item_size < "+avg_size_m * 1.3+" and item_type ='"+ BestRoomType(c_no)+"'"
				+ "and item_addr like '%"+BestAddr(c_no)+"%'" ;
			}
			System.out.println(sql);
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			while (rs.next()){
				Item a = new Item(			
						rs.getInt("ITEM_NO"),
						rs.getString("ITEM_ADDR"),
						rs.getString("ITEM_DEAL_TYPE"),
						rs.getInt("ITEM_SIZE"),
						rs.getInt("ITEM_COST"),
						rs.getInt("ITEM_DEPOSIT"));
				recomm.add(a);				// List�� User ��ü ����
			}		
			for(int a = 0; a < list.size(); a++)
			{
				for(int b=0; b < recomm.size(); b++)
					if(list.get(a).getItem_no() == recomm.get(b).getItem_no()) {
						recomm.remove(b);
					}
			}
			List<Item> recomm_ran = new ArrayList<Item>();
			
			if(recomm.size() <= 5)
				return recomm;
			else
				for(int r =0; r < 5;  r++) {
					int r_num = random_num(recomm.size());
					recomm_ran.add(recomm.get(r_num));
					recomm.remove(r_num);
				}
				return recomm_ran;					
		
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(sql);
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		}
		return recomm;
	}
	//���� ���� ���� ���ǽ���, ����, ����Ʈ ���� ���� ���� �� �� �ϳ��� �������� ������, ~�� �� �������� << �̰��� ���� �� ���� ������ ������?
	public int countBasket(int c_no) throws SQLException{
		List<Item> list = findwishList(c_no);
		return list.size();
	}
	public List<Item> findwishList(int c_no) throws SQLException {
        String sql = "SELECT item.item_no, item.item_addr, item.item_deal_type, item.item_size, item.item_cost, NVL(item.item_deposit,0) AS item_deposit FROM item, basket WHERE basket.c_no=? and basket.item_no=item.item_no";
    	List<Item> wList = new ArrayList<Item>();
        Object[] param = new Object[] {c_no};				
    	jdbcUtil.setSqlAndParameters(sql, param);			
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				// User���� ����Ʈ ����
			while (rs.next()) {
				Item i = new Item(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("ITEM_NO"),
					rs.getString("ITEM_ADDR"),
					rs.getString("ITEM_DEAL_TYPE"),
					rs.getInt("ITEM_SIZE"),
					rs.getInt("ITEM_COST"),
					rs.getInt("ITEM_DEPOSIT")
					);
				wList.add(i);				// List�� User ��ü ����
			}		
			return wList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	public int create(Basket i) {
		String sql = "INSERT INTO BASKET VALUES (?, ?)";		
		Object[] param = new Object[] { i.getItem_no(), i.getC_no() };				
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
	public int remove(Basket i) {
	
		String sql = "DELETE FROM BASKET WHERE item_no=? and c_no=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {i.getItem_no(), i.getC_no()});	// JDBCUtil�� delete���� �Ű� ���� ����
		System.out.println(i.getItem_no()+i.getC_no());
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
	public boolean existingWish(int c_no, int item_no) throws SQLException {
		String sql = "SELECT count(*) FROM BASKET WHERE c_no= ? and item_no = ?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {c_no,item_no});	// JDBCUtil�� query���� �Ű� ���� ����

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
}