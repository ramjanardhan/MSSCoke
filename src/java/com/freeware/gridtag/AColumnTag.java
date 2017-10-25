/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : AColumnTag.java
 * CREATED: Jul 22, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * The base class for all HTML elements.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public abstract class AColumnTag 
	extends TagSupport 
	implements IColumnTag
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2481861060752236778L;
	protected int mintWidth;
    protected int mintHeight;
    protected int mintBorder;

    protected String mstrName;
    protected String mstrBgColor;
    protected String mstrForeColor;
    protected String mstrCssClass;
    protected String mstrHAlign;
    protected String mstrVAlign;
    protected String mstrHeaderText;
    protected String mstrDataField;    
   
    protected boolean mstrHideField;
    protected boolean mblnSortable;

    public AColumnTag()
    {
        super();
        this.mintWidth = -1;
        this.mintHeight = -1;
        this.mintBorder = -1;
    }

/*------------------------------------------------------------------------------
 * Getters
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getWidth()
     */
    public int getWidth()
    {
        return this.mintWidth;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getHeight()
     */
    public int getHeight()
    {
        return this.mintHeight;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getBorder()
     */
    public int getBorder()
    {
        return this.mintBorder;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getName()
     */
    public String getName()
    {
        return this.mstrName;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getBgColor()
     */
    public String getBgColor()
    {
        return this.mstrBgColor;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getForeColor()
     */
    public String getForeColor()
    {
        return this.mstrForeColor;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getCssClass()
     */
    public String getCssClass()
    {
        return this.mstrCssClass;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getHAlign()
     */
    public String getHAlign()
    {
        return this.mstrHAlign;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getVAlign()
     */
    public String getVAlign()
    {
        return this.mstrVAlign;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getHeaderText()
     */
    public String getHeaderText()
    {
        return this.mstrHeaderText;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getDataField()
     */
    public String getDataField()
    {
        return this.mstrDataField;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#getSortable()
     */
    public boolean getSortable()
    {
        return this.mblnSortable;
    }
    
    public boolean getHideField(){
    	return this.mstrHideField;
    }
    
  
    
/*------------------------------------------------------------------------------
 * Setters
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setWidth(int)
     */
    public void setWidth(int pintWidth)
    {
        this.mintWidth = pintWidth;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setHeight(int)
     */
    public void setHeight(int pintHeight)
    {
        this.mintHeight = pintHeight;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setBorder(int)
     */
    public void setBorder(int pintBorder)
    {
        this.mintBorder = pintBorder;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setName(java.lang.String)
     */
    public void setName(String pstrName)
    {
        this.mstrName = pstrName;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setBgColor(java.lang.String)
     */
    public void setBgColor(String pstrColor)
    {
        this.mstrBgColor = pstrColor;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setForeColor(java.lang.String)
     */
    public void setForeColor(String pstrColor)
    {
        this.mstrForeColor = pstrColor;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setCssClass(java.lang.String)
     */
    public void setCssClass(String pstrCssClass)
    {
        this.mstrCssClass = pstrCssClass;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setHAlign(java.lang.String)
     */
    public void setHAlign(String pstrHAlign)
    {
        this.mstrHAlign = pstrHAlign;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setVAlign(java.lang.String)
     */
    public void setVAlign(String pstrVAlign)
    {
        this.mstrVAlign = pstrVAlign;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setHeaderText(java.lang.String)
     */
    public void setHeaderText(String pstrHdrText)
    {
        this.mstrHeaderText = pstrHdrText;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setDataField(java.lang.String)
     */
    public void setDataField(String pstrDataField)
    {
        this.mstrDataField = pstrDataField;
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#setSortable(boolean)
     */
    public void setSortable(boolean pblnSortable)
    {
        this.mblnSortable = pblnSortable;
    }
    
    public void setHideField(boolean phideField){
    	this.mstrHideField = phideField;
    }
    
   
/*------------------------------------------------------------------------------
 * Methods
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#renderHeader()
     */
    public void renderHeader(boolean hiddenColumnFlag,int currColumn,int totColumns) throws JspException
    {
        String       strTxt    = null;
        DBGrid       objParent = null;
        StringBuffer objBuf    = null;

        try
        {
            if (this.mstrHeaderText == null)
                strTxt = "&nbsp;";
            else
                strTxt = this.mstrHeaderText;

            objParent = (DBGrid) this.getParent();
            objBuf = new StringBuffer();
            objBuf.append("<th");
            if (this.mintWidth > 0)
                objBuf.append(" width=\"" + this.mintWidth + "%\"");
            if (this.mintHeight > 0)
                objBuf.append(" height=\"" + this.mintHeight + "\"");
            /*if (this.mstrCssClass != null)
                objBuf.append(" class=\"" + this.mstrCssClass + "\"");
            else*/
                objBuf.append(" class=\"gridHeader\"");
            if (this.mstrHAlign != null)
                objBuf.append(" align=\"" + this.mstrHAlign.toLowerCase() + "\"");
            if (this.mstrVAlign != null)
                objBuf.append(" valign=\"" + this.mstrVAlign.toLowerCase() + "\"");

            if (this.mstrBgColor != null)
                objBuf.append(" bgcolor=\"" + this.mstrBgColor + "\"");
            if (this.mstrForeColor != null)
                objBuf.append(" color=\"" + this.mstrForeColor + "\"");
           if(hiddenColumnFlag && (currColumn==totColumns)){
        	   objBuf.append(" style=\"display:none;\"");
           }
            objBuf.append(">");
            if (this.mblnSortable && this.mstrDataField != null &&
                  objParent.supportSorting())
            {
                objBuf.append("<a href=\"javascript:doSort('");
                objBuf.append(this.mstrDataField + "', '");
                if (this.mstrDataField.equals(objParent.getSortColumn()))
                {
                    if (objParent.getSortAscending())
                        objBuf.append("DESC");
                    else
                        objBuf.append("ASC");
                    objBuf.append("')\" class=\"gridSort\">");
                    objBuf.append("<span>" + this.mstrHeaderText);
                    objBuf.append("&nbsp;<img src=\"");
                    if (objParent.getSortAscending())
                        objBuf.append(objParent.getAscendingImage());
                    else
                        objBuf.append(objParent.getDescendingImage());
                    objBuf.append("\" border=0 align=\"absmiddle\"></span>");
                }
                else
                {
                    objBuf.append("ASC");
                    objBuf.append("')\" class=\"gridSort\">");
                    objBuf.append(strTxt);
                }
                objBuf.append("</a>");
            }else{
                objBuf.append(strTxt);
            }
            
            

            objBuf.append("</th>");

            // Write created HTML to output stream.
            this.pageContext.getOut().print(objBuf.toString());
        }
        catch (IOException IOEx)
        {
            throw new JspException("Error: IOException while writing to client!", IOEx);
        }
        catch (Exception ex)
        {
            throw new JspException("Error: Exception while writing to client!", ex);
        }
        finally
        {
            if (objBuf != null) objBuf = null;
        }
    }

    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#renderBlank()
     */
    public void renderBlank() throws JspException
    {
        StringBuffer objBuf = null;

        try
        {
            objBuf = new StringBuffer();
            objBuf.append("<td");
            if (this.mintWidth > 0)
                objBuf.append(" width=\"" + this.mintWidth + "%\"");
            if (this.mintHeight > 0)
                objBuf.append(" height=\"" + this.mintHeight + "\"");
            if (this.mstrCssClass != null)
                objBuf.append(" class=\"" + this.mstrCssClass + "\"");
            if (this.mstrHAlign != null)
                objBuf.append(" align=\"" + this.mstrHAlign.toLowerCase() + "\"");
            if (this.mstrVAlign != null)
                objBuf.append(" valign=\"" + this.mstrVAlign.toLowerCase() + "\"");

            if (this.mstrBgColor != null)
                objBuf.append(" bgcolor=\"" + this.mstrBgColor + "\"");
            if (this.mstrForeColor != null)
                objBuf.append(" color=\"" + this.mstrForeColor + "\"");
            
            if(this.mstrHideField){
            	objBuf.append(" style=\"display:none\"");
            }
            
            objBuf.append(">&nbsp;</td>");

            // Write created HTML to output stream.
            this.pageContext.getOut().print(objBuf.toString());
        }
        catch (IOException IOEx)
        {
            throw new JspException("Error: IOException while writing to client!", IOEx);
        }
        catch (Exception ex)
        {
            throw new JspException("Error: Exception while writing to client!", ex);
        }
        finally
        {
            if (objBuf != null) objBuf = null;
        }
    }

    public void copyAttributesTo(IColumnTag pobjDest)
    {
        pobjDest.setBgColor(this.mstrBgColor);
        pobjDest.setBorder(this.mintBorder);
        pobjDest.setCssClass(this.mstrCssClass);
        pobjDest.setDataField(this.mstrDataField);
        pobjDest.setForeColor(this.mstrForeColor);
        pobjDest.setHAlign(this.mstrHAlign);
        pobjDest.setHeaderText(this.mstrHeaderText);
        pobjDest.setHeight(this.mintHeight);
        pobjDest.setName(this.mstrName);
        pobjDest.setSortable(this.mblnSortable);
        pobjDest.setVAlign(this.mstrVAlign);
        pobjDest.setWidth(this.mintWidth);
        pobjDest.setHideField(this.mstrHideField);
        
    }
}