package cc.mrbird.febs.message.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zjm
 * @className MessageAuditController
 * @date 2021/2/26
 */
@RestController
@RequestMapping("audit")
@RequiredArgsConstructor
public class MessageAuditController extends BaseController {

    private final IMessageService messageService;

    @GetMapping("list")
    @RequiresPermissions("audit:view")
    public FebsResponse auditList(Message message, QueryRequest request){
        return new FebsResponse().success()
                .data(getDataTable(messageService.findMessages(request, message)));
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
