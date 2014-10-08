package com.barkingdogsw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Mitch Pond on 10/2/2014.
 */
public class CCMainForm {
    private JPanel basePanel;
    private JPanel imagePanel;
    private JPanel listPanel;
    private JTextField filePathField;
    private JButton fileChooseButton;
    private JLabel imageLabel;
    private JList colorList;
    private Color selectedColor;

    private String filePath;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CCMainForm");
        frame.setContentPane(new CCMainForm().basePanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                if (filePath.endsWith(".pdf")) img = PDFHandler.renderPDF(filePath);
                else img = ImageHandling.getImage(filePath);
                imageLabel.setIcon(new ImageIcon(img.getScaledInstance(-1,imageLabel.getHeight(), Image.SCALE_SMOOTH)));
                colorList.setListData(ImageHandling.countColors(img).toArray());
                colorList.setCellRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus)
                    {
                        Component renderer = super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
                        renderer.setBackground(Color.decode((String) ((Map.Entry) value).getKey()));
                        return renderer;
                    }


                });
            }
        });


        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    Robot r = new Robot();
                    selectedColor = r.getPixelColor(e.getXOnScreen(),e.getYOnScreen());
                    System.out.println(Integer.toHexString(selectedColor.getRGB()));
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

}
