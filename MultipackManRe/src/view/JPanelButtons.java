/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerButtons;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import value.GlobalActionsAnimation;

/**
 *
 * @author KAROL ALFONSO
 */
public final class JPanelButtons extends JPanel{
    private JButton jbUp;
    private JButton jbDown;
    private JButton jbLeft;
    private JButton jbRight;
    private JButton jbDiagUPRight;
    private JButton jbDiagUPLeft;
    private JButton jbDiagDownRight;
    private JButton jbDiagDownLeft;
    private ControllerButtons controllerButtons;
    
    public JPanelButtons(ControllerButtons controllerButtons) {
    this.controllerButtons=controllerButtons;
//       this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        SpringLayout layout = new SpringLayout();
//        this.setLayout(layout);
       this.setBounds(510,0,300, 550);
       Border bordejpanel = new TitledBorder(new EtchedBorder(), "botones");
       this.setBorder(bordejpanel);
//    this.setBounds(500, 0, 300,500);
    this.setLayout(null);
    this.setBackground(Color.yellow);
    init();

    }
    
    public void init(){
    initButtons();
    }
    private void initButtons(){
    addjbUp();
    addjbDown();
    addjbLeft();
    addjbRight();
    addjbDiagUPRight();
    addjbDiagUPLeft();
    addjbDiagDownRight();
    addjbDiagDownLeft();
    }
        
    private void addjbUp() {
    jbUp=new JButton("8");
    jbUp.setBounds(100,0,100,160);
    ImageIcon imageIcon = new ImageIcon("src/iconProyect/up.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbUp.setIcon(icon);
        jbUp.setActionCommand(GlobalActionsAnimation.ACTION_UP);
        jbUp.addActionListener(controllerButtons);
               this.add(jbUp);    
    }

    private void addjbDown() {
        jbDown=new JButton("2");
        jbDown.setBounds(100, 320, 100,160);
//        this.jbDown.setToolTipText("Guarda el delito en el maestro de crimenes");
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/down.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbDown.setIcon(icon);
        jbDown.setActionCommand(GlobalActionsAnimation.ACTION_DOWN);
        jbDown.addActionListener(controllerButtons);
        this.add(jbDown); 
    
    }

    private void addjbLeft() {
     jbLeft=new JButton("4");
     jbLeft.setBounds(0, 160, 100, 160);
//        this.jbLeft.setToolTipText("Guarda el delito en el maestro de crimenes");
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/right.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbLeft.setIcon(icon);
        jbLeft.setActionCommand(GlobalActionsAnimation.ACTION_LEFT);
        jbLeft.addActionListener(controllerButtons);
        this.add(jbLeft); 
    
    }

    private void addjbRight() {
    jbRight=new JButton("6");
    jbRight.setBounds(200, 160, 100, 160);
//        this.jbRight.setToolTipText("Guarda el delito en el maestro de crimenes");
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/left.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbRight.setIcon(icon);
        jbRight.setActionCommand(GlobalActionsAnimation.ACTION_RIGHT);
        jbRight.addActionListener(controllerButtons);
        this.add(jbRight); 
          
    }

    private void addjbDiagUPRight() {
        jbDiagUPRight=new JButton("9");
        jbDiagUPRight.setBounds(200, 0, 100, 160);
//        this.jbDiagUPRight.setToolTipText("Guarda el delito en el maestro de crimenes");
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/diag_up_right.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbDiagUPRight.setIcon(icon);
        jbDiagUPRight.setActionCommand(GlobalActionsAnimation.ACTION_DIAG_UP_RIGHT);
        jbDiagUPRight.addActionListener(controllerButtons);
        this.add(jbDiagUPRight); 
   
    }

    private void addjbDiagUPLeft() {
    jbDiagUPLeft=new JButton("");
    jbDiagUPLeft.setBounds(0, 0, 100, 160);
//        this.jbDiagUPLeft.setToolTipText("Guarda el delito en el maestro de crimenes");
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/diag_up_left.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbDiagUPLeft.setIcon(icon);
        jbDiagUPLeft.setActionCommand(GlobalActionsAnimation.ACTION_DIAG_UP_LEFT);
        jbDiagUPLeft.addActionListener(controllerButtons);
        this.add(jbDiagUPLeft); 
    
    }

    private void addjbDiagDownRight() {
    jbDiagDownRight=new JButton("3");
    jbDiagDownRight.setBounds(200,320, 100, 160);

        ImageIcon imageIcon = new ImageIcon("src/iconProyect/diag_down_right.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbDiagDownRight.setIcon(icon);
        jbDiagDownRight.setActionCommand(GlobalActionsAnimation.ACTION_DIAG_DOWN_RIGHT);
        jbDiagDownRight.addActionListener(controllerButtons);
        this.add(jbDiagDownRight); 
       
    }

    private void addjbDiagDownLeft() {
        jbDiagDownLeft=new JButton("1"); 
       jbDiagDownLeft.setBounds(0, 320, 100, 160);
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/diag_down_left.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jbUp.getWidth(),jbUp.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbDiagDownLeft.setIcon(icon);
        jbDiagDownLeft.setActionCommand(GlobalActionsAnimation.ACTION_DIAG_DOWN_LEFT);
        jbDiagDownLeft.addActionListener(controllerButtons);
        this.add(jbDiagDownLeft); 
    }
    }

