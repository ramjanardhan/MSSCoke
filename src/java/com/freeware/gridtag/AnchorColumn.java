/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : AnchorColumn.java
 * CREATED: Jul 22, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;

/**
 * This class is responsible for rendeing an anchor column.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class AnchorColumn extends AColumnTag
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3201594724708690415L;
	private String mstrLinkText;
    private String mstrLinkUrl;
    private String mstrTarget;
    private String mstrDataFormat;

    public AnchorColumn()
    {
        super();
    }

/*------------------------------------------------------------------------------
 * Getters 
 *----------------------------------------------------------------------------*/
    public String getLinkText()
    {
        return this.mstrLinkText;
    }

    public String getLinkUrl()
    {
        return this.mstrLinkUrl;
    }

    public String getTarget()
    {
        return this.mstrTarget;
    }

    public String getDataFormat()
    {
        return this.mstrDataFormat;
    }

/*------------------------------------------------------------------------------
 * Setters 
 *----------------------------------------------------------------------------*/
    public void setLinkText(String pstrLinkText)
    {
        this.mstrLinkText = pstrLinkText;
    }

    public void setLinkUrl(String pstrLinkUrl)
    {
        this.mstrLinkUrl = pstrLinkUrl;
    }

    public void setTarget(String pstrTarget)
    {
        this.mstrTarget = pstrTarget;
    }

    public void setDataFormat(String pstrDataFormat)
    {
        this.mstrDataFormat = pstrDataFormat;
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
            throw new JspException("Error: ImageColumn tag is not a child of DBGrid", CCEx);
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
                objBuf.append(" width=\"" + this.mintWidth + "%\"");
            if (this.mintHeight > 0)
                objBuf.append(" height=\"" + this.mintHeight + "\"");
            /*if (this.mstrCssClass != null)
                objBuf.append(" class=\"" + this.mstrCssClass + "\"");
            else
                objBuf.append(" class=\"gridRowEven\"");*/
            if (this.mstrHAlign != null)
                objBuf.append(" align=\"" + this.mstrHAlign.toLowerCase() + "\"");
            if (this.mstrVAlign != null)
                objBuf.append(" valign=\"" + this.mstrVAlign.toLowerCase() + "\"");

            if (this.mstrBgColor != null)
                objBuf.append(" bgcolor=\"" + this.mstrBgColor + "\"");
            if (this.mstrForeColor != null)
                objBuf.append(" color=\"" + this.mstrForeColor + "\"");
            
            objBuf.append(" id=\""+rowNum+"\"");
            
            objBuf.append(">");
            objBuf.append("<a href=\"");
            objBuf.append(resolveFields(this.mstrLinkUrl));
            objBuf.append("\"");
            if (this.mstrTarget != null)
                	objBuf.append(" target=\"" + this.mstrTarget + "\"");
            objBuf.append(">");
            if (this.mstrLinkText != null)
                objBuf.append(resolveFields(this.mstrLinkText));
            else
                objBuf.append(formatField(pobjValue, this.mstrDataFormat));

            objBuf.append("</a>");
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

    private String resolveFields(String pstrUrl) throws ClassCastException
    {
        int    intPos = 0;
        int    intEnd = 0;
        String strCol = null;
        String strRet = null;
        DBGrid objTmp = null;

        strRet = pstrUrl;
        objTmp = (DBGrid) getParent();
        intPos = strRet.indexOf("{");
        while (intPos >= 0)
        {
            intEnd = strRet.indexOf("}", intPos + 1);
            if (intEnd != -1)
            {
                strCol = strRet.substring(intPos + 1, intEnd); 
                strRet = strRet.substring(0, intPos) +
                		objTmp.getColumnValue(strCol) + 
                		strRet.substring(intEnd + 1);
            }
            intPos = strRet.indexOf("{", intPos +1);
        }
        if (objTmp != null) objTmp = null;
        return strRet;
    }

    private String formatField(Object pobjVal, String pstrFmt) throws ClassCastException
    {
        String strRet = null;
        Format objFmt = null;

        try
        {
            if (pobjVal instanceof java.sql.Date || pobjVal instanceof java.util.Date)
            {
                objFmt = new SimpleDateFormat(pstrFmt);
                strRet = objFmt.format(pobjVal);
            }
            else if (pobjVal instanceof Number)
            {
                objFmt = new DecimalFormat(pstrFmt);
                strRet = objFmt.format(pobjVal);
            }
            else
                strRet = pobjVal.toString();
        }
        catch (NullPointerException NPExIgnore)
        {
        }
        catch (IllegalArgumentException IArgExIgnore)
        {
        }
        finally
        {
            if (objFmt != null) objFmt = null;
        }
        if (strRet == null) strRet = DBGrid.DEFAULT_NULLTEXT;
        return strRet;
    }

    private AnchorColumn getCopy()
    {
        AnchorColumn objRet = null;

        objRet = new AnchorColumn();
        super.copyAttributesTo(objRet);
        objRet.setDataFormat(this.mstrDataFormat);
        objRet.setId(this.getId());
        objRet.setLinkText(this.mstrLinkText);
        objRet.setLinkUrl(this.mstrLinkUrl);
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        objRet.setTarget(this.mstrTarget);
        return objRet;
    }
}
