package com.promotionsapi.pojo.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "properties", "images" })
public class Promotions {

	private String promotionId;
	private int orderId;
	private List<String> promoArea;
	private String promoType;
	private String showPrice;
	private String showText;
	private LocalizedText localizedTexts;
	private List<Properties> properties;
	private List<Images> images;

	public String getPromotionId() {
		return promotionId;
	}

	public int getOrderId() {
		return orderId;
	}

	public List<String> getPromoArea() {
		return promoArea;
	}

	public String getPromoType() {
		return promoType;
	}

	public String getShowPrice() {
		return showPrice;
	}

	public String getShowText() {
		return showText;
	}

	public LocalizedText getLocalizedTexts() {
		return localizedTexts;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public List<Images> getImages() {
		return images;
	}
}
