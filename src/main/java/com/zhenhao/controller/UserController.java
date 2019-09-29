package com.zhenhao.controller;

import com.zhenhao.pojo.User;
import com.zhenhao.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping("/list")
    public String userList(Model model) {
        model.addAttribute("value", "user");
        return "/user";
    }

    @RequestMapping("/add")
    public String userAdd(Model model) {
        model.addAttribute("value", "add");
        return "/user";
    }

    @RequestMapping("/delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "删除");
        return "/user";
    }
    @RequestMapping("/editUser")
    public String editUser(Model model,@RequestParam("name") String  name)
    {
        User user = null;
        if(name!=null){
            user=userService.getUserByName(name);
        }
        model.addAttribute("user",user);
        return "/user/editUser";
    }

    @ResponseBody
    @RequestMapping("/uploadImg/")
    public Map<String,Object> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
//        String filepath = request.getServletContext().getRealPath("/photo"); //原文的

//        String filepath1 = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
//        String path = ResourceUtils.getURL("classpath:").getPath();
        String filepath =System.getProperty("user.dir")+"//src//main//resources//static//imgs";
        User user = userService.getUserByName(request.getParameter("name"));

//        String filename = System.currentTimeMillis()+file.getOriginalFilename();
//        System.out.println("***"+file.getResource().getFilename());
        String filename = file.getOriginalFilename();
        File file2 = new File(filepath);

        if (!file2.exists()) {
            file2.mkdirs();
        }
        String savepath = filepath+"\\"+filename;
        user.setImgUrl("/static/imgs/"+filename);
        userService.updateUser(user);
        System.out.println("轮播图保存路径:"+savepath);
        Map map = new HashMap<String,Object>();
        try {
            //保存文件到服务器
            file.transferTo(new File(savepath));
            //保存到数据库

            //返回json
            map.put("msg","ok");
            map.put("code",200);
            /*加不加这个都行
            map.put("data",new HashMap<String,Object>(){
                {
                    put("src",savepath);
                }
            });*/

        } catch (Exception e) {
            map.put("msg","error");
            map.put("code",0);
            e.printStackTrace();
        }

        return map;
    }

}