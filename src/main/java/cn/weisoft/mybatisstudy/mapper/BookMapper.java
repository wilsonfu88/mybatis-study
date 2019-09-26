package cn.weisoft.mybatisstudy.mapper;


import org.apache.ibatis.annotations.*;
import cn.weisoft.mybatisstudy.entity.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    @Select("select * from book where id=#{id}")
    Book selectBookById(int id);

    //使用SQL语句读取
    @Select("select * from book")
    List<Book> selectAllBooks();

    //使用存储过程读取
    @Select("{call p_get_book_by_id(#{id,mode=IN})}")
    @Options(statementType = StatementType.CALLABLE)
    Book selectBookByIdProc(int id);

    //使用存储过程方式插入
    @Insert("{call p_ins_book(#{id,mode=OUT," +
            "jdbcType=INTEGER}," +
            "#{name,mode=IN}," +
            "#{author,mode=IN}," +
            "#{publishDate,mode=IN}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    int insertBookByProc(Book book);

    //使用SQL语句插入
    @Insert("insert into book(name,author,publishDate)" +
            " values(#{name},#{author},#{publishDate})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertBookBySQL(Book book);

    @SelectProvider(type=BookDynamicSqlProvider.class,method = "selectWithParam")
    List<Book>selectWithParam(Map<String,Object> param);


}
