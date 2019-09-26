package cn.weisoft.mybatisstudy.mapper;


import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class BookDynamicSqlProvider {
    public String selectWithParam(Map<String,Object> param){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM("book");
                if(param.get("id") !=null){
                    WHERE("id<=#{id}");
                }
                if(param.get("name") !=null){
                    WHERE("name like #{name}");
                }
                if(param.get("author") !=null){
                    WHERE("author=#{author}");
                }
            }

        }.toString();

        System.out.println("sql:" + sql);
        return sql;
    }
}
