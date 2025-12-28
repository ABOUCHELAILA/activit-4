package com.sdia.configservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=native")
class ConfigServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
