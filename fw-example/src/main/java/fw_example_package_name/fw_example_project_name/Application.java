package fw_example_package_name.fw_example_project_name;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author seeyu
 * @date 2019/3/25
 */
@SpringBootApplication
@MapperScan(basePackages = {"fw_example_package_name.fw_example_project_name.biz.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
