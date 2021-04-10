package config;

import utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {

    private String sqlText;  //解析的sql

    //存放解析后的参数
    private List<ParameterMapping> ParameterMappingList = new ArrayList<ParameterMapping>();

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return ParameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        ParameterMappingList = parameterMappingList;
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        ParameterMappingList = parameterMappingList;
    }
}
