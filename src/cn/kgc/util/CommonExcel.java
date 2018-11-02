package cn.kgc.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.kgc.dao.StudentDao;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.entity.Student;

import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ����Excel��������
 *
 * @author zhanghb
 * @version 1.0
 */
public class CommonExcel {

    //��ʾ�ĵ�����ı���
    private String title;
    //�����������
    private String[] rowName;
    //�������ļ���
    private String fileName;

    private List<Object[]> dataList = new ArrayList<Object[]>();

    private HttpServletResponse response;

    //���췽��������Ҫ����������
    public CommonExcel(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response, String fileName) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
        this.response = response;
        this.fileName = fileName;
    }

    /*
     * ��������
     */
    public void downloadExcel() throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();                     // ��������������
            HSSFSheet sheet = workbook.createSheet(title);                  // ����������

            // ������������
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            //sheet��ʽ���塾getColumnTopStyle()/getStyle()��Ϊ�Զ��巽�� - ������  - ����չ��
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//��ȡ��ͷ��ʽ����
            HSSFCellStyle style = this.getStyle(workbook);                  //��Ԫ����ʽ����

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // ������������
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);                // ������2��λ�ô�����(��˵��п�ʼ�ĵڶ���)

            // ����ͷ���õ�sheet�ĵ�Ԫ����
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);               //������ͷ��Ӧ�����ĵ�Ԫ��
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //������ͷ��Ԫ�����������
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);                                 //������ͷ��Ԫ���ֵ
                cellRowName.setCellStyle(columnTopStyle);                       //������ͷ��Ԫ����ʽ
            }

            //����ѯ�����������õ�sheet��Ӧ�ĵ�Ԫ����
            for (int i = 0; i < dataList.size(); i++) {

                Object[] obj = dataList.get(i);//����ÿ������
                HSSFRow row = sheet.createRow(i + 3);//�������������

                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;   //���õ�Ԫ�����������
                    if (j == 0) {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i + 1);
                    } else {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!"".equals(obj[j]) && obj[j] != null) {
                            cell.setCellValue(obj[j].toString());                       //���õ�Ԫ���ֵ
                        }
                    }
                    cell.setCellStyle(style);                                   //���õ�Ԫ����ʽ
                }
            }
            //���п����ŵ������г��Զ���Ӧ
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //��ǰ��δ��ʹ�ù�
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            if (workbook != null) {
                try {
                    if (response != null) {
                        response.setContentType("application/vnd.ms-excel;charset=utf-8");
                        response.setHeader("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                        OutputStream out = response.getOutputStream();
                        workbook.write(out);
                        out.close();
                    } else {
                        FileOutputStream outputStream = new FileOutputStream("D:/"+fileName);
                        workbook.write(outputStream);
                        System.out.println(outputStream);
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * ��ͷ��Ԫ����ʽ
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // ��������
        HSSFFont font = workbook.createFont();
        //���������С
        font.setFontHeightInPoints((short) 12);
        //����Ӵ�
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //������������
        font.setFontName("΢���ź�");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * ��������Ϣ��Ԫ����ʽ
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // ��������
        HSSFFont font = workbook.createFont();
        //������������
        font.setFontName("΢���ź�");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }



/*
 * ���Է���
 */
public static void main(String[] args)throws Exception {
    HttpServletResponse response=null;
  /* String title = "xx����";
    String[] rowsName = new String[]{"oo","xx","oo","xx","xx","oo"};
    List<Object[]>  dataList = new ArrayList<Object[]>();
    Object[] objs = null;
    for (int i = 0; i < 10; i++) {
        objs = new Object[rowsName.length];
        objs[0] = i;
        objs[1] = i;
        objs[2] = i;
        objs[3] = i;
        objs[4] = i;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        objs[5] = date;
        dataList.add(objs);
    }
    String fileName="xx����-"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";
    CommonExcel ex = new CommonExcel(title, rowsName, dataList,response,fileName);
    ex.downloadExcel();*/
               StudentDao studentdao = new StudentDaoImpl();
			    String title = "ѧ����������";
			    String[] rowsName = new String[]{"ѧ������","ѧ��","�༶","��������","��ҵѧУ","��ѧרҵ","��ҵʱ��","�ֻ�����","����"};
			    List<Object[]>  dataList = new ArrayList<Object[]>();
			    List list = studentdao.queryListStudent();
			    Object[] obj = (Object[]) list.toArray();
			   
			    dataList.add(obj);
			    
			    
			    System.out.println(dataList);
			    String fileName="ѧ����������-"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";
			    CommonExcel ex = new CommonExcel(title, rowsName,dataList ,response,fileName);
			    ex.downloadExcel();

}	
}
