import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.*;

public class MusicPlayer{
    Clip clip;
    float previousVol = 0;
    float currentVol = -25;
    int pausePosition;
    FloatControl fc;
    boolean mute = false;
    public void setFile(File file){
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(File file){
            clip.setFramePosition(0);
            clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void pause(){
        clip.stop();
        pausePosition = clip.getFramePosition();
    }
    public void resume(){
        clip.setFramePosition(pausePosition);
        clip.start();
    }

    public void volMute(){
        if(mute == false){
            previousVol = currentVol;
            currentVol = -80.0f;
            fc.setValue(currentVol);
            mute = true;
        }else{
            currentVol = previousVol;
            fc.setValue(currentVol);
            mute = false;
        }
    }
}

