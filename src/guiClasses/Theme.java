package guiClasses;

import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
/*
 * TROLOLOLOLOLOLOLOLOLOLOLOL
 */
public class Theme {
	public static void applyTheme(JButton b){
		b.setBackground(new Color(196,253,3));
		b.setFocusable(false);
		b.setFont(new Font("Verdana",Font.BOLD,12));
	}
	
	public static void applyTheme(JRadioButton b){
		b.setBackground(null);
		b.setForeground(new Color(62,36,9));
		b.setFont(new Font("Verdana",Font.BOLD,12));
	}

	public static void applyFrameTheme(JFrame f) {
		f.getContentPane().setBackground(new Color(214,202,22));		
		//NIMBUS!
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
	}
}
