package core.interfaces;

import core.interfaces.impl.WebtableImpl;
import core.interfaces.impl.internal.ImplementedBy;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface that wraps a WebElement in CheckBox functionality.
 */
@ImplementedBy(WebtableImpl.class)
public interface Webtable extends Element {

    /**
     * Get the row count of the Webtable
     */
    int getRowCount();

    /**
     * Get the column count for the Webtable on a specified Row
     */
    int getColumnCount(WebDriver driver, int row);

    /**
     * Get cell data of the specified row and Column in a Webtable
     */
    String getCellData( WebDriver driver, int row, int column);
    

    /**
     * Return the Cell of the specified row and Column in a Webtable
     */
    WebElement getCell( WebDriver driver, int row, int column);
    WebElement getCell( SearchContext driver, int row, int column);

    /**
     * Click cell in the specified row and Column in a Webtable
     */
    void clickCell( WebDriver driver, int row, int column);
    
    
    /**
     * Get Row number where text is found
     */
    int getRowWithCellText(WebDriver driver, String text);

    /**
     * Get Row number where text is found in a specific column
     */    
    int getRowWithCellText( WebDriver driver, String text, int columnPosition);

    /**
     * Get Row number where text is found in a specific column and starting row
     */    
    int getRowWithCellText( WebDriver driver, String text, int columnPosition, int startRow);
    
    /**
     * Get Column number where text is found
     */  
    int getColumnWithCellText(WebDriver driver, String text);
    
    /**
     * Get Column number where text is found in a specific row
     */  
    int getColumnWithCellText(WebDriver driver, String text, int rowPosition);
    
    /**
     * Get Row number where text is found within a specific column - using 'contains'
     */    
    int getRowThatContainsCellText( SearchContext driver, String text, int columnPosition);
  
}
