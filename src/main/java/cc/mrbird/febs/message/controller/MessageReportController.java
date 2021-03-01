package cc.mrbird.febs.message.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.UUIDutil;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.service.IMessageService;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IRoleService;
import cc.mrbird.febs.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 信息管理表 Controller
 *
 * @author zjm
 * @date 2021-02-26 15:04:30
 */
@Slf4j
@Validated
@Controller
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageReportController extends BaseController {

    private final IMessageService messageService;
    private final IRoleService roleService;
    private final IUserService userService;

    @GetMapping("messages")
    @ResponseBody
    @RequiresPermissions("message:list")
    public FebsResponse getAllMessages(Message message) {
        return new FebsResponse().success().data(messageService.findMessages(message));
    }

    @GetMapping("list")
    @ResponseBody
    @RequiresPermissions("message:list")
    public FebsResponse messageList(QueryRequest request, Message message) {
        Map<String, Object> dataTable = getDataTable(this.messageService.findMessages(request, message));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Message", exceptionMessage = "新增Message失败")
    @PostMapping("message")
    @ResponseBody
    @RequiresPermissions("message:add")
    public FebsResponse addMessage(@Valid Message message) {
        message.setMessageId(UUIDutil.getUUID());
        message.setLoginName(getCurrentUser().getUsername());
        message.setStatus("0");
        message.setCreateTime(new Date());
        message.setUpdateTime(null);
        this.messageService.createMessage(message);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "获取状态", exceptionMessage = "获取状态失败")
    @ResponseBody
    @PostMapping("getStatus")
    public FebsResponse getStatus(String identity, String phone){
        String status = this.messageService.getStatus(identity, phone);
        return new FebsResponse().success().data(status);
    }

    @ControllerEndpoint(operation = "用户名获取状态", exceptionMessage = "用户名获取状态失败")
    @ResponseBody
    @GetMapping("getStatusByLoginName")
    public FebsResponse getStatusByLoginName(){
        String str = getCurrentUser().getUsername();
        String status = this.messageService.getStatusByLoginName(str);
        return new FebsResponse().success().data(status);
    }

    @ControllerEndpoint(operation = "删除Message", exceptionMessage = "删除Message失败")
    @GetMapping("message/delete")
    @ResponseBody
    @RequiresPermissions("message:delete")
    public FebsResponse deleteMessage(Message message) {
        this.messageService.deleteMessage(message);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Message", exceptionMessage = "修改Message失败")
    @PostMapping("audit/update")
    @ResponseBody
    @RequiresPermissions("message:update")
    public FebsResponse updateMessage(@Valid Message message) {
        if (message.getMessageId() == null){
            throw new FebsException("信息ID为空");
        }
        message.setAuditPerson(getCurrentUser().getUsername());
        message.setUpdateTime(new Date());
        messageService.updateMessage(message);
        roleService.updateUserRole((userService.findByName(message.getName())).getUserId());
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Message", exceptionMessage = "导出Excel失败")
    @PostMapping("message/excel")
    @ResponseBody
    @RequiresPermissions("message:export")
    public void export(QueryRequest queryRequest, Message message, HttpServletResponse response) {
        List<Message> messages = this.messageService.findMessages(queryRequest, message).getRecords();
        ExcelKit.$Export(Message.class, response).downXlsx(messages, false);
    }
}
