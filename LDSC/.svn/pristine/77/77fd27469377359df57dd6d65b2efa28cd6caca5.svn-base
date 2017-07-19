package android.extend.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 声音有关工具类
 */
public class PlaySoundPoolUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static PlaySoundPoolUtils soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static PlaySoundPoolUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new PlaySoundPoolUtils();
        }

        // 初始化声音
        mContext = context;
        mSoundPlayer.load("/etc/Scan_new.ogg", 1);
        mSoundPlayer.load("/etc/Scan_new.ogg", 1);
        mSoundPlayer.load("/etc/Scan_new.ogg", 1);
//        mSoundPlayer.load(mContext, R.raw.ok, 1);// 1
//        mSoundPlayer.load(mContext, R.raw.didi, 1);//2
//        mSoundPlayer.load(mContext, R.raw.msg, 1);// 3
        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1f, 1f, 1, 0, 1);
    }

    public static void play1(int soundID) {
        mSoundPlayer.play(soundID, 0.5f, 0.5f, 1, 0, 1);
    }

    public static void play2(int soundID) {
        mSoundPlayer.play(soundID, 0.6f, 0.6f, 1, 0, 1);
    }

    public static void play3(int soundID) {
        mSoundPlayer.play(soundID, 0.7f, 0.7f, 1, 0, 1);
        mSoundPlayer.play(soundID, 0.7f, 0.7f, 1, 0, 1);
    }

    public static void play4(int soundID) {
        mSoundPlayer.play(soundID, 0.9f, 0.9f, 1, 0, 1);
        mSoundPlayer.play(soundID, 0.9f, 0.9f, 1, 0, 1);
        mSoundPlayer.play(soundID, 0.9f, 0.9f, 1, 0, 1);
    }

    public static void play5(int soundID) {
        mSoundPlayer.play(soundID, 1f, 1f, 1, 0, 1);
        mSoundPlayer.play(soundID, 1f, 1f, 1, 0, 1);
        mSoundPlayer.play(soundID, 1f, 1f, 1, 0, 1);
        mSoundPlayer.play(soundID, 1f, 1f, 1, 0, 1);
    }
}