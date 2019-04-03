package com.hj.Demo3;

import org.junit.jupiter.api.Test;

/**
 * Author: hj
 * Date: 2019-04-03 16:12
 * Description: <lombok测试>
 */
public class LombokUserTest {

    public static void main(String[] args) {
        LombokUser lombokUser = new LombokUser();
        lombokUser.setUsername("hj");
        lombokUser.setPassword("123");
        System.out.println(lombokUser);
    }

    @Test
    void testLombok() {
        LombokUser lombokUser = new LombokUser();
        lombokUser.setUsername("hj");
        lombokUser.setPassword("1233");
        System.out.println(lombokUser);
    }
}
