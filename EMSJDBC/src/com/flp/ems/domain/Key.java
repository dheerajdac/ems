package com.flp.ems.domain;

@SuppressWarnings("rawtypes")
public class Key implements Comparable{
		String str;
		Integer i;

		public Key(int i,String str) {
			this.i=i;
			this.str=str;
		}
		public Key(String key) {
			String[] str = key.split("-");
			this.str=str[0];
			this.i= Integer.parseInt(str[1]);
		}

	public String getStr() {
		return str;
	}

	public Integer getI() {
		return i;
	}

	@Override
		public boolean equals(Object obj) {
			if((this.i==((Key)obj).i)&&(this.str.equals(((Key)obj).str)))
				return true;
			return false;
		}
		
		@Override
		public int compareTo(Object o) {
			int diff =this.i-(((Key)o).i);
			return diff;
		}
		
		@Override
		public int hashCode(){
			return i+str.length();
		}
		
}