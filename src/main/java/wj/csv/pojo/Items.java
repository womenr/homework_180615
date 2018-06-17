package wj.csv.pojo;

import java.math.BigDecimal;

public class Items {
    private String item;

    private BigDecimal price;

    private String info;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

	@Override
	public String toString() {
		return "Items [item=" + item + ", price=" + price + ", info=" + info + "]";
	}
}