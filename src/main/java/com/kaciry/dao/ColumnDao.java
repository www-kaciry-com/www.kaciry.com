package com.kaciry.dao;

import com.kaciry.entity.ColumnInfo;
import com.kaciry.entity.ColumnShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ColumnDao {
    @Insert("insert into user_column (username,columnContent,uploadTime) values(#{username},#{columnContent},#{uploadTime})")
    boolean insertColumn(ColumnInfo columnInfo);

    @Select("SELECT\n" +
            "\tuser_column.username,columnContent,uploadTime,userHeadIcon,userNickName,isVip,userSignature\n" +
            "FROM\n" +
            "\tuser_column\n" +
            "LEFT JOIN `user` ON user_column.username = `user` .username")
    List<ColumnShow> selectColumn();
}
