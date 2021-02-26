package cc.mrbird.febs.message.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 信息管理表 Entity
 *
 * @author zjm
 * @date 2021-02-26 15:04:30
 */
@Data
@TableName("t_message")
public class Message {

    /**
     * 信息ID
     */
    @TableId(value = "MESSAGE_ID", type = IdType.AUTO)
    private Long messageId;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 登录名
     */
    @TableField("LOGIN_NAME")
    private String loginName;

    /**
     * 年龄
     */
    @TableField("AGE")
    private Integer age;

    /**
     * 民族
     */
    @TableField("NATION")
    private String nation;

    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 籍贯
     */
    @TableField("NATIVE_PLACE")
    private String nativePlace;

    /**
     * 手机号码
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 身份证号码
     */
    @TableField("IDENTITY")
    private String identity;

    /**
     * 在住老人
     */
    @TableField("ELDER")
    private String elder;

    /**
     * 关系
     */
    @TableField("RELATION")
    private String relation;

    /**
     * 备注
     */
    @TableField("REMARKS")
    private String remarks;

    /**
     * 状态 0 已保存 1 待审核 2 审核通过 3 退回
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

}
