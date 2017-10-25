
/*******************************************************************************
 *
 * Project : Electronic Data Interchange Portal v1.0
 *
 * Package : com.freeware.gridtag
 *
 * Date    : Jun 3, 2010 4:26:58 AM
 *
 * Author  : Mrutyumjaya Rao Chennu <mchennu@miraclesoft.com>
 *
 * File    : CkBoxColumn.java
 *
 * Copyright 2010 Blue Cross Blue Shield of Michigan All rights reserved.
 * BCBSM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

	/*------------------------------------------------------------------------------
	 * PACKAGE: com.freeware.gridtag
	 * FILE   : ImageColumn.java
	 * CREATED: Jul 22, 2004
	 * AUTHOR : Prasad P. Khandekar
	 *------------------------------------------------------------------------------
	 * Change Log:
	 *-----------------------------------------------------------------------------*/
	package com.freeware.gridtag;

	import java.io.IOException;

	import javax.servlet.jsp.JspException;


	/**
	 * A class to represent the image column.
	 * @author Prasad P. Khandekar
	 * @version 1.0
	 * @since 1.0
	 */
	public final class CkBoxColumn extends AColumnTag
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 4333997931708026601L;
		private int mintImageWidth;
	    private int mintImageHeight;
	    private int mintImageBorder;

	    private String mstrImageSrc;
	    private String mstrLinkUrl;
	    private String mstrAlterText;
	    private String mstrTarget;
	    private String mstrOnImgClick;
	 
	    public CkBoxColumn()
	    {
	        super();
	        this.mintImageWidth = -1;
	        this.mintImageHeight = -1;
	        this.mintImageBorder = -1;
	    }

	/*------------------------------------------------------------------------------
	 * Getters 
	 *----------------------------------------------------------------------------*/
	    public String getImageSrc()
	    {
	        return this.mstrImageSrc;
	    }

	    public String getLinkUrl()
	    {
	        return this.mstrLinkUrl;
	    }

	    public String getAlterText()
	    {
	        return this.mstrAlterText;
	    }

	    public String getTarget()
	    {
	        return this.mstrTarget;
	    }

	    public int getImageWidth()
	    {
	        return this.mintImageWidth;
	    }

	    public int getImageHeight()
	    {
	        return this.mintImageHeight;
	    }

	    public int getImageBorder()
	    {
	        return this.mintImageBorder;
	    }
	    
	    public String getOnImgClick() {
	        return this.mstrOnImgClick;
	    }

	/*------------------------------------------------------------------------------
	 * Setters 
	 *----------------------------------------------------------------------------*/
	    public void setImageSrc(String pstrSrc)
	    {
	        this.mstrImageSrc = pstrSrc;
	    }

	    public void setLinkUrl(String pstrUrl)
	    {
	        this.mstrLinkUrl = pstrUrl;
	    }

	    public void setAlterText(String pstrAltText)
	    {
	        this.mstrAlterText = pstrAltText;
	    }

	    public void setTarget(String pstrTarget)
	    {
	        this.mstrTarget = pstrTarget;
	    }

	    public void setImageWidth(int pintWidth)
	    {
	        this.mintImageWidth = pintWidth;
	    }

	    public void setImageHeight(int pintHeight)
	    {
	        this.mintImageHeight = pintHeight;
	    }

	    public void setImageBorder(int pintBorder)
	    {
	        this.mintImageBorder = pintBorder;
	    }
	    
	    public void setOnImgClick(String onImgClick) {
	        this.mstrOnImgClick = onImgClick;
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
	            if(!"".equals(this.mstrLinkUrl) && this.mstrLinkUrl!=null)
	            {
	            /*objBuf.append("<a HREF=\"");
	            objBuf.append(resolveFields(this.mstrLinkUrl));
	            objBuf.append("\">");*/
	            }
	            if(!"".equals(this.mstrImageSrc) && this.mstrImageSrc!=null)
	            	objBuf.append("<input id=\"" + this.mstrImageSrc + rowNum + "\"");
	            else
	            	objBuf.append("<input id=\"ckbox" + rowNum + "\"");
	            if(!"".equals(this.mstrLinkUrl) && this.mstrLinkUrl!=null)
	 	           objBuf.append(" name=\"" + resolveFields(this.mstrLinkUrl) + "\"");
	            objBuf.append(" type=\"checkbox\" ");
	            if(this.mstrOnImgClick != null)
                    objBuf.append(" onclick=\""+resolveFields(this.mstrOnImgClick)+"\"");
	            objBuf.append(">");
	            
	           /* objBuf.append("<img SRC=\"");
	            objBuf.append(this.mstrImageSrc);
	            objBuf.append("\"");
	            if (this.mintImageWidth != -1)
	                objBuf.append(" WIDTH=" + this.mintImageWidth);
	            if (this.mintImageHeight != -1)
	                objBuf.append(" HEIGHT=" + this.mintImageHeight);
	            if (this.mintImageBorder != -1)
	                objBuf.append(" BORDER=" + this.mintImageBorder);
	            objBuf.append(" ALTER=\"" + this.mstrAlterText + "\"");
	            if (this.mstrTarget != null)
	                	objBuf.append(" TARGET=\"" + this.mstrTarget + "\"");
	            if(this.mstrOnImgClick != null)
	                        objBuf.append(" onclick=\""+this.mstrOnImgClick+"\"");
	            objBuf.append(">");
	            
	            if(!"".equals(this.mstrLinkUrl) && this.mstrLinkUrl!=null){
	                objBuf.append("</a>");
	            }*/
	            
	            objBuf.append("</td>");

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
	        return strRet;
	    }

	    private CkBoxColumn getCopy()
	    {
	        CkBoxColumn objRet = null;

	        objRet = new CkBoxColumn();
	        super.copyAttributesTo(objRet);
	        objRet.setId(this.getId());
	        objRet.setImageBorder(this.mintImageBorder);
	        objRet.setImageHeight(this.mintImageHeight);
	        objRet.setImageSrc(this.mstrImageSrc);
	        objRet.setImageWidth(this.mintImageWidth);
	        objRet.setLinkUrl(this.mstrLinkUrl);
	        objRet.setPageContext(this.pageContext);
	        objRet.setParent(this.getParent());
	        objRet.setTarget(this.mstrTarget);
	        objRet.setOnImgClick(this.mstrOnImgClick);
	        objRet.setAlterText(this.mstrAlterText);
	        return objRet;
	    }

	   

	
}
