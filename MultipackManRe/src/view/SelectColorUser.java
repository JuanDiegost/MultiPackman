/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Pedro
 */
public class SelectColorUser extends JDialog {

    private JColorChooser jColorChooser;
    private JPanel panel;
    private JButton button;
    private Color color;
    
    public SelectColorUser() {
        
        setSize(650, 400);
        setTitle("Seleccione Color");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        jColorChooser = new JColorChooser();
          panel.add(jColorChooser);
        jColorChooser.setVisible(true);
        button = new JButton("Enviar Color");
        add(button, BorderLayout.SOUTH);
      button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = jColorChooser.getColor();
            }
        });
    }

    public Color getColor() {
        return color;
    }
    
    public static void main(String[] args) {
        SelectColorUser s = new SelectColorUser();
        s.setVisible(true);
        System.out.println("color seleccionado: "+s.getColor());
    }
}
