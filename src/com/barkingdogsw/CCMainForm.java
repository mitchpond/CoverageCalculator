package com.barkingdogsw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by 126668 on 10/2/2014.
 */
public class CCMainForm {
    private JPanel basePanel;
    private JPanel imagePanel;
    private JPanel controlPanel;
    private JTextField filePathField;
    private JButton fileChooseButton;
    private JButton countButton;
    private JLabel imageLabel;

    private String filePath;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CCMainForm");
        frame.setContentPane(new CCMainForm().basePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CCMainForm() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        fileChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                BufferedImage img;
                if (fileChooser.showOpenDialog(basePanel)==0) filePath = fileChooser.getSelectedFile().getAbsolutePath();
                filePathField.setText(filePath);
                img = ImageHandling.getImage(filePath);
                imageLabel.setIcon(new ImageIcon(img.getScaledInstance(imagePanel.getWidth(),imagePanel.getHeight(), Image.SCALE_DEFAULT)));
                ImageHandling.countColors(img);
            }
        });


        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ImageHandling.countColors(filePath);
            }
        });
    }

}
