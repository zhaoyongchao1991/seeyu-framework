package fw_example_package_name.fw_example_project_name.config;

import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class GlobalConfig {

    /**
     * 获取项目根路径
     * @return
     */
    public static String getProjectRootPath(){
        String path = GlobalConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path);
        if(file.isDirectory()){
            return file.getPath();
        }
        else{
            return file.getParent();
        }
    }

    /**
     * 获取项目中的资源路径
     * @return
     */
    public static String getProjectResourcePath(String subPath){
        return new File(getProjectRootPath(), subPath).getPath();
    }

}
