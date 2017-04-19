var cityData = [];//点击或勾选获得的数据
//判断是不是直辖市	
function isDirectly(code){
	var municipalities = [];//存所有直辖区和港澳台的城市code
	municipalities.push("110000");//北京
	municipalities.push("120000");//天津
	municipalities.push("310000");//上海
	municipalities.push("500000");//重庆
	for(var i=0;i<municipalities.length;i++){
		if(code==municipalities[i]){
			return true;
		}
	}
}
function isSAR(code){
	var municipalities = [];//存所有直辖区和港澳台的城市code
	municipalities.push("700000");//香港
	municipalities.push("800000");//台湾
	municipalities.push("900000");//澳门
	for(var i=0;i<municipalities.length;i++){
		if(code==municipalities[i]){
			return true;
		}
	}
}


function getProvinceAndCityJson(pCitys,type){
	var nationwide = {};//全国
 	var citys = [];//市数组
 	var provinces = [];//省数组
 	var countys = [];//县数组
 	//得到省和市的数组		
	for(var i=0;i<pCitys.length;i++){
		var levelNumber = pCitys[i].levelNumber;
		/**start 设置数据包装头  **/
		nationwide.name = "地区";
	 	nationwide.cityCode = "100000";
	 	nationwide.levelNumber = "0";
	 	/**end **/
		if(levelNumber=="1"&&pCitys[i].cityCode!="010000"){
			provinces.push(pCitys[i]);
		}
		if(levelNumber=="2"){
			citys.push(pCitys[i]);
		}
		if(levelNumber=="3"){
			countys.push(pCitys[i]);
		}
	}  
 	var zNodes = [];//省市节点数组	
 	for(var i=0;i<provinces.length;i++){
 		var province = provinces[i];
 		var provinceCode = province.cityCode;//省的code
 		if(isDirectly(provinceCode)){
 			var directly = {};//直辖市对象
 			directly.name = province.cityName;
 			directly.cityFullCode = province.cityFullCode;
 			directly.cityCode = province.cityCode;
 			directly.levelNumber = province.levelNumber;
 			directly.isProvince = true;
 			var countyArray = [];
 			for(var c=0;c<countys.length;c++){
 				var countyFullCode = countys[c].cityFullCode;
 				var countyCode = countyFullCode.split(".");
 				if(countyCode.length>=3&&countyCode[2]==provinceCode){
 					var county = {};
 					county.name = countys[c].cityName;
 					county.cityFullCode = countys[c].cityFullCode;
 					county.cityCode = countys[c].cityCode;
 					county.levelNumber = countys[c].levelNumber;
 					countyArray.push(county);
 				}
 			} 			
 			if(type==2||type==1){
 				directly.isParent = false;	
 			}
 			if(type==3){
 				directly.isParent = true;
 				directly.children = countyArray;
 			}     			
 			zNodes.push(directly);
 		}else{   			
 			var noDirectly = {};//非直辖市对象
 			noDirectly.name = province.cityName;
 			noDirectly.cityFullCode = province.cityFullCode;
 			noDirectly.cityCode = province.cityCode;
 			noDirectly.levelNumber = province.levelNumber;
 			noDirectly.isProvince = true;
 			var cityArray = [];
 			for(var j=0;j<citys.length;j++){
 				var cityFullCode = citys[j].cityFullCode;
 				cityFullCode = cityFullCode.split(".");
 				if(cityFullCode[2]==provinceCode){   					
 					var city = {};
 					city.name = citys[j].cityName;
 					city.cityFullCode = citys[j].cityFullCode;
 					city.cityCode = citys[j].cityCode; 
 					city.levelNumber = citys[j].levelNumber;
 					var countyArray = [];
 					for(var n=0;n<countys.length;n++){
 	     				var countyFullCode = countys[n].cityFullCode;
 	     				var countyCode = countyFullCode.split(".");
 	     				if(countyCode[3]==citys[j].cityCode){
 	     					var county = {};
 	     					county.name = countys[n].cityName;
 	     					county.cityFullCode = countys[n].cityFullCode;
 	     					county.cityCode = countys[n].cityCode;
 	     					county.levelNumber = countys[n].levelNumber;
 	     					countyArray.push(county);
 	     				}
 	     			}
 					if(type==3){
 	 					city.children = countyArray;
 					}
 					cityArray.push(city);
 				}
 			}
 			if(type==1){
 				noDirectly.children = cityArray.length;
 			}
 			if(type==2||type==3){
 				noDirectly.children = cityArray;
 			}    			
 			zNodes.push(noDirectly);
 		}
 	}
 	nationwide.children = zNodes;
 	return nationwide;
 }