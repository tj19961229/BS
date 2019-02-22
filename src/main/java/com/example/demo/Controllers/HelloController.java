package com.example.demo.Controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.entity.*;
import com.example.demo.service.impl.MiniuserService;
import com.example.demo.service.impl.SysuserService;
import com.example.demo.service.impl.Userservice;
import com.example.demo.service.impl.loginService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private Userservice userservice;
    @Autowired
    private loginService loginservice;
    @Autowired
    private SysuserService sysuserService;
    @Autowired
    private MiniuserService miniuserService;
//    @Autowired
//    private Jsonchange jsonchange;


    private static  String path="./src/main/resources/static/table/";
    @RequestMapping(value="/adduser_cx")
    @ResponseBody
    public  List query() {
        List<Sysuser> users=sysuserService.Users_select();
        return users;
    }
    @RequestMapping(value="/testdata")
    @ResponseBody
    public   Object testdata(HttpServletRequest request){
        List<Sysuser> data=sysuserService.Users_select();
        datatest jsondata=new datatest();
        HttpSession session=request.getSession();

        System.out.println(request.getParameter("method"));
        if(request.getParameter("method").equals("GetDepartmentEmployees"))
        {

            Enumeration enu=request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                System.out.println(paraName+": "+request.getParameter(paraName));
            }
            jsondata.setTotal(1);
            jsondata.setData(miniuserService.mini_select_bumen(request.getParameter("dept_id")));
            session.setAttribute("dept_id",request.getParameter("dept_id"));

        }
        else if(request.getParameter("method").equals("SaveEmployees")){
            User_miniui user_miniui=new User_miniui();
            JSONArray jsonArray = JSON.parseArray(request.getParameter("data"));
            String TT="";
            //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
            //遍历方式1
            for (int i = 0; i < jsonArray.size(); i++){

                com.alibaba.fastjson.JSONObject jsonObject=jsonArray.getJSONObject(i);
                TT=jsonObject.getString("_state");

                if(jsonObject.containsKey("id")) user_miniui.setId(jsonObject.getInteger("id"));
                if(jsonObject.containsKey("age"))user_miniui.setAge(jsonObject.getInteger("age"));
                if(jsonObject.containsKey("name"))user_miniui.setName(jsonObject.getString("name"));
                if(jsonObject.containsKey("loginname"))user_miniui.setLoginname(jsonObject.getString("loginname"));
                if(jsonObject.containsKey("gender"))user_miniui.setGender(jsonObject.getInteger("gender"));
                if(jsonObject.containsKey("birthday"))user_miniui.setBirthday(jsonObject.getString("birthday"));
            }

            user_miniui.setBumen(session.getAttribute("dept_id").toString());
            if(TT.equals("added")){
                miniuserService.mini_add(user_miniui);
            }else{
                miniuserService.mini_update(user_miniui);
            }
            System.out.println(request.getParameter("data"));

//            System.out.println(user_miniui.getAge());
//            String jjjj=request.getParameter("data");
//            JSONArray json=new JSONObject(jjjj);
        }
        return jsondata;
    }
    @RequestMapping(value="/adduser/Delete")
    @ResponseBody
    public  String Delete(String id){
        System.out.println(id);
        return sysuserService.User_delete(id);
    }



    @RequestMapping(value="/adduser/Add")
    @ResponseBody
    public  String Add(Sysuser sysuser){
        System.out.println(sysuser.getName());        //调试输出
        System.out.println(sysuser.getUsername());
        System.out.println(sysuser.getPassword());
        System.out.println(sysuser.getBJ());
        System.out.println(sysuser.getRoles());
        return sysuserService.User_add(sysuser);
    }

    @RequestMapping(value="/adduser/Edit")
    @ResponseBody
    public  String Edit(int Edit_name,String name,String oldValue,String newValue) {


        System.out.println(Edit_name);        //调试输出
        System.out.println(name);
        System.out.println(oldValue);
        System.out.println(newValue);

        return sysuserService.User_updata(Edit_name,name,oldValue,newValue);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adduser")
     private String adduser()
    {
        return "adduser";
    }

    @Secured("ROLE_TEACHER")
    @RequestMapping("/items")
    private String items()
    {
        return "items";
    }

    @Secured("ROLE_TEACHER")
    @RequestMapping("/examinations")
    private String examinations() { return "examinations";    }

    @RequestMapping("")
    private String login2(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session=request.getSession();
        Sysuser sysuser = sysuserService.User_select(auth.getName());
        Role_name role_name=new Role_name();
        if(!(auth instanceof AnonymousAuthenticationToken))
        {
            session.setAttribute("name",sysuser.getName());
            session.setAttribute("ip_test","https://localhost");
            session.setAttribute("class",sysuser.getBJ());
            session.setAttribute("id",sysuser.getId());
            session.setAttribute("role",role_name.Role_name(sysuser.getRoles()));
            return "index";
        }
        else return "login2";


    }
    @RequestMapping("/main")
    @ResponseBody
    private String main(){
        UserDetails user=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    @RequestMapping("/loginout")
    @ResponseBody
    private String loginout()
    {
        return "超时了啊兄弟";




    }


    @RequestMapping("/login")
    private String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            return "login2";
        }else{
            return "index";
        }
    }

    @RequestMapping("/register")
    private String register(){
        return "register";
    }


    @RequestMapping("/show")
    @ResponseBody
    public Object show() {
        return userservice.show();
    }

    @RequestMapping("/home")
    public String hello() {

        return "home";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(String username, String userpwd,String nickname,int t_o_s){
        return userservice.insert(username, userpwd,nickname,t_o_s);
    }

    @RequestMapping("/login2")
    @ResponseBody
    public int login(HttpSession Session,@ModelAttribute login _login){
        if(_login.getUsername()== null|| _login.getUserpwd()==null){
            return 0;
        }
        else{

            return loginservice._logincx(_login.getUsername(),_login.getUserpwd());
        }
    }
//    @CrossOrigin
    @RequestMapping("/test")
    public String test(String Id){
        String treename;
        try {
            System.out.println(Id);
            treename=path+Id+".txt";
            File filename=new File(treename);
            if (!filename.exists()) {
                filename.createNewFile();
                System.out.println("无文件，执行创建");
                OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filename),"utf-8");
                BufferedWriter out=new BufferedWriter(write);
                // 写东西：\r\n即为换行
                out.write("[\n" +
                        "\t{\"name\":\"行政部\", \"id\":\"xz\", expanded: true},\n" +
                        "\t{\"name\":\"人事部\", \"id\":\"rs\", \"pid\": \"xz\"},\n" +
                        "\t{\"name\":\"财务部\",\"id\":\"cw\", \"pid\": \"xz\"},\n" +
                        "\n" +
                        "\t{\"name\":\"业务部\", \"id\":\"yw\", expanded: true},\n" +
                        "\t{\"name\":\"市场销售部\", \"id\":\"sc\", \"pid\": \"yw\"},\t\n" +
                        "\t{\"name\":\"技术部\", \"id\":\"js\", \"pid\": \"yw\"}\n" +
                        "\t\n" +
                        "]");
                // 把缓存区内容压入文件
                out.flush();
                // 最后关闭流
                out.close();
                //返回成功
            }
            System.out.println(filename.getAbsolutePath());
        }catch (Exception e) {
            // e.printStackTrace();
            System.out.println("not OK");
            return null;
        }

        return "test";
    }


}
