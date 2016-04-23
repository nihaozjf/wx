/** */
package com.akmi.jyxt.model.enu;

/**
 * 周次，0周日,2周一,7周六
 */
public enum LMTYPE {
	HAVELM {
		@Override
		public String getName() {
			return "有子栏目";
		}
	},
	ONEART {
		@Override
		public String getName() {
			return "单信息";
		}
	},
	MANYART {
		@Override
		public String getName() {
			return "多信息";
		}
	},
	OTHERLINK {
		@Override
		public String getName() {
			return "其他链接";
		}
	};


	public abstract String getName();

	public int getIndex() {
		return ordinal();
	}
	
	public static String  getLmTypeName(int i){ 
		String retStr="";
	     switch(i){ 
	       case 0: retStr=LMTYPE.HAVELM.getName(); break; 
	       case 1: retStr=LMTYPE.ONEART.getName();break; 
	       case 2: retStr=LMTYPE.MANYART.getName();break; 
	       case 3: retStr=LMTYPE.OTHERLINK.getName();break; 
	       default:retStr="wrong number!"; 
	     } 
	     return retStr;
	   } 
}
