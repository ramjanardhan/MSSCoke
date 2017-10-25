package com.freeware.gridtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
/*------------------------------------------------------------------------------
 * PACKAGE: 
 * FILE   : RowNumColumn.java
 * CREATED: Jul 26, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/

/**
 * This class is responsible for row number display.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class RowNumColumn extends AColumnTag
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4269818630991011588L;

	public RowNumColumn()
    {
        super();
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
            objBuf.append(pobjValue.toString());
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

    private RowNumColumn getCopy()
    {
        RowNumColumn objRet = null;

        objRet = new RowNumColumn();
        super.copyAttributesTo(objRet);
        objRet.setId(this.getId());
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        return objRet;
    }
}
