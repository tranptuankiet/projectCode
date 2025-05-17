/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trello;
import javax.swing.*;
import java.awt.event.*;

public class CongGUI extends JFrame {
    private JTextField txtSo1, txtSo2, txtKetQua;
    private JButton btnCong;

    public CongGUI() {
        setTitle("Cộng Hai Số");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblSo1 = new JLabel("Số 1:");
        lblSo1.setBounds(20, 20, 50, 25);
        add(lblSo1);

        txtSo1 = new JTextField();
        txtSo1.setBounds(80, 20, 180, 25);
        add(txtSo1);

        JLabel lblSo2 = new JLabel("Số 2:");
        lblSo2.setBounds(20, 50, 50, 25);
        add(lblSo2);

        txtSo2 = new JTextField();
        txtSo2.setBounds(80, 50, 180, 25);
        add(txtSo2);

        btnCong = new JButton("Cộng");
        btnCong.setBounds(100, 90, 100, 30);
        add(btnCong);

        JLabel lblKetQua = new JLabel("Kết quả:");
        lblKetQua.setBounds(20, 130, 60, 25);
        add(lblKetQua);

        txtKetQua = new JTextField();
        txtKetQua.setBounds(80, 130, 180, 25);
        txtKetQua.setEditable(false);
        add(txtKetQua);

        btnCong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double so1 = Double.parseDouble(txtSo1.getText());
                    double so2 = Double.parseDouble(txtSo2.getText());
                    double tong = so1 + so2;
                    txtKetQua.setText(String.valueOf(tong));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!");
                }
            }
        });
    }

    public static void main(String[] args) {
        CongGUI frame = new CongGUI();
        frame.setVisible(true);
    }
}
