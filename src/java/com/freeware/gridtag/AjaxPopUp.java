

package com.freeware.gridtag;


import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;

/**
 *
 * @author miracle
 */
public class AjaxPopUp extends AColumnTag{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4054413518952473062L;
	private boolean mstrOnClick = true;
    private boolean mstrOnMouse = false;
    private String mstrPrimaryId;
    private String mstrStyle;
    private String mstrClassName;
    private String mstrFunction;
    private String mstrUrlFunction;
    private String mstrWindowStyle;
    private String mstrWindowClassName;
    private String mstrBeforeAction;
    private String mstrAfterAction;
    private String mstrSBody;
    
    private String mstrDataFormat;
    private String mstrLinkText;
    private int mintMaxLength;    
    
    
    public AjaxPopUp() {
        super();
        this.mintMaxLength = -1;
    }
    
/*------------------------------------------------------------------------------
 * Getters
 *----------------------------------------------------------------------------*/
    
    public String getId() {
        return mstrPrimaryId;
    }
    
    public int getMaxLength(){
        return this.mintMaxLength;
    }
    
    public String getLinkText() {
        return this.mstrLinkText;
    }
    
    
    public String getDataFormat() {
        return this.mstrDataFormat;
    }
    
    //Addedd
    public boolean isOnClick() {
        return this.mstrOnClick;
    }
    
    public boolean isOnMouse() {
        return this.mstrOnMouse;
    }
    
    public String getPrimaryId() {
        return this.mstrPrimaryId;
    }
    
    public String getStyle() {
        return this.mstrStyle;
    }
    
    public String getClassName() {
        return this.mstrClassName;
    }
    
    public String getJSFunction() {
        return this.mstrFunction;
    }
    
    public String getUrlFunction() {
        return this.mstrUrlFunction;
    }
    
    public String getWindowStyle() {
        return this.mstrWindowStyle;
    }
    
    
    public String getWindowClassName() {
        return this.mstrWindowClassName;
    }
    
    public String getBeforeAction() {
        return this.mstrBeforeAction;
    }
    
    public String getAfterAction() {
        return mstrAfterAction;
    }
    
    
    public String getSBody() {
        return this.mstrSBody;
    }
    
/*------------------------------------------------------------------------------
 * Setters
 *----------------------------------------------------------------------------*/
    
    public void setId(String id) {
        this.mstrPrimaryId = id;
    }
    
    public void setMaxLength(int pintMaxLen){
        this.mintMaxLength = pintMaxLen;
    }
    
    public void setLinkText(String linkText) {
        this.mstrLinkText = linkText;
    }
    
    
    public void setDataFormat(String pstrDataFormat) {
        this.mstrDataFormat = pstrDataFormat;
    }
    
    //Setters
    public void setOnClick(boolean onClick) {
        this.mstrOnClick = onClick;
    }
    
    public void setOnMouse(boolean onMouse) {
        this.mstrOnMouse = onMouse;
    }
//
    
    public void setPrimaryId(String id) {
        this.mstrPrimaryId = id;
    }
    
    
    public void setStyle(String style) {
        this.mstrStyle = style;
    }
    
    
    public void setClassName(String className) {
        this.mstrClassName = className;
    }
    
    
    public void setJSFunction(String url) {
        this.mstrFunction = url;
    }
    
    
    public void setUrlFunction(String urlFunction) {
        this.mstrUrlFunction = urlFunction;
    }
    
    
    public void setWindowStyle(String windowStyle) {
        this.mstrWindowStyle = windowStyle;
    }
    
    
    public void setWindowClassName(String windowClassName) {
        this.mstrWindowClassName = windowClassName;
    }
    
    
    public void setBeforeAction(String beforeAction) {
        this.mstrBeforeAction = beforeAction;
    }
    
    
    public void setAfterAction(String afterAction) {
        this.mstrAfterAction = afterAction;
    }
    
    
    public void setSBody(String sBody) {
        this.mstrSBody = sBody;
    }
    
/*------------------------------------------------------------------------------
 * Overridden methods
 * @see javax.servlet.jsp.tagext.Tag
 *----------------------------------------------------------------------------*/
    
    
    
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        DBGrid objTmp = null;
        
        try {
            objTmp = (DBGrid) getParent();
            objTmp.addColumn(getCopy());
        } catch (ClassCastException CCEx) {
            throw new JspException("Error: ImageColumn tag is not a child of DBGrid", CCEx);
        } finally {
            if (objTmp != null) objTmp = null;
        }
        return EVAL_PAGE;
    }
    
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
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
    public void renderDetail(Object pobjValue,int rowNum,int colNum) throws JspException {
        StringBuffer objBuf = null;
        String       strVal = null;
        
        try {
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
            
            strVal = formatField(pobjValue, this.mstrDataFormat);
            
            if (strVal != null && this.mintMaxLength > 0)
                if (strVal.length() > this.mintMaxLength)
                    strVal = strVal.substring(0, this.mintMaxLength);
            objBuf.append(">"+strVal);
            
//            objBuf.append("<a HREF=\"");
//            objBuf.append(resolveFields(this.mstrUrl));
//            objBuf.append("\"");
//            objBuf.append(">");
//            
            objBuf.append("<a href='#' onclick='"+this.mstrFunction+"("+resolveFields(this.mstrPrimaryId)+");' onmouseover='"+this.mstrFunction+"("+resolveFields(this.mstrPrimaryId)+");' onmouseout='closepopUpWindow();'>");
            
            if (this.mstrLinkText != null)
                objBuf.append(this.mstrLinkText);
            else
                objBuf.append("....");
            
            objBuf.append("</a>");
            objBuf.append("</td>");
            if(this.mstrPrimaryId != null)
             //   System.out.println("Id is : "+resolveFields(this.mstrPrimaryId));
            // Write created HTML to output stream.
            this.pageContext.getOut().print(objBuf.toString());
        } catch (ClassCastException CCEx) {
            throw new JspException("Error: Anchorcolumn must be a child of DBGrid!", CCEx);
        } catch (IOException IOEx) {
            throw new JspException("Error: IOException while writing to client!", IOEx);
        } catch (Exception ex) {
            throw new JspException("Error: Exception while writing to client!", ex);
        } finally {
            if (objBuf != null) objBuf = null;
        }
    }
    
    private String resolveFields(String pstrUrl) throws ClassCastException {
        int    intPos = 0;
        int    intEnd = 0;
        String strCol = null;
        String strRet = null;
        DBGrid objTmp = null;
        
        strRet = pstrUrl;
        objTmp = (DBGrid) getParent();
        intPos = strRet.indexOf("{");
        while (intPos >= 0) {
            intEnd = strRet.indexOf("}", intPos + 1);
            if (intEnd != -1) {
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
    
    private String formatField(Object pobjVal, String pstrFmt) throws ClassCastException {
        String strRet = null;
        Format objFmt = null;
        
        try {
            if (pobjVal instanceof java.sql.Date || pobjVal instanceof java.util.Date) {
                objFmt = new SimpleDateFormat(pstrFmt);
                strRet = objFmt.format(pobjVal);
            } else if (pobjVal instanceof Number) {
                objFmt = new DecimalFormat(pstrFmt);
                strRet = objFmt.format(pobjVal);
            } else
                strRet = pobjVal.toString();
        } catch (NullPointerException NPExIgnore) {
        } catch (IllegalArgumentException IArgExIgnore) {
        } finally {
            if (objFmt != null) objFmt = null;
        }
        if (strRet == null) strRet = DBGrid.DEFAULT_NULLTEXT;
        return strRet;
    }
    
    private AjaxPopUp getCopy() {
        AjaxPopUp objRet = null;
        
        objRet = new AjaxPopUp();
        super.copyAttributesTo(objRet);
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        
        objRet.setId(this.mstrPrimaryId);
        objRet.setDataFormat(this.mstrDataFormat);
        objRet.setLinkText(this.mstrLinkText);
        objRet.setOnClick(this.mstrOnClick);
        objRet.setOnMouse(this.mstrOnMouse);
        objRet.setStyle(this.mstrStyle) ;
        objRet.setClassName(this.mstrClassName);
        objRet.setJSFunction(this.mstrFunction);
        objRet.setUrlFunction(this.mstrUrlFunction);
        objRet.setWindowStyle(this.mstrWindowStyle);
        objRet.setWindowClassName(this.mstrWindowClassName);
        objRet.setBeforeAction(this.mstrBeforeAction);
        objRet.setAfterAction(this.mstrAfterAction);
        objRet.setSBody(this.mstrSBody);
        objRet.setMaxLength(this.mintMaxLength);
        
        return objRet;
    }
    
    
}
