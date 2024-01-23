package views;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.security.PublicKey;
import java.awt.image.BufferedImage;
import Controllers.LoginController;
import Entities.UserEntity;

import java.io.File;

public class LoginView extends JFrame {
    private LoginController _loginController=new LoginController();
    final private Font mainFont = new Font("Segoe UI",Font.BOLD,20);
    Container c=getContentPane();

    public LoginView()
    {
        setTitle("Login");
        setSize(840,490);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelkanan();
        panekKiri();
        setVisible(true);
        // createVisitorButton(); //
        
    }

    public void panelkanan()
    {
        JLabel lblUsername,lblPassword,lblLogin;
        JButton btnSignIn,btnRegister;
        JTextField jfUsername;
        JPasswordField pfPassword;
        JPanel pnlKanan;
        JCheckBox cbPassword;
        
        
        //panel
        pnlKanan=new JPanel();
        pnlKanan.setBounds(370,0,470,490);
        pnlKanan.setBackground(Color.gray);
        pnlKanan.setLayout(null);

        //label
        lblLogin=new JLabel("LOGIN");
        lblLogin.setFont(new Font("Segoe UI",Font.BOLD,32));
        lblLogin.setForeground(Color.darkGray);
        lblLogin.setBounds(195,50,210,50);
        

        lblUsername=new JLabel("Username");
        lblUsername.setFont(mainFont);
        lblUsername.setForeground(Color.darkGray);
        lblUsername.setBounds(26,220,97,25);

        //pengunjung
        JLabel lblVisitorUsername = new JLabel("Pengunjung");
        lblVisitorUsername.setFont(mainFont);
        lblVisitorUsername.setForeground(Color.darkGray);
        lblVisitorUsername.setBounds(26, 170, 150, 25);
        
        JTextField tfVisitorUsername = new JTextField();
        tfVisitorUsername.setFont(mainFont);
        tfVisitorUsername.setBounds(26, 200, 150, 25);
        //end

        lblPassword=new JLabel("password");
        lblPassword.setFont(mainFont);
        lblPassword.setForeground(Color.darkGray);
        lblPassword.setBounds(26,220,97,150);

        //txtField
        jfUsername=new JTextField();
        jfUsername.setFont(mainFont);
        jfUsername.setBounds(26,250,400,25);

        pfPassword=new JPasswordField();
        pfPassword.setFont(mainFont);
        pfPassword.setBounds(26,320,380,25);

        //button
        btnSignIn=new JButton("SIGN IN");
        btnSignIn.setFont(new Font("Segoe UI",Font.BOLD,9));
        btnSignIn.setBounds(26, 350, 75, 20);
        btnSignIn.setBackground(Color.lightGray);
        btnSignIn.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
        
                String username=jfUsername.getText();
                String password=pfPassword.getText();

                UserEntity user=_loginController.login(username,password);

                if (jfUsername.getText().equals("")&&pfPassword.getText().equals("")){

                    JOptionPane.showMessageDialog(null,"Silahkan Input !","Warning !" ,JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (user!=null)
                    {
                      JOptionPane.showMessageDialog(null,"login sukses !","Information !" ,JOptionPane.INFORMATION_MESSAGE);
                      setVisible(false);
                      
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"login gagal !","Warning !" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
                   
            }

            @Override
            public void mousePressed(MouseEvent e) {
             
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSignIn.setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSignIn.setBackground(Color.lightGray);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
  
            }
            
        });

        btnRegister=new JButton("SIGN UP");
        btnRegister.setFont(new Font( "Segoe UI",Font.BOLD,9));
        btnRegister.setBounds(120, 350, 75, 20);
        btnRegister.setBackground(Color.lightGray);
        btnRegister.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            //    new view_controller().toRegister();
               setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnRegister.setBackground(Color.gray);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRegister.setBackground(Color.lightGray);
                
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
      
        //checkbox
        cbPassword=new JCheckBox();
        cbPassword.setBounds(405,320,20,25);
        cbPassword.setBackground(Color.lightGray);
        cbPassword.addActionListener((e)->{
            if (cbPassword.isSelected()){
                pfPassword.setEchoChar((char)0);
            }else{
                pfPassword.setEchoChar('*');
            }
        });
        pnlKanan.add(cbPassword);
        pnlKanan.add(jfUsername);
        pnlKanan.add(lblUsername);
        pnlKanan.add(lblPassword);
        pnlKanan.add(lblLogin);
        pnlKanan.add(pfPassword);
        pnlKanan.add(btnSignIn);
        pnlKanan.add(btnRegister);
        //
        pnlKanan.add(lblVisitorUsername);
        pnlKanan.add(tfVisitorUsername);
        // pnlKanan.add(btnVisitor);
        // pnlKanan.add(btnVisitor);
        c.add(pnlKanan);

        //pengunjung
        JButton btnVisitor = new JButton("VISITOR");
        btnVisitor.setFont(new Font("Segoe UI", Font.BOLD, 9));
        btnVisitor.setBounds(200, 350, 75, 20);
        btnVisitor.setBackground(Color.lightGray);
    //     btnVisitor.addMouseListener(new MouseInputListener() {
    //     String username = "izzat";

    //     @Override
    //     public void mouseClicked(MouseEvent e) {
    //         DashboardUserView dashboard = new DashboardUserView(username);
    //         // Handle visitor button click action
    //         // JOptionPane.showMessageDialog(null, "Welcome, Visitor!", "Information !", JOptionPane.INFORMATION_MESSAGE);
    //         // DashboardUserView();
    //     }

    //     @Override
    //     public void mousePressed(MouseEvent e) {
    //     }

    //     @Override
    //     public void mouseReleased(MouseEvent e) {
    //     }

    //     @Override
    //     public void mouseEntered(MouseEvent e) {
    //         btnVisitor.setBackground(Color.gray);
    //     }

    //     @Override
    //     public void mouseExited(MouseEvent e) {
    //         btnVisitor.setBackground(Color.lightGray);
    //     }

    //     @Override
    //     public void mouseDragged(MouseEvent e) {
    //     }

    //     @Override
    //     public void mouseMoved(MouseEvent e) {
    //     }
    // });


    // Implementasi MouseInputListener
    btnVisitor.addMouseListener(new MouseInputListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Mendapatkan username dari JTextField
        String manualUsername = tfVisitorUsername.getText();
        DashboardUserView dashboard = new DashboardUserView(manualUsername);
        // Handle visitor button click action
        // JOptionPane.showMessageDialog(null, "Welcome, Visitor!", "Information !", JOptionPane.INFORMATION_MESSAGE);
        // DashboardUserView();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implementasi jika tombol mouse ditekan
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implementasi jika tombol mouse dilepas
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implementasi jika mouse masuk ke area tombol
        btnVisitor.setBackground(Color.gray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implementasi jika mouse keluar dari area tombol
        btnVisitor.setBackground(Color.lightGray);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Implementasi jika mouse sedang di-drag
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Implementasi jika mouse bergerak (tanpa menekan tombol)
    }
});



    pnlKanan.add(btnVisitor);


    //end pengunjung
    // existing code...
    }

    public void panekKiri()
    {
        //gambar
         JLabel lblWelcome;
        ImageIcon imgIcon = loadImage("assets/th.jpeg");
        JLabel imgLabel = new JLabel(imgIcon);
        imgLabel.setBounds(9, 100, 350, 350);

        // panel
        JPanel pnlKiri = new JPanel();
        pnlKiri.setBounds(0, 0, 370, 490);
        pnlKiri.setBackground(Color.white);
        pnlKiri.setLayout(null);

        // label
        lblWelcome = new JLabel("Perpus");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblWelcome.setBounds(100, 50, 300, 50);
        lblWelcome.setForeground(Color.gray);

        // image
        pnlKiri.add(imgLabel);
        pnlKiri.add(lblWelcome);
        c.add(pnlKiri);

    }

 private ImageIcon loadImage(String imagePath) {
        try {
            BufferedImage bImage = ImageIO.read(new File(imagePath));
            Image imgResize = bImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            return new ImageIcon(imgResize);
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(); // You can return a default or blank ImageIcon here
        }
    }
}
