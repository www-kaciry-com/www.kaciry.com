package com.xiaoxiaobulletscreen.service;

import com.xiaoxiaobulletscreen.entity.Comment;
import com.xiaoxiaobulletscreen.entity.Ops;
import com.xiaoxiaobulletscreen.entity.VideoPage;

import java.util.List;

public interface VideoService {

    Comment addComment(Comment comment);

    List<Comment> selectVideoComment(String videoFileName);

    VideoPage initVideoInfo(String videoFileName, String username);

    boolean opsStar(Ops ops);

    boolean opsCollect(Ops ops);

    void deleteOpsData(Ops ops);
}
