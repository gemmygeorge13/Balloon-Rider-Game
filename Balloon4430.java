//Mini Project 1- Balloon Game
//Gemmy George   SE_CMPN_A   Batch 2    Roll no 44
//Asher D'Mello      SE_CMPN_A    Batch 2    Roll no 30

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*<applet code="Balloon4430.class" width=1000 height=1000>
</applet>*/

public class Balloon4430 extends Applet implements KeyListener{

int cred1=1, cred2=1, cred3=1, cred4=1;
int go=1;
int score=0,level=1;
boolean isRunning=true;
String sco;
Font myFont = new Font("TimesRoman", Font.BOLD ,20);
Font myFont2 = new Font("Monotype Corsiva", Font.ITALIC + Font.BOLD,60);
int cy=10,cy1=150,cy2=450;

int objx[]={100,400,300};
int objy[]={-400,-100,-700};

int coinx[]={200,350};
int coiny[]={-300,-100};

int mx=330,my=500;
int vital1x=mx+64, vital1y=my;
int vital2x=mx+15, vital2y=my+64;
int vital3x=mx+128-15, vital3y=my+64;

Image img,obj1,obj2,obj3,coin;

 Graphics bufferGraphics; 
 Image offscreen; 
 Dimension dim; 
 
Random randomGenerator = new Random();

public void init()
  {
     dim = getSize();  
     setBackground(new Color(135,206,250)); 
     offscreen = createImage(dim.width,dim.height); 
     bufferGraphics = offscreen.getGraphics(); 

     img = getImage(getDocumentBase(), "ballooning.png");
     obj1= getImage(getDocumentBase(), "obj1.png");
     obj2= getImage(getDocumentBase(), "Obj2.png");
     obj3= getImage(getDocumentBase(), "obj3.png");
     coin= getImage(getDocumentBase(), "coin1.png");
     addKeyListener(this); 
     setFocusable(true);
   }

 public void paint(Graphics g)
 {
// DOUBLE BUFFER
{
        bufferGraphics.clearRect(0,0,dim.width,dim.width); 
}
go++;

if(score>=500 && score<1000)
level=2;
if(score>=1000 && score<2000)
level=3;
if(score>=2000 && score<3000)
level=4;
if(score>=3000)
level=5;

 backGround(bufferGraphics);

 bufferGraphics.setColor(Color.black);
 Integer x=new Integer(score);
 Integer y=new Integer(level);
 //sco=score.toString();

if(isRunning==true)
{
 bufferGraphics.setFont(myFont);
 bufferGraphics.drawString("SCORE:",650,50);
 bufferGraphics.drawString(x.toString(),730,50);
 bufferGraphics.drawString("LEVEL:",650,70);
 bufferGraphics.drawString(y.toString(),730,70);

 checkCondition(g,x,y);

 drawObs1(bufferGraphics);
 drawObs2(bufferGraphics);
 drawObs3(bufferGraphics);

 drawC1(bufferGraphics);
 drawC2(bufferGraphics);

 mainObj(bufferGraphics);
}

if(isRunning==true)
{
if(score<500)
 try{Thread.currentThread().sleep(50);} catch(Exception e){} 
else if(score<1000)
 try{Thread.currentThread().sleep(40);} catch(Exception e){} 
else if(score<2000)
 try{Thread.currentThread().sleep(30);} catch(Exception e){} 
else if(score<3000)
 try{Thread.currentThread().sleep(20);} catch(Exception e){} 
else
 try{Thread.currentThread().sleep(10);} catch(Exception e){} 
}
else 
 try{Thread.currentThread().sleep(45);} catch(Exception e){} 


if(isRunning==false)
{
gameOver(bufferGraphics,x,y);
}

 g.drawImage(offscreen,0,0,this); 
 //score++;
 repaint();
 }


   public void update(Graphics g) 
     { 
          paint(g); 
     } 


public void mainObj(Graphics g)
{
//  g.drawLine(vital1x,vital1y,vital2x,vital2y);
//  g.drawLine(vital2x,vital2y,vital3x,vital3y);
//  g.drawLine(vital3x,vital3y,vital2x,vital2y);
  g.drawImage(img,mx,my,this);
}

public void backGround(Graphics g)
{
  if(cy1>=650) cy1=-150;
  else cy1=cy1+3;
  if(cy2>=650) cy2=-150;
  else cy2=cy2+2;
  if(cy>=650) cy=-150;
  else cy=cy+3;

  drawCloud(g,25,cy);
  drawCloud(g,450,cy1);
  drawCloud(g,125,cy2);
}

public void drawCloud(Graphics g, int cx, int cy)
{
g.setColor(new Color(230,220,220));
g.fillOval(cx,cy,150,100);
g.fillOval(cx-35,cy-60,120,100);
g.fillOval(cx+20,cy-80,140,140);
g.fillOval(cx+80,cy-20,140,100);
}

public void keyTyped( KeyEvent evt ) { }
public void keyReleased( KeyEvent evt ) { }

public void keyPressed( KeyEvent evt ) {

int key= evt.getKeyCode();

if (evt.getKeyCode() == KeyEvent.VK_LEFT)
{
if(mx>100) 
{
mx=mx-10;
vital1x=mx+64; vital1y=my;
vital2x=mx+15; vital2y=my+64;
vital3x=mx+128-15; vital3y=my+64;
}
repaint();
}

if (evt.getKeyCode() == KeyEvent.VK_RIGHT)
{
if(mx<650) 
{
mx=mx+10;
vital1x=mx+64; vital1y=my;
vital2x=mx+15; vital2y=my+64;
vital3x=mx+128-15; vital3y=my+64;
}
repaint();
}

if (evt.getKeyCode() == KeyEvent.VK_ENTER)
{
mx=330;my=500;
vital1x=mx+64; vital1y=my;
vital2x=mx+15; vital2y=my+64;
vital3x=mx+128-15; vital3y=my+64;
score=0;level=1;
 isRunning=true;

objx[0]=100;
objx[1]=400;
objx[2]=300;
objy[0]=-400;
objy[1]=-100;
objy[2]=-700;

coinx[0]=200;
coinx[1]=350;
coiny[0]=-300;
coiny[1]=-100;

repaint();
}


}

public void drawObs1(Graphics g)         //FIRST OBJECT
{
 //g.drawRect(objx[0]+4,objy[0],80-4,80);
 g.drawImage(obj1,objx[0],objy[0],this);
if(objy[0]>600)
{                           
objy[0]=-10;
objx[0]=100+randomGenerator.nextInt(550);
}
else
objy[0]=objy[0]+4;
}

public void drawObs2(Graphics g)         //SECOND OBJECT
{
// g.drawRect(objx[1],objy[1],80,87);
 g.drawImage(obj2,objx[1],objy[1],this);
if(objy[1]>600)
{
objy[1]=-10;
objx[1]=100+randomGenerator.nextInt(550);
}
else
objy[1]=objy[1]+5;
}

public void drawObs3(Graphics g)         //Third OBJECT
{
 //g.drawRect(objx[2],objy[2],80,70);
 g.drawImage(obj3,objx[2],objy[2],this);
if(objy[2]>600)
{
objy[2]=-10;
objx[2]=100+randomGenerator.nextInt(550);
}
else
objy[2]=objy[2]+3;
}

public void drawC1(Graphics g)         //COIN 1
{
 g.drawImage(coin,coinx[0],coiny[0],this);
if(coiny[0]>600)
{
coiny[0]=-10;
coinx[0]=100+randomGenerator.nextInt(550);
}
else
coiny[0]=coiny[0]+3;
}


public void drawC2(Graphics g)         //COIN 2
{
 g.drawImage(coin,coinx[1],coiny[1],this);
if(coiny[1]>600)
{
coiny[1]=-10;
coinx[1]=100+randomGenerator.nextInt(550);
}
else
coiny[1]=coiny[1]+3;
}


public void checkCondition(Graphics g, Integer x, Integer y)        //checkCondition
{

if(vital1x<(objx[0]+80-4) && vital1x>objx[0]+4  &&  vital1y>objy[0] && vital1y<objy[0]+80) isRunning=false;
if(vital1x<(objx[1]+80) && vital1x>objx[1]  &&  vital1y>objy[1] && vital1y<objy[1]+87) isRunning=false;
if(vital1x<(objx[2]+80) && vital1x>objx[2]  &&  vital1y>objy[2] && vital1y<objy[2]+70) isRunning=false;

if(vital2x<(objx[0]+80-4) && vital2x>objx[0]+4  &&  vital2y>objy[0] && vital2y<objy[0]+80) isRunning=false;
if(vital2x<(objx[1]+80) && vital2x>objx[1]  &&  vital2y>objy[1] && vital2y<objy[1]+87) isRunning=false;
if(vital2x<(objx[2]+80) && vital2x>objx[2]  &&  vital2y>objy[2] && vital2y<objy[2]+70) isRunning=false;

if(vital3x<(objx[0]+80-4) && vital3x>objx[0]+4  &&  vital3y>objy[0] && vital3y<objy[0]+80) isRunning=false;
if(vital3x<(objx[1]+80) && vital3x>objx[1]  &&  vital3y>objy[1] && vital3y<objy[1]+87) isRunning=false;
if(vital3x<(objx[2]+80) && vital3x>objx[2]  &&  vital3y>objy[2] && vital3y<objy[2]+70) isRunning=false;

if(vital1x<(coinx[0]+80) && vital1x>coinx[0]  &&  vital1y>coiny[0] && vital1y<coiny[0]+80)  
{
score=score+100;
coiny[0]=-10;
coinx[0]=100+randomGenerator.nextInt(550);
}
if(vital1x<(coinx[1]+80) && vital1x>coinx[1]  &&  vital1y>coiny[1] && vital1y<coiny[1]+80)  
{
score=score+100;
coiny[1]=-10;
coinx[1]=100+randomGenerator.nextInt(550);
}

if(vital2x<(coinx[0]+80) && vital2x>coinx[0]  &&  vital2y>coiny[0] && vital2y<coiny[0]+80)  
{
score=score+100;
coiny[0]=-10;
coinx[0]=100+randomGenerator.nextInt(550);
}
if(vital2x<(coinx[1]+80) && vital2x>coinx[1]  &&  vital2y>coiny[1] && vital2y<coiny[1]+80)  
{
score=score+100;
coiny[1]=-10;
coinx[1]=100+randomGenerator.nextInt(550);
}


if(vital3x<(coinx[0]+80) && vital3x>coinx[0]  &&  vital3y>coiny[0] && vital3y<coiny[0]+80)  
{
score=score+100;
coiny[0]=-10;
coinx[0]=100+randomGenerator.nextInt(550);
}
if(vital3x<(coinx[1]+80) && vital3x>coinx[1]  &&  vital3y>coiny[1] && vital3y<coiny[1]+80)  
{
score=score+100;
coiny[1]=-10;
coinx[1]=100+randomGenerator.nextInt(550);
}
} // end of func


public void gameOver(Graphics g, Integer x, Integer y)
{
// g.drawImage(offscreen,0,0,this); 
//setBackground(Color.black);

if(go%30<=15) 
g.setColor(Color.red);
else 
g.setColor(Color.blue);
g.setFont(myFont2);
g.drawString("GAME OVER !!!",300,200);
g.setColor(Color.black);
g.setFont(myFont);
g.drawString("PRESS ENTER TO RESTART", 340,300);
g.drawString("FINAL SCORE: ",406,50);
g.drawString(x.toString(),550,50);
g.drawString("LEVEL:",450,70);
g.drawString(y.toString(),523,70);
//try{Thread.currentThread().sleep(3000);} catch(Exception e){} 
//score=0;
Font myFontx = new Font("Monotype Corsiva", Font.ITALIC + Font.BOLD,cred1);
g.setFont(myFontx);
g.drawString("Programmed  By ", 150,450);
if(cred1<40) cred1++;

if (cred1==40)
{
Font myFonty = new Font("Monotype Corsiva", Font.ITALIC + Font.BOLD,cred2);
g.setFont(myFonty);
g.drawString("GEMMY GEORGE", 290,500);
if(cred2<40) cred2++;
}

if (cred2==40)
{
Font myFontz = new Font("Monotype Corsiva", Font.ITALIC + Font.BOLD,cred3);
g.setFont(myFontz);
g.drawString("&", 400,550);
if(cred3<40) cred3++;
}


if (cred3==40)
{
Font myFontzz = new Font("Monotype Corsiva", Font.ITALIC + Font.BOLD,cred4);
g.setFont(myFontzz);
g.drawString("ASHER D'MELLO", 290,590);
if(cred4<40) cred4++;
}

if(cred4==40)
{
g.setFont(myFont);
g.drawString("All Rights Reserved",370,670);
g.drawString("© 2014",440,690);
}
}// game ova

}// MAIN CLASS

