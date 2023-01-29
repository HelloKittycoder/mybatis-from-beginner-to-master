package tk.mybatis.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author shucheng
 * @date 2023/1/29 18:23
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("now", new Date());
        return mv;
    }
}
