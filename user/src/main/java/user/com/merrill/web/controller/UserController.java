package user.com.merrill.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import user.com.merrill.dao.entity.User;
import user.com.merrill.service.IUserService;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 梅峰鑫
 * Date: 2019-02-16
 * Time: 17:01
 * Description:
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public Object getUser(@RequestBody Map<String, String> map){
        String userID = map.get("userID");
        Long id = Long.valueOf(userID);
        User user = userService.getUserByID(id);
        return user;
    }
}
