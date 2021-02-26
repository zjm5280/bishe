package cc.mrbird.febs.message.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.service.IMessageService;
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

    @GetMapping("message")
    @ResponseBody
    @RequiresPermissions("message:list")
    public FebsResponse getAllMessages(Message message) {
        return new FebsResponse().success().data(messageService.findMessages(message));
    }

    @GetMapping("message/list")
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
        this.messageService.createMessage(message);
        return new FebsResponse().success();
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
    @PostMapping("message/update")
    @ResponseBody
    @RequiresPermissions("message:update")
    public FebsResponse updateMessage(Message message) {
        this.messageService.updateMessage(message);
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
