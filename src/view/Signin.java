/**
*
* @author Nilanka
*/
package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user;
import util.Authenticater;
import util.UserRepo;

public class Signin extends javax.swing.JFrame
{

    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSignup;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarn;
    private javax.swing.JTextField txtKey;
    private Signin signIn;
    private List<String> characters = new ArrayList<>();
    private List<Long> pressTimes = new ArrayList<>();
    private List<Long> interKeyTimes = new ArrayList<>();
    private Long time;
    public Long interKeyTime;
    private final int keySize=6;
    private UserRepo userRepo;

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    private int attempts=0;

    public Signin() throws SQLException
    {

        initComponents();
        this.setResizable(false);
        this.signIn=this;
        txtKey.setText("");
        lblWarn.setText("");
        lblWarn.setForeground(Color.red);
        this.setTitle("BioAuth-signIn");
        clearLists();
        userRepo = new UserRepo();
        Authenticater authenticater = new Authenticater();

        KeyListener keyListener;
        keyListener = new KeyListener()
        {

            public void keyPressed(KeyEvent keyEvent)
            {
                lblWarn.setText("");
                time=System.currentTimeMillis();
                if(pressTimes.size()>0)
                {
                    interKeyTimes.add(time-interKeyTime);
                }
            }
            public void keyReleased(KeyEvent keyEvent)
            {
                if(getIt(keyEvent).chars().allMatch(Character :: isLetter) && !getIt(keyEvent).equals("Shift") && !getIt(keyEvent).equals("Space") && !getIt(keyEvent).equals("Backspace"))
                {
                    characters.add(getIt(keyEvent));
                    pressTimes.add(System.currentTimeMillis()-time);
                    if(characters.size()==keySize)
                    {

                        System.out.println(interKeyTimes);
                        try
                        {
                            List<user> users=userRepo.getUsers(characters.toString().replace("[", "").replace("]", ""));
                            if(users.isEmpty())
                            {
                                lblWarn.setText("try again : invalid authentication");
                                attempts++;
                                if(attempts==5)
                                {


                                    new Thread(
                                    new Runnable()
                                    {
                                        @Override
                                        public void run() {
                                            txtKey.setEnabled(false);
                                            btnClear.setEnabled(false);
                                            btnSignup.setEnabled(false);
                                            int wait=0;
                                            while(wait<20)
                                            {
                                                try
                                                {
                                                    Thread.sleep(1000);
                                                    wait++;
                                                    lblWarn.setText("wait : "+Integer.toString(20-wait)+" seconds");
                                                    if(wait==20)
                                                    {
                                                        txtKey.setEnabled(true);
                                                        btnClear.setEnabled(true);
                                                        btnSignup.setEnabled(true);
                                                        attempts=0;
                                                        lblWarn.setText("");
                                                    }
                                                }
                                                catch (InterruptedException ex)
                                                {
                                                    Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                            }
                                        }

                                    }
                                    ).start();



                                }
                            }
                            else
                            {
                                user u = authenticater.authenticate(users, pressTimes, interKeyTimes);
                                if(u==null)
                                {
                                    lblWarn.setText("try again : invalid authentication");
                                    attempts++;
                                    if(attempts==5)
                                {


                                    new Thread(
                                    new Runnable()
                                    {
                                        @Override
                                        public void run() {
                                            txtKey.setEnabled(false);
                                            btnClear.setEnabled(false);
                                            btnSignup.setEnabled(false);
                                            int wait=0;
                                            while(wait<20)
                                            {
                                                try
                                                {
                                                    Thread.sleep(1000);
                                                    wait++;
                                                    lblWarn.setText("wait : "+Integer.toString(20-wait)+" seconds");
                                                    if(wait==20)
                                                    {
                                                        txtKey.setEnabled(true);
                                                        btnClear.setEnabled(true);
                                                        btnSignup.setEnabled(true);
                                                        attempts=0;
                                                        lblWarn.setText("");
                                                    }
                                                }
                                                catch (InterruptedException ex)
                                                {
                                                    Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                            }
                                        }

                                    }
                                    ).start();



                                }
                                }
                                else
                                {
                                    
                                    new Home().runHome(signIn,u);
                                }
                            }
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        clearLists();
                        txtKey.setText("");

                    }
                    interKeyTime=System.currentTimeMillis();

                }
                else
                {
                    clearLists();
                    txtKey.setText("");
                    lblWarn.setText("invalid key");

                }
            }
            public void keyTyped(KeyEvent keyEvent)
            {

            }
            private String getIt(KeyEvent keyEvent)
            {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                return keyText;
            }
        };

        txtKey.addKeyListener(keyListener);

    }



    @SuppressWarnings("unchecked")
    private void initComponents()
    {

        btnClear = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnSignup = new javax.swing.JButton();
        lblWarn = new javax.swing.JLabel();
        txtKey = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnClear.setText("clear");
        btnClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClearActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblTitle.setText("Enter Key Word");

        btnSignup.setText("sign up");
        btnSignup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSignupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(lblWarn, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(99, 99, 99))
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGap(133, 133, 133)
        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup()
        .addGap(52, 52, 52)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(layout.createSequentialGroup()
        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGap(33, 33, 33)
        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lblWarn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(130, Short.MAX_VALUE))
        );

        btnClear.getAccessibleContext().setAccessibleName("btnClear");
        btnClear.getAccessibleContext().setAccessibleDescription("");

        pack();
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt)
    {
        txtKey.setText("");
        characters.clear();
        pressTimes.clear();
        interKeyTimes.clear();
    }

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt)
    {
        new Signup().runSignUp(this);
    }

    public void runSignIn()
    {

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
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                signIn.setVisible(true);
            }

        });

    }

    private int getSum(Long[] list)
    {
        int sum =0;
        for(Long d : list)
        {
            sum+=d;
        }
        return sum;
    }

    private void clearLists()
    {
        characters.clear();
        pressTimes.clear();
        interKeyTimes.clear();
    }

}
