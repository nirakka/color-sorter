package entity;

import java.util.Arrays;

import utility.Product;
import utility.RGB;

public class ProductPool {
	private Product[] products;
	private int index = 0;
	
	private Product zero = new Product(0,new RGB(0, 0, 0));
	
	public ProductPool() {}
	
	public ProductPool(Product[] p) {
		products = Arrays.copyOf(p, p.length);
	}

	public Product getProduct() {
		if (index < products.length)
			return products[index++];
		else
			return zero;
	}
	
	public boolean isEmpty() {
		return (index == products.length);
	}

	public void setProducts(Product[] p) {
		products = Arrays.copyOf(p, p.length);
	}
}
