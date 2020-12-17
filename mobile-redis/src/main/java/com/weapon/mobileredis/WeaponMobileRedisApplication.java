package com.weapon.mobileredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class WeaponMobileRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeaponMobileRedisApplication.class, args);
    }

}
