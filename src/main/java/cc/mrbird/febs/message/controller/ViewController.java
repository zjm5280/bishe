package cc.mrbird.febs.message.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.mapper.MessageMapper;
import cc.mrbird.febs.message.service.IMessageService;
import cc.mrbird.febs.monitor.helper.FebsActuatorHelper;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IUserDataPermissionService;
import cc.mrbird.febs.system.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author zjm
 * @className ViewController
 * @date 2021/2/26
 */
@Controller("messageView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "message")
@RequiredArgsConstructor
public class ViewController extends BaseController {

    private final IUserService userService;
    private final IUserDataPermissionService userDataPermissionService;
    private final FebsActuatorHelper actuatorHelper;
    private final IMessageService messageService;

    @GetMapping("report")
    @RequiresPermissions("report:view")
    public String report() {
       return FebsUtil.view("message/report");
    }

    @GetMapping("audit")
    @RequiresPermissions("audit:view")
    public String audit(){
        return FebsUtil.view("message/audit");
    }

    @GetMapping("audit/detail/{name}")
    @RequiresPermissions("audit:view")
    public String messageAuditDetail(@PathVariable String name, Model model) {
        resolveUserModel(name, model, false);
        return FebsUtil.view("message/messageDetail");
    }

    @GetMapping("audit/update/{name}")
    @RequiresPermissions("message:update")
    public String messageUpdate(@PathVariable String name, Model model){
        resolveUserModel(name, model, false);
        return FebsUtil.view("message/messageUpdate");
    }

    private void resolveUserModel(String name, Model model, boolean transform){
        Message message = messageService.findByName(name);
        User user = userService.findByName(name);

        model.addAttribute("message", message);
        model.addAttribute("user", user);
        if (transform) {
            String gender = message.getGender();
            switch (gender) {
                case User.SEX_MALE:
                    message.setGender("男");
                    break;
                case User.SEX_FEMALE:
                    message.setGender("女");
                    break;
                default:
                    message.setGender("未知");
                    break;
            }
        }
        if (user.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }

}
