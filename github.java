import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;


public class github extends JFrame implements ActionListener {

    JButton before_button;
    JButton after_button;
    JButton correct_button;
    JLabel curr_date;
    String input="A";
    ArrayList<Integer> dates= new ArrayList<>();
    int low_bound=0;
    int high_bound=45384;
    int curr=0;
    boolean game_in_play= true;
    Random random = new Random();
    Scanner scan = new Scanner(System.in);
    public static String thedate="";
    int count =0;
    private static String BD = "Birthday";


    public github(){
        super();
        this.setSize(315,105);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Birthday guesser");
        JFrame.setDefaultLookAndFeelDecorated(true);

        setupCurrentDateLabel();
        setupAfterButton();
        setupBeforeButton();
        setupCorrectButton();

        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        setArray();
    }

    private void setArray() {
        for(int i=1900; i<2022;i++){
            for(int j=1; j<13; j++){
                for (int k=1; k<32;k++){
                        int number= (i*10000)+(j*100)+k;
                        dates.add(number);
                }
            }
        }
    }

    private void setupCurrentDateLabel() {
        this.curr_date= new JLabel("December 31 2022");
        curr_date.setBounds(0,5,300,30);
        curr_date.setHorizontalAlignment(JLabel.CENTER);
        this.add(curr_date);
    }

    private void setupAfterButton() {
        this.after_button = new JButton("After");
        after_button.setBounds(210,10,80,20);
        after_button.setBackground(Color.white);
        after_button.addActionListener(this);
        this.add(after_button);
    }

    private void setupBeforeButton() {
        this.before_button = new JButton("Before");
        before_button.setBounds(10,10,80,20);
        before_button.setBackground(Color.white);
        before_button.addActionListener(this);
        this.add(before_button);
    }

    private void setupCorrectButton() {
        this.correct_button = new JButton("Correct!");
        correct_button.setBounds(108,40,80,20);
        correct_button.setBackground(Color.white);
        correct_button.addActionListener(this);
        this.add(correct_button);
    }

    public static void main(String[] args){
        hi Hi= new hi();
    }

    public void paint(Graphics g){
        super.paint(g);
        if(input.equals("A") || input.equals("B")) {
            if (low_bound != high_bound) {
                curr = random.nextInt(low_bound, high_bound);
            } else {
                curr = low_bound;
            }
            String this_date = printDate(dates.get(curr));
            curr_date.setText(this_date);
            input="";

        } else if(input=="C"){
            curr_date.setText( "It took " + count + " tries!");
            Desktop d =Desktop.getDesktop();
            String number= String.valueOf(dates.get(curr));
            String file = makeHtml(number);
            try {
                d.browse(new URI(file));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private String makeHtml(String number) {
        String html_date ="";
        html_date += String.valueOf(number.charAt(4));
        html_date += String.valueOf(number.charAt(5));
        html_date += String.valueOf(number.charAt(6));
        html_date += String.valueOf(number.charAt(7));
        String file="https://apod.nasa.gov/apod/ap21"+html_date+".html";
        return file;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==after_button) {
            input = "A";
            low_bound = curr + 1;
            count++;
        } else if (e.getSource()==before_button){
            input ="B";
            high_bound = curr - 1;
            count++;
        } else if (e.getSource()==correct_button){
            input="C";
            System.out.println(BD);
        }
        repaint();
    }

    public static String printDate(int date){
        String d=Integer.toString(date);
        String current_date="";
        String day="";
        String year="";
        String month="";
        for(int i=0; i<8;i++){
            if(i<4){
                year += d.charAt(i);
            } else if ( i>=4 && i<6){
                month += d.charAt(i);
            } else {
                day += d.charAt(i);
            }
        }
        month = monthPicker(month);
        String ans = (month + " " + day + " " + year);
        return ans;
    }
    private static String monthPicker(String month) {
        switch (month){
            case "01" -> month ="January";
            case "02" -> month ="February";
            case "03" -> month ="March";
            case "04" -> month ="April";
            case "05" -> month ="May";
            case "06" -> month ="June";
            case "07" -> month ="July";
            case "08" -> month ="August";
            case "09" -> month ="September";
            case "10" -> month ="October";
            case "11" -> month ="November";
            case "12" -> month ="December";
        }
        return month;
    }
}
