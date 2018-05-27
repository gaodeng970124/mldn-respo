window.onload = function() {
    document.getElementById("oldpwd").addEventListener("blur", function() {
        validateOldPwd();
    }, false);
    document.getElementById("newpwd").addEventListener("blur", function() {
        validateNewPwd();
    }, false);
    document.getElementById("conpwd").addEventListener("blur", function() {
        validateConPwd();
    }, false);
    document.getElementById("myForm").addEventListener("submit",
        function(event) {
            if (!(validateOldPwd() & validateNewPwd() &
                validateConPwd() & validateUpdatePassword())) { // 如果有一个方法  返回false 就表示 没有通过验证
                event.preventDefault(); // 阻止表单提交
            }
        }, false);
};

function validateOldPwd() {
    return validateEmpty("oldpwd");
}

function validateNewPwd() {
    return validateEmpty("newpwd");
}

function validateConPwd() {
    return validateEmpty("conpwd");
}

function validateUpdatePassword() {
    return validateRepeat("newpwd", "conpwd");
}

// 进行指定元素内容是否为空的判断
function validateEmpty(eleId) {
    divObject = document.getElementById(eleId + "Div") ;
    obj = document.getElementById(eleId) ;
    if (obj != null) {
        if (obj.value != "") {
            divObject.className = "form-group has-success" ;
            return true ;
        } else {
            divObject.className = "form-group has-error" ;
            return false ;
        }
    }
}

/**
 * 进行两个元素内容的对比
 * @param srcEle 要验证的原始数据
 * @param destEle 匹配的数据
 * @returns 成功返回true
 */
function validateRepeat(srcEle,destEle) {	// 对确认密码进行验证处理
    conObject = document.getElementById(destEle) ; // 确认密码处理
    pwdObject = document.getElementById(srcEle) ;
    conDivObject = document.getElementById(destEle + "Div") ;
    conSpanObject = document.getElementById(destEle + "Msg") ;
    if (conObject.value == "") {	// 没有输入确认密码
        conDivObject.className = "form-group has-error" ;
        conSpanObject.innerHTML = "内容不允许为空！" ;
        return false ;
    } else {
        if (conObject.value == pwdObject.value) {
            conDivObject.className = "form-group has-success" ;
            conSpanObject.innerHTML = "" ;
            return true ;
        } else {
            conDivObject.className = "form-group has-error" ;
            conSpanObject.innerHTML = "两次输入的内容不一致！" ;
            return false ;
        }
    }
}