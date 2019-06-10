package com.graduate.engine.utils;

/**
 * 常量
 */
public class Constant {
    /**
     * 管理员
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 临时用户角色ID
     */
    public static final Long GUEST_ID = 5L;
    /**
     * 学会会员角色ID
     */
    public static final Long PERSON_MEMBER_ID = 6L;
    /**
     * 会员账号初始密码
     */
    public static final String INIT_PASSWORD = "welcomeINSTITUTE123";
    /**
     * 会员编号字首
     */
    public static final Integer MEMBER_ID_PREFIX = 201900000;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
