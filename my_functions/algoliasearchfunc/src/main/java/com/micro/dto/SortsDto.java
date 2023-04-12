package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class SortsDto {
	
		private String code;
		private String name;
		private boolean selected;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
}

