package cc.mrbird.febs.common.entity;

/**
 * 正则常量
 *
 * @author MrBird
 */
public interface Regexp {

    /**
     * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
     */
    //String MOBILE_REG = "[1]\\d{10}";
    String MOBILE_REG = "/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$/";

}
