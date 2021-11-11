package drawingellipses;

import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingEllipses extends JFrame{

    GraphicsPanel drawPanel = new GraphicsPanel();
    JButton drawButton = new JButton();
    JButton fillButton = new JButton();
    JButton clearButton = new JButton();
    static Ellipse2D.Double myEllipse;
    static boolean isDrawn = false;
    static boolean isFilled = false;
    static int fillRed, fillGreen, fillBlue;
    Random myRandom = new Random();
    
    public static void main(String[] args) {
       new DrawingEllipses().show();
    }

    public DrawingEllipses() throws HeadlessException {
        setTitle("Drawing Ellipses");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
            
});
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        drawPanel.setPreferredSize(new Dimension(300, 200));
        drawPanel.setBackground(Color.WHITE);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridBagConstraints);
        drawButton.setText("Draw Ellipse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(drawButton, gridBagConstraints);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawButtonActionPerformed(e);
            }
        });
        fillButton.setText("Fill Ellipse");
        fillButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        getContentPane().add(fillButton, gridBagConstraints);
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillButtonActionPerformed(e);
            }
        });
        clearButton.setText("Clear Ellipse");
        clearButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        getContentPane().add(clearButton, gridBagConstraints);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearButtonActionPerformed(e);
            }
        });
        pack();
    }
    void drawButtonActionPerformed(ActionEvent e){
        int w = (myRandom.nextInt(71)+ 20) * drawPanel.getWidth()/100;
        int h = (myRandom.nextInt(71)+ 20) * drawPanel.getHeight()/100;
        int x = (int)(0.5*(drawPanel.getWidth()-w));
        int y = (int)(0.5*(drawPanel.getHeight()-h));
        myEllipse = new Ellipse2D.Double(x, y, w, h);
        isDrawn = true;
        isFilled = false;
        drawButton.setEnabled(false);
        fillButton.setEnabled(true);
        clearButton.setEnabled(true);
        drawPanel.repaint();
    }
    void fillButtonActionPerformed(ActionEvent e){
        isFilled = true;
        drawButton.setEnabled(false);
        fillRed = myRandom.nextInt(256);
        fillGreen = myRandom.nextInt(256);
        fillBlue = myRandom.nextInt(256);
        drawPanel.repaint();
    }
    void clearButtonActionPerformed(ActionEvent e){
        isDrawn = false;
        isFilled = false;
        drawButton.setEnabled(true);
        fillButton.setEnabled(false);
        clearButton.setEnabled(false);
        drawPanel.repaint();
    }
    void exitForm(WindowEvent e){
        System.exit(0);
    }

    class GraphicsPanel extends JPanel{

        public GraphicsPanel() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            super.paintComponent(g2D);
            if(DrawingEllipses.isFilled){
                g2D.setPaint(new Color(DrawingEllipses.fillRed, DrawingEllipses.fillGreen, DrawingEllipses.fillBlue));
                g2D.fill(DrawingEllipses.myEllipse);
            }
            if(DrawingEllipses.isDrawn){
                g2D.setStroke(new BasicStroke(3));
                g2D.setPaint(Color.BLACK);
                g2D.draw(DrawingEllipses.myEllipse);
            }
            g2D.dispose();
        }
        
    }
}
