package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.counsel.CounselDetailController;
import controller.counsel.CounselListController;
import controller.counsel.CreateScheduleController;
import controller.counsel.ReservationCounsel;
import controller.counsel.ScheduleListController;
import controller.customer.*;
import controller.item.DeleteItemController;
import controller.item.InsertItemController;
import controller.item.ListItemController;
import controller.item.UpdateItemController;
import controller.item.UsersItemController;
import controller.item.ViewItemController;
import controller.point.ListPayLogController;
import controller.wish.CreateWishController;
import controller.wish.RecommandController;
import controller.wish.RemoveWishController;
import controller.wish.WishListController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/main", new ForwardController("/user/main.jsp"));
        mappings.put("/user/operPage", new ForwardController("/user/operPage.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/userInfo", new UserInfoController());
        mappings.put("/user/counselList", new CounselListController());
        mappings.put("/user/wish", new WishListController());
        mappings.put("/user/userInfo/update", new UpdateUserController());
        mappings.put("/user/recommand", new RecommandController());
        mappings.put("/user/counselDetail", new CounselDetailController());
        mappings.put("/user/wish/create", new CreateWishController());
        mappings.put("/user/wish/remove", new RemoveWishController());
        mappings.put("/user/scheduleList", new ScheduleListController());
      
        mappings.put("/user/schedule", new CreateScheduleController());
     
        mappings.put("/item/list", new ListItemController());
        mappings.put("/item/view", new ViewItemController());
        mappings.put("/item/update", new UpdateItemController());
        mappings.put("/item/delete", new DeleteItemController());
        mappings.put("/item/insert/form", new ForwardController("/item/insertForm.jsp"));
        mappings.put("/item/insert", new InsertItemController());
        mappings.put("/item/counsel/form", new ReservationCounsel());
        mappings.put("/item/userItem", new UsersItemController());
        mappings.put("/item/usersItemView", new ViewItemController());

        mappings.put("/point/pointList", new ListPayLogController());
        
        logger.info("Initialized Request Mapping!");

    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
