package com.charityfoundation.cfarticlefirstclassify.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 文章一级分类信息表
 * @author onlineGenerator
 * @date 2019-03-28 21:35:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cf_article_first_classify", schema = "")
@SuppressWarnings("serial")
public class CfArticleFirstClassifyEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**一级分类名称*/
	private String firstClassName;
	/**分类编号*/
	private Integer classNumber;
	/**创建日期*/
	private Date createDate;
	/**更新数据*/
	private Date updateDate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  一级分类名称
	 */

	@Column(name ="FIRST_CLASS_NAME",nullable=true,length=36)
	public String getFirstClassName(){
		return this.firstClassName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一级分类名称
	 */
	public void setFirstClassName(String firstClassName){
		this.firstClassName = firstClassName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分类编号
	 */

	@Column(name ="CLASS_NUMBER",nullable=true,length=2)
	public Integer getClassNumber(){
		return this.classNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分类编号
	 */
	public void setClassNumber(Integer classNumber){
		this.classNumber = classNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=50)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新数据
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=50)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新数据
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
}
