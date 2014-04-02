package com.example.mygame1;

 
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

public class Game 
{
	public JFrame mainFrame;
	public GLCanvas glcanvas;
	Renderer r;

	public Game()
	{
		// setup OpenGL Version 2
    	GLProfile profile = GLProfile.get(GLProfile.GL2);
    	GLCapabilities capabilities = new GLCapabilities(profile);
 
    	// The canvas is the widget that's drawn in the JFrame
    	 glcanvas = new GLCanvas(capabilities);
    	 r=new Renderer(this);

    	glcanvas.addGLEventListener(r);

    	//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    	//glcanvas.setSize( d.width, d.height );
    	glcanvas.setSize(600,600);
		
         mainFrame = new JFrame( "Hello World" );
        mainFrame.getContentPane().add( glcanvas);

        Animator animator = new Animator(glcanvas);
	    animator.start();
        // shutdown the program on windows close event
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
 
        mainFrame.setSize( mainFrame.getContentPane().getPreferredSize() );
        mainFrame.setVisible( true );
	}
    public static void main(String[] args) 
    {
    	Game game=new Game();
    }
}