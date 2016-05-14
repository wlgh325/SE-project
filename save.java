import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.table.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class save implements ActionListener {
   final static int MAX_USER = 10000;
   static dataAddTable table;
    static JFrame frame1;
   String [][] save_data=null;
   static String sheetName= new String();
   
  
     
    save (dataAddTable table){
     sheetName=frame.id_s;
     this.table = table;
     System.out.println(sheetName);
     //String sheetName=new String(); 
     //sheetName=frame.id;
   
       
     int table_row = table.get_rowcount();
     
    
    
    
   
     //System.out.println(sheetName);
    }
    
    public void actionPerformed(ActionEvent e){
      
        dataAdd.main_frame.dispose();
         food_calorie.main_frame.dispose();
         
       int row = table.get_rowcount();
       
       save_data = new String[row][4];
       
       String date = new String();
       date=day.date;
        for(int i=0;i<row;i++){
           save_data[i][0]=date;
           save_data[i][1]=dataAddTable.catego;
           Object o_n = table.get_value(i, 0);
            Object o_c = table.get_value(i,1);;
           save_data[i][2]=o_n.toString();
           save_data[i][3]=o_c.toString();
        }
        for(int i=0;i<row;i++){
           System.out.print(save_data[i][0]);
           System.out.print(save_data[i][1]);
           System.out.print(save_data[i][2]);
           System.out.println(save_data[i][3]);
        }
    
   
     if ( row == -1)
      return;
    
      try {
         write_info();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      
   
     }  
    public boolean write_info() throws IOException {
       String sucess = "저장되었습니다.";
           File file = new File("data_info.xls");
          long timeout = 3;
           data[] data = new data[MAX_USER];
           String [][] str ;
          int num=0;
           int i=0;
           int row_table=dataAddTable.addTable.getRowCount();
           if(file.exists())   //파일이 있을경우
           {
              data = readXLSXFile(data);   //이어쓰기 위해서 저장된 정보 읽기
     
              FileOutputStream output = null;
              FileInputStream input = new FileInputStream(file);
              HSSFWorkbook workbook = new HSSFWorkbook(input);   //파일이 되는 workbook 생성
              
                 //workbook 부분에 sheet 생성
              HSSFSheet sheet  = workbook.getSheet(sheetName);
                 //sheet에 있는 row의 갯수
              output = new FileOutputStream(file);
              HSSFRow row = null;
              for(int k=workbook.getNumberOfSheets()-1;k>=0;k--){
                   HSSFSheet tmpSheet =workbook.getSheetAt(k);
                   if(tmpSheet.getSheetName().equals(sheetName)){
                      
                      num= tmpSheet.getPhysicalNumberOfRows();
                      workbook.removeSheetAt(k);
                   }
               }    
               sheet  = workbook.createSheet(sheetName);
               int rows = sheet.getPhysicalNumberOfRows();
               System.out.println(num);
              str= new String[num][4];
              for(i=0; i<num; i++)   //미리써져있던 정보쓰기
              {   
                 row = sheet.createRow((short)i);
                
                 str[i][0]=data[i].getData_date();
                 str[i][1]=data[i].getData_cate();
                 str[i][2]=data[i].get_list();
                 str[i][3]=data[i].get_value();
                
                 
                 row.createCell(0).setCellValue(str[i][0]);
                 row.createCell(1).setCellValue(str[i][1]);
                 row.createCell(2).setCellValue(str[i][2]);
                 row.createCell(3).setCellValue(str[i][3]);
                 
               /*  row.createCell(0).setCellValue(data[i].get_value());
                 row.createCell(1).setCellValue(data[i].getData_date());
                 row.createCell(2).setCellValue(data[i].getData_cate());
                 row.createCell(3).setCellValue(data[i].get_list());
                 System.out.println(data[i].getData_date());
                 System.out.println(data[i].get_value());
              }*/
            
              }
              for(i=0;i<row_table;i++){
                 row = sheet.createRow((short)i+num); 
                 row.createCell(0).setCellValue(save_data[i][0]);
                  row.createCell(1).setCellValue(save_data[i][1]);
                  row.createCell(2).setCellValue(save_data[i][2]);
                  row.createCell(3).setCellValue(save_data[i][3]);
              }
            
           
            
              
              workbook.write(output);
              workbook.close();
              output.close();
             window(sucess);
             
             //table.removeAll();
            // save.model.setNumRows(0);
             //save.table.removeAll();
             
             //save.frame1.setVisible(false);
             table.remove_All(0);
           }
           else   //파일이 없을경우
           {
              HSSFWorkbook workbook=new HSSFWorkbook();
              HSSFSheet sheet=workbook.createSheet(sheetName);
              HSSFRow row=null;
              
              for(i=0;i<row_table;i++){
                 row = sheet.createRow((short)i); 
                 row.createCell(0).setCellValue(save_data[i][0]);
                  row.createCell(1).setCellValue(save_data[i][1]);
                  row.createCell(2).setCellValue(save_data[i][2]);
                  row.createCell(3).setCellValue(save_data[i][3]);
              }
               FileOutputStream output =new FileOutputStream("data_info.xls");
               workbook.write(output);
               workbook.close();
               output.close();
               window(sucess);
               table.remove_All(0);
               save.frame1.dispose();
               save.frame1.setVisible(false);
           }
           //save.model.setNumRows(0);
           table.remove_All(0);
           
           day.setKcal();
           frame.setKcalLabel();

           return true;
          
       }
       
    

      public static data[] readXLSXFile(data[] data) throws IOException{
          int check = 0;
          int num=0;
          int i=0;
           String szValue = "";
            InputStream ExcelFileToRead = new FileInputStream("data_info.xls");
            HSSFWorkbook  wb = new HSSFWorkbook(ExcelFileToRead);
            int sheetNum = wb.getNumberOfSheets();
            //sheetNum=wb.getNameIndex(sheetName);
            //시트갯수 가져오기
              for (int k = 0; k < sheetNum; k++) {
                HSSFSheet sheet = wb.getSheetAt(k); //시트 가져오기
                String sheetname = wb.getSheetName(k); //시트명 가져오기
                if(sheetname.equals(sheetName)){
                   num=k;
                }
              }
             
            HSSFSheet sheet = wb.getSheetAt(num);   //첫번째 sheet 하나 읽기
            HSSFRow row; 
            HSSFCell cell;

            Iterator rows = sheet.rowIterator();
           
            while (rows.hasNext())
            {
               row=(HSSFRow) rows.next();
               Iterator cells = row.cellIterator();
               data[i] = new data(null, null, null,null);   //초기화
               while (cells.hasNext())
               {
                  cell=(HSSFCell) cells.next();
                  
                  if(check == 0)   //날짜
                  {
                     if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                      {
                         szValue = cell.getStringCellValue();
                      }
                     else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                     {
                         szValue = String.valueOf( (int)cell.getNumericCellValue() );  
                     }
                     data[i].setData_date(szValue);
                     check++;
                  }
                  else if(check ==1)   //카테고리
                  {
                     if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                      {
                         szValue = cell.getStringCellValue();
                      }
                     else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                     {
                         szValue = String.valueOf( cell.getNumericCellValue());  
                     }
                     data[i].setData_cate(szValue);
                     check++;
                  }
                  else if(check == 2)   //내용
                  {
                     if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                      {
                         szValue = cell.getStringCellValue(); 
                      }
                     else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                      {
                         szValue = String.valueOf( cell.getNumericCellValue());  
                      }
                     data[i].setData_list(szValue);
                     check++;
                  }
                  else if(check ==3)   //
                  {
                     if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                      {
                         szValue = cell.getStringCellValue();             
                      }
                     else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                      {
                         szValue = String.valueOf( cell.getNumericCellValue());  
                      }
                     data[i].setData_value(szValue);
                     check++;
                  }
               }
               System.out.println(data[i].get_list());
               System.out.println(data[i].getData_date());
               i++;
               check=0;
             
            }


            wb.close();
            return data;    
       }
       public void window(String str){
           JLabel label1;
           JPanel panel;
          
           
          panel = new JPanel();
          
          panel.add(new JLabel(str ,JLabel.CENTER));
          frame1 = new JFrame();
           frame1.add(panel);
            frame1.setSize(180, 90);
           frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
   
}