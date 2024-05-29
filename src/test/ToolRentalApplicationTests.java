package com.toolrental;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ToolRentalApplication.class)
@ActiveProfiles("test")
class ToolRentAppApplicationTests {

    @Test
    void contextLoads() {
    }
}
