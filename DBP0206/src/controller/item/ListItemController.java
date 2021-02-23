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
    			
    			//itemList 객체와 검색한 주소값을 request에 저장하여 전달
    			request.setAttribute("itemList", itemList);
   			
    			//물건 리스트 화면으로 이동(forwarding)
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
    				System.out.println("주소는 : " + address);
    				System.out.println("거래 종류는 : " + dealType);
    				System.out.println("방 종류는 : " + roomType);
    				System.out.println("가격 : " + cost);
    				System.out.println("보증금 : " + depo);
    				itemList = manager.findConditionItem(address, dealType, roomType, cost, depo);
    			}
    		
    			//itemList 객체와 검색한 주소값을 request에 저장하여 전달
   				request.setAttribute("itemList", itemList);
   				request.setAttribute("address", address);
    		
   				String encodedString = URLEncoder.encode (address, "UTF-8");
   				//물건 리스트 화면으로 이동(forwarding)
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
