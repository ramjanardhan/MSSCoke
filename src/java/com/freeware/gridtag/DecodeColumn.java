/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : DecodeColumn.java
 * CREATED: Jul 26, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * This class provides functionality similar to Oracle's DECODE function. i.e.
 * it allows to display different value based on a value of column.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class DecodeColumn extends AColumnTag
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6868221442424930015L;
	private String mstrDecodeValues;
    private String mstrDisplayValues;
    private String mstrValueSeperator;

    public DecodeColumn()
    {
        super();
    }

/*------------------------------------------------------------------------------
 * Getters 
 *----------------------------------------------------------------------------*/
    public String getDecodeValues()
    {
        return this.mstrDecodeValues;
    }

    public String getDisplayValues()
    {
        return this.mstrDisplayValues;
    }

    public String getValueSeperator()
    {
        return this.mstrValueSeperator;
    }

/*------------------------------------------------------------------------------
 * Setters 
 *----------------------------------------------------------------------------*/
    public void setDecodeValues(String mstrValues)
    {
        this.mstrDecodeValues = mstrValues;
    }

    public void setDisplayValues(String mstrValues)
    {
        this.mstrDisplayValues = mstrValues;
    }

    public void setValueSeperator(String pstrSeperator)
    {
        this.mstrValueSeperator = pstrSeperator;
    }

/*------------------------------------------------------------------------------
 * Overridden methods
 * @see javax.servlet.jsp.tagext.Tag
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException
    {
        DBGrid objTmp = null;

        try
        {
            objTmp = (DBGrid) getParent();
            objTmp.addColumn(getCopy());
        }
        catch (ClassCastException CCEx)
        {
            throw new JspException("Error: Column is not a child of DBGrid", CCEx);
        }
        finally
        {
            if (objTmp != null) objTmp = null;
        }
        return EVAL_PAGE;
    }

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException
    {
        if (!(this.getParent() instanceof DBGrid))
            throw new JspException("Error: Column tag needs to be a child of DBGrid!");

        if (!checkValues())
            throw new JspException("Error: For every decode value a display value must be specified!");

        // This tag does not have body contents.
        return SKIP_BODY;
    }

/*------------------------------------------------------------------------------
 * Methods 
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see com.freeware.gridtag.IColumnTag#renderDetail()
     */
    public void renderDetail(Object pobjValue,int rowNum,int colNum) throws JspException
    {
        StringBuffer objBuf = null;

        try
        {
            objBuf = new StringBuffer();
            objBuf.append("<td");
            if (this.mintWidth > 0)
                objBuf.append(" WIDTH=\"" + this.mintWidth + "%\"");
            if (this.mintHeight > 0)
                objBuf.append(" HEIGHT=\"" + this.mintHeight + "\"");
            /*if (this.mstrCssClass != null)
                objBuf.append(" CLASS=\"" + this.mstrCssClass + "\"");
            else
                objBuf.append(" CLASS=\"gridRowEven\"");*/
            if (this.mstrHAlign != null)
                objBuf.append(" ALIGN=\"" + this.mstrHAlign.toLowerCase() + "\"");
            if (this.mstrVAlign != null)
                objBuf.append(" VALIGN=\"" + this.mstrVAlign.toLowerCase() + "\"");

            if (this.mstrBgColor != null)
                objBuf.append(" BGCOLOR=\"" + this.mstrBgColor + "\"");
            if (this.mstrForeColor != null)
                objBuf.append(" COLOR=\"" + this.mstrForeColor + "\"");
            objBuf.append(" id=\""+rowNum+"\"");
            objBuf.append(">");
            objBuf.append(formatField(pobjValue));
            objBuf.append("</td>");

            // Write created HTML to output stream.
            this.pageContext.getOut().print(objBuf.toString());
        }
        catch (ClassCastException CCEx)
        {
            throw new JspException("Error: Anchorcolumn must be a child of DBGrid!", CCEx);
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

    private String formatField(Object pobjVal) throws ClassCastException
    {
        int    intCnt = 0;
        String strRet = null;

        String[] arrDecode  = null;
        String[] arrDisplay = null;

        arrDecode = this.mstrDecodeValues.split(this.mstrValueSeperator);
        arrDisplay = this.mstrDisplayValues.split(this.mstrValueSeperator);
        for (intCnt = 0; intCnt < arrDecode.length; intCnt++)
        {
            if (pobjVal.equals(arrDecode[intCnt]))
            {
                strRet = arrDisplay[intCnt];
                break;
            }
            else if (pobjVal.toString().equals(arrDecode[intCnt]))
            {
                strRet = arrDisplay[intCnt];
                break;
            }
        }

        if (arrDecode != null) arrDecode = null;
        if (arrDisplay != null) arrDisplay = null;

        if (strRet == null) strRet = DBGrid.DEFAULT_NULLTEXT;
        return strRet;
    }

    private DecodeColumn getCopy()
    {
        DecodeColumn objRet = null;

        objRet = new DecodeColumn();
        super.copyAttributesTo(objRet);
        objRet.setDecodeValues(this.mstrDecodeValues);
        objRet.setDisplayValues(this.mstrDisplayValues);
        objRet.setId(this.getId());
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        objRet.setValueSeperator(this.mstrValueSeperator);
        return objRet;
    }

    private boolean checkValues()
    {
        boolean  blnRet     = false;
        String[] arrDecode  = null;
        String[] arrDisplay = null;

        arrDecode = this.mstrDecodeValues.split(this.mstrValueSeperator);
        arrDisplay = this.mstrDisplayValues.split(this.mstrValueSeperator);

        if (arrDecode.length == arrDisplay.length)
            blnRet = true;
        else if (arrDecode.length < arrDisplay.length)
            blnRet = true;

        arrDecode = null;
        arrDisplay = null;
        return blnRet;
    }
}
