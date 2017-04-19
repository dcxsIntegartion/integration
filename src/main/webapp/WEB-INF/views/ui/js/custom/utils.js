//检查email邮箱
function isEmail(str) {
    emailReg = /^[\w\.\-]+@([\w\-]+\.)+[a-z]{2,4}$/;
    return emailReg.test(str);
}

//验证电话号码
function isPhoneNumber(str) {
    patrn = /^[\d\+\-\s]+$/;
    if (!patrn.exec(str)) {
        return false;
    }
    return true;
}

//验证手机号码
function isCellPhoneNumber(str) {
    patrn = /^[0-9]{11}$/;
    if (!patrn.exec(str)) {
        return false;
    }
    return true;
}

function isNumber(str) {
    numberReg = /^\d+$/;
    return numberReg.test(str);
}

function isFloat(str) {
    var reg = /^\d+\.{0,1}\d+|\d+$/;
    return reg.test(str);
}

//只能输数字 element 元素ID numlen 数字长度
function onlyNum(element, numlen) {
    if (element.length == 0) {
        return;
    }
    var $this = $("#" + element);
    if (!(event.keyCode == 46) && !(event.keyCode == 8) && !(event.keyCode == 37) && !(event.keyCode == 39)) {
        if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105))) {
            event.returnValue = false;
        }
    }
    $this.keyup(function () {
        var real_val = $this.val().replace(/[^0-9]/g, '');
        if (numlen && isNumber) {
            real_val = real_val.substr(0, numlen);
        }
        $this.val(real_val);
    });
}

//比较elment_small elment_big元素 如果small.val >= big.val  制空最后输入的元素editor的val 
function compare_val(element_editor, elment_small, elment_big) {
    if (element_editor.length == 0 || elment_small.length == 0 || elment_big.length == 0) {
        return;
    }
    var $element_editor = $("#" + element_editor),
        $elment_small = $("#" + elment_small),
        $elment_big = $("#" + elment_big);
    if ($elment_small.val().length > 0 && $elment_big.val().length > 0) {
        if (Number($elment_small.val()) > Number($elment_big.val())) {
            $element_editor.css("border", "1px solid #FF0000");
            $element_editor.val("");
        } else {
            $element_editor.css("border", "1px solid #AAAAAA");
        }
    }
}

function go_back() {
    history.go(-1);
}

function go_to(url) {
    location.href = url;
}

function show_message(message) {
    alert(message);
}

function show_modal_message() {
    if (arguments.length == 1) {
        show_modal_message_internal(arguments[0], "系统提示", null);
    } else if (arguments.length == 2) {
        show_modal_message_internal(arguments[0], arguments[1], null);
    } else if (arguments.length == 3) {
        show_modal_message_internal(arguments[0], arguments[1], arguments[2]);
    } else {
        console.log("WTF??!");
    }
}

function show_modal_message_internal(message, title, func) {
    $("#message #content").html(message);
    if (title) {
        $("#message #title").html(title);
    }
    if (func) {
        $("#message #message_close").click(func);
    }
    $("#message").modal();
}

function datetime_formatter(value, style) {
    if (!value) {
        return "";
    }
    if (!style) {
        style = "yyyy-MM-dd HH:mm:ss"
    }
    return $.format.date(parseInt(value), style);
}

function datetime_formatter_str(value, style) {
    if (!value) {
        return "";
    }
    if (!style) {
        style = "yyyy-MM-dd HH:mm:ss"
    }
    return $.format.date(value, style);
}

function get_repair_status() {
    return {
        "1": "已提交",
        "2": "已受理",
        "3": "已派工",
        "4": "已处理",
        "5": "已关闭",
        "6": "已评价",
        "7": "已撤销"
    };
}

function repair_status_formatter(value) {
    return get_repair_status()[value];
}
