package com.kaciry.service;

import com.kaciry.entity.ColumnShow;
import com.kaciry.entity.ResultBean;

import java.util.List;

public interface ColumnService {
    ResultBean setColumn(String columnInfo);

    List<ColumnShow> findColumn();

    List<ColumnShow> findTodayColumn();

    List<ColumnShow> findThreeDaysColumn();
}
