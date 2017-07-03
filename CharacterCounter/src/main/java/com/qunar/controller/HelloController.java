//package com.qunar.fresh2017.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * Created by 曹 on 2017.5.17.
// */
//@Controller
//public class HelloController {
//    @RequestMapping(value="/hello")
//    public String print() {
//        System.out.println("controller");
//        return "success";
//    }
//}
package com.qunar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiazihao on 7/2/17.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/start")
    public String start() {
        return "start";
    }

//    @RequestMapping(value = "/uploadFile")
//    public String upLoadFile() {
//        return "UpLoad";
//    }


}
