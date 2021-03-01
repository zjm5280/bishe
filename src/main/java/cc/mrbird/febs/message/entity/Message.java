package cc.mrbird.febs.message.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableId("MESSAGE_ID")
    private String messageId;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

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
    private String age;

    /**
     * 民族
     */
    @TableField("NATION")
    private String nation;

    /**
     * 性别 0 男 1 女
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
     * 地址
     */
    @TableField("ADDRESS")
    private String address;

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
     * 审核人
     */
    @TableField("AUDIT_PERSON")
    private String auditPerson;

    /**
     * 状态 0 待审核 1 审核通过 2 退回
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

}
