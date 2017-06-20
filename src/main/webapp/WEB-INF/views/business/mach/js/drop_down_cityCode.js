/** 下拉框选中的城市全名 **/
var selectedcityfullname = "";
/** 下拉框选中的城市编码 **/
var selectedcityCode = "";

$(function() {
	var selectedCode = $("#areaCode").val();
	if(selectedCode.length==6){
		var provinces = selectedCode.substring(0, 2)+"0000";
		var city = selectedCode.substring(0, 4)+"00";
		getProvinces(provinces);
		if(isDirectly(provinces)){
			getCitys(provinces,selectedCode);
		}else{
			getCitys(provinces,city);
			getCountry(city,selectedCode);
		};
	}else{
		getProvinces();
	}
	
 });
/*
 * 默认加载省份列表
 */
function getProvinces(selectedCode) {
	$.ajax({
		type : "POST",
		url : basePath + "/nonbis/cityCode/selectProvince",
		data : {
			levelNumber : 1
		},
		// contentType:"application/json",
		dataType : "json",
		success : function(data) {
			setOptions($("#provienceCode"),data,selectedCode);
			$("#provienceCode").change(function() {
				var provinceCode = $(this).val();
				if (provinceCode != "" && provinceCode != undefined) {
					getCitys(provinceCode);
					$("#areaCode").val(provinceCode.substr(0, 2));
				} else {
					$("#areaCode").val("");
				}
				$("#cityCode").val("");
				$("#countryCode").val("");
			});
		},
		error : function(data) {
		},
		complete : function() {

		}
	});
}

/*
 * 根据省份ID查询城市
 */
function getCitys(provinceCode,selectedCode) {
	var model = {};
		model.cityParentCode = provinceCode;
		model.levelNumber = 2;
	var data= encrypt(JSON.stringify(model),publicKey,privateKey,"md5");
	$.ajax({
		type : "POST",
		url : basePath + "/nonbis/cityCode/selectByCodeCityNodes",
		 headers:{'sign': data.sign,'str':data.str,'times':data.times},
         data: data.data,
		// contentType:"application/json",
		dataType : "json",
		success : function(data) {
			setOptions($("#cityCode"),data,selectedCode);
			$("#cityCode").change(function() {
				var cityCode = $(this).val();
				if (cityCode != "" && cityCode != undefined) {
					$("#areaCode").val(cityCode);
					getCountry(cityCode);
				} else {
					$("#areaCode").val(provinceCode);
				}
				$("#countryCode").val("");
				if(isDirectly(provinceCode)){
					selectedcityfullname = $('#cityCode').find('option:selected').attr('cityfullname');
					if(selectedcityfullname.length>1)selectedcityfullname = selectedcityfullname.substring(1, selectedcityfullname.length-1);
					selectedcityCode = $('#cityCode').val();
				}
			});
		},
		error : function(data) {
		},
		complete : function() {

		}
	});

}

/*
 * 根据城市CODE获取县/区
 */
function getCountry(cityCode,selectedCode) {
	$.ajax({
		type : "POST",
		url : basePath + "/nonbis/cityCode/selectByCodeCityNodes",
		data : {
			cityParentCode : cityCode,
			levelNumber : 3
		},
		// contentType:"application/json",
		dataType : "json",
		success : function(data) {
			setOptions($("#countryCode"),data,selectedCode);
			$("#countryCode").change(function() {
				var countryCode = $(this).val();
				if (countryCode != "" && countryCode != undefined) {
					$("#countryCode").val(countryCode);
					$("#areaCode").val(countryCode);
				} else {
					$("#areaCode").val(cityCode);
				}
				selectedcityfullname = $('#countryCode').find('option:selected').attr('cityfullname');
				if(selectedcityfullname.length>1)selectedcityfullname = selectedcityfullname.substring(1, selectedcityfullname.length-1);
				selectedcityCode = $('#countryCode').val();
			});
		},
		error : function(data) {
		},
		complete : function() {

		}
	});
}

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
function setOptions(htmlVo,data,selectedCode){
	var html = '<option value="">地区</option>';
	for ( var i in data) {
		if(selectedCode==data[i].cityCode){
			html += '<option selected="selected" cityFullName="' + data[i].cityFullName
			+'" value="' + data[i].cityCode + '">'
			+ data[i].cityName + '</option>';
		}else{
			html += '<option  cityFullName="' + data[i].cityFullName
			+'" value="' + data[i].cityCode + '">'
			+ data[i].cityName + '</option>';
		}
	}
	$(htmlVo).empty().append(html);
}