/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoot.me;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maybe
 */
public class ShootMe extends Applet implements Runnable,KeyListener  {
    int level=1;
    List<Integer> myListx = new ArrayList<Integer>();
    List<Integer> myListy = new ArrayList<Integer>();
    List<String> myListchar = new ArrayList<String>();
    int point=0;
    int result=0;
    int right=0;
    int wrong=0;
    int getTime=30;
    String high="";
    int highScore;
    int animate=500;
    FileInputStream in=null;
    FileOutputStream out=null;
    public void init(){
        try {
            resize(1000,600);
            in = new FileInputStream("/home/kavin/NetBeansProjects/Shoot Me/src/shoot/me/input.txt");
            int c;
            high="";
            while( (c= in.read())  != -1){
                //System.out.println("in while");
                high=high+(char)c;
            }
            in.close();
            highScore=Integer.parseInt(high);
            //System.out.println(highScore);
            setBackground(Color.white);
            
            //Scanner in=new Scanner(System.in);
            //getTime=in.nextInt();
            addKeyListener(this);
            generateRect(level);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println(e.getKeyChar());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("detected key pressed : "+e.getKeyCode());
     //Just restart
     if(e.getKeyCode()==10 && result==0){
            try {
                myListx.clear();
                myListy.clear();
                myListchar.clear();
                level=1;
                result=0;
                point=0;
                right=0;
                wrong=0;
                timer=30;
                animate=500;
                in = new FileInputStream("/home/kavin/NetBeansProjects/Shoot Me/src/shoot/me/input.txt");
                int c;
                high="";
                while( (c= in.read())  != -1){
                    //System.out.println("in while");
                    high=high+(char)c;
                }
                in.close();
                highScore=Integer.parseInt(high);
                
                generateRect(level);
                //stop();
                //start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
     else if(e.getKeyCode()==10 && result==1){
            try {
                myListx.clear();
                myListy.clear();
                myListchar.clear();
                level=1;
                result=0;
                point=0;
                right=0;
                wrong=0;
                timer=30;
                animate=500;
                in = new FileInputStream("/home/kavin/NetBeansProjects/Shoot Me/src/shoot/me/input.txt");
                int c;
                high="";
                while( (c= in.read())  != -1){
                    //System.out.println("in while");
                    high=high+(char)c;
                }
                in.close();
                highScore=Integer.parseInt(high);
                
                generateRect(level);
                //stop();
                start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
     else{       //game on 
     if(result==0){   
        if(e.getKeyCode()==27){
            result=1;
            stop();
            repaint();
        }
        else{
            int ans=0;
            for(int i=0;i<myListchar.size();i++){
                if(  (""+e.getKeyChar()).equals(myListchar.get(i))  )
                {
                    myListchar.remove(i);
                    myListx.remove(i);
                    myListy.remove(i);
                    point=point+level;
                    ++right;
                    ans=1;
                    repaint();
                    break;
                }
            }
            if(ans==0){
                point=point-1;
                ++wrong;
                repaint();
            }
            if(myListchar.size()==0){
                point=(int) (point+Math.floor(timer/2)*level);
                timer=timer+5+level;
                generateRect(++level);
                repaint();
            }
        }
     } //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void generateRect(int level){
        int[] arr;
        int x,y;
        char mychar;
        for(int i=0;i<5*level;i++)
        {
            x =100+ (int) Math.floor(Math.random()*(1000-230));
            myListx.add(x);
            y=100+(int) Math.floor(Math.random()*(600-230));
            myListy.add(y);
            mychar=(char) ((int) Math.floor(Math.random()*(26))+97);
            myListchar.add(""+mychar);
            
            
        }
    }
    
    public void drawGame(Graphics g) throws FileNotFoundException{
        if(result==0){
            for(int i=0;i<myListx.size();i++){
                g.setColor(Color.black);
                g.fillRect(myListx.get(i),myListy.get(i), 30, 30);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
                g.drawString(""+myListchar.get(i), myListx.get(i)+10, myListy.get(i)+20);              
            }
            g.setColor(Color.black);
            g.fillRect(0,60 , 1000, 2);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.drawString("Rocket Typer", 420, 25);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Level = "+level, 10,20);
            g.drawString("Points = "+point, 870,20);
            if(timer<=5)
                g.setColor(Color.red);
            g.drawString("Timer = "+timer, 450,50);
            g.setColor(Color.black);
            g.drawString("Type the letters appearing in the boxes before the timer runs out", 250, 550);
            g.drawString("(esc) to quit & (enter) to restart", 400, 575);
        }
        else if(result==1){
            g.setColor(Color.black);
            g.fillRect(0,50 , 1000, 2);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Result", 450, 40);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Level : "+(level-1)+" Cleared", 425, 200);
            g.setColor(Color.GREEN);
            g.drawString("Right Strokes : "+right, 428, 250);
            g.setColor(Color.red);
            g.drawString("Wrong Strokes : "+wrong, 426, 300);
            g.setColor(Color.black);
            g.drawString("Score : "+point, 450, 350);
            g.drawString("(enter) to restart", 440, 575);
            if((right+wrong)==0){
                wrong=1;
            }
            double eff= (double) right/(right+wrong) ;
            //System.out.println("efficiency = "+eff+" right = "+right+" wrong : "+wrong);
            g.drawString("Efficiency : "+( eff*100)+" %", 430, 400);
            Image img;
            MediaTracker tr;
            tr = new MediaTracker(this);
            img = getImage(getCodeBase(), "/home/kavin/NetBeansProjects/Shoot Me/src/shoot/me/rock.png");
            tr.addImage(img,0);
            g.drawImage(img, 0, animate, this);
            g.drawImage(img, 900, animate, this);
            if(point>highScore){
                try {
                    g.setColor(Color.blue);
                    g.drawString("New High Score Achieved !", 420, 450);
                    g.drawString("High Score !",575 , 350);
                    out=new FileOutputStream("/home/kavin/NetBeansProjects/Shoot Me/src/shoot/me/input.txt");
                    String mystr=""+point;
                    //System.out.println("Before writing");
                    for(int i=0;i<mystr.length();i++)
                    {
                        
                        //System.out.println("inside for");
                        out.write( (int) mystr.charAt(i));
                    }
                    //System.out.println("closing file");
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                g.setColor(Color.blue);
                g.drawString("High Score : "+highScore, 430, 450);
            }
            if(animate>-200){
                try {
                    animate=animate-5;
                    Thread.sleep(10);
                    
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void paint(Graphics g){
        try {
            //g.fillRect(100, 100, 200, 200);

            drawGame(g);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShootMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int timer; Thread cd;

    public void start() { 
        
        timer = getTime; cd = new Thread(this); cd.start();

    }

    public void stop() { cd = null;}
    @Override
    public void run() {	

    while (timer>=0 && cd!=null) {

    try{Thread.sleep(1000);} catch (InterruptedException e){}
        if(timer==0){
            result=1;
        }
        --timer; repaint(); 

    }

    }
    
    
}
