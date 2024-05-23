import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MusicScreen {
    File file;
    String fileName;
    String path;
    JFrame frame;
    MusicPlayer mp;
    JTextField textFileName;
    JPanel buttonPanel, filePanel;
    JButton playButton, pauseButton, previousButton, nextButton, chooseButton, muteButton;
    JSlider volSlider;
    Font myFont = new Font("Times New Roman", Font.BOLD, 30);
    //MusicPlayer player;
    Exception exception;


    public void musicScreen() {

        mp = new MusicPlayer();
        frame = new JFrame("Müzik Çalar");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        ImageIcon play = new ImageIcon("src//icon//play.png");
        ImageIcon pause = new ImageIcon("src//icon//pause.png");
        ImageIcon previous = new ImageIcon("src//icon//previous.png");
        ImageIcon next = new ImageIcon("src//icon//forward.png");
        ImageIcon mute = new ImageIcon("src//icon//mute.png");
        ImageIcon unmute = new ImageIcon("src//icon//unmute.png");
        ImageIcon add = new ImageIcon("src//icon//add.png");


        chooseButton = new JButton(add);
        chooseButton.setSize(10, 10);
        chooseButton.setBounds(10, 10, 50, 40);
        chooseButton.setFocusable(false);
        chooseButton.setOpaque(false);
        chooseButton.setContentAreaFilled(false);
        chooseButton.setBorderPainted(false);
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChoosing fileChoosing = new FileChoosing();
                file = fileChoosing.file;
                fileName = fileChoosing.fileName;
                path = fileChoosing.path;
                textFileName.setText(fileName);
                mp.setFile(file);
            }
        });


        // ▶ ⏹ ⏮ ⏭🔈🔉
        playButton = new JButton(play);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        pauseButton = new JButton(pause);
        pauseButton.setOpaque(true);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setVisible(false);
        previousButton = new JButton(previous);
        previousButton.setOpaque(true);
        previousButton.setBorderPainted(false);
        previousButton.setContentAreaFilled(false);
        nextButton = new JButton(next);
        nextButton.setOpaque(true);
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        muteButton = new JButton(mute);
        muteButton.setOpaque(true);
        muteButton.setBorderPainted(false);
        muteButton.setContentAreaFilled(false);


        playButton.setBounds(160, 5, 50, 50);
        playButton.setFocusable(false);

        pauseButton.setBounds(160, 5, 50, 50);
        pauseButton.setFocusable(false);

        previousButton.setBounds(100, 5, 50, 50);
        previousButton.setFocusable(false);

        nextButton.setBounds(220, 5, 50, 50);
        nextButton.setFocusable(false);

        muteButton.setBounds(275,5,50,50);
        muteButton.setFocusable(false);


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(mp.clip.getFramePosition()==0) {
                   mp.play(file);
                   System.out.println(file);
               }
               else
                   mp.resume();

               playButton.setVisible(false);
               pauseButton.setVisible(true);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mp.pause();
                    pauseButton.setVisible(false);
                    playButton.setVisible(true);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mp.mute)
                    muteButton.setIcon(unmute);
                else
                    muteButton.setIcon(mute);
                mp.volMute();
            }
        });

        volSlider = new JSlider(-60, 10);
        volSlider.setPaintTicks(true);
        volSlider.setPaintLabels(true);
        volSlider.setPreferredSize(new Dimension(300, 50));
        volSlider.setBounds(330, 10, 150, 40);


        volSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mp.currentVol = volSlider.getValue();
                mp.fc.setValue(mp.currentVol);
            }
        });


        textFileName = new JTextField();
        textFileName.setBounds(0, 0, 500, 200);
        textFileName.setHorizontalAlignment(JTextField.CENTER);
        textFileName.setEditable(false);
        textFileName.setFocusable(false);
        textFileName.setFont(myFont);


        filePanel = new JPanel();
        filePanel.setLayout(null);
        filePanel.setBounds(0, 0, 500, 200);
        filePanel.add(textFileName);


        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 200, 500, 300);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.PINK);

        buttonPanel.add(chooseButton);
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(muteButton);
        buttonPanel.add(volSlider);


        frame.add(buttonPanel);
        frame.add(filePanel);
        frame.setVisible(true);
    }
}
