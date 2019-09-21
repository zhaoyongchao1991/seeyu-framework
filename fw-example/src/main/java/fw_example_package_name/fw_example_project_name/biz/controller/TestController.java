package fw_example_package_name.fw_example_project_name.biz.controller;

import com.seeyu.normal.controller.base.BaseController;
import com.seeyu.normal.utils.JsonData;
import fw_example_package_name.fw_example_project_name.biz.service.processer.TestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author seeyu
 * @date 2019/2/12
 */
@Controller
@RequestMapping("test")
public class TestController extends BaseController {

    @Autowired
    private TestServer testServer;

    @RequestMapping("test")
    @ResponseBody
    public JsonData myTest(){
        return SUCCESS().setData(testServer.test());
    }

}
