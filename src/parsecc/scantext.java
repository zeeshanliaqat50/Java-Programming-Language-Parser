/*f=new File("code.txt");
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecc;

import java.awt.TextArea;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author Zeeshan Liaqat
 */
public class scantext {
    Map<String,StringBuffer> mp=new HashMap();
   public ArrayList al; //arraylist of tokens
   ArrayList al2; //literals arraylist
    int lineno;
    File f;
    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
      BufferedReader br;
    scantext(){
        f=new File("code.txt");
        lineno=0;
        al=new ArrayList();
        al2=new ArrayList();
        
    }
 public boolean writeinFile(JTextArea a){
    
     String arr[]=a.getText().split("\\n");
     try{
     FileWriter fw=new FileWriter(f);
     BufferedWriter bw=new BufferedWriter(fw);
     for(int i=0; i<arr.length; i++){
     bw.write(arr[i]);
     bw.newLine();
     }
     bw.flush();
     bw.close();
     return true;
     
     }
     catch(Exception e){
         e.printStackTrace();
         
     }
     return false;
    
}
 public void readfromFile(){
     try{
         fr=new FileReader(f);
         br=new BufferedReader(fr);
         String line;
      int c=-1;
      int e=-1;
         while((line=br.readLine())!=null)
                 {
                      String AL="";
                     try{
                     int b=-1;
                     
                     lineno++;
                     for(int i=0; i<line.length(); i++){
                         b=line.indexOf("//");
                        
                        c=line.indexOf("/*");
                        
                        e=line.indexOf("*/");
                     }
                     
                     if(b!=-1){
                     
                     
                    String l= line.substring(0,b).replace(line," ");
                    AL=l;
                     //JOptionPane.showMessageDialog(null, l);
                     }
                     else{
                         AL=line;
                     }
                     }catch(Exception ex){
                         
                     }
            StringTokenizer st = new StringTokenizer(AL,"[ =+-;()*{}[],!@#$%^&/]", true); 
            while(st.hasMoreElements()){
                String tok=st.nextToken();
                if(tok.equals("+") && st.nextToken().equals("+")){
                      String op="++";
                      tok=op;
                }
                if(tok.equals("-") && st.nextToken().equals("-")){
                      String op="--";
                      tok=op;
                }
               if(!(tok.equals(" "))){
                
                if(tok.matches("int|float|char|double|long|boolean|String|void|main|else|if|else-if|switch|break|continue|private|public|protected|return|HashMap|Map")){
                    al.add(tok+"-[Keyword]"+" "+lineno);
                    
                }
                else if(tok.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                     al.add(tok+"-[Identifier]"+" "+lineno);  
                }
                else if(tok.matches("[;(){}]")){
                    al.add(tok+"-[Seperator]"+" "+lineno);
                    
                }
                 else if(tok.equals("++")){
                    al.add(tok+"-[Operator]"+" "+lineno);
                }
                else if(tok.equals("--")){
                    al.add(tok+"-[Operator]"+" "+lineno);
                }
                else if(tok.matches("[%*/+-++--=]")){
                     al.add(tok+"-[Operator]"+" "+lineno);
                }
                else if(tok.matches("[0-9]*") || tok.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)") ||( tok.startsWith("\"") && tok.endsWith("\"")) || ( tok.startsWith("\'") && tok.endsWith("\'") )  ) {
                     al.add(tok+"-[Literals]"+" "+lineno);
                }
               
                else{
                    al.add(tok+"-[Invalid]"+" "+lineno);
                }
               }
               
               //Handling Literals here
              if(tok.matches("[0-9]*") ||tok.matches("[-+]?([0-9]*\\.[0-9]+|[0-9]+)") ||( tok.startsWith("\"") && tok.endsWith("\"")) || ( tok.startsWith("\'") && tok.endsWith("\'") ) ){
                 
                  StringBuffer lno=new StringBuffer(String.valueOf(lineno)); 
                  if(mp.containsKey(tok)){
                     // JOptionPane.showMessageDialog(null,"Value contains");
                     StringBuffer y=new StringBuffer(mp.get(tok));
                     y.append(","+lineno);
                     mp.put(tok, y);
                  }
                  else{
                  mp.put(tok,lno);
                  }
              
}
            }
                 }
       
         }
     
     
   catch(Exception e){
       e.printStackTrace();
   }
     
   
 }
 public ArrayList loadTable(){
     return al;
 }
 public ArrayList LoadLiteralTable(){
     return al2;
 }
 
  public Map loadliterals(){
         return mp;
     }
 public String check(String tok,int lno){
     String str="";
     for(int i=0; i<al2.size(); i++){
         if(al2.get(i).equals(tok)){
           StringBuffer a=new StringBuffer(tok);
           a.append(" "+lno);
           str= a.toString();
           al2.remove(i);
             
         }
     }
     return str;
     
 }

 
}
