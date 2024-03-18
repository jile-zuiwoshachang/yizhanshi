package com.yizhanshi.talent;


import com.yizhanshi.common.security.annotation.EnableCustomConfig;
import com.yizhanshi.common.security.annotation.EnableRyFeignClients;
import com.yizhanshi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 人才模块
 *
 * @author hejiale
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YiZhanShiTalentApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(YiZhanShiTalentApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  人才模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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
