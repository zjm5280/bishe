package cc.mrbird.febs.message.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.message.mapper.MessageMapper;
import cc.mrbird.febs.message.service.IMessageService;
import cc.mrbird.febs.monitor.entity.SystemLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

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
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        // TODO 设置查询条件
        if (StringUtils.isNotBlank(message.getCreateTimeFrom())&&StringUtils.equals(message.getCreateTimeFrom(),
                message.getCreateTimeTo())){
            message.setCreateTimeFrom(message.getCreateTimeFrom() + FebsConstant.DAY_START_PATTERN_SUFFIX);
            message.setCreateTimeTo(message.getCreateTimeTo() + FebsConstant.DAY_END_PATTERN_SUFFIX);
        }
        if (StringUtils.isNotBlank(message.getName())){
            queryWrapper.lambda().eq(Message::getName, message.getName());
        }
        if (StringUtils.isNotBlank(message.getNativePlace())){
            queryWrapper.lambda().like(Message::getNativePlace, message.getNativePlace());
        }
        if (StringUtils.isNotBlank(message.getPhone())){
            queryWrapper.lambda().like(Message::getPhone, message.getPhone());
        }
        if (StringUtils.isNotBlank(message.getGender())){
            queryWrapper.lambda().like(Message::getPhone, message.getPhone());
        }
        if (StringUtils.isNotBlank(message.getCreateTimeFrom()) && StringUtils.isNotBlank(message.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(Message::getCreateTime, message.getCreateTimeFrom())
                    .le(Message::getCreateTime, message.getCreateTimeTo());
        }

        Page<Message> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);
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

    @Override
    public String getStatus(String identity, String phone) {
        return messageMapper.getStatus(identity,phone);
    }

    @Override
    public String getStatusByLoginName(String loginName) {
        return messageMapper.getStatusByLoginName(loginName);
    }

    @Override
    public Message findByName(String name) {
        return messageMapper.findByName(name);
    }
}
