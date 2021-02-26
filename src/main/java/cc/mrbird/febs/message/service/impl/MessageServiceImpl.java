package cc.mrbird.febs.message.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.mapper.MessageMapper;
import cc.mrbird.febs.message.service.IMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 信息管理表 Service实现
 *
 * @author zjm
 * @date 2021-02-26 15:04:30
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    private final MessageMapper messageMapper;

    @Override
    public IPage<Message> findMessages(QueryRequest request, Message message) {
        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Message> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Message> findMessages(Message message) {
	    LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMessage(Message message) {
        this.save(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessage(Message message) {
        this.saveOrUpdate(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMessage(Message message) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
