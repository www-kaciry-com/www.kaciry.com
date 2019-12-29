package com.kaciry.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.kaciry.dao.ColumnDao;
import com.kaciry.entity.ColumnInfo;
import com.kaciry.entity.ColumnShow;
import com.kaciry.entity.ResultBean;
import com.kaciry.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author FLLH
 * @date 2019/12/23 13:02
 * @description
 */
@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    private ColumnDao columnDao;

    @Override
    public ResultBean setColumn(String columnInfo) {

        ColumnInfo column = JSONObject.parseObject(columnInfo, ColumnInfo.class);
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        column.setUploadTime(time);
        if (columnDao.insertColumn(column)) {
            return new ResultBean<>("上传成功！success");
        } else {
            return new ResultBean<>("上传失败，请检查网络！error!");
        }
    }

    @Override
    public List<ColumnShow> findColumn() {
        return columnDao.selectColumn();
    }
}
