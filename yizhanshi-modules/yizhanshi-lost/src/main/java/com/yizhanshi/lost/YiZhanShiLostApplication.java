package com.yizhanshi.lost;


import com.yizhanshi.common.security.annotation.EnableCustomConfig;
import com.yizhanshi.common.security.annotation.EnableRyFeignClients;
import com.yizhanshi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 失物模块
 *
 * @author hejiale
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YiZhanShiLostApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(YiZhanShiLostApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  失物模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
