var publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDE6iE68s8YrsJ435rkHgmmJQhl1/yHY7zUzvyEDhKJC+d1wTO8d3+Ibw88X6egVI+05Rq79ANhOv5uVt9fValspSQRnzKLGd7JkW0kPMHdaU6Sae1ft3xVUl8Qw3VDzHh2UKKmxu8LbU4k10V5Rs9K6zD3W+wELOZKrqj9SbZcLQIDAQAB";
var privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI38rv/IVO2hLt7jSsXvq00zHg2+aIObtjjM8vKqJgNc4yzbcOlPELzbhEQjjR1/ZasHfP8C9bDcXI70tTic7ny8C9p/MZvemoSZOoFfX5ptIhPOf5udWzbtqWyxeW37zebLlgV712wZ+dZpDfT05Y0K7M2s0QugCLRctW7MqRZZAgMBAAECgYAoeQOFI5yZ090haZaxzzx8F/sUHdd61FLf5APIihml0b9r1O1dg80YmFYeeifKZbscQlkt87EHBflYQoa5qXoNIyo3CUeaHAP6pYr/nPaxu7PANI0ZnrpjW1t1Xe02GWd6p3joFbcWUhCGcfMfEa2hXHwFHOVZIA2bHqaxZ4pZxQJBAOopiARiLSleqc2TkBnW8SbLaYlXaRRF1/eAqdsv/zphRq9fEg0c9eY/7FQuLR+ua5IhW57qZmEmQKM2mMkTsiMCQQCbOofSWtlloJ+BgYPuB/Wq9Wko0D6K1mMlCtX51xaMubVTBaMbM5o9qd++DZQMkahCr+EKz7D/ex+Q9liDASdTAkA1EcaO38VGe/rV6ZyeDpXG6hD4HIRnINEqedGFKKKak5NWiaBosmiUj2Y7Sd/WL0yX6NF/+bXMTMQXeXc1Ey6rAkBdHYo2HDtGpEiqdhe+5NVwfRBc5DZwMFR+9vYOjgC/3/KuX7ZM7fJ7RPirWBfURlfJ0RlM0/OX/bKc0bmctEdzAkEAmAP5NAy7RdcB80yx+Jy/fVNd9HWTv3orl1skoh1XgtxYyKmdQPZ4L/Kwl0WUbJUW0kIRN4SqR/+Ht/J5+toiSQ==";


//获取随机数
function RandomNumBoth(Min,Max){
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.round(Rand * Range); //四舍五入
    return num;
	}
function genRandomPass(pwd_len){
	var maxNum = 36;
	var i; // 生成的随机数
	var count = 0; // 生成的密码的长度
	var str = [ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ];
	var pwd = "";
	while (count < pwd_len) {
		// 生成随机数，取绝对值，防止生成负数，
		i = RandomNumBoth(1,maxNum); // 生成的数最大为36-1
		if (i >= 0 && i < str.length) {
			pwd+=str[i];
			count++;
		}
	}
	return pwd;
}
function getFirstNum(text){
	var value = text.replace(/[^0-9]/ig,"")+"0";
	return value.substr(0,1);
}
//转码Unicode
function toUnicode(s){
	return s.replace(/([\u4E00-\u9FA5]|[\uFE30-\uFFA0])/g,function(work){
	return "\\u" + work.charCodeAt(0).toString(16);
	});
} 
//压缩
function zipdata(str){  
    var binaryString = pako.gzip(str, { to: 'string' });  
    return btoa(binaryString);  
}  
function unzip(b64Data){  
    var strData     = atob(b64Data);  
    // Convert binary string to character-number array  
    var charData    = strData.split('').map(function(x){return x.charCodeAt(0);});  
    // Turn number array into byte-array  
    var binData     = new Uint8Array(charData);  
    // // unzip  
    var data        = pako.inflate(binData);  
    // Convert gunzipped byteArray back to ascii string:  
    strData     = String.fromCharCode.apply(null, new Uint16Array(data));  
    return strData;  
}  
//获取浏览器信息
var browser={
	versions:function(){
    var u = navigator.userAgent, app = navigator.appVersion;
    return {//移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
} 

//rsa
function doEncryptByPublicKey(publicKey,data){
	var key = pidCryptUtil.decodeBase64(publicKey);
	var rsa = new pidCrypt.RSA();
	var asn = pidCrypt.ASN1.decode(pidCryptUtil.toByteArray(key));
	var tree = asn.toHexTree();
	rsa.setPublicKeyFromASN(tree);
	return encryptBase64(data, rsa);
}
function doSign(privateKey,data,hashAlg) {
	var rsa = new RSAKey();
	    rsa =KEYUTIL.getKey("-----BEGIN PRIVATE KEY-----"+privateKey+"-----END PRIVATE KEY-----");
	var hSig = rsa.signString(data,hashAlg);
	return hex2b64(hSig);
}
function doVerify(sMsg,hSig) {
	//var x509 = new X509();
	//x509.readCertPEM(document.form1.cert.value);
	var pubKey = KEYUTIL.getKey(document.form1.cert.value);
	//var isValid = x509.subjectPublicKeyRSA.verifyString(sMsg, hSig);
	var isValid = pubKey.verifyString(sMsg, hSig);
	// display verification result
	if (isValid) {
	 return true;
	} else {
	 return false;
	}
}

function encrypt(data,publicKey,privateKey,hashAlg){
	var times = new Date().getTime();
	var unicode = toUnicode(data);
	var DataZip = zipdata(unicode);
	var encryptData = "";
	var subStrLength = 110;
	for(var i=0;i<Math.ceil(DataZip.length/subStrLength);i++){
		if(encryptData){
			encryptData += ",110,"+doEncryptByPublicKey(publicKey,DataZip.substring(i*subStrLength,subStrLength*(i+1)));
		}else{
			encryptData += doEncryptByPublicKey(publicKey,DataZip.substring(i*subStrLength,subStrLength*(i+1)));
		}
	}
	var randomStr = genRandomPass(32);
	var firstnNum  = getFirstNum(randomStr);
	var signData = encryptData+times+randomStr;
	var sign = doSign(privateKey,signData.substr(firstnNum,signData.length),hashAlg);
	var result = {};
	    result.sign = sign;
	    result.data = encryptData;
	    result.str = randomStr;
	    result.times = times;
	return result;
}


