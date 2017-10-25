/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : GridSorter.java
 * CREATED: Jul 27, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class to support sorting mechanism for grids.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class GridSorter extends TagSupport
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 67343262239153828L;
	private String mstrSortColumn;
    private boolean mblnSortAscending;
    private String mstrImgAsc;
    private String mstrImgDes;

    public GridSorter()
    {
        super();
    }

/*------------------------------------------------------------------------------
 * Getters
 *----------------------------------------------------------------------------*/
    public String getSortColumn()
    {
        return this.mstrSortColumn;
    }

    public boolean getSortAscending()
    {
        return this.mblnSortAscending;
    }

    public String getImageAscending()
    {
        return this.mstrImgAsc;
    }

    public String getImageDescending()
    {
        return this.mstrImgDes;
    }
    
/*------------------------------------------------------------------------------
 * Setters
 *----------------------------------------------------------------------------*/
    public void setSortColumn(String pstrSortCol)
    {
        this.mstrSortColumn = pstrSortCol;
    }

    public void setSortAscending(boolean pblnSortAsc)
    {
        this.mblnSortAscending = pblnSortAsc;
    }

    public void setImageAscending(String pstrImgAsc)
    {
        this.mstrImgAsc = pstrImgAsc;
    }

    public void setImageDescending(String pstrImgDes)
    {
        this.mstrImgDes = pstrImgDes;
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
            objTmp.setSorter(getCopy());
        }
        catch (ClassCastException CCEx)
        {
            throw new JspException("Error: GridSorter should be a child of DBGrid", CCEx);
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
            throw new JspException("Error: GridSorter tag needs to be a child of DBGrid!");

        // This tag does not have body contents.
        return SKIP_BODY;
    }

/*------------------------------------------------------------------------------
 * Helpers
 *----------------------------------------------------------------------------*/
    private GridSorter getCopy()
    {
        GridSorter objRet = null;

        objRet = new GridSorter();
        objRet.setId(this.getId());
        objRet.setImageAscending(this.mstrImgAsc);
        objRet.setImageDescending(this.mstrImgDes);
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        objRet.setSortColumn(this.mstrSortColumn);
        objRet.setSortAscending(this.mblnSortAscending);
        return objRet;
    }
}
