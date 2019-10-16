package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.*;

import java.util.List;

public interface VideoService {

    Comment addComment(Comment comment);

    List<Comment> selectVideoComment(String videoFileName);

    VideoPage initVideoInfo(String videoFileName, String username);

    boolean opsStar(Ops ops);

    boolean opsCollect(Ops ops);

    boolean opsShare(Ops ops);

    void deleteOpsData(Ops ops);

    ResultBean addOneReportVideoData(ReportVideoBean reportVideoBean);
}
