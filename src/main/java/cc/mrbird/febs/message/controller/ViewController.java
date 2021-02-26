package cc.mrbird.febs.message.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.monitor.helper.FebsActuatorHelper;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zjm
 * @className ViewController
 * @date 2021/2/26
 */
@Controller("messageView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "report")
@RequiredArgsConstructor
public class ViewController {

    private final FebsActuatorHelper actuatorHelper;

    @GetMapping("report")
    @RequiresPermissions("report:view")
    public String report() {
        return FebsUtil.view("message/report");
    }

}
