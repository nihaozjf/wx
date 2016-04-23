package com.wx.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.zhuanpan.model.Magic_Config;

public class Lottery {
	
	public Map<String, Object> choujiang(List<Magic_Config> magic_configs,int times){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int listSize =magic_configs.size();
		Integer chance[]=new Integer[listSize];//中奖概率
		String number[]=new String[listSize];//库存次数
		for(int i=0;i<listSize;i++){
			chance[i]=magic_configs.get(i).getChance();
			number[i]=magic_configs.get(i).getPraisenumber();
			//System.out.println("库存次数"+number[i]);
		}
		Integer prizeId = getRand(chance,number);
		map.put("prizeid", prizeId+1);
		int angle=0;
		String praisename=null;
		if(times==0){
			map.put("angle", angle);
			map.put("praisename", praisename);
			map.put("num", 0);
			
		}else{
			map.put("num", times-1);
			String max= magic_configs.get(prizeId).getMax();
			String min = magic_configs.get(prizeId).getMin();
			
			int minLen=min.split(",").length;
			if(minLen>1){
				int i = new Random().nextInt(minLen-1);
				String [] minarr=min.split(",");
				String [] maxarr=max.split(",");
				int minangle=Integer.parseInt(minarr[i]);
				int maxangle=Integer.parseInt(maxarr[i]);
				angle=new Random().nextInt(maxangle)%(maxangle-minangle+1)+minangle;
				
			}else{
				int minangle=Integer.parseInt(min);
				int maxangle=Integer.parseInt(max);
				 angle=new Random().nextInt(maxangle)%(maxangle-minangle+1)+minangle;
			}
			praisename =magic_configs.get(prizeId).getPraisename();
			map.put("praisename", praisename);
		}
		map.put("angle", angle);
		map.put("praisecontent", magic_configs.get(prizeId).getPraisecontent());
	
		return map;
		
	}
	private Integer getRand(Integer[] chance,String[] number){
		Integer result=null;
		
		try{
			int sum=0;
			for(int i=0;i<chance.length;i++){
				if(number[i].equals("0")){
					continue;
				}else{
					sum +=chance[i];
				}
				
			}
			
			for(int i=0;i<chance.length;i++){
				if(number[i].equals("0")){
					continue;
				}else{
					int randNum = new Random().nextInt(sum);
					if(randNum<chance[i]){
						result=i;
						break;
					}else{
						sum -= chance[i];
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

}
