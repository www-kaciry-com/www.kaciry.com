package com.kaciry.service.Impl;

import com.kaciry.dao.MusicDao;
import com.kaciry.entity.Music;
import com.kaciry.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLLH
 * @date 2019/11/16 12:51
 * @description
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    @Override
    public List<Music> findMusicInfo(String musicFilename) {
        return musicDao.selectMusicInfo(musicFilename);
    }

    @Override
    public List<Music> selectMoreMusic(String musicType) {
        List<Music> res = musicDao.selectMoreMusic(musicType);
        return res;
    }

    @Override
    public List<Music> findSearchedMusic(String musicName) {
        return musicDao.selectSearchedMusic(musicName);
    }

    @Override
    public List<Object> findPageMusics() {
        List<Music> res1 = musicDao.selectHotMusic();
        List<Music> res2 = musicDao.selectPopMusic();
        List<Music> res3 = musicDao.selectNetMusic();
        List<Music> res4 = musicDao.selectEnglishMusic();
        List<Music> res5 = musicDao.selectOldMusic();
        List<Object> res = new ArrayList<>();
        res.add(res1);
        res.add(res2);
        res.add(res3);
        res.add(res4);
        res.add(res5);
        return res;
    }

/*    @Override
    public List<Music> selectPopMusic() {
        List<Music> res = musicDao.selectPopMusic();
        return res;
    }

    @Override
    public List<Music> selectNetMusic() {
        List<Music> res = musicDao.selectNetMusic();
        return res;
    }

    @Override
    public List<Music> selectEnglishMusic() {
        List<Music> res = musicDao.selectEnglishMusic();
        System.out.println(res);
        return res;
    }

    @Override
    public List<Music> selectOldMusic() {
        List<Music> res = musicDao.selectOldMusic();
        return res;
    }*/

    @Override
    public int setPlayNum(String musicFilename) {
        return musicDao.updatePlayNum(musicFilename);
    }

/*    @Override
    public boolean setMusic(Music music) {
        return false;
    }*/

}
