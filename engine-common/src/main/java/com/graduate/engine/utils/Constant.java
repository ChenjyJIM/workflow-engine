package com.graduate.engine.utils;

/**
 * 常量
 */
public class Constant {
    /**
     * 管理员
     */
    public static final int SUPER_ADMIN =1;
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
