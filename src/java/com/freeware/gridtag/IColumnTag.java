/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : IColumnTag.java
 * CREATED: Jul 21, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import javax.servlet.jsp.JspException;

/**
 * An interface defining basic HTML element attributes.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
interface IColumnTag
{
    public int getWidth();
    public int getHeight();
    public int getBorder();

    public String getName();
    public String getBgColor();
    public String getForeColor();
    public String getCssClass();
    public String getHAlign();
    public String getVAlign();
    public String getHeaderText();
    public String getDataField();
    
    
    public boolean getHideField();    
    public boolean getSortable();

    public void setWidth(int pintWidth);
    public void setHeight(int pintHeight);
    public void setBorder(int pintBorder);

    public void setName(String pstrName);
    public void setBgColor(String pstrColor);
    public void setForeColor(String pstrColor);
    public void setCssClass(String pstrCssClass);
    public void setHAlign(String pstrHAlign);
    public void setVAlign(String pstrVAlign);
    public void setHeaderText(String pstrHdrText);
    public void setDataField(String pstrField);
    
    
    public void setHideField(boolean phideField);    
    public void setSortable(boolean pblnSortable);

    public void renderDetail(Object pobjValue,int rowNum,int colNum) throws JspException;   
    public void renderHeader(boolean hiddenColumnFlag,int currColumn,int totColumns) throws JspException;
    public void renderBlank() throws JspException;

    public void copyAttributesTo(IColumnTag pobjDest);
}