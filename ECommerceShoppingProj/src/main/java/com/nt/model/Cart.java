package com.nt.model;

public class Cart extends Product {

		private int quantity;

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "Cart [quantity=" + quantity + "]";
		}
}
