package com.tan.community.controller;

import com.tan.community.entity.DiscussPost;
import com.tan.community.entity.Page;
import com.tan.community.entity.User;
import com.tan.community.service.DiscussPostService;
import com.tan.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 谭浩
 * @version 1.0
 */
//@Controller
//public class HomeController {
//    @Autowired
//    private DiscussPostService discussPostService;
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(path = "/index", method = RequestMethod.GET)
//    public String getIndexPage(Model model){
//        List <DiscussPost> list = discussPostService.findDiscussPosts(0,0,10);
//        List<Map<String,Object>> discussPosts = new ArrayList<>();//上面查询的集合userid只是id而不是用户名，新建一个集合
//
//        if(list != null){
//            for(DiscussPost post : list){
//                Map<String,Object> map = new HashMap<>();
//                map.put("post",post);
//                User user = userService.findUserById(post.getUserId());
//                map.put("user",user);
//                discussPosts.add(map);
//            }
//        }
//        model.addAllAttributes(discussPosts);
//        List<DiscussPost> list = discussPostService.findDiscussPosts(0, 0, 10);
//        List<Map<String, Object>> discussPosts = new ArrayList<>();
//        if (list != null) {
//            for (DiscussPost post : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("post", post);
//                User user = userService.findUserById(post.getUserId());
//                map.put("user", user);
//                discussPosts.add(map);
//            }
//        }
//        model.addAttribute("discussPosts", discussPosts);
//        return "/index";
//    }
//}
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();//discussPosts存储帖子信息 包含帖子，和user
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }

}
