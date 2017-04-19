/**
 * 获取页面提交数据以及绑定值
 */
var ModelUtils = function(defined) {
	var modelPropertis = function(definded) {
		var propertis ={};
		for ( var i = 0; i < definded.length; i++) {
			var keyDefined = definded[i].split(":");
			var property = {};
			property.key = keyDefined[0];
			if (keyDefined[1]) {
				property.type = keyDefined[1];
			}
			propertis[property.key]=property;
		}
		return propertis;
	};
	var keyPropertis = modelPropertis(defined);
	this.initModel = function() {
		var properties = keyPropertis;
		var model = {};
		for (pro in properties) {
			var property = properties[pro];
			var type = null;
			if(property.type)
			{
				type = property.type;
			}
			else if($("[name='"+keyPropertis[pro].key+"']").length==0)
			{
				type =$("[id='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
				
			}else
			{
				type=$("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type)
			{
			case "easyui-datebox":
				model[property.key] =$("input[name='" + property.key + "']").val();
				break;
			case "combobox":
				model[property.key] =$("input[name='" + property.key + "']").val();
				break;
			case "radio":
				model[property.key] = $("input[name='" + property.key + "']:checked").val();
				break;
			case "checkbox":
//				var value =$("input[name='" + property.key + "']:checked").val();
//				model[property.key] =(value==undefined?"0":value) ;
				var checkbox = document.getElementById( property.key);
				model[property.key] =(checkbox.checked==false?"0":"1");
				break;
			case "textarea":
				var $textarea = $("textarea[name='" + property.key + "']");
				model[property.key]=$textarea.val()==""?$textarea.text():$textarea.val();
				break;
			case "select-one":
				model[property.key] = $("select[name='" + pro + "']").val();
				break;
			default:
				model[property.key] = $("input[name='" + property.key + "']").val();
				break;
			}
		}
		return model;
	};

	this.fillData = function(data) {
		for (pro in keyPropertis) {
			var type = null;
			if(keyPropertis[pro].type)
			{
				type = keyPropertis[pro].type;
			}else
			{
				type = $("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type){
				case "easyui-datebox":
					if(data[pro])
					{
						$("#"+keyPropertis[pro].key).datebox("setValue", new Date(data[pro]).format("yyyy-MM-dd"));
					}
					break;
				case "combobox":
					$("#"+keyPropertis[pro].key).combobox("setValue",data[pro]);
					$("#"+keyPropertis[pro].key).combobox("select",data[pro]);
					break;
				case "numberbox":
					$("#"+keyPropertis[pro].key).numberbox("setValue",data[pro]);
					break;
				case "radio":
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					$("input[name='" + keyPropertis[pro].key + "']").blur();
					$("input[name='" + keyPropertis[pro].key + "']").focus();
					$("input[name='" + keyPropertis[pro].key + "'][value='" + data[pro] + "']").click();
					$("input[name='" + keyPropertis[pro].key + "'][value='" + data[pro] + "']").attr("checked","checked");
					
					break;
				case "textarea":
					$("textarea[name='"+keyPropertis[pro].key+"']").text(data[pro]==null?"":data[pro]);
					break;
				case "checkbox":
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					var _found =$("input[name='" + keyPropertis[pro].key + "'][value='"+data[pro]+"']");
					if(_found.length==1)
						_found.click();
					break;
				case "select-one":
					$("select[name='" + pro + "']").val(data[pro]);
					break;
				default:
					$("input[name='" + pro + "']").val(data[pro]);
					break;
			}

			
		}
	};
	this.clearData = function(){
		for (pro in keyPropertis) {
			var type ="";
			if(keyPropertis[pro].type)
			{
				type = keyPropertis[pro].type;
			}else
			{
				type = $("[name='"+keyPropertis[pro].key+"']")[0].type.toLowerCase();
			}
			switch(type){
				case "radio":
					$("input[name='" + keyPropertis[pro].key + "'][value='']").click();
					break;
				case "textarea":
					$("textarea[name='"+keyPropertis[pro].key+"']").text("");
					break;
				case "checkbox":
					$("input[name='" + keyPropertis[pro].key + "']").removeAttr("checked");
					break;
				case "select-one":
					$("select[name='" + pro + "']").val("");
					break;
				case "numberbox":
					$("#"+keyPropertis[pro].key).numberbox("clear");
					break;
				default:
					$("input[name='" + pro + "']").val("");
					break;
			}
		}
	};
};