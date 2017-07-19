package com.lide.app.persistence.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.lide.app.R;

import java.util.HashMap;
import java.util.Map;


public class SoundUtils {
    public static SoundPool sp;
    public static Map<Integer, Integer> suondMap;
    public static Context context;
    public static int Volume = 0;
    private static AudioManager mAudioManager;

    //初始化声音池
    public static void initSoundPool(Context context) {
        SoundUtils.context = context;
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        suondMap = new HashMap<Integer, Integer>();
        suondMap.put(1, sp.load(context, R.raw.msg, 1));
        suondMap.put(2, sp.load(context, R.raw.scan_error, 1));
        sp.load("/etc/Scan_new.ogg", 1);
        Volume = (int) SPUtils.get(context, "volume", 0);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0);
    }

    //播放声音池声音
    public static void play(int sound) {
        sp.play(
                suondMap.get(sound), //播放的音乐Id
                Volume, //左声道音量
                Volume, //右声道音量
                1, //优先级，0为最低
                0, //循环次数，0无不循环，-1无永远循环
                1);//回放速度，值在0.5-2.0之间，1为正常速度
    }

    public static void playErrorSound() {
        sp.play(
                suondMap.get(2), //播放的音乐Id
                Volume, //左声道音量
                Volume, //右声道音量
                1, //优先级，0为最低
                0, //循环次数，0无不循环，-1无永远循环
                1);//回放速度，值在0.5-2.0之间，1为正常速度
    }

    public static void playByVolume(int sound, int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        sp.play(
                2,
                Volume, //左声道音量
                Volume, //右声道音量
                1, //优先级，0为最低
                0, //循环次数，0无不循环，-1无永远循环
                1);//回放速度，值在0.5-2.0之间，1为正常速度
    }

    public static void setVolume(int volume) {
        Volume = volume;
        SPUtils.put(context, "volume", volume);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        SoundUtils.play(1);
    }
}