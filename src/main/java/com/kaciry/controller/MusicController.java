package com.kaciry.controller;

import com.alibaba.fastjson.JSONObject;
import com.kaciry.entity.Music;
import com.kaciry.entity.ResultBean;
import com.kaciry.service.Impl.MusicServiceImpl;
import com.kaciry.service.Impl.UserServiceImpl;
import com.kaciry.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author FLLH
 * @date 2019/11/14 23:51
 * @description 音乐相关
 */
@Controller
public class MusicController {
    @Autowired
    private MusicServiceImpl musicServiceImpl;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = {"/jumpMusic"})
    public String jump() {
        return "music/music";
    }

    @GetMapping("/playMusic")
    public String playMusic() {
        return "music/playMusic";
    }

    @GetMapping("/moreMusic")
    public String playMoreMusic() {
        return "music/moreMusic";
    }

    @GetMapping("/searchMusic")
    public String searchedMusic() {
        return "music/searchedMusic";
    }

    @PostMapping("/selectMoreMusic")
    @ResponseBody
    public List<Music> selectMoreMusic(String musicType) {
        List<Music> res = musicServiceImpl.selectMoreMusic(musicType);
        //System.out.println(res);
        return res;
    }

    @PostMapping("/searchedMusic")
    @ResponseBody
    public List<Music> querySearchedMusic(String musicName) {
        return musicServiceImpl.findSearchedMusic(musicName);
    }

    /**
     * @param musicFilename 视频文件名
     * @return java.util.List<com.kaciry.entity.Music>
     * @author FLLH
     * @description  播放点击的音乐
     * @date 2019/11/26 21:26
     **/
    @PostMapping("/playMusic")
    @ResponseBody
    public List<Music> queryMusicInfo(String musicFilename) {
        return musicServiceImpl.findMusicInfo(musicFilename);
    }

    @PostMapping("/selectPageMusics")
    @ResponseBody
    public List<Object> queryPageMusics() {
        return musicServiceImpl.findPageMusics();
    }
/*
    @PostMapping("/selectPopMusic")
    @ResponseBody
    public List<Music> selectPopMusic() {
        List<Music> res = musicServiceImpl.selectPopMusic();
        //System.out.println(res);
        return res;
    }

    @PostMapping("/selectNetMusic")
    @ResponseBody
    public List<Music> selectNetMusic() {
        List<Music> res = musicServiceImpl.selectNetMusic();
        //System.out.println(res);
        return res;
    }

    @PostMapping("/selectEnglishMusic")
    @ResponseBody
    public List<Music> selectEnglishMusic() {
        List<Music> res = musicServiceImpl.selectEnglishMusic();
        //System.out.println(res);
        return res;
    }

    @PostMapping("/selectOldMusic")
    @ResponseBody
    public List<Music> selectOldMusic() {
        List<Music> res = musicServiceImpl.selectOldMusic();
        //System.out.println(res);
        return res;
    }*/

    @PostMapping("/addPlayNum")
    @ResponseBody
    public int addPlayNum(String musicFilename) {
        return musicServiceImpl.setPlayNum(musicFilename);
    }

    /**
     * @author FLLH
     * @description  用户上传的文件，音乐 音乐封面与音乐歌词
     * @date  2019/12/14 17:31
     * @param musicFile
     * @param musicCoverFile
     * @param musicLrc
     * @param musicInfo
     * @return com.kaciry.entity.ResultBean
     **/
    @RequestMapping(value = "/uploadMusic", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean uploadMusic(@RequestParam("musicFile") MultipartFile musicFile, @RequestParam("musicCoverFile") MultipartFile musicCoverFile,
                                  @RequestParam("musicLrcFile") MultipartFile musicLrc , @RequestParam("musicInfo") String musicInfo) {
        //json字符换转化为实体对象
        Music musicInfomation = JSONObject.parseObject(musicInfo, Music.class);
        UploadFiles uploadMusic = new UploadFiles();
        if (userService.setMusic((Music) uploadMusic.uploadMusicFiles(musicFile, musicCoverFile,musicLrc,musicInfomation).getData())) {
            return new ResultBean<>("上传成功！success");
        } else {
            return new ResultBean<>("上传失败，请检查网络！error!");
        }
    }

}
