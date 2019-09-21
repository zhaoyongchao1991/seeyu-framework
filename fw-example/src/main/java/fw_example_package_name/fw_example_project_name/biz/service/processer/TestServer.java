package fw_example_package_name.fw_example_project_name.biz.service.processer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author seeyu
 * @date 2019/3/25
 */
@Service
@Slf4j
public class TestServer {


    public String test(){
        return "this is test server!";
    }

}
