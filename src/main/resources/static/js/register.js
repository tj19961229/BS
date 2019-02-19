function check() {
    var user_name=form2.username.value;//获取表单form1的用户名的值
    var user_pwd=form2.password.value;//获取表单form1密码值
	var user_rpwd=form2.rpassword.value;
	var user_email=form2.email.value;
	var str_temp = form2.username.value.substr(0,1);
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if((user_name=="")||(user_name==null)){//判断用户名是否为空，为空就弹出提示框"请输入用户名"，否则正常执行下面的代码。
        alert("请输入用户名！");
        form2.username.focus();//获取焦点，即：鼠标自动定位到用户名输入框，等待用户输入用户名。
        return;
    }
	else if(!((str_temp>="a"&&str_temp<="z")||(str_temp>="A"&&str_temp<="Z"))){
	alert("用户只能以字母开头!");
	form2.username.focus();
	return false;
	} 
	else if((user_pwd=="")||(user_pwd==null)){//判断密码是否为空，为空就弹出提示框"请输入密码"，否则正常执行下面的代码。
        alert("请输入密码！");
        form2.password.focus();//获取焦点。
        return;}
	else if((user_pwd.length<8)){//判断密码是否为空，为空就弹出提示框"请输入密码"，否则正常执行下面的代码。
        alert("密码不能小于8位！");
        form2.password.focus();//获取焦点。
        return;}
    else if(user_pwd!=user_rpwd){//判断密码是否为空，为空就弹出提示框"请输入密码"，否则正常执行下面的代码。
        alert("两次密码不一致！");
        form2.rpassword.focus();//获取焦点。
        return;}
	else if(!myreg.test(user_email)){
		alert('邮箱格式不正确！');
		form2.email.focus();
		return;}
    else {//如果用户名、密码都正常输入，则提交表单，浏览器经打开新的（主页）窗口。
        form2.submit();
        // window.location.href="http://baidu.com/";
        window.open("index");
    }
}