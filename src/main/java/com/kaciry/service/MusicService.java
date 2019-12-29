package com.kaciry.service;

import com.kaciry.entity.Music;

import java.util.List;

public interface MusicService {

    List<Music> findMusicInfo(String musicFilename);

    List<Music> selectMoreMusic(String musicType);

    List<Music> findSearchedMusic(String musicName);

    List<Object> findPageMusics();

  /* List<Music> selectNetMusic();

    List<Music> selectEnglishMusic();

    List<Music> selectOldMusic();

    List<Music> selectPopMusic();*/

    int setPlayNum(String musicFilename);

    /*boolean setMusic(Music music);*/
}
