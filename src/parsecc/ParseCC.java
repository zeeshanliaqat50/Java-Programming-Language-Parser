/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecc;

//import com.jtattoo.plaf.aero.AeroLookAndFeel;
import java.util.Properties;
import javax.swing.UIManager;

/**
 *
 * @author Zeeshan Liaqat
 */
public class ParseCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* try{
             Properties props = new Properties();
  props.put("logoString", "Lexical Analyser");
  AeroLookAndFeel.setCurrentTheme(props);
  UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
        
        }catch(Exception e){
            
        }
*/
       
    InterFace a=new InterFace();
  //main a=new main();
splash b =new splash();
      b.setVisible(true);
      for(int i=0; i<=100; i++){
            try{
                
                b.loaderlabel.setText("Loading "+Integer.toString(i)+" % ");
                Thread.sleep(40);
            }
            catch(Exception e){
                
            }
      }
      b.dispose();
      a.setVisible(true);
      
    }
    
}
