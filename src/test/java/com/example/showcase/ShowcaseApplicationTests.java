package com.example.showcase;

import com.example.showcase.utils.AESUtils;
import org.junit.jupiter.api.Test;


class ShowcaseApplicationTests {
    @Test
    void contextLoads() {
    }
    public static void main(String[] args) throws Exception {

        System.out.println(AESUtils.encrypt("passw0rd","showcase"));
    }

}