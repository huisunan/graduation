package com.hsn.mall.admin;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huisunan
 * @date 2020/1/13 12:08
 */
@SpringBootApplication(scanBasePackages = {"com.hsn.mall.admin","com.hsn.mall.core"})
@EnableDubboConfiguration
public class AdminApplication {
    public static void main(String[] args) {
        String fozu =
                            "                   _ooOoo_"+"\n"+
                        "                  o8888888o"+"\n"+
                        "                  88\" . \"88"+"\n"+
                        "                  (| -_- |)"+"\n"+
                        "                  O\\  =  /O"+"\n"+
                        "               ____/`---'\\____"+"\n"+
                        "             .'  \\\\|     |//  `."+"\n"+
                        "            /  \\\\|||  :  |||//  \\"+"\n"+
                        "           /  _||||| -:- |||||-  \\"+"\n"+
                        "           |   | \\\\\\  -  /// |   |"+"\n"+
                        "           | \\_|  ''\\---/''  |   |"+"\n"+
                        "           \\  .-\\__  `-`  ___/-. /"+"\n"+
                        "         ___`. .'  /--.--\\  `. . __"+"\n"+
                        "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\"."+"\n"+
                        "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |"+"\n"+
                        "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /"+"\n"+
                        "======`-.____`-.___\\_____/___.-`____.-'======"+"\n"+
                        "                   `=---='"+"\n"+
                        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+"\n"+
                        "                 佛祖保佑       永无BUG";
        System.out.println(fozu);
        SpringApplication.run(AdminApplication.class,args);
    }
}
