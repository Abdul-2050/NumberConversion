package Practice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BinaryConversion extends JFrame implements ActionListener{
    JPanel panel;
    JComboBox<String> from;
    JComboBox<String> to;
    JTextField textField;
    JTextArea textArea;
    JButton convert;
    JButton reset;

    BinaryConversion(){

        setBounds(200, 100, 500, 500);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        JLabel label0= new JLabel("Binary Conversion");
        label0.setBounds(20, 5, 200, 20);
        label0.setFont(new Font("", Font.BOLD, 18));
        add(label0);

        panel= new JPanel();
        panel.setBounds(20, 40, 440, 400);
        panel.setBackground(new Color(240, 240, 208));
        panel.setLayout(null);
        add(panel);

        JLabel label= new JLabel("From");
        label.setBounds(20, 20, 100, 20);
        label.setFont(new Font("", Font.PLAIN, 15));
        panel.add(label);

        String[] text= {"Binary", "Decimal"};
        from= new JComboBox<>(text);
        from.setBounds(20, 45, 180, 25);
        panel.add(from);

        JLabel label2= new JLabel("To");
        label2.setBounds(250, 20, 100, 20);
        label2.setFont(new Font("", Font.PLAIN, 15));
        panel.add(label2);

        String[] text2= {"Decimal" ,"Binary"};
        to= new JComboBox<>(text2);
        to.setBounds(250, 45, 180, 25);
        panel.add(to);

        JLabel label3= new JLabel("Enter a Binary Number");
        label3.setBounds(20, 90, 200, 20);
        label3.setFont(new Font("", Font.PLAIN, 14));
        panel.add(label3);

        textField= new JTextField();
        textField.setBounds(20, 115, 400, 40);
        textField.setFont(new Font("", Font.PLAIN, 16));
        panel.add(textField);

        convert= new JButton("= Convert");
        convert.setBounds(20, 160, 100, 30);
        convert.setBackground(new Color(0, 105, 217));
        convert.setForeground(Color.white);
        convert.addActionListener(this);
        panel.add(convert);

        reset= new JButton("x Reset");
        reset.setBounds(130, 160, 100, 30);
        reset.setBackground(new Color(90, 98, 104));
        reset.setForeground(Color.white);
        reset.addActionListener(this);
        panel.add(reset);

        textArea = new JTextArea();
        textArea.setBounds(20, 220, 400, 80);
        textArea.setEditable(false);
        textArea.setFont(new Font("", Font.BOLD, 14));
        panel.add(textArea);

    }

    long binaryToDecimal(){
        // variable for storing the deciamal number
        long decimal=0;
        // char[] for storing the reverse char
        char[] ch= new char[textField.getText().length()];

        int b=0;
        for(int a=textField.getText().length()-1;a>=0;a--, b++){
            ch[a]= textField.getText().charAt(b);
        }
        
        // loop for converting the binary to decimal
        for(int a=textField.getText().length()-1;a>=0;a--){
            decimal= decimal+ power(2, a)*Character.getNumericValue(ch[a]);
        }
        
        return decimal;
    }

    static int power(int a, int b){
        // Method will return the power of an particular number
        int res=1;
        for(int c=0;c<b;c++){
            res=res * a;
        }
        return res;
    }

    boolean isEmpty(JTextField textField){
        if(textField.getText().isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    void reset(JTextField textField, JTextArea textArea){
        textField.setText("");
        textArea.setText("");
    }


    StringBuilder deciamalToBinary(){

        long quotient=0;
        long decimal=Long.parseLong(textField.getText());
        int a=0;

        // this will give the length
        quotient= decimal/2;
        while(quotient!=0){
            quotient= decimal/2;
            decimal=quotient;
            a++;
        }

        // give the intinal value
        quotient=0;
        decimal= Long.parseLong(textField.getText());

        // for storing the remainder
        long[] remainder= new long[a];

        for(int b=0;b<a;b++){
            quotient= decimal/2;
            remainder[b]= decimal % 2;
            decimal=quotient;
        }

        // array for saving the reverse value
        long[] binary=new long[remainder.length];

        int c=0;
        for(int b=remainder.length-1;b>=0;b--, c++){
            binary[c]= remainder[b];
        }

        // save binary in StringBuilder
        StringBuilder arr2= new StringBuilder();
        for(int b=0;b<=binary.length-1;b++){
            arr2.append(binary[b]);
        }


        return arr2;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== convert){
            if(from.getSelectedItem().equals("Binary")&& to.getSelectedItem().equals("Decimal")){
                if(isEmpty(textField)){
                    JOptionPane.showMessageDialog(null, "Can not be Empty");
                }else{
                    textArea.setText("Decimal: "+ binaryToDecimal());
                }
            }else if(from.getSelectedItem().equals("Decimal")&& to.getSelectedItem().equals("Binary")){
                if(isEmpty(textField)){
                    JOptionPane.showMessageDialog(null, "Can not be Empty");
                }else{
                    textArea.setText("Binary: "+ deciamalToBinary());
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please select Binary or Decimal");
            }
        }else if(e.getSource()== reset){
            reset(textField, textArea);
        }
    }


    public static void main(String[] args) {
        new BinaryConversion().setVisible(true);
    }
}
