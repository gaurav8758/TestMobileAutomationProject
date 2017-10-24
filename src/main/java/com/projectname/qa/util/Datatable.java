package com.projectname.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.LogStatus;

public class Datatable {
	public String GlobalSheet = "";
	public String LocalSheet = "";
	private int CurrentSheetNumber = 0;
	
	private int currentRow = 1;
	private String FileLocation = "";
	
	
	//Setting the default values with the scripts is instantiated
	
	//Adds the specified sheet to the run-time data table
	public void AddSheet(String SheetName){
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    workbook.createSheet(SheetName);
		    LocalSheet = SheetName;
		    CurrentSheetNumber = workbook.getSheetIndex(SheetName);
		    file.close();
		    
		    FileOutputStream output = new FileOutputStream(FileLocation);
		    workbook.write(output);
		    output.close();
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
	//Deletes the specified sheet from the run-time data table
	public void DeleteSheet(String SheetID){
	    //Create a object of File class to open xlsx file
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    if(SheetID != null)   {
		        int index = workbook.getSheetIndex(SheetID);
		        workbook.removeSheetAt(index);
		        if (index==CurrentSheetNumber){
		        	CurrentSheetNumber = -1;
		        	LocalSheet = "";
		        }
		    }
		    file.close();
		    
		    FileOutputStream output = new FileOutputStream(FileLocation);
		    workbook.write(output);
		    output.close();
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Exports the Datatable to a new file in the specified location
	public void Export(String FileName){
		File source = new File(FileLocation);
		File dest = new File(FileName);
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	//Exports a Specific Sheet of the Datatable in run-time
	public void ExportSheet(String FileName, String SheetName){
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return;
		}
		
	    try {
	        FileInputStream file = new FileInputStream(new File(FileLocation));
	        // Create Workbook instance holding reference to .xlsx file
		    String fileExtensionName = FileLocation.substring(FileLocation.indexOf("."));
		    //Check condition if the file is xlsx file
		    Workbook workbook = null;
		    if(fileExtensionName.equals(".xlsx")){
		    //If it is xlsx file then create object of XSSFWorkbook class
		    	workbook = new XSSFWorkbook(file);
		    }
		    //Check condition if the file is xls file
		    else if(fileExtensionName.equals(".xls")){
		        //If it is xls file then create object of XSSFWorkbook class
		    	workbook = new HSSFWorkbook(file);
		    }
	        String sheetName = SheetName;
	        for (int i = workbook.getNumberOfSheets() - 1; i >= 0; i--) {
	        	Sheet tmpSheet = workbook.getSheetAt(i);
	            if (!tmpSheet.getSheetName().equals(sheetName)) {
	                workbook.removeSheetAt(i);
	            }
	        }
	        file.close();
	        FileOutputStream out = new FileOutputStream(FileName);
	        workbook.write(out);
	        out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Returns the active row of the run-time data table of global sheet
	public int GetCurrentRow()
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return 0;
		}
		return currentRow;
	}
	
	//Returns the number of columns in the run-time data Table of Global Sheet
	public int GetParameterCount()
	{
	    //Create a object of File class to open xlsx file
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return 0;
		}
		int GetParameterCount = 0;
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
		    int j = 0;
		    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
		    {
		    	j = j + 1;
		    }
		    GetParameterCount = j+1;
		    file.close();
		    return GetParameterCount;
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return GetParameterCount; 
	}
	
	//Returns the number of rows in the run-time data table of Global Sheet
	public int GetRowCount()
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return 0;
		}
	    //Create a object of File class to open xlsx file
		int GetRowCount = 0;
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
		    GetRowCount = workSheet.getLastRowNum();
		    file.close();
		    return GetRowCount;
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return GetRowCount; 
	}
	
	//Returns the specified sheet from the run-time data table.
	public int GetSheet(){
		 return CurrentSheetNumber;
	}
	
	//Sets specified sheet in the data table.
	public void SetSheet(int SheetID){
		 CurrentSheetNumber  = SheetID;
	}
	
	public void SetSheet(String SheetName){
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
		}
	    //Create a object of File class to open xlsx file
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);

			for (int i=0; i<workbook.getNumberOfSheets(); i++) {
				if (SheetName.trim().equalsIgnoreCase(workbook.getSheetName(i).trim()))
				{
					CurrentSheetNumber = i;
				}
			}
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Returns the total number of sheets in the run-time data table.
	public int GetSheetCount()
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return 0;
		}
	    //Create a object of File class to open xlsx file
		int GetSheetCount = 0;
		try 
		{
			FileInputStream file;
			file = new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    
		    GetSheetCount =  workbook.getNumberOfSheets();
		    file.close();
		    return GetSheetCount;
		} 
		catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 	   
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return CurrentSheetNumber; 
	}
	
	//Imports a specific external Excel file to the run-time data table.
	public void Import(String FileName)
	{
		FileLocation = FileName;
		CurrentSheetNumber = 0;
		currentRow = 1;
	}
	
	//Imports the specified sheet of the specific excel file to the destination sheet.
	public void ImportSheet(String FileName, int SheetSourceInt, String SheetDestPath)
	{
		FileLocation = FileName;
		CurrentSheetNumber =  SheetSourceInt;
		@SuppressWarnings("unused")
		String NewLocation = SheetDestPath;
		currentRow = 1;
	}
	
	//Sets the Focus of the Current row to the Specified Row Number
	public void SetCurrentRow(int RowNumber){
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return;
		}
		currentRow = RowNumber;
	}
	
	//Sets the focus of the next row in the run-time data table
	public void SetNextRow()
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return;
		}
		currentRow = currentRow +1;
	}
	
	public void SetPreviousRow(){
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return;
		}
		currentRow = currentRow -1;
	}
	
	//Returns the first sheet of the run-time data table.
	public String GlobalSheet(){
		return GlobalSheet;
	}
	
	//Returns the Active local sheet of the run-time data table.
	public String LocalSheet(){
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return "";
		}
		return LocalSheet;
	}
	
	
	public String RawValue(String ParameterID)
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return "";
		}	
		try 
			{
				FileInputStream file= new FileInputStream(new File(FileLocation));
			    Workbook workbook = null;
			    workbook = setWorkbook(file, workbook);
			    //Read sheet inside the workbook by its name
			    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
			    //Create a loop over all the rows of excel file to read it
			    int j = 0;
			    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
			    {
			    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase()==ParameterID.toString().toLowerCase())
			    	{
			    		return workSheet.getRow(currentRow).getCell(j).getCellFormula();
			    	}
			    	j = j + 1;
			    }
			    file.close();
			    ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.");
			    //ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.", false);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return "";
	}
	
	//Retrieves the raw value of the cell
	public String RawValue(String ParameterID, int SheetID)
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return "";
		}
		try {
			FileInputStream file= new FileInputStream(new File(FileLocation));
		    Workbook workbook = null;
		    workbook = setWorkbook(file, workbook);
		    //Read sheet inside the workbook by its name
		    Sheet workSheet = workbook.getSheetAt(SheetID);
		    CurrentSheetNumber =  SheetID;
		    LocalSheet = workSheet.getSheetName();
		    //Create a loop over all the rows of excel file to read it
		    int j = 0;
		    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
		    {
		    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase()==ParameterID.toString().toLowerCase())
		    	{
		    		return workSheet.getRow(currentRow).getCell(j).getCellFormula();
		    	}
		    	j = j + 1;
		    }
		    file.close();
		    ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.");
		    //ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.", false);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String Value(String ParameterID)
	{
    //Create a object of File class to open xlsx file
	try
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return "";
		}
    File file = new File(FileLocation); //filePath+"\\"+fileName
    //Create an object of FileInputStream class to read excel file
    FileInputStream inputStream = new FileInputStream(file);
    Workbook workbook = null;
    workbook = setWorkbook(inputStream, workbook);
    //Read sheet inside the workbook by its name
    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
    //Create a loop over all the rows of excel file to read it
    int j = 0;
    
    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
    {
    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase().equalsIgnoreCase(ParameterID.toString().toLowerCase()))
    	{
    		if (workSheet.getRow(currentRow).getCell(j).getCellType() == 0 )
    		{
    			return new BigDecimal(workSheet.getRow(currentRow).getCell(j).getNumericCellValue()).toPlainString();
    			//return String.valueOf(workSheet.getRow(currentRow).getCell(j).getNumericCellValue());
    		}
    		else
    		{
    			return workSheet.getRow(currentRow).getCell(j).getStringCellValue();
    		}
    	}
    	j = j + 1;
    }
    inputStream.close();
    //ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "", false);
	}
	catch (FileNotFoundException e) 
	{
		//ATUReports.add("", "", "" , "DataTable was not found in the location'" + FileLocation, false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DataTable was not found in the location'" + FileLocation);
	}
	catch (Exception e) 
	{
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.");
		//ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.", false);
	}
	return "";
}
	
	public void SetScriptRow(String ScriptName) throws IOException
	{
    //Create a object of File class to open xlsx file
	try
	{
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return;
		}
    File file = new File(FileLocation); //filePath+"\\"+fileName
    //Create an object of FileInputStream class to read excel file
    FileInputStream inputStream = new FileInputStream(file);
    Workbook workbook = null;
    workbook = setWorkbook(inputStream, workbook);
    //Read sheet inside the workbook by its name
    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
    //Create a loop over all the rows of excel file to read it
    int j = 0;
    boolean foundColomn = false;
    int ScriptNameCol = 0;
    
    
    //Search for the 'ScriptName' Parameter
    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
    {
    	//if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase().equalsIgnoreCase("ScriptName".toLowerCase()))
    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase().equalsIgnoreCase("ScriptName".toLowerCase()))
    	{
    		ScriptNameCol = j;
    		foundColomn = true;
    		break;
    	}
    	j = j + 1;
    }
    
    if (foundColomn==false){
    	//ATUReports.add("There is no column with the header variable 'ScriptName'", false);
    	ExtentTestManager.getTest().log(LogStatus.FAIL, "There is no column with the header variable 'ScriptName'");
    	return;
    }
    
    currentRow = 0;
    boolean foundScriptRow = false;
    //Search for the 'ScriptName' in the column
    while (!(workSheet.getRow(currentRow).getCell(ScriptNameCol).getStringCellValue()==null)) 
    {
    	if (workSheet.getRow(currentRow).getCell(ScriptNameCol).getStringCellValue().toString().trim().toLowerCase().equalsIgnoreCase(ScriptName.trim().toLowerCase()))
    	{
    		foundScriptRow = true;
    		break;
    	}
    	currentRow = currentRow + 1;
    }
    
    if (foundScriptRow==false){
    	//ATUReports.add("There is no row with the script name " + ScriptName, false);
    	ExtentTestManager.getTest().log(LogStatus.FAIL, "There is no row with the script name " + ScriptName);
    }
    
    inputStream.close();
    //ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "", false);
	}
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
		//ATUReports.add("", "", "" , "DataTable was not found in the location'" + FileLocation, false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DataTable was not found in the location'" + FileLocation);
	}

}
	
	//Retrieves the value of the cell in the specified parameter.
	public String Value(String ParameterID, int SheetID) {
	    //Create a object of File class to open xlsx file
		if (CurrentSheetNumber == -1)
		{
			ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
			//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
			return "";
		}
		try
		{
	    File file =    new File(FileLocation); //filePath+"\\"+fileName
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook workbook = null;
	    //Find the file extension by spliting file name in substring and getting only extension name
	    workbook = setWorkbook(inputStream, workbook);
	    //Read sheet inside the workbook by its name
	    Sheet workSheet = workbook.getSheetAt(SheetID);
	    CurrentSheetNumber =  SheetID;
	    LocalSheet = workSheet.getSheetName();
	    //Create a loop over all the rows of excel file to read it
	    int j = 0;
	    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
	    {
	    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase()==ParameterID.toString().toLowerCase())
	    	{
	    		return workSheet.getRow(currentRow).getCell(j).getStringCellValue();
	    	}
	    	j = j + 1;
	    }
	    inputStream.close();
	    //ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "", false);
	    ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.");
		}
		catch (FileNotFoundException e) 
		{
			//ATUReports.add("", "", "" , "DataTable was not found in the location'" + FileLocation, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DataTable was not found in the location'" + FileLocation);
		}
		catch (Exception e) 
		{
			//ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.", false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.");
		}
		return "";
	}

	// Added by Badhu - Extracted from individual mehthod to reusable
	private Workbook setWorkbook(FileInputStream inputStream, Workbook workbook) throws IOException {
		String fileExtensionName = FileLocation.substring(FileLocation.lastIndexOf("."));// Badhu - changed from index to lastIndex
	    //Check condition if the file is xlsx file
	    if(fileExtensionName.equals(".xlsx")){
	    //If it is xlsx file then create object of XSSFWorkbook class
	    	workbook = new XSSFWorkbook(inputStream);
	    }
	    //Check condition if the file is xls file
	    else if(fileExtensionName.equals(".xls")){
	        //If it is xls file then create object of XSSFWorkbook class
	    	workbook = new HSSFWorkbook(inputStream);
	    }
		return workbook;
	}
	
	public void SetValue(String ParameterID, String InputValue)
	{
    //Create a object of File class to open xlsx file
		try
		{
			if (CurrentSheetNumber == -1)
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.");
				//ATUReports.add("You have deleted the sheet in previous steps. You need need to use SetSheet/Import new file.", false);
				return;
			}
		    File file = new File(FileLocation); //filePath+"\\"+fileName
		    //Create an object of FileInputStream class to read excel file
		    FileInputStream inputStream = new FileInputStream(file);
		    Workbook workbook = null;
		    workbook = setWorkbook(inputStream, workbook);
		    //Read sheet inside the workbook by its name
		    Sheet workSheet = workbook.getSheetAt(CurrentSheetNumber);
		    //Create a loop over all the rows of excel file to read it
		    int j = 0;
		    
		    Cell cell = null; 
	
		    while (!(workSheet.getRow(0).getCell(j).getStringCellValue()==null)) 
		    {
		    	if (workSheet.getRow(0).getCell(j).getStringCellValue().toString().toLowerCase().equalsIgnoreCase(ParameterID.toString().toLowerCase()))
		    	{
		    		if (workSheet.getRow(currentRow).getCell(j)==null)//then create cell
		    		{
		    			cell = workSheet.getRow(currentRow).createCell(j);
		    			cell.setCellType(Cell.CELL_TYPE_STRING);
		    			cell.setCellValue(InputValue);
		    		}
		    		else
		    		{
		    			cell = workSheet.getRow(currentRow).getCell(j);
		    			cell.setCellValue(InputValue);
		    		}
		    		break;
		    	}
		    	j = j + 1;
		    	
		    }
		    inputStream.close();
		    
		    FileOutputStream output = new FileOutputStream(FileLocation);
		    workbook.write(output);
		    output.close();
		    //ATUReports.add("", "", "DataTable variable '" + ParameterID + "' updated.", "Updated value - '" + InputValue + "'.", false);
		    ExtentTestManager.getTest().log(LogStatus.FAIL, "DataTable variable '" + ParameterID + "' updated.", "Updated value - '" + InputValue + "'.");
	}
	catch (FileNotFoundException e) 
	{
		//ATUReports.add("", "", "" , "DataTable was not found in the location'" + FileLocation, false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "DataTable was not found in the location'" + FileLocation);
	}
	catch (Exception e) 
	{
		//ATUReports.add("", "", "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Replace datatable variable - '" + ParameterID + "'.", "Varible not found in datatable.");
	}
}
	
	public void Setup(String dataTablePath, String Environment, String ScriptName) throws IOException{
		Import(dataTablePath);
		SetSheet(Environment);
		SetScriptRow(ScriptName);
	}
}
