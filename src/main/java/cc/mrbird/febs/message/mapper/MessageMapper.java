package cc.mrbird.febs.message.mapper;

import cc.mrbird.febs.message.entity.Message;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 信息管理表 Mapper
 *
 * @author zjm
 * @date 2021-02-26 15:04:30
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 获取状态
     * @param identity
     * @param phone
     * @return
     */
    String getStatus(String identity, String phone);

    /**
     * 根据用户名获取状态
     * @param loginName
     * @return
     */
    String getStatusByLoginName(String loginName);

    /**
     * 通过用户名查找用户
     *
     * @param name 用户名
     * @return 用户
     */
    Message findByName(String name);
}
