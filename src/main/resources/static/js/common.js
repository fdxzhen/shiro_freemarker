(function($){
    $.common={

        commonSubmit:function (formId) {
            var _that = this;
            if(!formId){
                formId="updateForm";
            }
            var _action = jQuery("#"+formId).attr("action");
            jQuery("#"+formId).attr("action",_action).submit();
            layer.load(2);
        },

        commonSaveForm:function (form,listurl,message,_id,callback) {
            var _that = this;
            if(!form){
                form="updateForm";
            }
            var id="#id";
            if(_id){
                id="#"+_id;
            }
            var pageurl=jQuery("#"+form).attr('action');
            var mydata=jQuery('#'+form).serialize();
            _that.ajaxpostonlayer(pageurl,listurl,mydata,message,callback);
            return _form;
        },
        ajaxpostonlayer:function (pageurl,liaturl,mydata,msg,callback) {
            var _that=this;
            var index = layer.load(null,{shade:[0.8,'#393D49']});
            if(pageurl==nulll||pageurl==''){
                layer.alert("提交地址不能为空");
                layer.closeAll('loading');
                return false;
            }
            if(!msg){
                msg="操作成功";
            }

            jQuery.ajax({
                url:pageurl,
                type:"post",
                data:mydata,
                dataType:"json",
                success:function (ret) {
                    layer.closeAll('loading')
                    if (callback&&typeof callback=="function"){
                        callback(ret);
                        return;
                    }
                    if(ret.status=="success"){
                        layer.alert(ret.message,function () {
                            layer.closeAll();
                            if(listUrl!=null&&listUrl!=""){
                                _that.goTo(listUrl);
                            }
                        });
                    }else{
                        _that.error(ret.message);
                        layer.closeAll('loading')
                    }

                },
                error:function () {
                    layer.closeAll('loading')
                    _that.error("sorry,操作失败了。。。")
                }
            });


        },
        goTo:function (_url) {
            var _that = this;
            if(!_url)return;
            location.href=_url;
        }
    }

})(window);