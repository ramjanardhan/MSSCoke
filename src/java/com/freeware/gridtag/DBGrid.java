/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : DBGrid.java
 * CREATED: Jul 20, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * A tag class to render a html table. The class expects that a valid SQL and a
 * java.sql.Connection object be passed.
 * 
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class DBGrid extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5463832977972518538L;

	public static final String DEFAULT_NULLTEXT = "&nbsp";

	private int mintBorder;
	private int mintCellPadding;
	private int mintCellSpacing;
	private int mintWidth;
	private int mintPageSize;
	private int mintCurrPage;

	private String mstrCssClass;
	private String mstrBgColor;
	private String mstrForeColor;
	private String mstrID;
	private String mstrName;
	private String mstrTrAttributes;

	private boolean mstrHiddenColumn;

	private String mstrDataMember;

	private List<Object> mlstColumns;

	private GridPager mobjPager;

	private GridSorter mobjSorter;

	private Object mobjDataSource;
	private ResultSet mobjRS;
	private Object mCurrItem;
	int  msgValue=0;
	String ExceptionMessage;

	public DBGrid() {
		super();
		this.mintCurrPage = 1;
		this.mintPageSize = 10;
		this.mintBorder = 0;
		this.mintCellPadding = 0;
		this.mintCellSpacing = 0;
		this.mintWidth = 100;
		this.mstrTrAttributes = "";
		mlstColumns = new ArrayList();
	}

	/*
	 * --------------------------------------------------------------------------
	 * ---- Getters
	 * --------------------------------------------------------------
	 * --------------
	 */
	/**
	 * Returns grid width.
	 * 
	 * @return grid width.
	 */
	public int getWidth() {
		return this.mintWidth;
	}

	/**
	 * Returns border width of the grid.
	 * 
	 * @return border width
	 */
	public int getBorder() {
		return this.mintBorder;
	}

	/**
	 * Returns cellspacing between table cells.
	 * 
	 * @return cellsapcing between table cells.
	 */
	public int getCellSpacing() {
		return this.mintCellSpacing;
	}

	/**
	 * Returns amount of padding to be applied for a table cell.
	 * 
	 * @return padding for a table cell.
	 */
	public int getCellPadding() {
		return this.mintCellPadding;
	}

	/**
	 * Returns background color to be used for entire table.
	 * 
	 * @return background color value.
	 */
	public String getBgColor() {
		return this.mstrBgColor;
	}

	/**
	 * Returns foreground color to be used for entire table.
	 * 
	 * @return foreground color value.
	 */
	public String getForeColor() {
		return this.mstrForeColor;
	}

	/**
	 * Returns the id to be used to identify this table in HTML DOM.
	 * 
	 * @return table identifier.
	 */
	public String getID() {
		return this.mstrID;
	}

	/**
	 * Returns the short name to be used to identify this table in HTML DOM.
	 * 
	 * @return table's short name identifier.
	 */
	public String getName() {
		return this.mstrName;
	}

	/**
	 * Returns the List of Extensions being made to TR TAG with in a TABLE
	 * 
	 * @return table's TR Tag Extensions
	 */
	public String getTrAttributes() {
		return this.mstrTrAttributes;
	}

	/**
	 * Returns the datasource used to populate this table. This can be a
	 * java.util.List or java.sql.Conection.
	 * 
	 * @return data source used for table population.
	 */
	public Object getDataSource() {
		return this.mobjDataSource;
	}

	/**
	 * Returns either the SQL statement used for data retrieval or the class
	 * name of the item in list.
	 * 
	 * @return data member either SQL statement of class name.
	 */
	public String getDataMember() {
		return this.mstrDataMember;
	}

	/**
	 * Returns the number of items to be displayed at any given time.
	 * 
	 * @return Number of items to be displayed.
	 */
	public int getPageSize() {
		return this.mintPageSize;
	}

	/**
	 * Returns the current page number.
	 * 
	 * @return current page number.
	 */
	public int getCurrentPage() {
		return this.mintCurrPage;
	}

	/**
	 * Returns CSS class to be associated with the table.
	 * 
	 * @return CSS class for table.
	 */
	public String getCssClass() {
		return this.mstrCssClass;
	}

	/**
	 * Returns the name of the sort column.
	 * 
	 * @return sort column name.
	 */
	public String getSortColumn() {
		if (this.mobjSorter != null)
			return this.mobjSorter.getSortColumn();
		return null;
	}

	/**
	 * Returns the sort order, either can either be ASC or DSC.
	 * 
	 * @return sort order identifier.
	 */
	public boolean getSortAscending() {
		if (this.mobjSorter != null)
			return this.mobjSorter.getSortAscending();
		return true;
	}

	/**
	 * Returns the name of the image which gets displayed when sorting is done
	 * in ascending order.
	 * 
	 * @return ascending order image.
	 */
	public String getAscendingImage() {
		String strTmp = null;
		if (this.mobjSorter != null)
			strTmp = this.mobjSorter.getImageAscending();
		return (strTmp == null ? "images/ImgAsc.gif" : strTmp);
	}

	/**
	 * Returns the name of the image which gets displayed when sorting is done
	 * in descending order.
	 * 
	 * @return descending order image.
	 */
	public String getDescendingImage() {
		String strTmp = null;
		if (this.mobjSorter != null)
			strTmp = this.mobjSorter.getImageDescending();
		return (strTmp == null ? "images/ImgDesc.gif" : strTmp);
	}

	public boolean getHiddenColumn() {
		return this.mstrHiddenColumn;
	}

	/*
	 * --------------------------------------------------------------------------
	 * ---- Setters
	 * --------------------------------------------------------------
	 * --------------
	 */
	/**
	 * Sets grid width.
	 * 
	 * @param pintWidth
	 *            new grid width.
	 */
	public void setWidth(int pintWidth) {
		if (pintWidth >= 0)
			this.mintWidth = pintWidth;
	}

	/**
	 * Sets border width of the grid.
	 * 
	 * @param pintBorder
	 *            border width
	 */
	public void setBorder(int pintBorder) {
		if (pintBorder >= 0)
			this.mintBorder = pintBorder;
	}

	/**
	 * Sets cellspacing between table cells.
	 * 
	 * @param pintCellSpacing
	 *            cellsapcing between table cells.
	 */
	public void setCellSpacing(int pintCellSpacing) {
		if (pintCellSpacing >= 0)
			this.mintCellSpacing = pintCellSpacing;
	}

	/**
	 * Sets amount of padding to be applied for a table cell.
	 * 
	 * @param pintCellPadding
	 *            padding for a table cell.
	 */
	public void setCellPadding(int pintCellPadding) {
		if (pintCellPadding >= 0)
			this.mintCellPadding = pintCellPadding;
	}

	/**
	 * Sets background color to be used for entire table.
	 * 
	 * @param pstrBgColor
	 *            background color value.
	 */
	public void setBgColor(String pstrBgColor) {
		this.mstrBgColor = pstrBgColor;
	}

	/**
	 * Sets foreground color to be used for entire table.
	 * 
	 * @param pstrColor
	 *            foreground color value.
	 */
	public void setForeColor(String pstrColor) {
		this.mstrForeColor = pstrColor;
	}

	/**
	 * Sets the id to be used to identify this table in HTML DOM.
	 * 
	 * @param pstrID
	 *            table identifier.
	 */
	public void setID(String pstrID) {
		this.mstrID = pstrID;
		this.mstrName = pstrID;
	}

	/**
	 * Sets the short name to be used to identify this table in HTML DOM.
	 * 
	 * @param pstrName
	 *            table's short name identifier.
	 */
	public void setName(String pstrName) {
		this.mstrName = pstrName;
		this.mstrID = pstrName;
	}

	/**
	 * Sets the TR Tag Extensions to be used in the HTML TABLE Code generation
	 * 
	 * @param pstrTrExtension
	 *            table's TR Tag Extension.
	 */
	public void setTrAttributes(String pstrTrAttributes) {
		this.mstrTrAttributes = pstrTrAttributes;
	}

	/**
	 * Sets the datasource used to populate this table. This can be a
	 * java.util.List or java.sql.Conection.
	 * 
	 * @param pobjDataSrc
	 *            data source used for table population.
	 */
	public void setDataSource(Object pobjDataSrc)
			throws UnsupportedOperationException {
		if (pobjDataSrc != null) {
			// test print
			// System.out.println("hai set the data source method*** ");
			if (pobjDataSrc instanceof Connection
					|| pobjDataSrc instanceof List) {
				// System.out.println("hai set the data source to"+pobjDataSrc);
				this.mobjDataSource = pobjDataSrc;
			} else {
				throw new UnsupportedOperationException(
						"Invalid data source only "
								+ " java.util.List or java.sql.Connection are supported!");
			}
		}
	}

	/**
	 * Sets either the SQL statement used for data retrieval or the class name
	 * of the item in list.
	 * 
	 * @param pstrDataMember
	 *            data member either SQL statement of class name.
	 */
	public void setDataMember(String pstrDataMember) {
		// test print
		// System.out.println("hai set the data member to "+pstrDataMember);
		this.mstrDataMember = pstrDataMember;
	}

	/**
	 * Sets the number of items to be displayed at any given time.
	 * 
	 * @param pintPageSize
	 *            Number of items to be displayed.
	 */
	public void setPageSize(int pintPageSize) {
		if (pintPageSize >= 0)
			this.mintPageSize = pintPageSize;
	}

	/**
	 * Sets the current page number.
	 * 
	 * @param pintCurrPage
	 *            current page number.
	 */
	public void setCurrentPage(int pintCurrPage) {
		if (pintCurrPage >= 0)
			this.mintCurrPage = pintCurrPage;
	}

	/**
	 * Sets CSS class to be associated with the table.
	 * 
	 * @param pstrCssClass
	 *            CSS class for table.
	 */
	public void setCssClass(String pstrCssClass) {
		this.mstrCssClass = pstrCssClass;
	}

	public void setHiddenColumn(boolean phiddenColumn) {
		this.mstrHiddenColumn = phiddenColumn;
	}

	/*
	 * --------------------------------------------------------------------------
	 * ---- Overridden Methods
	 * 
	 * @see javax.servlet.jsp.tagext.Tag
	 * ------------------------------------------
	 * ----------------------------------
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		// If columns are not defined then report an error
		if (this.mlstColumns.size() == 0) {

			throw new JspException("Error: No columns defined for the table!");
		}
		drawGrid();
		release();
		return EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		try {
			if (this.mobjRS != null) {
				// System.out.println("\n\n\n****Rs object not null");
				this.mobjRS.close();
			}
		} catch (SQLException SQLExIgnore) {
			// System.out.println(
			// "The following exceeption occured in release method "
			// +SQLExIgnore.getMessage());
			SQLExIgnore.printStackTrace();
		}
		if (this.mobjRS != null)
			this.mobjRS = null;
		if (this.mlstColumns != null)
			this.mlstColumns.clear();
		if (this.mobjPager != null)
			this.mobjPager = null;
		super.release();
	}

	/*
	 * --------------------------------------------------------------------------
	 * ---- Methods
	 * --------------------------------------------------------------
	 * --------------
	 */
	/**
	 * Method to find out whether sorting is currently supported or not.
	 * 
	 * @return true if grid is sortable, false otherwise
	 */
	public boolean supportSorting() {
		if (this.mobjSorter == null)
			return false;
		return true;
	}

	/**
	 * Method to assign the page object which is responsible for displaying
	 * navigational components for the grid.
	 * 
	 * @param pobjPgr
	 *            The page object reference.
	 */
	public void setPager(GridPager pobjPgr) {
		this.mobjPager = pobjPgr;
	}

	/**
	 * Method to assign sorting capability to the grid. If the instance of this
	 * object is not configured in grid the grid does not exhibit sorting
	 * capability/
	 * 
	 * @param pobjSort
	 *            The instance of sort object.
	 */
	public void setSorter(GridSorter pobjSort) {
		this.mobjSorter = pobjSort;
	}

	/**
	 * Helper method used by contained column tags to let know the grid about
	 * the columns to be displayed.
	 * 
	 * @param pobjCol
	 *            The column object.
	 */
	public void addColumn(IColumnTag pobjCol) {
		this.mlstColumns.add(pobjCol);
	}

	/**
	 * Helper method for retrieving value of a particular column.
	 * 
	 * @param pstrCol
	 *            The name of the column whose value is to be retrieved.
	 * @return The column value.
	 */
	public Object getColumnValue(String pstrCol) {
		Object objRet = null;

		try {
			if (pstrCol != null) {
				if (this.mobjDataSource instanceof Connection) {
					objRet = this.mobjRS.getObject(pstrCol);
				} else {
					objRet = PropertyUtils.getProperty(this.mCurrItem, pstrCol);
				}
			}
		} catch (SQLException SQLEx) {
			
			//System.err.println("SQLException"+SQLEx.getMessage());
			
			//throw new 
			SQLEx.printStackTrace();
			
		} catch (IllegalAccessException IAEx) {
			IAEx.printStackTrace();
		} catch (InvocationTargetException ITargetEx) {
			ITargetEx.printStackTrace();
		} catch (NoSuchMethodException NSMEx) {
			NSMEx.printStackTrace();
			
		}
		if (objRet == null)
			objRet = new String(DBGrid.DEFAULT_NULLTEXT);
		return objRet;
	}

	/*
	 * --------------------------------------------------------------------------
	 * ---- Helpers
	 * --------------------------------------------------------------
	 * --------------
	 */
	private void drawGrid() throws JspException {
		int intTotRec = 0;
		int intCntr = 0;
		int intStart = 0;
		String strFld = null;
		IColumnTag objCol = null;
		JspWriter objOut = null;
		Iterator iterCol = null;
		Statement objStmt = null;

		intTotRec = findTotalRecords();

		if (intTotRec <= 0) {
			drawEmptyTable();
			return;
		}

		try {
			if (this.mintCurrPage == 1)
				intStart = 0;
			else {
				intStart = ((this.mintCurrPage - 1) * this.mintPageSize);
				if (intStart >= intTotRec)
					intStart = computeLastPageStart(intTotRec);
			}

			drawTableStart();
			drawHeaderRow();

			objOut = this.pageContext.getOut();
			if (this.mobjDataSource instanceof Connection) {
				objStmt = ((Connection) this.mobjDataSource).createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// System.out.println("The final ececuting statement is "+this.
				// mstrDataMember + " " + getOrderByClause());
				this.mobjRS = objStmt.executeQuery(this.mstrDataMember + " "
						+ getOrderByClause());
				// if(this.mobjRS!=null)System.out.println(
				// "got some result and intSTART is"+intStart);
				if (intStart > 0)
					this.mobjRS.absolute(intStart+1);
				else
					this.mobjRS.next();
			}

			for (intCntr = 0; intCntr < this.mintPageSize; intCntr++) {
				if ((intCntr % 2) == 0) {
					objOut.println("<tr class=\"gridCMRow\" onMouseOver=\"this.className='rowHighlight'\" onMouseOut=\"this.className='gridCMRow'\"");
					//objOut.println("<tr class=\"gridRowEven\" ");
					// objOut.println(
					// " onmouseover=this.style.background='#D1D1DE'");
					// objOut.println(
					// " onmouseout =this.style.background='#e7edf8'");
					objOut.println(resolveFields(this.mstrTrAttributes) + " >");
				} else {
					objOut.println("<tr class=\"gridCMRow\" onMouseOver=\"this.className='rowHighlight'\" onMouseOut=\"this.className='gridCMRow'\"");
					//objOut.println("<tr class=\"gridRowEven\" ");
					// objOut.println(
					// " onmouseover=this.style.background='#D1D1DE'");
					// objOut.println(
					// " onmouseout =this.style.background='#ffffff'");
					objOut.println(resolveFields(this.mstrTrAttributes) + " >");
				}
				iterCol = null;
				if (this.mobjDataSource instanceof List)
					this.mCurrItem = ((List) this.mobjDataSource).get(intStart
							+ intCntr);
				int currColNum = 1;
				for (iterCol = this.mlstColumns.iterator(); iterCol.hasNext();) {

					objCol = null;
					objCol = (IColumnTag) iterCol.next();
					if (objCol instanceof RowNumColumn) {
						// objCol.renderDetail(new Integer(intStart +
						// intCntr +
						// 1));
						objCol.renderDetail(
								new Integer(intStart + intCntr + 1), (intStart
										+ intCntr + 1), currColNum);
						currColNum += 1;
					} else {
						strFld = objCol.getDataField();
						objCol.renderDetail(this.getColumnValue(strFld),
								(intStart + intCntr + 1), currColNum);
						currColNum += 1;
					}

				}// Closing for loop
				objCol = null;
				objOut.println("</tr>");
				if (this.mobjDataSource instanceof Connection) {
					if (!(this.mobjRS.next()))
						break;
				} else {
					if ((intStart + intCntr) == ((List) this.mobjDataSource)
							.size() - 1)
						break;
				}
			}// Closing for loop
			if (intCntr < this.mintPageSize)
				emptyRowsOut(intCntr);

			if (this.mobjPager != null) {
				objOut.println("<tr class=\"gridFooter\">\r\n");
				if (this.mstrHiddenColumn) {
					objOut.println("<td colspan="
							+ (this.mlstColumns.size() - 1) + ">");
				} else {
					objOut.println("<td colspan=" + this.mlstColumns.size()
							+ ">");
				}

				this.mobjPager.renderPager(this.mintCurrPage, intTotRec,
						this.mintPageSize);
				objOut.println("</td>");
				objOut.println("</tr>");
			}
			objOut.println("</table>");
		} catch (IOException IOEx) {
			IOEx.printStackTrace();
			throw new JspException("Error: Unable to write grid contents!",
					IOEx);
		} catch (SQLException SQLEx) {
			SQLEx.printStackTrace();
			throw new JspException("Error: Unable to fetch data content!",
					SQLEx);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new JspException("Error: Unknown error occured!", ex);
		} finally {
			if (objCol != null)
				objCol = null;
			if (objOut != null)
				objOut = null;
			if (iterCol != null)
				iterCol = null;
			try {
				if (objStmt != null)
					objStmt.close();
			} catch (SQLException SqlExIgnore) {
			}
			if (objStmt != null)
				objStmt = null;
		}
	}

	private void drawEmptyTable() throws JspException {
		int intCols = 0;
		int intCntr = 0;
		JspWriter objOut = null;

		try {
			intCols = this.mlstColumns.size();
			objOut = this.pageContext.getOut();
			drawTableStart();
			for (intCntr = 0; intCntr < this.mintPageSize; intCntr++) {
				if ((intCntr % 2) == 0)
					objOut.println("<tr class=\"gridRowEven\">");
					//objOut.println("<tr class=\"gridCMRow\" onMouseOver=\"this.className='rowHighlight'\" onMouseOut=\"this.className='gridCMRow'\">");
			else
				//objOut.println("<tr class=\"gridCMRow\" onMouseOver=\"this.className='rowHighlight'\" onMouseOut=\"this.className='gridCMRow'\">");
					objOut.println("<tr class=\"gridRowEven\">");
				objOut.print("<td colspan=");
				objOut.print(this.mlstColumns.size());
				
				if(msgValue==0)
				{
				if (intCntr == 0)
					objOut.println(">No records to display!</td>");
				else
					objOut.println(">&nbsp;</td>");
				}
				else
					objOut.println(">Error : "+ExceptionMessage+"</td>");
				objOut.println("</tr>");
			}
			if (this.mobjPager != null) {
				objOut.println("<tr class=\"gridFooter\">");
				objOut.println("<td colspan=" + this.mlstColumns.size() + " >");
				this.mobjPager.renderPager(this.mintCurrPage, 0,
						this.mintPageSize);
				objOut.println("</td>");
				objOut.println("</tr>");
			}
			objOut.println("</table>");
		} catch (IOException IOEx) {
			// System.out.println("the following exception occured in db grid"+
			// IOEx.getMessage());
			IOEx.printStackTrace();
			throw new JspException("Error: Exception while writing to client!",
					IOEx);
		} finally {
			if (objOut != null)
				objOut = null;
		}
	}

	private void drawTableStart() throws JspException {
		StringBuffer objBuf = null;

		objBuf = new StringBuffer();
		objBuf.append("<table");
		if (this.mstrCssClass != null)
			objBuf.append(" class=\"" + this.mstrCssClass + "\"");
		objBuf.append(" width=\"" + this.mintWidth + "%\"");
		objBuf.append(" cellspacing=" + this.mintCellSpacing);
		objBuf.append(" cellpadding=" + this.mintCellPadding);
		if (this.mstrID != null)
			objBuf.append(" id=\"" + this.mstrID + "\"");
		if (this.mstrName != null)
			objBuf.append(" name=\"" + this.mstrName + "\"");
		if (this.mstrForeColor != null)
			objBuf.append(" color=\"" + this.mstrForeColor + "\"");
		if (this.mstrBgColor != null)
			objBuf.append(" bgcolor=\"" + this.mstrBgColor + "\"");
		objBuf.append(">\r\n");
		try {
			this.pageContext.getOut().println(objBuf.toString());
		} catch (IOException IOEx) {
			throw new JspException("Error: Error while writing to client!",
					IOEx);
		}
		objBuf = null;
	}

	private void drawHeaderRow() throws JspException {
		JspWriter objOut = null;
		IColumnTag objCol = null;
		Iterator iterCol = null;

		try {
			objOut = this.pageContext.getOut();
			objOut.println("<tr class=\"gridHeader\">");
			int currColumn = 1;
			for (iterCol = this.mlstColumns.iterator(); iterCol.hasNext();) {
				objCol = null;
				objCol = (IColumnTag) iterCol.next();
				objCol.renderHeader(this.mstrHiddenColumn, currColumn,
						this.mlstColumns.size());
				currColumn += 1;
			}
			objCol = null;
			objOut.println("</tr>");
		} catch (IOException IOEx) {
			throw new JspException("Error: Unable to render grid header!", IOEx);
		} finally {
			if (objCol != null)
				objCol = null;
			if (iterCol != null)
				iterCol = null;
			if (objOut != null)
				objOut = null;
		}
	}

	public String genCountSQL() {
		int intPos = 0;
		int intPos1 = 0;
		String strRet = null;

		if (this.mstrDataMember != null) {

			intPos1 = this.mstrDataMember.indexOf("DISTINCT");
			intPos = this.mstrDataMember.indexOf(" FROM ");
			if (intPos == -1) {
				intPos = this.mstrDataMember.indexOf(" from ");
				if (intPos == -1) {
					intPos = this.mstrDataMember.indexOf(" From ");
				}
			}

			if (intPos1 != -1) {
				/*
				 * int positionOfON = this.mstrDataMember.indexOf(" on ");
				 * positionOfON = positionOfON + 5; int positionOfEquals =
				 * this.mstrDataMember.indexOf("=",positionOfON); String query =
				 * this.mstrDataMember.substring(positionOfON,positionOfEquals -
				 * 1);
				 * 
				 * strRet = "SELECT COUNT(DISTINCT " + query + ") " +
				 * this.mstrDataMember.substring(intPos);
				 */
				// Start : Chennu Code
				strRet = "SELECT COUNT(DISTINCT "
						+ this.mstrDataMember.substring(intPos1 + 8,
								this.mstrDataMember.indexOf(",")) + ") "
						+ this.mstrDataMember.substring(intPos);
				// End : Chennu Code
			} else if (intPos != -1) {
				strRet = "SELECT COUNT(1) "
						+ this.mstrDataMember.substring(intPos);
			}
		}
		return strRet;
	}

	public int findTotalRecords() {
		
		int intRet = 0;
		Statement objStmt = null;
		ResultSet objRS = null;

		if (this.mobjDataSource == null)
			return 0;

		try {
			if (this.mobjDataSource instanceof Connection) {
				objStmt = ((Connection) this.mobjDataSource).createStatement();
				objRS = objStmt.executeQuery(genCountSQL());
				if (objRS == null) {
					// /System.out.println(
					// "\n\n///////%%%%%% did not ftch any records");
				}
				if (objRS.next())
					intRet = objRS.getInt(1);
			} else {
				intRet = ((List) this.mobjDataSource).size();
			}
		} catch (SQLException SQLExIgnore) {
			msgValue++;
			ExceptionMessage=SQLExIgnore.getMessage();
			////System.err.println("SQLException"+SQLExIgnore.getMessage());
			
		} finally {
			try {
				if (objRS != null)
					objRS.close();
			} catch (SQLException SqlExIgnore) {
			}
			if (objRS != null)
				objRS = null;

			try {
				if (objStmt != null)
					objStmt.close();
			} catch (SQLException SqlExIgnore) {
			}
			if (objStmt != null)
				objStmt = null;
		}
		return intRet;
	}

	private int computeLastPageStart(int pintTotal) {
		int intRet = 0;
		int intLastPage = 0;

		if ((pintTotal % this.mintPageSize) == 0)
			intLastPage = (pintTotal / this.mintPageSize) - 1;
		else
			intLastPage = (pintTotal / this.mintPageSize);

		this.mintCurrPage = intLastPage;
		intRet = (intLastPage * this.mintPageSize);
		return intRet;
	}

	private void emptyRowsOut(int pintFrom) throws JspException {
		int intCntr = 0;
		Iterator iterCol = null;
		IColumnTag objCol = null;
		JspWriter objOut = null;

		try {
			objOut = this.pageContext.getOut();
			for (intCntr = pintFrom + 1; intCntr < this.mintPageSize; intCntr++) {
				if ((intCntr % 2) == 0)
					objOut.println("<tr class=\"gridRowEven\">");
				else
					objOut.println("<tr class=\"gridRowOdd\">");

				iterCol = null;

				for (iterCol = this.mlstColumns.iterator(); iterCol.hasNext();) {
					objCol = null;
					objCol = (IColumnTag) iterCol.next();
					objCol.renderBlank();
				}
				objCol = null;
				objOut.println("</tr>");
			}
		} catch (IOException IoEx) {
			throw new JspException("Error: Writing empty rows!", IoEx);
		} finally {
			if (objOut != null)
				objOut = null;
			if (iterCol != null)
				iterCol = null;
			if (objCol != null)
				objCol = null;
		}
	}

	private String getOrderByClause() {
		String strRet = "";
		String strTmp = null;
		String strCol = null;

		Iterator objIter = null;
		IColumnTag objCol = null;

		if (this.mobjSorter == null)
			return "";
		if (this.mlstColumns == null)
			return "";
		if (this.mlstColumns.size() <= 0)
			return "";

		strCol = this.mobjSorter.getSortColumn();
		if (strCol == null)
			return "";

		for (objIter = this.mlstColumns.iterator(); objIter.hasNext();) {
			objCol = null;
			objCol = (IColumnTag) objIter.next();
			if (objCol.getSortable()) {
				if (objCol.getDataField() != null) {
					if (objCol.getDataField().equals(strCol)) {
						strRet = " ORDER BY "
								+ strCol
								+ " "
								+ (this.mobjSorter.getSortAscending() ? "ASC"
										: "DESC");
						break;
					}
				}
			}
		}
		if (objIter != null)
			objIter = null;
		if (objCol != null)
			objCol = null;
		return strRet;
	}

	private String resolveFields(String pstrUrl) throws ClassCastException {
		int intPos = 0;
		int intEnd = 0;
		String strCol = null;
		String strRet = null;
		DBGrid objTmp = null;

		strRet = pstrUrl;
		objTmp = (DBGrid) this;
		intPos = strRet.indexOf("{");
		while (intPos >= 0) {
			intEnd = strRet.indexOf("}", intPos + 1);
			if (intEnd != -1) {
				strCol = strRet.substring(intPos + 1, intEnd);
				strRet = strRet.substring(0, intPos)
						+ objTmp.getColumnValue(strCol)
						+ strRet.substring(intEnd + 1);
			}
			intPos = strRet.indexOf("{", intPos + 1);
		}
		return strRet;
	}

}

