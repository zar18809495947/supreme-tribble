package com.lanou.role.controller;

import com.lanou.role.bean.Module;
import com.lanou.role.service.ModuleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
@Controller
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping(value = "/findallmodule")
    public AjaxResult findAllModule() {
        List<Module> allModule = moduleService.findAllModule();
        return new AjaxResult(allModule);
    }
}
