package cc.mrbird.febs.message.service;

import cc.mrbird.febs.message.entity.Message;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 信息管理表 Service接口
 *
 * @author zjm
 * @date 2021-02-26 15:04:30
 */
public interface IMessageService extends IService<Message> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param message message
     * @return IPage<Message>
     */
    IPage<Message> findMessages(QueryRequest request, Message message);

    /**
     * 查询（所有）
     *
     * @param message message
     * @return List<Message>
     */
    List<Message> findMessages(Message message);

    /**
     * 新增
     *
     * @param message message
     */
    void createMessage(Message message);

    /**
     * 修改
     *
     * @param message message
     */
    void updateMessage(Message message);

    /**
     * 删除
     *
     * @param message message
     */
    void deleteMessage(Message message);
}
