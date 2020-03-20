package link.thingscloud.mobile.area.domain;

/**
 * 1 移动
 * 2 联通
 * 3 电信
 * 4 电信虚拟运营商
 * 5 联通虚拟运营商
 * 6 移动虚拟运营商
 *
 * @author : zhouhailin
 */
public enum MobileType {
    /**
     * 未知
     */
    NONE,
    /**
     * 中国移动通信
     */
    CMCC,
    /**
     * 中国联通通讯
     */
    CUCC,
    /**
     * 中国电信
     */
    CTCC,
    /**
     * 中国移动通信
     */
    V_CMCC,
    /**
     * 中国联通通讯
     */
    V_CUCC,
    /**
     * 中国电信
     */
    V_CTCC
}
