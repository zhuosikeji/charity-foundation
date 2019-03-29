package com.charityfoundation.cfcarousel.entity;

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
 * @Description: 轮播图表
 * @author onlineGenerator
 * @date 2019-03-28 21:23:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cf_carousel", schema = "")
@SuppressWarnings("serial")
public class CfCarouselEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**轮播图标题*/
	@Excel(name="轮播图标题",width=15)
	private String carouselTitle;
	/**轮播图路径*/
	@Excel(name="轮播图路径",width=15)
	private String carouselPath;
	/**序号*/
	@Excel(name="序号",width=15)
	private Integer carourselNumber;
	/**是否显示*/
	@Excel(name="是否显示",width=15)
	private Integer isShown;
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
	 *@return: java.lang.String  轮播图标题
	 */

	@Column(name ="CAROUSEL_TITLE",nullable=true,length=255)
	public String getCarouselTitle(){
		return this.carouselTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  轮播图标题
	 */
	public void setCarouselTitle(String carouselTitle){
		this.carouselTitle = carouselTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  轮播图路径
	 */

	@Column(name ="CAROUSEL_PATH",nullable=true,length=255)
	public String getCarouselPath(){
		return this.carouselPath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  轮播图路径
	 */
	public void setCarouselPath(String carouselPath){
		this.carouselPath = carouselPath;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */

	@Column(name ="CAROURSEL_NUMBER",nullable=true,length=11)
	public Integer getCarourselNumber(){
		return this.carourselNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 */
	public void setCarourselNumber(Integer carourselNumber){
		this.carourselNumber = carourselNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否显示
	 */

	@Column(name ="IS_SHOWN",nullable=true,length=2)
	public Integer getIsShown(){
		return this.isShown;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否显示
	 */
	public void setIsShown(Integer isShown){
		this.isShown = isShown;
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
