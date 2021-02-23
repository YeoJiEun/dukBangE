package controller.item;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Item;
import model.service.ItemManager;

public class ListItemController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String address = request.getParameter("address");
    	
    	if(address == null || address == "") {
    		try {
    			ItemManager manager = ItemManager.getInstance();
    			List<Item> itemList = new ArrayList<Item>();
    	
    			if(request.getParameter("condition") == null || request.getParameter("condition") == "") {
           			itemList = manager.findItemList();
        		}
        		else {
        			String dealType = request.getParameter("dealType");
    				String roomType = request.getParameter("roomType");
    	
    				String[] array1, array2;
    				int cost, depo;
        		
    				String costRange = request.getParameter("costRange");
    				array1 = costRange.split(" ~ ");
    				if(costRange.startsWith(" ~ ")) 
    					cost = Integer.parseInt(array1[1]);
    				else 
    					cost = Integer.parseInt(array1[0]);
    			
    				String deposit = request.getParameter("depositRange");
    				if(deposit == null || deposit == "")
    					depo = 0;
    				else {
    					array2 = deposit.split(" ~ ");
    					if(deposit.startsWith(" ~ ")) 
    						depo = Integer.parseInt(array2[1]);
    					else
    						depo = Integer.parseInt(array2[0]);
    				}
    				itemList = manager.findConditionItem(address, dealType, roomType, cost, depo);
        		}
    			
    			//itemList ��ü�� �˻��� �ּҰ��� request�� �����Ͽ� ����
    			request.setAttribute("itemList", itemList);
   			
    			//���� ����Ʈ ȭ������ �̵�(forwarding)
    			return "/item/list.jsp";  
    		}
    		catch(Exception e) {
    			request.setAttribute("searchFailed", true);
    			request.setAttribute("exception", e);
    			return "/item/searchForm.jsp";
    		}
		}
    	else {
    		try {
    			ItemManager manager = ItemManager.getInstance();
    			List<Item> itemList = new ArrayList<Item>();
    		
    			if(request.getParameter("condition") == null || request.getParameter("condition") == "") {
    				itemList = manager.findItemList(address);
    			}
    			else {
    				String dealType = request.getParameter("dealType");
    				String roomType = request.getParameter("roomType");
    	
    				String[] array1, array2;
    				int cost, depo;
        		
    				String costRange = request.getParameter("costRange");
    				if(costRange == null || costRange == "")
    					cost = 0;
    				else {
    					array1 = costRange.split(" ~ ");
    					if(costRange.startsWith(" ~ ")) 
    						cost = Integer.parseInt(array1[1]);
    					else 
    						cost = Integer.parseInt(array1[0]);
    				}
    			
    				String deposit = request.getParameter("depositRange");
    				if(deposit == null || deposit == "")
    					depo = 0;
    				else {
    					array2 = deposit.split(" ~ ");
    					if(deposit.startsWith(" ~ ")) 
    						depo = Integer.parseInt(array2[1]);
    					else
    						depo = Integer.parseInt(array2[0]);
    				}
    				System.out.println("�ּҴ� : " + address);
    				System.out.println("�ŷ� ������ : " + dealType);
    				System.out.println("�� ������ : " + roomType);
    				System.out.println("���� : " + cost);
    				System.out.println("������ : " + depo);
    				itemList = manager.findConditionItem(address, dealType, roomType, cost, depo);
    			}
    		
    			//itemList ��ü�� �˻��� �ּҰ��� request�� �����Ͽ� ����
   				request.setAttribute("itemList", itemList);
   				request.setAttribute("address", address);
    		
   				String encodedString = URLEncoder.encode (address, "UTF-8");
   				//���� ����Ʈ ȭ������ �̵�(forwarding)
   				return "/item/list.jsp?address=" + encodedString;  
      	
   			}
   			catch(Exception e) {
   				request.setAttribute("searchFailed", true);
   				request.setAttribute("exception", e);
   				return "/item/searchForm.jsp";
   			}
    	}
    }
}
