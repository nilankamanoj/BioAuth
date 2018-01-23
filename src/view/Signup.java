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
import util.UserRepo;

public class Signup extends javax.swing.JFrame
{
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnProceed;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblKey;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRound;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarn;
    private javax.swing.JTextField txtKey;
    private javax.swing.JTextField txtUname;

    private Signup signUp;
    private Signin signIn;
    private int round = 1;
    private final int keySize = 6;

    private List<String> characters = new ArrayList<>();
    private List<Long> pressTimes = new ArrayList<>();
    private List<Long> interKeyTimes = new ArrayList<>();

    private List<String> finalCharacters = new ArrayList<>();
    private List<List<Long>> allPressTimes = new ArrayList<>();
    private List<List<Long>> allInterKeyTimes = new ArrayList<>();

    private Long time;
    public Long interKeyTime;

    public Signup()
    {
        initComponents();
        this.setResizable(false);
        this.signUp=this;
        txtKey.setText("");
        lblWarn.setText("");
        lblWarn.setForeground(Color.red);
        this.setTitle("BioAuth-SignUp");
        clearLists();
        btnProceed.setEnabled(false);

        KeyListener keyListener = new KeyListener()
        {

            public void keyPressed(KeyEvent keyEvent)
            {
                time=System.currentTimeMillis();
                if(pressTimes.size()>0)
                {
                    interKeyTimes.add(time-interKeyTime);
                }
                lblWarn.setText("");
                lblRound.setText("round "+(round));
            }
            public void keyReleased(KeyEvent keyEvent)
            {

                if(getIt(keyEvent).chars().allMatch(Character :: isLetter) && !getIt(keyEvent).equals("Shift") && !getIt(keyEvent).equals("Space") && !getIt(keyEvent).equals("Backspace"))
                {

                    characters.add(getIt(keyEvent));
                    lblInfo.setText("key text : "+characters.size()+" out of "+keySize);
                    if(round==1 || finalCharacters.get(characters.size()-1).equals(characters.get(characters.size()-1)))
                    {


                        pressTimes.add(System.currentTimeMillis()-time);
                        if(characters.size()==keySize &&pressTimes.size()==keySize)
                        {
                            lblInfo.setText("");

                            finalCharacters.clear();

                            for(int i= 0 ; i<keySize ;i++)
                            {
                                finalCharacters.add(characters.get(i));
                            }

                            allPressTimes.add(cloneLst(pressTimes));
                            allInterKeyTimes.add(cloneLst(interKeyTimes));
                            clearLists();
                            round++;
                            if(round==4)
                            {

                                if(txtUname.getText().length()>4)
                                {
                                    txtKey.setEnabled(false);
                                    btnProceed.setEnabled(true);
                                    System.out.println(finalCharacters.toString().replace("[", "").replace("]", ""));
                                    System.out.println(getAvg(allPressTimes).toString().replace("[", "").replace("]", ""));
                                    System.out.println(getAvg(allInterKeyTimes).toString().replace("[", "").replace("]", ""));
                                }
                                else
                                {
                                    txtKey.setEnabled(false);
                                    lblWarn.setText("input a name, longer than 4");
                                }

                            }

                            clearLists();
                            txtKey.setText("");

                        }

                        interKeyTime=System.currentTimeMillis();
                    }
                    else
                    {
                        lblWarn.setText("missmatch with privious");
                        clearLists();
                        txtKey.setText("");
                    }
                }
                else
                {
                    lblWarn.setText("input only simple letters");
                    clearLists();
                    txtKey.setText("");
                }


            }
            public void keyTyped(KeyEvent keyEvent)
            {

            }
            private String getIt(KeyEvent keyEvent)
            {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                return String.valueOf(keyText);
            }
        };
        txtKey.addKeyListener(keyListener);

        KeyListener keyListener2 = new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if(txtUname.getText().length()>4 && !txtKey.isEnabled())
                {
                    lblWarn.setText("");
                    btnProceed.setEnabled(true);
                }
                else
                {
                    btnProceed.setEnabled(false);
                }

            }
        };
        txtUname.addKeyListener(keyListener2);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents()
    {

        txtKey = new javax.swing.JTextField();
        lblKey = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblWarn = new javax.swing.JLabel();
        lblRound = new javax.swing.JLabel();
        txtUname = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        btnProceed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtKey.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtKeyActionPerformed(evt);
            }
        });

        lblKey.setFont(new java.awt.Font("Tahoma", 0, 12));
        lblKey.setText("key ");
        btnClear.setText("clear key");
        btnClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClearActionPerformed(evt);
            }
        });

        btnReset.setText("start again");
        btnReset.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnResetActionPerformed(evt);
            }
        });

        btnBack.setText("go back");
        btnBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Tahoma", 0, 12));
        lblName.setText("Name");
        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblTitle.setText("Sign Up");
        btnProceed.setText("proceed");

        btnProceed.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnProceedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(117, 117, 117))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())))
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
        .addGap(36, 36, 36)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(layout.createSequentialGroup()
        .addGap(0, 60, Short.MAX_VALUE)
        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(lblKey, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
        .addComponent(lblInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(txtKey, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        .addComponent(txtUname)))))
        .addGroup(layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addComponent(lblWarn, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(btnProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(lblRound, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        .addContainerGap())
        );
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addComponent(lblName)
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(txtUname, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(lblKey))
        .addComponent(lblRound, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lblWarn, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(35, 35, 35)
        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
        );

        pack();
    }

    private void txtKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt)
    {
        txtKey.setText("");
        clearLists();
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt)
    {
        signUp.setVisible(false);
        signIn.setAttempts(0);
        signIn.setVisible(true);

    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt)
    {
        txtKey.setText("");
        txtUname.setText("");
        lblWarn.setText("");
        lblRound.setText("round 1");
        characters.clear();
        pressTimes.clear();
        interKeyTimes.clear();
        finalCharacters.clear();
        allPressTimes.clear();
        allInterKeyTimes.clear();
        round = 1;
        btnProceed.setEnabled(false);
        txtKey.setEnabled(true);

    }

    private void btnProceedActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserRepo userRepo;
        user user = new user();
        user.setName(txtUname.getText().toString());
        user.setCharacters(finalCharacters);
        user.setPressTimes(getAvg(allPressTimes));
        user.setInterKeyTimes(getAvg(allInterKeyTimes));
        try
        {
            userRepo = new UserRepo();
            userRepo.saveUser(user);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
        user =null;
        signIn.setVisible(true);
        signUp.setVisible(false);

    }

    public void runSignUp(Signin sinIn)
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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                signUp.signIn= sinIn;
                signIn.setVisible(false);
                signUp.setVisible(true);
            }
        });
    }

    private List<Long> cloneLst(List<Long> lst)
    {
        List<Long> lst2 = new ArrayList<>();

        for(Long i : lst)
        {
            lst2.add(i);
        }

        return lst2;
    }

    private void clearLists()
    {
        characters.clear();
        pressTimes.clear();
        interKeyTimes.clear();
    }

    private List<Long> getAvg(List<List<Long>> lists)
    {
        List<Long> avgTimes = new ArrayList<>();
        for(int i =0; i<lists.get(0).size() ;i++)
        {
            avgTimes.add((lists.get(0).get(i) +lists.get(1).get(i)+lists.get(2).get(i))/3 );
        }

        return avgTimes;
    }

}
