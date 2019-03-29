package com.charityfoundation.cfarticlesecondclassify.entity;

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
 * @Description: 文章二级分类信息表
 * @author onlineGenerator
 * @date 2019-03-28 21:58:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cf_article_second_classify", schema = "")
@SuppressWarnings("serial")
public class CfArticleSecondClassifyEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**一级分类id*/
	@Excel(name="一级分类id",width=15)
	private String firstClassifyId;
	/**分类名称*/
	@Excel(name="分类名称",width=15)
	private String secondClassName;
	/**序号*/
	@Excel(name="序号",width=15)
	private Integer classNumber;
	/**创建日期*/
	private Date createDate;
	/**更新日期*/
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
	 *@return: java.lang.String  一级分类id
	 */

	@Column(name ="FIRST_CLASSIFY_ID",nullable=true,length=36)
	public String getFirstClassifyId(){
		return this.firstClassifyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一级分类id
	 */
	public void setFirstClassifyId(String firstClassifyId){
		this.firstClassifyId = firstClassifyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分类名称
	 */

	@Column(name ="SECOND_CLASS_NAME",nullable=true,length=36)
	public String getSecondClassName(){
		return this.secondClassName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类名称
	 */
	public void setSecondClassName(String secondClassName){
		this.secondClassName = secondClassName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */

	@Column(name ="CLASS_NUMBER",nullable=true,length=11)
	public Integer getClassNumber(){
		return this.classNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 */
	public void setClassNumber(Integer classNumber){
		this.classNumber = classNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
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
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
}
