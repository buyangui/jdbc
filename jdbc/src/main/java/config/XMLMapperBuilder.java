package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.Configuration;
import pojo.MapperStatement;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private Configuration configuration;
    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


    public void  parse(InputStream in) throws DocumentException {
        Document document = new SAXReader().read(in);
        //解析 获取配置文件中的对象
        Element element = document.getRootElement();
        List<Element> list = element.selectNodes("*");
        String nameSpace = element.attributeValue("namespace");
        for(Element lt:list){
            String id = lt.attributeValue("id");
            String resultType = lt.attributeValue("resultType");
            String paramterType = lt.attributeValue("paramterType");
            String sql = lt.getTextTrim();

            //每个操作为一个mapperStatment  每个存储在map中的key是statementId
            MapperStatement mapperStatement=new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setParamteType(paramterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sql);

            configuration.getMapperStatementMap().put(nameSpace+"."+id,mapperStatement);
        }
    }
}
