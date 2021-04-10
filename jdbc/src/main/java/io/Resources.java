package io;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;

public class Resources {

    //根据配置文件路径，加载配置文件为字节输入流，存在内存中
    public static InputStream getResourceAsSteam(String path){
        InputStream inputStream =  Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
