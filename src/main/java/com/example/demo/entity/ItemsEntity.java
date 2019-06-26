package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ItemsEntity {
	
	@Id
	private int itemId;
	private String name;
	private String detail;
	private Timestamp createAt;
	private Timestamp updateAt;
	private Timestamp deleteAt;
	private int price;
	private int status;
	private String imagePath;
	private int genreId;
	private int brandId;
	private int stock;
	private int adminId;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="brandId", insertable=false, updatable=false)
	private BrandsEntity brandsEntity;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="genreId", insertable=false, updatable=false)
	private GenresEntity genresEntity;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	public Timestamp getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(Timestamp deleteAt) {
		this.deleteAt = deleteAt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setJunleId(int genreId) {
		this.genreId = genreId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBlandId(int brandId) {
		this.brandId = brandId;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public BrandsEntity getBrandsEntity() {
		return brandsEntity;
	}
	public void setBrandsEntity(BrandsEntity brandsEntity) {
		this.brandsEntity = brandsEntity;
	}
	public GenresEntity getGenresEntity() {
		return genresEntity;
	}
	public void setGenresEntity(GenresEntity genresEntity) {
		this.genresEntity = genresEntity;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	

}
