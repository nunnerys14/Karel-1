/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package karel;

<<<<<<< HEAD
import java.awt.Desktop;
=======
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
import java.util.Arrays;
=======
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
<<<<<<< HEAD
=======
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import static karel.World.infoBox;
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e

/**
 *
 * @author Sam, Amber
 */
public class Karel extends javax.swing.JFrame
{
    private final int OFFSET = 0;
    /**
     * Creates new form Karel
     */
    JTextArea lines;
    JTextArea jta;
    JFrame textframe;
    
    public Karel()
    {
        this.setTitle("Karel");
        initComponents();
        manualPanel.setVisible(false);
        buttonPanel.setVisible(true);
        InitUI();
        
    }
    
    public void InitUI() 
    {
      // Creating the popout frame with line numbering
      textframe = new JFrame("Programmer Mode");
      // Building Menu
      JMenuBar bar1;
      JMenu menu1;
      JMenuItem menuItem, menuSave, menuSaveAs;
      bar1 = new JMenuBar();
      menu1 = new JMenu("File");
      menu1.setMnemonic(KeyEvent.VK_A);
      bar1.add(menu1);
      menuItem = new JMenuItem("Run",
                             KeyEvent.VK_T);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(
                                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
      menu1.add(menuItem);

      menuSaveAs = new JMenuItem("Auto Save");
      menu1.add(menuSaveAs);
            
      menuSave = new JMenuItem("Save As");
      menu1.add(menuSave);
            
            
      // Creating the JTextArea's
      textframe.setJMenuBar(bar1);
      JScrollPane textpane = new JScrollPane();
      jta = new JTextArea();
      lines = new JTextArea("1");
      // Listening for input and adding lines
      jta.getDocument().addDocumentListener(new DocumentListener()
      {
			public String getText()
                        {
				int caretPosition = jta.getDocument().getLength();
				Element root = jta.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++)
                                {
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}
 
		});
 
        textpane.getViewport().add(jta);
        textpane.setRowHeaderView(lines);
        textpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 
        textframe.add(textpane);
        textframe.pack();
        textframe.setSize(500,500);
        textframe.setVisible(false);
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        menuItem.addActionListener(new ActionListener() 
        {
                   @Override
                   public void actionPerformed(java.awt.event.ActionEvent e)
                   {
                       final List<String> user_input = Arrays.asList(jta.getText().split("\n"));
                       Thread loop;
                       Runnable r1 = new Runnable()
                       {
                            public void run()
                            {
                                int line_count = world.doScript(0, 0, user_input); // Running
                                if (line_count == user_input.size())
                                {
                                    infoBox("Successful run!", "Yay");
                                }
                            }
                       };
                       loop = new Thread(r1);
                       loop.start();
                   }
                                           
                                           
                                           
                                           
        });
        menuSave.addActionListener(new ActionListener() 
        {
                   @Override
                   public void actionPerformed(java.awt.event.ActionEvent e)
                   {
         		JFileChooser fileChooser = new JFileChooser();
                	fileChooser.setDialogTitle("Please Enter File Name and Choose Location");
                        List<String> user_input = Arrays.asList(jta.getText().split("\n"));
                        PrintWriter out = null;                      

                        int userSelection = fileChooser.showSaveDialog(fileChooser);
                        if (userSelection == JFileChooser.APPROVE_OPTION) 
                        {
                             try 
                             {
                                 File fileToSave = fileChooser.getSelectedFile();

                                 out = new PrintWriter(fileToSave.getAbsolutePath()+".txt");
                                 for(int loop = 0; loop < user_input.size(); loop++)
                                 {
                                    out.println(user_input.get(loop));                                
                                 }

                            out.close();
                             } catch (FileNotFoundException ex) {
                                 Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                           
                   }                       
        });

        menuSaveAs.addActionListener(new ActionListener() 
        {
                   @Override
                   public void actionPerformed(java.awt.event.ActionEvent e)
                   {
                       try 
                       {
                            List<String> user_input = Arrays.asList(jta.getText().split("\n"));
                            PrintWriter out;
                            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_HH_mm_ss");
                            Date date = new Date();
        
                            String fileName1;
                            fileName1 = "KarelCode_";
                            fileName1 += dateFormat.format(date);
                            fileName1 += ".txt";
                           
                            
                            out = new PrintWriter(fileName1);
                            
                            for(int loop = 0; loop < user_input.size(); loop++)
                            {
                               out.println(user_input.get(loop));                                
                            }

                            out.close();
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }                       
          });
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        topSubContainer = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        StepCount = new javax.swing.JLabel();
        GemCount = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bottomSubContainer = new javax.swing.JPanel();
        middleContainer = new javax.swing.JPanel();
        leftContainer = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        manualPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        rightContainer = new javax.swing.JPanel();
        world = new karel.World();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
<<<<<<< HEAD
        jMenuItem1 = new javax.swing.JMenuItem();
=======
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1006, 550));
        setPreferredSize(new java.awt.Dimension(990, 546));
        setResizable(false);

        mainContainer.setBackground(new java.awt.Color(51, 0, 0));
        mainContainer.setLayout(new java.awt.BorderLayout());

        topSubContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        topSubContainer.setFocusable(false);
        topSubContainer.setMaximumSize(new java.awt.Dimension(32767, 28));
        topSubContainer.setMinimumSize(new java.awt.Dimension(100, 28));
        topSubContainer.setPreferredSize(new java.awt.Dimension(733, 28));

        jButton3.setText("Button Mode");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton10.setText("Manual Mode");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        StepCount.setText("0");

        GemCount.setText("0");

        jLabel2.setText("Moves:");

        jLabel3.setText("Gems:");

        javax.swing.GroupLayout topSubContainerLayout = new javax.swing.GroupLayout(topSubContainer);
        topSubContainer.setLayout(topSubContainerLayout);
        topSubContainerLayout.setHorizontalGroup(
            topSubContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topSubContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 635, Short.MAX_VALUE)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 631, Short.MAX_VALUE)
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StepCount)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GemCount)
                .addGap(70, 70, 70))
        );
        topSubContainerLayout.setVerticalGroup(
            topSubContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topSubContainerLayout.createSequentialGroup()
                .addGroup(topSubContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton10)
                    .addComponent(StepCount)
                    .addComponent(GemCount)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        mainContainer.add(topSubContainer, java.awt.BorderLayout.PAGE_START);

        bottomSubContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bottomSubContainer.setMaximumSize(new java.awt.Dimension(32767, 30));
        bottomSubContainer.setMinimumSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout bottomSubContainerLayout = new javax.swing.GroupLayout(bottomSubContainer);
        bottomSubContainer.setLayout(bottomSubContainerLayout);
        bottomSubContainerLayout.setHorizontalGroup(
            bottomSubContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        bottomSubContainerLayout.setVerticalGroup(
            bottomSubContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        mainContainer.add(bottomSubContainer, java.awt.BorderLayout.PAGE_END);

        middleContainer.setBackground(new java.awt.Color(153, 153, 153));
        middleContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        middleContainer.setMaximumSize(new java.awt.Dimension(2147483647, 438));
        middleContainer.setPreferredSize(new java.awt.Dimension(975, 436));
        middleContainer.setLayout(new java.awt.BorderLayout());

        leftContainer.setMaximumSize(new java.awt.Dimension(430, 32767));
        leftContainer.setMinimumSize(new java.awt.Dimension(430, 100));
        leftContainer.setPreferredSize(new java.awt.Dimension(395, 436));
        leftContainer.setLayout(new java.awt.CardLayout());

        buttonPanel.setVisible(false);

        jButton4.setText("Go");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Left");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Right");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Get");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Put");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton5)
                        .addGap(71, 71, 71)
                        .addComponent(jButton6))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jButton4))
                    .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton8)
                        .addGap(41, 41, 41)
                        .addComponent(jButton9)))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        buttonPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5, jButton6, jButton8, jButton9});

        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        leftContainer.add(buttonPanel, "card2");

        manualPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Some BS here about welcome to karel or map info etc.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        manualPanel.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Manual Programmer text box\n\nChange the name of this textbox so its not \n\n\n\n\nso\n\n\n\n\ngeneric\n\nand\n\ntesting\nthe\nscroll\nbar\ndoes\nit\nwork\nor\ndoes\nit\nsuck");
        jScrollPane1.setViewportView(jTextArea1);

        manualPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        leftContainer.add(manualPanel, "card3");

        middleContainer.add(leftContainer, java.awt.BorderLayout.LINE_START);

        rightContainer.setMinimumSize(new java.awt.Dimension(589, 100));
        rightContainer.setPreferredSize(new java.awt.Dimension(589, 469));
        rightContainer.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout worldLayout = new javax.swing.GroupLayout(world);
        world.setLayout(worldLayout);
        worldLayout.setHorizontalGroup(
            worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        worldLayout.setVerticalGroup(
            worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        rightContainer.add(world);

        middleContainer.add(rightContainer, java.awt.BorderLayout.CENTER);

        mainContainer.add(middleContainer, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        jMenuItem2.setText("Open New Map From File");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

<<<<<<< HEAD
        jMenuItem1.setText("Reset Current Map");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

=======
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setText("Open Help File (.txt)");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        manualPanel.setVisible(false);
        buttonPanel.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        world.choiceMade("go");
        StepCount.setText("" + world.getStepCount());
        buttonPanel.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        world.choiceMade("left");
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        world.choiceMade("right");
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton8ActionPerformed
    {//GEN-HEADEREND:event_jButton8ActionPerformed
        world.choiceMade("get");
        GemCount.setText("" + world.getPlayerGem());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton9ActionPerformed
    {//GEN-HEADEREND:event_jButton9ActionPerformed
        world.choiceMade("put");
        GemCount.setText("" + world.getPlayerGem());
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton10ActionPerformed
    {//GEN-HEADEREND:event_jButton10ActionPerformed
        buttonPanel.setVisible(false);
        textframe.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    //Open help file (write help file...)
        Desktop dt = Desktop.getDesktop();
        try
        {
            dt.open( new File("help.txt") );
        } catch (IOException e) {//exception handling?
            }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        // get a file path from the user
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Please Specify the File To Open");
        File fileToOpen;
        BufferedReader readIn;
        String newMap = new String();
        
        int userSelection = userSelection = fileChooser.showOpenDialog(fileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) 
        {
            try {
                fileToOpen = fileChooser.getSelectedFile();
                readIn = new BufferedReader(new FileReader(fileToOpen));
                
                while(readIn.ready())
                {
                    newMap += readIn.readLine();
                    newMap += '\n';
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Karel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Karel.class.getName()).log(Level.SEVERE, null, ex);
            }            
            //file is now in newMap string, turn string into actual new map!
            world.setLevelString(newMap);
            world.worldDeleter();
            world.initWorld();

            //paint
            this.repaint();
            
        }
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

<<<<<<< HEAD
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        world.worldDeleter();
        world.initWorld();

        //paint
        this.repaint();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

=======
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Karel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Karel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Karel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Karel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Karel karel = new Karel();
                karel.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GemCount;
    private javax.swing.JLabel StepCount;
    private javax.swing.JPanel bottomSubContainer;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
<<<<<<< HEAD
    private javax.swing.JMenuItem jMenuItem1;
=======
>>>>>>> 2d6baf26ecad1fe7d231b02ebac790f0d3a2f61e
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel leftContainer;
    private javax.swing.JPanel mainContainer;
    private javax.swing.JPanel manualPanel;
    private javax.swing.JPanel middleContainer;
    private javax.swing.JPanel rightContainer;
    private javax.swing.JPanel topSubContainer;
    private karel.World world;
    // End of variables declaration//GEN-END:variables
}
