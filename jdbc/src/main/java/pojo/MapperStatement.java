package pojo;

public class MapperStatement {

    //标识
    private  String id;
    //返回类型
    private  String resultType;
   //参数类型
    private  String paramteType;
     //sql
    private  String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamteType() {
        return paramteType;
    }

    public void setParamteType(String paramteType) {
        this.paramteType = paramteType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
